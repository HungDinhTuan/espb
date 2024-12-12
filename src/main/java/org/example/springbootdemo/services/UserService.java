package org.example.springbootdemo.services;

import org.example.springbootdemo.entity.User;
import org.example.springbootdemo.models.request.UserRequest;
import org.example.springbootdemo.models.response.UserResponse;
import org.example.springbootdemo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponse> getAllUsers() {
        List<UserResponse> data = new ArrayList<>();
        userRepository.findAll().stream().forEach(user -> data.add(new UserResponse(user)));
        return data;
    }

    public UserResponse createUser(UserRequest data) {
        User req = new User();
        req.setName(data.getName());
        req.setAge(data.getAge());
        req.setSalary(data.getSalary());
        return UserResponse.getUser(userRepository.save(req));
    }

    public UserResponse updateUser(Long id, UserRequest data) {
        return UserResponse.getUser(userRepository.findById(id).map(u -> {
            u.setName(data.getName());
            u.setAge(data.getAge());
            u.setSalary(data.getSalary());
            return userRepository.save(u);
        }).orElseGet(() -> {
            User u = new User();
            u.setId(id);
            u.setName(data.getName());
            u.setAge(data.getAge());
            u.setSalary(data.getSalary());
            return userRepository.save(u);
        }));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
