package com.ecommerce.dtos;

import com.ecommerce.entities.Category;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class ProductDto {


    private String productId;

    private String title;


    private String discription;

    private int price;

    private int discountedPrice;

    private int quantity;

    private Date productDate;

    private boolean live;

    private boolean stock;

    private String productImageName;

    private Category category;
}
