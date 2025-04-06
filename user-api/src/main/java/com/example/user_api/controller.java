package com.example.user_api;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class controller {

    private List<User> users = new ArrayList<>();

    @GetMapping
    public List<User> getAllUsers() {
        return users;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable long id) {
        return users.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        user.setId(users.size() + 1);
        users.add(user);
        return user;
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable long id, @RequestBody User user) {
        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);
            if(u.getId() == id) {
                u.setName(user.getName());
                u.setEmail(user.getEmail());
                return u;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable long id) {
        users.removeIf(user -> user.getId() == id);
        return "User with id " + id + " deleted";
    }
}
