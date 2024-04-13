package org.midya.productservice.service;

import org.midya.productservice.dtos.ProductFetchDTO;
import org.midya.productservice.models.Category;
import org.midya.productservice.models.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getAllProducts();
    public Product getProductById(Long id);
    public List<Product> getProductsByLimit(int limit);
    public List<Product> getSortedProducts(String order);
    public List<Category> getAllCategories();
    public List<Product> getProductsByCategoryName(String categoryName);
    public void deleteProduct(Long id);
    public Product addProduct(Product product);
    public void updateProduct(Product product);

}
