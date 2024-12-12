package org.example.springbootdemo.controller;

import org.example.springbootdemo.models.request.ProductRequest;
import org.example.springbootdemo.models.response.ProductResponse;
import org.example.springbootdemo.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    private ProductService productService;
    private ProductRequest req;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productService.getAll();
    }

    @PostMapping
    public ProductResponse createProduct(@RequestBody ProductRequest req) {
        return productService.createProduct(req);
    }

    @PutMapping("/{id}")
    public ProductResponse updateProduct(@PathVariable Long id, @RequestBody ProductRequest req) {
        return productService.updateProduct(id, req);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @GetMapping("/search")
    public List<ProductResponse> search(@RequestParam String search) {
        return productService.search(search);
    }

    @GetMapping("/filter")
    public List<ProductResponse> filter(@RequestParam(required = false) String name,
                                        @RequestParam(required = false) Long minPrice,
                                        @RequestParam(required = false) Long maxPrice) {
        return productService.filter(name, minPrice, maxPrice);
    }
}
