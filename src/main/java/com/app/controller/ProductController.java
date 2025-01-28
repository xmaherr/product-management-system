package com.app.controller;


import com.app.model.ProductModel;
import com.app.service.ProductService;
import com.app.util.UtilFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

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
    public String viewDetails(@RequestParam int id, Model model) throws UnsupportedEncodingException {
        ProductModel productWithDetails = productService.getProductDetailsById(id);

        model.addAttribute("product", productWithDetails);
        if(productWithDetails.getDetails().getImage() != null) {
            model.addAttribute("image", UtilFile.encodeBase64(productWithDetails.getDetails().getImage()));
        }
        return "view-details";
    }

    @GetMapping("/update-product")
    public String updateProduct(@RequestParam int id, Model model) throws UnsupportedEncodingException {
        ProductModel productWithDetails = productService.getProductDetailsById(id);
        model.addAttribute("product", productWithDetails);
        if(productWithDetails.getDetails().getImage() != null) {
            model.addAttribute("image", UtilFile.encodeBase64(productWithDetails.getDetails().getImage()));
        }
        return "update-product";
    }

    @PostMapping("/update-product")
    public String doUpdate(
            @Valid @ModelAttribute("product") ProductModel productModel, BindingResult result, Model model) throws IOException {

        if (result.hasErrors()) {
            return "update-product";
        }

      productService.updateProduct( productModel, productModel.getDetails());
        model.addAttribute("id", productModel.getId());
        return "redirect:/update-product";
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

    @PostMapping("/delete-image")
    public String deleteImage(@RequestParam int id, @RequestParam int productId, Model model) {

        this.productService.deleteProductImage(id);
        model.addAttribute("id",productId);
        return "redirect:/update-product";
    }

}
