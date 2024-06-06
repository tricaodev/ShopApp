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
import com.project.shopapp.responses.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductService implements IProductService{
    private final ICategoryRepository categoryRepository;
    private final IProductRepository productRepository;
    private final IProductImageRepository productImageRepository;
    @Override
    public Product createProduct(ProductDto productDto) throws Exception {
        Category existingCategory = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new DataNotFoundException("Cannot find category with id: " + productDto.getCategoryId()));
        Product newProduct = Product.builder()
                .name(productDto.getName())
                .price(productDto.getPrice())
                .thumbnail(productDto.getThumbnail())
                .description(productDto.getDescription())
                .category(existingCategory)
                .build();
        return productRepository.save(newProduct);
    }

    @Override
    public Product getProductById(long id) throws Exception {
        return productRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Cannot find product with id = " + id));
    }

    @Override
    public Page<ProductResponse> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable).map(product -> {
            ProductResponse productResponse = ProductResponse.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .price(product.getPrice())
                    .thumbnail(product.getThumbnail())
                    .description(product.getDescription())
                    .categoryId(product.getCategory().getId())
                    .build();
            productResponse.setCreatedAt(product.getCreatedAt());
            productResponse.setUpdatedAt(product.getUpdatedAt());
            return productResponse;
        });
    }

    @Override
    public Product updateProduct(long id, ProductDto productDto) throws Exception {
        Category existingCategory = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new DataNotFoundException("Cannot find category with id: " + productDto.getCategoryId()));
        Product existingProduct = getProductById(id);
        existingProduct.setName(productDto.getName());
        existingProduct.setPrice(productDto.getPrice());
        existingProduct.setThumbnail(productDto.getThumbnail());
        existingProduct.setDescription(productDto.getDescription());
        existingProduct.setCategory(existingCategory);
        return productRepository.save(existingProduct);
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
    public ProductImage createProductImage(Long productId, ProductImageDto productImageDto) throws Exception {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new DataNotFoundException("Cannot find product with id: " + productId));
        ProductImage newProductImage = ProductImage.builder()
                .product(existingProduct)
                .imageUrl(productImageDto.getImageUrl())
                .build();
        int size = productImageRepository.findByProductId(productId).size();
        if (size >= ProductImage.MAXIMUM_IMAGES_PER_PRODUCT) throw new InvalidParamException("Number of images must be <= 5");
        return productImageRepository.save(newProductImage);
    }
}
