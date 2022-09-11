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

    public Set<Role>getByName(String name){
        return entityManager.createQuery("SELECT rol from Role rol WHERE rol.name=:name",Role.class)
                .setParameter("name",name).getResultStream().collect(Collectors.toSet());
    }


}
