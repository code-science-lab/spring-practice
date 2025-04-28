package io.codescience.repository;

import io.codescience.model.User;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    private List<User> users = new ArrayList<>();

    public UserRepository() {
        // 初始化一些假数据
        users.add(new User("1", "John Doe", "john@example.com"));
        users.add(new User("2", "Jane Doe", "jane@example.com"));
    }

    public List<User> findAll() {
        return users;
    }

    public User findById(String id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
    }

    public User save(User user) {
        users.add(user);
        return user;
    }

    public User update(String id, User user) {
        User existingUser = findById(id);
        if (existingUser != null) {
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
        }
        return existingUser;
    }

    public void delete(String id) {
        users.removeIf(user -> user.getId().equals(id));
    }
}