package com.example.asm01.dao;
import com.example.asm01.entity.Donation;
import com.example.asm01.entity.User;
import com.example.asm01.entity.UserDonation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public class DonationDAOJPAImpl implements DonationDAO {
    private EntityManager entityManager;
    @Autowired
    public DonationDAOJPAImpl(EntityManager theEntityManager) {
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

    @Override
    public List<UserDonation> findListUserDonationById(int theId) {
        // create a query
        TypedQuery<UserDonation> theQuery =
                entityManager.createQuery(
                        "SELECT ud FROM UserDonation ud WHERE ud.donation.id = :data", UserDonation.class);
        theQuery.setParameter("data", theId);

        // return the list
        List<UserDonation> userDonations = theQuery.getResultList();

        return userDonations;
    }

    @Override
    public void saveUserDonation(UserDonation userDonation, int theDonationId, int theUserId) {
        Donation donation = entityManager.find(Donation.class, theDonationId);
        User user = entityManager.find(User.class,theUserId);

        userDonation.setUser(user);
        userDonation.setDonation(donation);

        entityManager.persist(userDonation);
    }

    @Override
    public UserDonation findUserDonationById(int theId) {
        UserDonation userDonation = entityManager.find(UserDonation.class,theId);
        return userDonation ;
    }


    @Override
    public List<Donation> findAllDonation() {
        // create a query
        TypedQuery<Donation> theQuery = entityManager.createQuery("FROM Donation", Donation.class);

        // get the list
        List<Donation> theDonations = theQuery.getResultList();

        // return the list
        return theDonations;
    }

    @Override
    public Donation findById(int theId) {
        // get donation
        Donation theDonation = entityManager.find(Donation.class, theId);

        // return donation
        return theDonation;
    }

    @Override
    public void save(Donation theDonation) {
        // save donation
        entityManager.persist(theDonation);
    }

    @Override
    public void deleteDonationById(int theId) {
        // find donation by id
        Donation theDonation = entityManager.find(Donation.class, theId);

        // remove donation
        entityManager.remove(theDonation);
    }

    @Override
    public void updateDonation(Donation theDonation) {
        theDonation.setStatus(theDonation.getStatus());
        theDonation.setDescription(theDonation.getDescription());
        entityManager.merge(theDonation);

    }

    @Override
    public List<Donation> getPaginatedDonations(int pageNumber, int pageSize) {
        TypedQuery<Donation> query = entityManager.createQuery("SELECT d FROM Donation d", Donation.class);
        query.setFirstResult(pageNumber * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }
    @Override
    public int getTotalDonations() {
        TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(d) FROM Donation d", Long.class);
        return query.getSingleResult().intValue();
    }
}
