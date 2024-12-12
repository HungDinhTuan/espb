package org.example.springbootdemo.models.response;

import lombok.Getter;
import org.example.springbootdemo.entity.Category;

@Getter
public class CategoryResponse {

    public CategoryResponse(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.existSlug = !category.getSlug().isEmpty();
    }

    private Long id;
    private String name;
    private boolean existSlug;

    public static CategoryResponse getCategory(Category category) {
        return new CategoryResponse(category);
    }
}
