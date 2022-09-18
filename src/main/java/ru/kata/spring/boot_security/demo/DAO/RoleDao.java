package ru.kata.spring.boot_security.demo.DAO;

import ru.kata.spring.boot_security.demo.models.Role;

import java.util.Set;

public interface RoleDao {
    public Set<Role> findAllRoles();

    public Role getRole(String name);

    public Set<Role> getSetOfRoles(String[] roleNames);

}
