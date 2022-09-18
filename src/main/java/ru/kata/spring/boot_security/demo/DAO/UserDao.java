package ru.kata.spring.boot_security.demo.DAO;

import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserDao {
    public void save(User user);

    public void deleteById(int id);

    public User getById(int id);

    public User findByUseremail(String email);

    public List<User> findAll();

    public void update(User user);


}
