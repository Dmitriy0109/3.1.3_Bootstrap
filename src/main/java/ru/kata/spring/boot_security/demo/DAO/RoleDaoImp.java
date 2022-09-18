package ru.kata.spring.boot_security.demo.DAO;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@Transactional
public class RoleDaoImp implements RoleDao {
    @PersistenceContext
    private EntityManager entityManager;


    public Set<Role> findAllRoles() {
        Set<Role> roleSet = entityManager.createQuery("select role from Role role", Role.class).getResultStream().collect(Collectors.toSet());
        return roleSet;
    }

    public Role getRole(String role){
        return entityManager.createQuery("select r from Role r where r.name = :role", Role.class)
                .setParameter("role", role).getSingleResult();
    }
    public Set<Role> getSetOfRoles(String[] roleNames) {
        Set<Role> roleSet = new HashSet<>();

        for (String role : roleNames) {
            roleSet.add(getRole(role));
        }
        return roleSet;
    }


}
