package com.kindincode.springboot.controllers;

import com.kindincode.springboot.repositories.ProductReposiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProcutController {

    @Autowired
    ProductReposiory productReposiory;


}
