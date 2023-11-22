package com.example.asm01.service;
import com.example.asm01.entity.Donation;
import com.example.asm01.entity.UserDonation;
import java.util.List;
public interface DonationService {
    List<Donation> findAllDonation();
    List<UserDonation> findListUserDonationById(int theId);
    UserDonation findUserDonationById(int theId);
    void saveUserDonation(UserDonation userDonation, int theDonationId, int theUserId);
    Donation findDonationById(int theId);
    void saveDonation(Donation theDonation);
    void updateDonation(Donation theDonation);
    void deleteDonationById(int theId);
    List<Donation> getPaginatedDonations(int pageNumber, int pageSize);
    int getTotalDonations();
    int getTotalPages(int pageSize);
}
