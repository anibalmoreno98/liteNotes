package com.litenotes.service;

import com.litenotes.model.Category;
import com.litenotes.repository.CategoryRepository;

import java.util.List;

public class CategoryService {

    private final CategoryRepository repo = new CategoryRepository();

    public List<Category> getAllCategories() {
        return repo.getAll();
    }
}
