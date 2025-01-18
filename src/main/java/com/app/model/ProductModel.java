package com.app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
//import org.hibernate.annotations.Cache;Cache

@Getter
@Setter
public class ProductModel {
    int id;


    String name;


    double price;

    String description;
}
