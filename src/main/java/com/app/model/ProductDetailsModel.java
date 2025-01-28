package com.app.model;

import com.app.entity.ProductDetails;

import lombok.*;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.persistence.Lob;
import javax.validation.constraints.*;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDetailsModel {
    int productDetailsModelId;

    @NotBlank(message = "required!!")
    @Size(min = 4 ,message = "the name must be at lest 4 characters")
    String manufacturerModel;

    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    double price;

    boolean available;

    @NotNull(message = "expiry date required!!")
    @Future(message = "The date must be in the future")
    @DateTimeFormat(pattern = "yyyy-MM-dd") // Corrected to use "MM" for months
    private Date expiryDate;

    @Lob
    private byte[] image;

    private MultipartFile imageFile;

    public ProductDetailsModel(ProductDetails details) {
        this.productDetailsModelId = details.getProductId();
        this.manufacturerModel = details.getManufacturer();
        this.price = details.getPrice();
        this.available = details.isAvailable();
        this.expiryDate = details.getExpiryDate();
        this.image = details.getImage();
    }

    public void deleteImage() {
        if (image != null) {
            image=null;
        }
    }
}
