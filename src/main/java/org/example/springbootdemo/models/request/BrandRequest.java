package org.example.springbootdemo.models.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrandRequest {
    private Long id;
    private String name;
    private String slug;
}
