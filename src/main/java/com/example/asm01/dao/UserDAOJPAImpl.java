package com.example.asm01.dao;

import com.example.asm01.entity.Role;
import com.example.asm01.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOJPAImpl implements UserDAO {
    private EntityManager entityManager;
    @Autowired
    public UserDAOJPAImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }
    @Override
    public List<User> findAll() {
        // create a query
        TypedQuery<User> theQuery = entityManager.createQuery("FROM User",User.class);

        // get the list
        List<User> theUsers = theQuery.getResultList();

        // return list of user
        return theUsers;
    }

    @Override
    public List<Role> findAllRoles() {
        return entityManager.createQuery("SELECT r FROM Role r", Role.class)
                .getResultList();
    }

    @Override
    public User findById(int theId) {
        // get user
        User theUser = entityManager.find(User.class,theId);

        // return user
        return theUser;
    }

    @Override
    public void save(User theUser) {
        // save user
        entityManager.persist(theUser);
    }

    @Override
    public void deleteById(int theId) {
        // find user by id
        User theUser = entityManager.find(User.class,theId);

        // remove user
        entityManager.remove(theUser);
    }
    @Override
    public void update(User theUser) {
        entityManager.merge(theUser);
    }
    @Override
    public Role findRoleById(int theId) {
        return entityManager.find(Role.class,theId);
    }
}
