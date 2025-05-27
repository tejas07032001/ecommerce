package com.ecommerce.controller;

import com.ecommerce.dtos.ApiResponseMessage;
import com.ecommerce.dtos.PageableResponse;
import com.ecommerce.dtos.ProductDto;
import com.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    //create
    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto){

        ProductDto createdProduct = productService.create(productDto);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    //update
    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable String productId,@RequestBody ProductDto productDto){

        ProductDto updateProduct = productService.update(productDto, productId);
        return new ResponseEntity<>(updateProduct,HttpStatus.OK);
    }

    //Delete
    @DeleteMapping("{productId}")
    public ResponseEntity<ApiResponseMessage> deleteProduct(@PathVariable String productId){

        productService.delete(productId);

        ApiResponseMessage delete_product = ApiResponseMessage.builder().msg("Delete product").success(true).status(HttpStatus.OK).build();

        return new ResponseEntity<>(delete_product,HttpStatus.OK);
    }

    //getSingle
    @GetMapping("{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable String productId){
        ProductDto getSingle = productService.get(productId);
        return new ResponseEntity<>(getSingle,HttpStatus.OK);

    }

    ///get all

    @GetMapping
    public ResponseEntity<PageableResponse<ProductDto>> getAllProduct(
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = "title",required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false)String sortDir

    ){
        PageableResponse<ProductDto> allProduct = productService.getAll(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(allProduct,HttpStatus.OK);
    }

    //Get ALl Live
//To get live /products/live
    @GetMapping("/live")
    public ResponseEntity<PageableResponse<ProductDto>> getAllLive(
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = "title",required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false)String sortDir

    ){
        PageableResponse<ProductDto> allProduct = productService.getAllLive(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(allProduct,HttpStatus.OK);
    }

    //search

    @GetMapping("/search/{query}")
    public ResponseEntity<PageableResponse<ProductDto>> searchProduct(
            @PathVariable String query,
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = "title",required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false)String sortDir

    ){
        PageableResponse<ProductDto> allProduct = productService.searchByTitle(query,pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(allProduct,HttpStatus.OK);
    }


}
