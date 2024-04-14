package com.kindincode.springboot.controllers;

import com.kindincode.springboot.dtos.ProductRecordDto;
import com.kindincode.springboot.models.Product;
import com.kindincode.springboot.repositories.ProductReposiory;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ProductController {

    @Autowired
    ProductReposiory productReposiory;

    @PostMapping("/products")
    public ResponseEntity<Product> saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto){
        var product = new Product();
        BeanUtils.copyProperties(productRecordDto, product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productReposiory.save(product));
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productReposiory.findAll());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id") UUID id) {
        Optional<Product> productFinded = productReposiory.findById(id);
        if(productFinded.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("product not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(productFinded.get());
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value = "id") UUID id,
                                                @RequestBody @Valid ProductRecordDto productRecordDto){
        Optional<Product> productFinded = productReposiory.findById(id);

        if(productFinded.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("product not found");
        }

        var productUpdate = productFinded.get();
        BeanUtils.copyProperties(productRecordDto, productUpdate);

        return ResponseEntity.status(HttpStatus.OK).body(productReposiory.save(productUpdate));
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") UUID id){
        Optional<Product> productFinded = productReposiory.findById(id);

        if(productFinded.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("product not found");
        }

        productReposiory.delete(productFinded.get());

        return ResponseEntity.status(HttpStatus.OK).body("prodduct deleted successfully");
    }
}
