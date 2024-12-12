package org.example.springbootdemo.models.response;

import org.example.springbootdemo.entity.Brand;

public class BrandResponse {
    private Long id;
    private String name;
    private boolean isExistSlug;

    public BrandResponse(Brand brand) {
        this.id = brand.getId();
        this.name = brand.getName();
        this.isExistSlug = !brand.getSlug().isEmpty();
    }

    public static BrandResponse getBrand(Brand brand) {
        return new BrandResponse(brand);
    }
}
