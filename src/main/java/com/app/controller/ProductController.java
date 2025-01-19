package com.app.controller;

import com.app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/")
    public String homePage( Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "home";
    }

    @GetMapping(value = "/view-details")
    public String viewDetails(@RequestParam int id, Model model) {
        model.addAttribute("productDetails", this.productService.getProductDetailsById(id));

        return "view-details";
    }


}
