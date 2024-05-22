package com.project.shopapp.services;

import com.project.shopapp.dtos.CategoryDto;
import com.project.shopapp.models.Category;
import com.project.shopapp.repositories.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {
    private final ICategoryRepository categoryRepository;
    @Override
    public Category createCategory(CategoryDto categoryDto) {
        Category category = Category.builder().name(categoryDto.getName()).build();
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category updateCategory(long id, CategoryDto categoryDto) {
        Category existsCategory = getCategoryById(id);
        existsCategory.setName(categoryDto.getName());
        return categoryRepository.save(existsCategory);
    }

    @Override
    public void deleteCategory(long id) {
        categoryRepository.deleteById(id);
    }
}
