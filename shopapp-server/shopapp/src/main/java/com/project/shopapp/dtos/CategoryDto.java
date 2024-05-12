package com.project.shopapp.dtos;


import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CategoryDto {
    @NotEmpty(message = "Name of category cannot be empty")
    private String name;
}