package org.example.springbootdemo.controller;

import org.example.springbootdemo.models.request.BrandRequest;
import org.example.springbootdemo.models.response.BrandResponse;
import org.example.springbootdemo.services.BrandService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/brand")
public class BrandController {
    private BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    public List<BrandResponse> getAllBrands() {
        return brandService.getAllBrands();
    }

    @PostMapping
    public BrandResponse createBrand(@RequestBody BrandRequest req) {
        return brandService.createBrand(req);
    }

    @PutMapping("/{id}")
    public BrandResponse updateBrand(@PathVariable Long id, @RequestBody BrandRequest req) {
        return brandService.updateBrand(id, req);
    }

    @DeleteMapping("/{id}")
    public void deleteBrand(@PathVariable Long id) {
        brandService.deleteBrand(id);
    }
}
