package org.midya.productservice.controllers;

import org.midya.productservice.dtos.ExceptionDTO;
import org.midya.productservice.dtos.ProductFetchDTO;
import org.midya.productservice.models.Category;
import org.midya.productservice.models.Product;
import org.midya.productservice.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/test")
    public String test() {
        return "Test";
    }

    @GetMapping("")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable("id") Long id){
        return productService.getProductById(id);
    }

    @GetMapping("/{limit}")
    public List<Product> getProductsByLimit(@PathVariable("limit") int limit){
        return productService.getProductsByLimit(limit);
    }

    @GetMapping("/sorted")
    public List<Product> getSortedProducts(@RequestParam("order") String order){
        return productService.getSortedProducts(order);
    }

    @GetMapping("/categories")
    public List<Category> getAllCategories(){
        return productService.getAllCategories();
    }

    @GetMapping("/category/{categoryName}")
    public List<Product> getProductsByCategoryName(@PathVariable("categoryName") String categoryName){
        return productService.getProductsByCategoryName(categoryName);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id){
        return productService.deleteProduct(id);
    }

    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @PutMapping("/update")
    public void updateProduct(@RequestBody Product product){
        productService.updateProduct(product);
    }

}
