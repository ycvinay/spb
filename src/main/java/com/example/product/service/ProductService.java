package com.example.product.service;


import com.example.product.model.Products;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Products> getAllProducts();
    Optional<Products> getProductById(Long Id);
    Products addProduct(Products product);
    Products updateProduct(Long id, Products product);
    boolean deleteProduct(Long id);


}
