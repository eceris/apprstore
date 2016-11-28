package com.daou.go.appr.store.component.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daou.go.appr.store.component.category.model.Category;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public void save(Category category) {
        categoryRepository.save(category);
    }

    public void delete(String id) {
        categoryRepository.delete(id);
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

}
