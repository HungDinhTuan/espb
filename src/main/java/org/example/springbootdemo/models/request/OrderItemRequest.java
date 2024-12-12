package org.example.springbootdemo.models.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemRequest {
    private Long productId;
    private Long price;
    private Integer quantity;
}
