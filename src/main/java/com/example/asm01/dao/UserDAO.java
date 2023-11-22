package com.example.asm01.dao;
import com.example.asm01.entity.Role;
import com.example.asm01.entity.User;

import java.util.List;

public interface UserDAO {
    List<User> findAll();
    List<Role> findAllRoles();
    User findById(int theId);
    void save(User theUser);
    void deleteById(int theId);
    void update(User theUser);
    Role findRoleById(int theId);
}
