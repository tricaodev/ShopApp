package com.project.shopapp.services;

import com.project.shopapp.dtos.ProductDto;
import com.project.shopapp.dtos.ProductImageDto;
import com.project.shopapp.exceptions.DataNotFoundException;
import com.project.shopapp.exceptions.InvalidParamException;
import com.project.shopapp.models.Category;
import com.project.shopapp.models.Product;
import com.project.shopapp.models.ProductImage;
import com.project.shopapp.repositories.ICategoryRepository;
import com.project.shopapp.repositories.IProductImageRepository;
import com.project.shopapp.repositories.IProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductService implements IProductService{
    private final ICategoryRepository categoryRepository;
    private final IProductRepository productRepository;
    private final IProductImageRepository productImageRepository;
    @Override
    public Product createProduct(ProductDto productDto) throws Exception {
        Category existsCategory = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new DataNotFoundException("Cannot find category with id: " + productDto.getCategoryId()));
        Product newProduct = Product.builder()
                .name(productDto.getName())
                .price(productDto.getPrice())
                .thumbnail(productDto.getThumbnail())
                .description(productDto.getDescription())
                .category(existsCategory)
                .build();
        return productRepository.save(newProduct);
    }

    @Override
    public Product getProductById(long id) throws Exception {
        return productRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Cannot find product with id = " + id));
    }

    @Override
    public Page<Product> getAllProducts(PageRequest pageRequest) {
        return productRepository.findAll(pageRequest);
    }

    @Override
    public Product updateProduct(long id, ProductDto productDto) throws Exception {
        Category existsCategory = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new DataNotFoundException("Cannot find category with id: " + productDto.getCategoryId()));
        Product existsProduct = getProductById(id);
        existsProduct.setName(productDto.getName());
        existsProduct.setPrice(productDto.getPrice());
        existsProduct.setThumbnail(productDto.getThumbnail());
        existsProduct.setDescription(productDto.getDescription());
        existsProduct.setCategory(existsCategory);
        return productRepository.save(existsProduct);
    }

    @Override
    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return productRepository.existsByName(name);
    }

    @Override
    public ProductImage createProductImage(long productId, ProductImageDto productImageDto) throws Exception {
        Product existsProduct = productRepository.findById(productId)
                .orElseThrow(() -> new DataNotFoundException("Cannot find product with id: " + productId));
        ProductImage newProductImage = ProductImage.builder()
                .product(existsProduct)
                .imageUrl(productImageDto.getImageUrl())
                .build();
        int size = productImageRepository.findByProductId(productId).size();
        if (size >= 5) throw new InvalidParamException("Number of images must be <= 5");
        return productImageRepository.save(newProductImage);
    }
}
