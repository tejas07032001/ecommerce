package com.ecommerce.service;

import com.ecommerce.dtos.PageableResponse;
import com.ecommerce.dtos.ProductDto;

import java.util.List;

public interface ProductService {

    //create
    ProductDto create(ProductDto productDto);

    //update
    ProductDto update(ProductDto productDto,String productId);

    //delete
    void delete(String productId);

    //get single
    ProductDto get(String productId);

    //getALL
    PageableResponse<ProductDto> getAll(int  pageNumber,int pageSize,String sortBy, String sortDir);

    //getALL : LIVE
    PageableResponse<ProductDto> getAllLive(int  pageNumber,int pageSize,String sortBy, String sortDir);

    //search
    PageableResponse<ProductDto> searchByTitle(String subTitle,int  pageNumber,int pageSize,String sortBy, String sortDir);



    //Create Product with Category

    ProductDto creatWithCategory(ProductDto productDto,String categoryId);


    //Update Products with category
    ProductDto updateCategory(String productId,String categoryId);


    //get all product by category

    PageableResponse<ProductDto> getAllByCategory(String categoryId,int pageNumber,int pageSize,String sortBy,String sortDir);

}
