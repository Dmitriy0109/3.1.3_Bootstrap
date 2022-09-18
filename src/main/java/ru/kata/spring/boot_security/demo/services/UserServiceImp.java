package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.DAO.UserDao;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

@Service
public class UserServiceImp implements UserService, UserDetailsService {

    private UserDao userDao;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImp(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User findByUseremail(String email) {
        return userDao.findByUseremail(email);
    }


    @Transactional
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDao.findByUseremail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Takogo usera net!" + email);
        }
        return user;
    }

    @Transactional
    public User getById(int id) {
        return userDao.getById(id);
    }

    @Transactional
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
    }

    @Transactional
    public void deleteById(int id) {
        userDao.deleteById(id);
    }

    @Transactional
    public List<User> findAll() {
        return userDao.findAll();
    }


    @Transactional
    public void update(User user) {
        User user1=userDao.getById(user.getId());
        if (user.getPassword().equals(user1.getPassword())){
            userDao.update(user);
        }else{
            String pass= passwordEncoder.encode(user.getPassword());
            user.setPassword(pass);
            userDao.update(user);
        }

    }
}

