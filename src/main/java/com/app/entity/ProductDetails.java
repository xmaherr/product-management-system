package com.app.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Product_details")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int productId;

    @Transient
    String productName;
    @Column(name = "manufacturer")
    String manufacturer;

    @Column(name = "price")
    double price;

    @Column(name = "available")
    boolean available;

    @Column(name = "expiry_date")
    @Temporal(TemporalType.DATE)  // Stores only the date (e.g., 2025-01-01)
    private Date expiryDate;

    @OneToOne(mappedBy = "details")
    ProductEntity productEntity;

}
