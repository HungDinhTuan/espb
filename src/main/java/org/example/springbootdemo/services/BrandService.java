package org.example.springbootdemo.services;

import org.example.springbootdemo.entity.Brand;
import org.example.springbootdemo.models.request.BrandRequest;
import org.example.springbootdemo.models.response.BrandResponse;
import org.example.springbootdemo.repository.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BrandService {
    BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public List<BrandResponse> getAllBrands() {
        List<BrandResponse> data = new ArrayList<BrandResponse>();
        brandRepository.findAll().stream().forEach(brand -> data.add(new BrandResponse(brand)));
        return data;
    }

    public BrandResponse createBrand(BrandRequest data) {
        Brand brand = new Brand();
        brand.setName(data.getName());
        brand.setName(data.getName());
        brand.setSlug(data.getSlug());
        return BrandResponse.getBrand(brandRepository.save(brand));
    }

    public BrandResponse updateBrand(Long id, BrandRequest data) {
        return BrandResponse.getBrand(brandRepository.findById(id)
                .map(b -> {
                    b.setName(data.getName());
                    b.setSlug(data.getSlug());
                    return brandRepository.save(b);
                }).orElseGet( () -> {
                    Brand b = new Brand();
                    b.setId(id);
                    b.setName(data.getName());
                    b.setSlug(data.getSlug());
                    return brandRepository.save(b);
                }));
    }

    public void deleteBrand(Long id) {
        brandRepository.deleteById(id);
    }
}
