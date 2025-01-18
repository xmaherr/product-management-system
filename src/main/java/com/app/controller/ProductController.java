package com.app.controller;

import com.app.entity.ProductEntity;
import com.app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/maher")
    public String homePage( Model model) {
        List<ProductEntity> productEntities = productService.getAllProducts();

        model.addAttribute("products", productEntities);
        return "home";
    }

}
