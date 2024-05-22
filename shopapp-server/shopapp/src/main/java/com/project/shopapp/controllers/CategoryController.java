package com.project.shopapp.controllers;

import com.project.shopapp.dtos.CategoryDto;
import com.project.shopapp.models.Category;
import com.project.shopapp.services.ICategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("${api.prefix}/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final ICategoryService categoryService;

    @PostMapping("")
    public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryDto categoryDto, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errorMessage = result.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
            return ResponseEntity.badRequest().body(errorMessage);
        }
        categoryService.createCategory(categoryDto);
        return ResponseEntity.ok("Insert category successfully!");
    }

    @GetMapping("")
    public ResponseEntity<List<Category>> getAllCategories(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
        categoryService.updateCategory(id, categoryDto);
        return ResponseEntity.ok("Update category successfully!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Delete category with id: " + id + " successfully!");
    }
}
