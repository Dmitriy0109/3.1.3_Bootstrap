package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UserDetails;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserService {
    public User findByUseremail(String username);

    public User getById(int id);

    public void save(User user);

    public void deleteById(int id);

    public List<User> findAll();

    public void update(User user);


}
