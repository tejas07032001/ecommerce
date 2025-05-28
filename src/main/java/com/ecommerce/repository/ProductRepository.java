package com.ecommerce.repository;

import com.ecommerce.entities.Category;
import com.ecommerce.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,String> {

    //Search
    Page<Product>  findByTitleContaining(String subTitle,Pageable pageable);

  Page<Product> findByLiveTrue(Pageable pageable);

  Page<Product> findByCategory(Category category,Pageable pageable);

    //Others methods

    //custome finders methods

    //query methods



}
