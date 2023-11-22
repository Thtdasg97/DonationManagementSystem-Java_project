package com.example.asm01.dao;

import com.example.asm01.entity.UserDonation;

import java.util.List;

public interface UserDonationDAO {
    List<UserDonation> findAllUserDonation();
}
