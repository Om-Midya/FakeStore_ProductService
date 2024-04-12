package org.midya.productservice.service;

import org.midya.productservice.models.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> getAllCategories();
    public Category getCategoryById(Long id);
    public Category getCategoryByName(String name);
    public Category addCategory(Category category);
}
