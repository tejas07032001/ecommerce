package com.ecommerce.service.impl;

import com.ecommerce.dtos.CategoryDto;
import com.ecommerce.dtos.PageableResponse;
import com.ecommerce.entities.Category;
import com.ecommerce.helper.Helper;
import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper mapper;



    @Override
    public CategoryDto create(CategoryDto categoryDto) {

        String categoryId = UUID.randomUUID().toString();
        categoryDto.setCategoryID(categoryId);

        Category category = mapper.map(categoryDto, Category.class);
        Category savedCartegory = categoryRepository.save(category);
        CategoryDto categoryDto1 = mapper.map(savedCartegory, CategoryDto.class);

        return categoryDto1;
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto, String categoryId) {

        //get category of given Id
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Category not found exception"));

        //update category details
        category.setTitle(categoryDto.getTitle());
        category.setDiscription(categoryDto.getDiscription());
        category.setCoverImage(categoryDto.getCoverImage());

        Category save = categoryRepository.save(category);

        CategoryDto map = mapper.map(save, CategoryDto.class);

        return map;
    }

    @Override
    public void delete(String categoryId) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Category not found exception"));
        categoryRepository.delete(category);


    }

    @Override
    public PageableResponse<CategoryDto> getAll(int pageNumber , int pageSize, String sortBy, String sortDir) {

        Sort sort=(sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()):(Sort.by(sortBy).ascending());


        Pageable pageable= PageRequest.of(pageNumber,pageSize,sort);

        Page<Category> page = categoryRepository.findAll(pageable);

        PageableResponse<CategoryDto> paegebleResponse = Helper.getPaegebleResponse(page, CategoryDto.class);

        return paegebleResponse;
    }

    @Override
    public CategoryDto get(String categoryId) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Category not found exception"));

        CategoryDto map = mapper.map(category, CategoryDto.class);

        return map;
    }
}
