package com.example.asm01.service;
import com.example.asm01.dao.UserDonationDAO;
import com.example.asm01.entity.UserDonation;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDonationServiceImpl implements UserDonationService{
    private UserDonationDAO userDonationDAO;
    @Autowired
    public UserDonationServiceImpl(UserDonationDAO theUserDonationDAO) {
        userDonationDAO = theUserDonationDAO;
    }
    @Override
    @Transactional
    public List<UserDonation> findAllUserDonation() {
        List<UserDonation> userDonations = userDonationDAO.findAllUserDonation();
        return userDonations ;
    }
}
