package org.example.springbootdemo.models.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    private String name;
    private Integer age;
    private Long salary;
}
