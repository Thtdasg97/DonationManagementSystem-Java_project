package com.example.asm01.service;
import com.example.asm01.entity.Role;
import com.example.asm01.entity.User;
import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(int theId);
    void save(User theUser);
    void update(User theUser);
    void deleteById(int theId);
    Role findRoleById(int theId);
    List<Role> findAllRoles();
}
