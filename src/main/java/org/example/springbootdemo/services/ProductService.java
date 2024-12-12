package org.example.springbootdemo.services;

import org.example.springbootdemo.entity.Product;
import org.example.springbootdemo.models.request.ProductRequest;
import org.example.springbootdemo.models.response.ProductResponse;
import org.example.springbootdemo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductResponse> getAll(){
        List<ProductResponse> data = new ArrayList<>();
        productRepository.findAll().stream().forEach(product -> data.add(new ProductResponse(product)));
        return data;
    }

    public ProductResponse  createProduct(ProductRequest data){
        Product request = new Product();
        request.setName(data.getName());
        request.setPrice(data.getPrice());
        request.setQuantity(data.getQuantity());
        request.setDescription(data.getDescription());
        return ProductResponse.getProduct(productRepository.save(request));
    }

    public  ProductResponse updateProduct(Long id, ProductRequest data){
        return ProductResponse.getProduct(productRepository.findById(id)
                .map(p -> {
                        p.setName(data.getName());
                        p.setPrice(data.getPrice());
                        p.setQuantity(data.getQuantity());
                        p.setDescription(data.getDescription());
                        return productRepository.save(p);
                    }).orElseGet(() -> {
                        Product p = new Product();
                        p.setId(id);
                        p.setName(data.getName());
                        p.setPrice(data.getPrice());
                        p.setQuantity(data.getQuantity());
                        p.setDescription(data.getDescription());
                        return productRepository.save(p);
                    }));
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

    public List<ProductResponse> search(String q){
        List<ProductResponse> data = new ArrayList<>();
        productRepository.findByNameContainingOrDescriptionContaining(q, q).stream()
                .forEach(product -> data.add(new ProductResponse(product)));
        return data;
    }

    public List<ProductResponse> filter(String name, Long minPrice, Long maxPrice){
        List<ProductResponse> data = new ArrayList<>();
        productRepository.filterProduct(name, minPrice, maxPrice).stream()
                .forEach(product -> data.add(new ProductResponse(product)));
        return data;
    }
}
