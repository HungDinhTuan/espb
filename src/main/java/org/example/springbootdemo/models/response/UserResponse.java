package org.example.springbootdemo.models.response;

import lombok.Getter;
import org.example.springbootdemo.entity.User;

@Getter
public class UserResponse {
    private final Long id;
    private final String name;
    private final Integer age;
    private final Long salary;

    public UserResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.age = user.getAge();
        this.salary = user.getSalary();
    }

    public static UserResponse getUser(User user) { return new UserResponse(user); }
}
