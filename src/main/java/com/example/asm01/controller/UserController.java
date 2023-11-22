package com.example.asm01.controller;
import com.example.asm01.entity.Role;
import com.example.asm01.entity.User;
import com.example.asm01.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;
@Controller
public class UserController {
    private UserService userService;
    public UserController(UserService theUserService) {
        userService = theUserService;
    }

    @GetMapping("/list")
    public String listUsers(Model theModel)  {
        // get list of account
        List<User> theUsers = userService.findAll();

        // create new user
        User newUser = new User();

        // set user in the model to prepopulate the form
        theModel.addAttribute("users",theUsers);
        theModel.addAttribute("newUser",newUser);

        // get the role list form database
        List<Role> roleList = userService.findAllRoles();
        theModel.addAttribute("roleList", roleList);

        return "admin/account";
    }

    @PostMapping("/saveUser")
    public String saveUser (@ModelAttribute("newUser") User theUser,
                            @RequestParam("idRole") int roleId,
                            RedirectAttributes redirectAttributes) {

        Role role = userService.findRoleById(roleId);
        theUser.setRole(role);

        Date createdAt = new Date();
        theUser.setCreatedAt(createdAt);

        userService.save(theUser);

        redirectAttributes.addFlashAttribute("successFlag", true);

        // user redirect
        return "redirect:/list";
    }

    @PostMapping("/updateUser")
    public String updateUser (@ModelAttribute("newUser") User theUser, @RequestParam("idRole") int roleId) {
        Role role = userService.findRoleById(roleId);
        theUser.setRole(role);

        Date createdAt = new Date();
        theUser.setCreatedAt(createdAt);

        userService.update(theUser);

        return "redirect:/list";
    }
    @PostMapping("/ql-user/delete")
    public String deleteUser(@RequestParam("idUser") int theId) {
        userService.deleteById(theId);
        return "redirect:/list";
    }
    @PostMapping("/ql-user/lock")
    public String lockUser(@RequestParam("idUser") int theId) {
        User user = userService.findById(theId);
        user.setStatus(0);
        userService.save(user);
        return "redirect:/list";
    }
    @PostMapping("/ql-user/un-lock")
    public String unlockUser(@RequestParam("idUser") int theId) {
        User user = userService.findById(theId);
        user.setStatus(1);
        userService.save(user);
        return "redirect:/list";
    }
}
