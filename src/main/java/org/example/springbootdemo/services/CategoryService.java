package org.example.springbootdemo.services;

import org.example.springbootdemo.entity.Category;
import org.example.springbootdemo.models.request.CategoryRequest;
import org.example.springbootdemo.models.response.CategoryResponse;
import org.example.springbootdemo.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryResponse> getAllCategories() {
        List<CategoryResponse> data = new ArrayList<>();
        categoryRepository.findAll().stream().forEach(c -> data.add(new CategoryResponse(c)));
        return data;
    }

    public CategoryResponse createCategory(CategoryRequest data) {
        Category req = new Category();
        req.setName(data.getName());
        req.setSlug(data.getSlug());
        return CategoryResponse.getCategory(categoryRepository.save(req));
    }

    public CategoryResponse updateCategory(Long id, CategoryRequest data) {
        return CategoryResponse.getCategory(categoryRepository.findById(id)
                .map(c -> {
                    c.setName(data.getName());
                    c.setSlug(data.getSlug());
                    return categoryRepository.save(c);
                }).orElseGet(() -> {
                    Category c = new Category();
                    c.setId(id);
                    c.setName(data.getName());
                    c.setSlug(data.getSlug());
                    return categoryRepository.save(c);
                }));
    }

    public  void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
