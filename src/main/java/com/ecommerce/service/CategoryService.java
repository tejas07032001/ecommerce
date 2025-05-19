package com.ecommerce.service;

import com.ecommerce.dtos.CategoryDto;
import com.ecommerce.entities.Category;

import java.util.List;

public interface CategoryService {

    //create
    CategoryDto create(CategoryDto categoryDto);


    //update
    CategoryDto update(CategoryDto categoryDto, String categoryId);

    //delete
    void delete(String categoryId);

    //get all
    List<CategoryDto> getAll();

    //get single
    CategoryDto get(String categoryId);

    //search


}
