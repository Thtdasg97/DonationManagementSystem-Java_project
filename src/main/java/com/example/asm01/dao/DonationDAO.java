package com.example.asm01.dao;

import com.example.asm01.entity.Donation;
import com.example.asm01.entity.UserDonation;

import java.util.List;
import java.util.Set;

public interface DonationDAO {
    List<UserDonation> findAllUserDonation();
    List<UserDonation> findListUserDonationById(int theId);
    void saveUserDonation(UserDonation userDonation, int theDonationId, int theUserId);
    UserDonation findUserDonationById(int theId);
    List<Donation> findAllDonation();
    Donation findById(int theId);
    void save(Donation theDonation);
    void deleteDonationById(int theId);
    void updateDonation(Donation theDonation);
    List<Donation> getPaginatedDonations(int pageNumber, int pageSize);
    int getTotalDonations();
}
