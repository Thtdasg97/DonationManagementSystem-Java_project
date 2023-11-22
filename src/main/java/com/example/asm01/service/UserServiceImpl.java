package com.example.asm01.service;
import com.example.asm01.dao.UserDAO;
import com.example.asm01.entity.Role;
import com.example.asm01.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private UserDAO userDAO;
    @Autowired
    public UserServiceImpl(UserDAO theUserDAO) {
        userDAO = theUserDAO;
    }
    @Override
    @Transactional
    public List<User> findAll() {

        List<User> users = userDAO.findAll();

        for (User user : users) {
            Role role = user.getRole();

            user.setRole(role);
        }
        return users;
    }

    @Override
    @Transactional
    public User findById(int theId) {
        Optional<User> result = Optional.ofNullable(userDAO.findById(theId));

        User theUser = null;

        if(result.isPresent()) {
            theUser = result.get();
        } else {
            throw new RuntimeException("Did not find user id - " + theId);
        }
        return theUser;
    }

    @Override
    @Transactional
    public void save(User theUser) {
        userDAO.save(theUser);
    }

    @Override
    @Transactional
    public void update(User theUser) {
            userDAO.update(theUser);
        }
    @Override
    @Transactional
    public void deleteById(int theId) {
        userDAO.deleteById(theId);
    }
    @Override
    public Role findRoleById(int theId) {
        return userDAO.findRoleById(theId);
    }
    @Override
    @Transactional
    public List<Role> findAllRoles() {
        return userDAO.findAllRoles();
    }
}
