package com.example.product.controller;


import com.example.product.model.Products;
import com.example.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/view-products")
    public String viewProducts(Model model) {
        List<Products> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "view-products";
    }


    @GetMapping("/create-product")
    public String showCreateProductForm(Model model) {
        model.addAttribute("product", new Products());
        return "create-product";
    }

    @PostMapping("/create-product")
    public String createProduct(@ModelAttribute Products product, Model model) {
        productService.addProduct(product);  // Add the new product to the list/database
        model.addAttribute("successMsg", "Product successfully added!");  // Add success message to the model
        model.addAttribute("product", new Products());
        return "create-product";  // Return the same Thymeleaf template for creating a product
    }

    @GetMapping("/update-product/{id}")
    public String showUpdateProductForm(@PathVariable Long id, Model model) {
        Optional<Products> optionalProducts = productService.getProductById(id);
        if (optionalProducts.isPresent()) {
            Products product = optionalProducts.get();
            model.addAttribute("product", product);
            return "update-product";
        } else {
            return "redirect:/view-products";
        }
    }

    @PostMapping("/update-product")
    public String updateProduct(@ModelAttribute Products product, Model model){
        productService.updateProduct(product.getId(), product);
        model.addAttribute("successMsg", "Successfully Updated");
        return "redirect:/view-products";
    }

    @PostMapping("/delete-product/{id}")
    public String deleteProduct(@PathVariable Long id, Model model){
        boolean deleted = productService.deleteProduct(id);
        if (deleted) {
            model.addAttribute("successMsg", "Successfully product deleted");
        } else {
            model.addAttribute("errorMsg", "Successfully product deleted");
        }
        return "redirect:/view-products";
    }

}
