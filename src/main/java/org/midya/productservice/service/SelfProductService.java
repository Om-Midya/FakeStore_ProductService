package org.midya.productservice.service;

import org.midya.productservice.exceptions.ProductNotFoundException;
import org.midya.productservice.models.Category;
import org.midya.productservice.models.Product;
import org.midya.productservice.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class SelfProductService implements ProductService{
    private final ProductRepository productRepository;

    private final CategoryService categoryService;

    public SelfProductService(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isEmpty()) {
            throw new ProductNotFoundException("Product with id " + id + " not found!");
        }
        return optionalProduct.get();
    }

    @Override
    public List<Product> getProductsByLimit(int limit) {
        return List.of();
    }

    @Override
    public List<Product> getSortedProducts(String order) {
        return List.of();
    }

    @Override
    public List<Category> getAllCategories() {
        return List.of();
    }

    @Override
    public List<Product> getProductsByCategoryName(String categoryName) {
        return List.of();
    }

    @Override
    public String deleteProduct(Long id) {
        return "";
    }

    @Override
    public Product addProduct(Product product) {
        Category category;
        try {
            category = categoryService.getCategoryByName(product.getCategory().getName());
        } catch (Exception e) {
            category = categoryService.addCategory(product.getCategory());
        }
        product.setCategory(category);
        return productRepository.save(product);
    }

    @Override
    public void updateProduct(Product updatedProduct) {
        Optional<Product> optionalProduct = productRepository.findById(updatedProduct.getId());
        if(optionalProduct.isEmpty()) {
            throw new ProductNotFoundException("Product with id " + updatedProduct.getId() + " not found!");
        }
        Product product = optionalProduct.get();
        product.setTitle(updatedProduct.getTitle());
        product.setDescription(updatedProduct.getDescription());
        product.setPrice(updatedProduct.getPrice());
//        product.setCategory(updatedProduct.getCategory());
        product.setImageUrl(updatedProduct.getImageUrl());
        productRepository.save(product);
    }
}
