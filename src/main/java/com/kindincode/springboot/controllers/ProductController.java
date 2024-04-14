package com.kindincode.springboot.controllers;

import com.kindincode.springboot.dtos.ProductRecordDto;
import com.kindincode.springboot.models.Product;
import com.kindincode.springboot.repositories.ProductReposiory;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    ProductReposiory productReposiory;

    @PostMapping("/products")
    public ResponseEntity<Product> saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto){
        var productModel = new Product();
        BeanUtils.copyProperties(productRecordDto, productModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(productReposiory.save(productModel));
    }
}
