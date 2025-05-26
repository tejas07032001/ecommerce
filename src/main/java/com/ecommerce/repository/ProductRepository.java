package com.ecommerce.repository;

import com.ecommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,String> {

    //Search
    List<Product>  findByTitleContaining(String subTitle);




}
