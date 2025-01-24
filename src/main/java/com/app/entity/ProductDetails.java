package com.app.entity;

import com.app.model.ProductDetailsModel;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Product_details")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "price")
    private double price;

    @Column(name = "available")
    private boolean available;

    @Temporal(TemporalType.DATE)
    @Column(name = "expiry_date")
    private Date expiryDate;

}
