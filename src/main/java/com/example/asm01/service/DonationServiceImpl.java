package com.example.asm01.service;
import com.example.asm01.dao.DonationDAO;
import com.example.asm01.entity.Donation;
import com.example.asm01.entity.UserDonation;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DonationServiceImpl implements DonationService {
    private DonationDAO donationDAO;
    @Autowired
    public DonationServiceImpl(DonationDAO theDonationDAO) {
        donationDAO = theDonationDAO;
    }

    @Override
    @Transactional
    public List<Donation> findAllDonation() {
        List<Donation> donations = donationDAO.findAllDonation();
        return donations;
    }
    @Override
    @Transactional
    public List<UserDonation> findListUserDonationById(int theId) {
        List<UserDonation> userDonations = donationDAO.findListUserDonationById(theId);
        return userDonations;
    }

    @Override
    public UserDonation findUserDonationById(int theId) {
        UserDonation userDonation = donationDAO.findUserDonationById(theId);
        return userDonation ;
    }

    @Override
    @Transactional
    public void saveUserDonation(UserDonation userDonation, int theDonationId, int theUserId) {
        donationDAO.saveUserDonation(userDonation, theDonationId, theUserId);
    }

    @Override
    @Transactional
    public Donation findDonationById(int theId) {
        Optional<Donation> result = Optional.ofNullable(donationDAO.findById(theId));

        Donation theDonation = null;

        if(result.isPresent()) {
            theDonation = result.get();
        } else {
            throw new RuntimeException("Did not find donation id - " + theId);
        }
        return theDonation;
    }

    @Override
    @Transactional
    public void saveDonation(Donation theDonation) {
        donationDAO.save(theDonation);
    }

    @Override
    @Transactional
    public void updateDonation(Donation theDonation) {
        donationDAO.updateDonation(theDonation);
    }
    @Override
    @Transactional
    public void deleteDonationById(int theId) {
        donationDAO.deleteDonationById(theId);
    }
    @Override
    public List<Donation> getPaginatedDonations(int pageNumber, int pageSize) {
        return donationDAO.getPaginatedDonations(pageNumber, pageSize);
    }
    @Override
    public int getTotalDonations() {
        return donationDAO.getTotalDonations();
    }
    @Override
    public int getTotalPages(int pageSize) {
        int totalDonations = donationDAO.getTotalDonations();
        return (int) Math.ceil((double) totalDonations / pageSize);
    }
}
