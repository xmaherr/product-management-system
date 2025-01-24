package com.app.controller;


import com.app.model.ProductModel;
import com.app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "home";
    }

    @GetMapping("/view-details")
    public String viewDetails(@RequestParam int id, Model model) {
        ProductModel productWithDetails = productService.getProductDetailsById(id);
        model.addAttribute("product", productWithDetails);
        return "view-details";
    }

    @GetMapping("/update-product")
    public String updateProduct(@RequestParam int id, Model model) {
        ProductModel productWithDetails = productService.getProductDetailsById(id);
        model.addAttribute("product", productWithDetails);
        return "update-product";
    }

    @PostMapping("/update-product")
    public String doUpdate(@RequestParam int id,
                           @Valid @ModelAttribute("product") ProductModel productModel,BindingResult result) {
        if (result.hasErrors()) {
            return "update-product";
        }

      productService.updateProduct(id, productModel, productModel.getDetails());
        return "redirect:/";
    }

    @GetMapping("/add-product")
    public String addProductPage( Model model) {
        model.addAttribute("product", new ProductModel());
        return "add-product";
    }

    @PostMapping("/add-product")
    public String addProduct(@Valid @ModelAttribute("product") ProductModel productModel, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-product";
        }
        productService.saveProduct(productModel);
        return "redirect:/";
    }


    @PostMapping("/delete-product")
    public String deleteProduct(@RequestParam int id) {
        productService.deleteProduct(id);
        return "redirect:/";
    }

}
