package org.midya.productservice.controllers;

import org.midya.productservice.models.Category;
import org.midya.productservice.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @PostMapping("")
    public Category addCategory(@RequestBody Category category){
        return categoryService.addCategory(category);
    }
}
