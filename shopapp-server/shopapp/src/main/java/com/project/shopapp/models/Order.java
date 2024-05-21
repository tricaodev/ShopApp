package com.project.shopapp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@Table(name = "orders")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "fullname", length = 100)
    private String fullName;

    @Column(length = 100)
    private String email;

    @Column(name = "phone_number", nullable = false, length = 20)
    private String phoneNumber;

    @Column(nullable = false, length = 200)
    private String address;

    @Column(length = 100)
    private String note;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(nullable = false)
    private String status;

    @Column(name = "total_money")
    private Float totalMoney;

    @Column(name = "shipping_method", nullable = false, length = 100)
    private String shippingMethod;

    @Column(name = "shipping_address", nullable = false, length = 200)
    private String shippingAddress;

    @Column(name = "shipping_date", nullable = false)
    private Date shippingDate;

    @Column(name = "tracking_number", nullable = false, length = 100)
    private String trackingNumber;

    @Column(name = "payment_method", nullable = false, length = 100)
    private String paymentMethod;

    private Boolean active;
}
