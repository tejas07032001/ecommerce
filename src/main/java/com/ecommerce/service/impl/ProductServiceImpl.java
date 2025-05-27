package com.ecommerce.service.impl;

import com.ecommerce.dtos.PageableResponse;
import com.ecommerce.dtos.ProductDto;
import com.ecommerce.entities.Product;
import com.ecommerce.helper.Helper;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper mapper;


    @Override
    public ProductDto create(ProductDto productDto) {

        Product product = mapper.map(productDto, Product.class);

        //generate product Id
        String random = UUID.randomUUID().toString();
        product.setProductId(random);

        //Generate added Date

        product.setProductDate(new Date());


        Product savedProduct = productRepository.save(product);
        ProductDto dto = mapper.map(savedProduct, ProductDto.class);
        return dto;
    }

    @Override
    public ProductDto update(ProductDto productDto, String productId) {

        //fetch the product of given ID

        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("product not found of given id"));

        product.setTitle(productDto.getTitle());
        product.setDiscription(productDto.getDiscription());
        product.setPrice(productDto.getPrice());
        product.setDiscountedPrice(productDto.getDiscountedPrice());
        product.setQuantity(productDto.getQuantity());
        product.setLive(productDto.isLive());
        product.setStock(productDto.isStock());

        //save the updated data

        Product updatedProduct = productRepository.save(product);

        ProductDto productDto1 = mapper.map(updatedProduct, ProductDto.class);

        return productDto1;
    }

    @Override
    public void delete(String productId) {

        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("product not found of given id"));
        productRepository.delete(product);

    }

    @Override
    public ProductDto get(String productId) {

        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("product not found of given id"));

        ProductDto getProduct = mapper.map(product, ProductDto.class);
        return getProduct;
    }

    @Override
    public PageableResponse<ProductDto> getAll(int  pageNumber,int pageSize,String sortBy, String sortDir) {

        Sort sort=(sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()):(Sort.by(sortBy).ascending());

        Pageable pageable= PageRequest.of(pageNumber,pageSize,sort);

        Page<Product> page = productRepository.findAll(pageable);

        PageableResponse<ProductDto> paegebleResponse = Helper.getPaegebleResponse(page, ProductDto.class);
        return paegebleResponse;
    }

    @Override
    public PageableResponse<ProductDto> getAllLive(int  pageNumber,int pageSize,String sortBy, String sortDir) {
        Sort sort=(sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()):(Sort.by(sortBy).ascending());

        Pageable pageable= PageRequest.of(pageNumber,pageSize,sort);

        Page<Product> page = productRepository.findByLiveTrue(pageable);

        PageableResponse<ProductDto> paegebleResponse = Helper.getPaegebleResponse(page, ProductDto.class);
        return paegebleResponse;
    }

    @Override
    public PageableResponse<ProductDto> searchByTitle(String subTitle,int  pageNumber,int pageSize,String sortBy, String sortDir) {
        Sort sort=(sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()):(Sort.by(sortBy).ascending());

        Pageable pageable= PageRequest.of(pageNumber,pageSize,sort);

        Page<Product> page = productRepository.findByTitleContaining(subTitle,pageable);

        PageableResponse<ProductDto> paegebleResponse = Helper.getPaegebleResponse(page, ProductDto.class);
        return paegebleResponse;
    }
}
