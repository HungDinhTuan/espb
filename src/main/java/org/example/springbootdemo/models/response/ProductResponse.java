package org.example.springbootdemo.models.response;

import lombok.Getter;
import org.example.springbootdemo.entity.Product;

@Getter
public class ProductResponse {

    private final Long id;
    private final String name;
    private final Long price;
    private final boolean inStock;
    private final String description;

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.inStock = product.getQuantity() > 0;
        this.description = product.getDescription();
    }

    // factory pattern
    public static ProductResponse getProduct(Product product) {
        return new ProductResponse(product);
    }
}
