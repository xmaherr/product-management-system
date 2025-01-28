package com.app.model;

import com.app.entity.ProductEntity;

import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.*;
//import org.hibernate.annotations.Cache;Cache

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductModel {
    int id;

//    @NotBlank(message = "required!!")
//    @Size(min = 4 ,message = "the name must be at lest 4 characters")
    String name;

    @Valid
    ProductDetailsModel details;

    public ProductModel(ProductEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }
}
