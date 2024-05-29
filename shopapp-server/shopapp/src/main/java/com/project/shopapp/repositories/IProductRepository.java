package com.project.shopapp.repositories;

import com.project.shopapp.models.Product;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, Long> {
    boolean existsByName(String name);

    @NotNull
    Page<Product> findAll(@NotNull Pageable pageable);
}
