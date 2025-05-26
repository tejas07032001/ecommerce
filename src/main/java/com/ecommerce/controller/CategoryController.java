package com.ecommerce.controller;

import com.ecommerce.dtos.ApiResponseMessage;
import com.ecommerce.dtos.CategoryDto;
import com.ecommerce.dtos.PageableResponse;
import com.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    //create
    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){

        CategoryDto categoryDto1 = categoryService.create(categoryDto);

        return new ResponseEntity<>(categoryDto1, HttpStatus.CREATED);


    }


    ///update
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(
                                @PathVariable String CategoryId,
                                @RequestBody CategoryDto categoryDto ,String categoryId){

        CategoryDto update = categoryService.update(categoryDto, categoryId);
        return new ResponseEntity<>(update,HttpStatus.OK);
    }

    //delete
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponseMessage> deleteCategory(@PathVariable String categoryId){

        categoryService.delete(categoryId);

        ApiResponseMessage responseMessage = ApiResponseMessage.builder().msg("deleted category").status(HttpStatus.OK).success(true).build();

        return new ResponseEntity<>(responseMessage,HttpStatus.OK);

    }

    //get ALl
    @GetMapping
    public ResponseEntity<PageableResponse<CategoryDto>> getAll(
                            @RequestParam(value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
                            @RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize,
                            @RequestParam(value = "sortBy",defaultValue = "title",required = false) String sortBy,
                            @RequestParam(value = "sortDir",defaultValue = "asc",required = false)String sortDir

            ){

        PageableResponse<CategoryDto> all = categoryService.getAll(pageNumber, pageSize, sortBy, sortDir);

        return new ResponseEntity<>(all,HttpStatus.OK);

    }

    //get Single

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> get(@PathVariable String categoryId){
        CategoryDto categoryDto = categoryService.get(categoryId);

        return new ResponseEntity<>(categoryDto,HttpStatus.OK);

    }

}
