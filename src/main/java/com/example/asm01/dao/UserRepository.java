package com.example.asm01.dao;
import com.example.asm01.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserName(String userName);
    User findByFullName(String fullName);
}

