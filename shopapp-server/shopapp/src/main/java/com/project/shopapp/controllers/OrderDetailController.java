package com.project.shopapp.controllers;

import com.project.shopapp.dtos.OrderDetailDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/order_details")
public class OrderDetailController {
    @PostMapping("")
    public ResponseEntity<?> createOrderDetail(@Valid @RequestBody OrderDetailDto orderDetail, BindingResult result) {
        if(result.hasErrors()) {
            List<String> errorMessage = result.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
            return ResponseEntity.badRequest().body(errorMessage);
        }
        return ResponseEntity.ok("createOrderDetail here");
    }
    @GetMapping("/{id}")
    public ResponseEntity<String> getOrderDetail(@Valid @PathVariable Long id) {
        return ResponseEntity.ok("getOrderDetail with id " + id);
    }
    @GetMapping("/order/{order_id}")
    public ResponseEntity<String> getOrderDetails(@Valid @PathVariable("order_id") Long orderId) {
        return ResponseEntity.ok("getOrderDetails with orderId = " + orderId);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateOrderDetail(@Valid @PathVariable Long id, @Valid @RequestBody OrderDetailDto orderDetail) {
        return ResponseEntity.ok("updateOrderDetail with id = " + id + ", newOrderDetailData: " + orderDetail);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderDetail(@Valid @PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }
}