package com.ecommerce.service;

import com.ecommerce.dtos.CategoryDto;
import com.ecommerce.dtos.PageableResponse;
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
    PageableResponse<CategoryDto> getAll(int pageNumber , int pageSize, String sortBy, String sortDir);

    //get single
    CategoryDto get(String categoryId);

    //search


}
