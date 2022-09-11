package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.DAO.RoleDao;
import ru.kata.spring.boot_security.demo.models.Role;

import java.util.Set;

@Service
@Transactional
public class RoleServiceImp implements RoleService {
    private RoleDao roleDao;

    @Autowired
    public RoleServiceImp(RoleDao roleDao) {
        this.roleDao = roleDao;

    }

    public Set<Role> getByName(String name) {
        return roleDao.getByName(name);
    }

    public Set<Role> findAllRoles() {
        return roleDao.findAllRoles();
    }

}
