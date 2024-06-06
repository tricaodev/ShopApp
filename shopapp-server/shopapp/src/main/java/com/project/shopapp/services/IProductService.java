package com.project.shopapp.services;

import com.project.shopapp.dtos.ProductDto;
import com.project.shopapp.dtos.ProductImageDto;
import com.project.shopapp.models.Product;
import com.project.shopapp.models.ProductImage;
import com.project.shopapp.responses.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductService {
    Product createProduct(ProductDto productDto) throws Exception;

    Product getProductById(long id) throws Exception;

    Page<ProductResponse> getAllProducts(Pageable pageable);

    Product updateProduct(long id, ProductDto productDto) throws Exception;

    void deleteProduct(long id);

    boolean existsByName(String name);

    ProductImage createProductImage(Long productId, ProductImageDto productImageDto) throws Exception;
}
