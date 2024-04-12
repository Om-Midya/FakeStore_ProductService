package org.midya.productservice.service;

import org.midya.productservice.exceptions.CategoryNotFoundException;
import org.midya.productservice.models.Category;
import org.midya.productservice.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class SelfCategoryService implements CategoryService {
    private final CategoryRepository categoryRepository;

    public SelfCategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        // if category is not found, throw an exception

        return optionalCategory.get();
    }

    @Override
    public Category getCategoryByName(String name) {
        Optional<Category> optionalCategory = categoryRepository.findByName(name);
        // if category is not found, throw an exception

        return optionalCategory.get();
    }

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }
}
