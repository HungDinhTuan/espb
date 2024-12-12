package org.example.springbootdemo.models.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {
    private String name;
    private Long price;
    private Integer quantity;
    private String description;
}
