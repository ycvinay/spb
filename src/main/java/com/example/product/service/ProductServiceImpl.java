package com.example.product.service;

import com.example.product.model.Products;
import com.example.product.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

//    private List<Products> produts = new ArrayList<>(){{
//       add(new Products("mobile", 1000));
//       add(new Products("charger", 200));
//       add(new Products("battery", 500));
//    }};

    @Autowired
    private ProductRepository repo;

    @Override
    public List<Products> getAllProducts() {
        return repo.findAll();
    }

    @Override
    public Optional<Products> getProductById(Long id) {
        return repo.findById(id);
    }

    @Override
    public Products addProduct(Products product) {
        return repo.save(product);
    }

    @Override
    public Products updateProduct(Long id, Products product) {
        if (!repo.existsById(id)){
            throw new RuntimeException("Product not found with id " + id);
        }
        product.setId(id);
        return repo.save(product);
    }

    @Override
    public boolean deleteProduct(Long id) {
        if (!repo.existsById(id)) {
            throw  new RuntimeException("Product not found with id " + id);
        }

        repo.deleteById(id);
        return true;

    }
}
