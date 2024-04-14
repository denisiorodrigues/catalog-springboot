package com.kindincode.springboot.repositories;

import com.kindincode.springboot.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductReposiory extends JpaRepository<Product, UUID> {
}
