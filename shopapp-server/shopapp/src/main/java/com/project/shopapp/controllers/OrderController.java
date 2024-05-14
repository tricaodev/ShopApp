package com.project.shopapp.controllers;

import com.project.shopapp.dtos.OrderDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/orders")
public class OrderController {
    @PostMapping("")
    public ResponseEntity<?> createOrder(@Valid @RequestBody OrderDto order, BindingResult result) {
        try {
            if(result.hasErrors()) {
                List<String> errorMessage = result.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
                return ResponseEntity.badRequest().body(errorMessage);
            }
            return ResponseEntity.ok("createOrder successfully");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/{user_id}")
    public ResponseEntity<String> getOrders(@Valid @PathVariable("user_id") Long userId) {
        try {
            return ResponseEntity.ok("Lấy ra danh sách order từ user_id");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateOrder(@Valid @PathVariable Long id, @Valid @RequestBody OrderDto order) {
        return ResponseEntity.ok("Cập nhật thông tin 1 order");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@Valid @PathVariable Long id) {
        return ResponseEntity.ok("Order deleted successfully.");
    }
}
