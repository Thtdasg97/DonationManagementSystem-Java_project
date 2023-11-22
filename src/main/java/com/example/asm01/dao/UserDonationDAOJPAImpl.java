package com.example.asm01.dao;

import com.example.asm01.entity.UserDonation;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class UserDonationDAOJPAImpl implements UserDonationDAO {
    private EntityManager entityManager;
    @Autowired
    public UserDonationDAOJPAImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }
    @Override
    public List<UserDonation> findAllUserDonation() {
        // create a query
        TypedQuery<UserDonation> theQuery = entityManager.createQuery("FROM UserDonation", UserDonation.class);

        // get the list
        List<UserDonation> userDonations = theQuery.getResultList();

        return userDonations;
    }
}
