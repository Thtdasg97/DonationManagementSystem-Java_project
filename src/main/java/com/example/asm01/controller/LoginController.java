package com.example.asm01.controller;
import com.example.asm01.dao.UserRepository;
import com.example.asm01.entity.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    private UserRepository userRepository;
    @Autowired
    public LoginController(UserRepository theUserRepository) {
        userRepository = theUserRepository;
    }

    @GetMapping("/showMyLoginPage")
    public String showMyLoginPage() {
        return "admin/login";
    }

    @PostMapping("/authenticateTheUser")
    public String authenticateUser(@RequestParam("username") String username,
                                   @RequestParam("password") String password,
                                   HttpSession session,
                                   Model theModel) {
        User user = userRepository.findByUserName(username);
        if (user != null && user.getPassword().equals(password)) {
            if (user.getStatus() == 0) {
                // Account is locked
                theModel.addAttribute("error", "Tài khoản của bạn đã bị khóa");
                return "admin/login";
            } else {
                // Successful login
                if (user.getRole().getRoleName().equals("Admin")) {
                    return "redirect:/showAdminHomePage";
                } else {
                    session.setAttribute("username", user.getFullName());
                    session.setAttribute("userId", user.getId());
                    return "redirect:/home";
                }
            }
        } else {
            // Invalid credentials
            theModel.addAttribute("error", "Sai tài khoản hoặc mật khẩu");
            return "admin/login";
        }
    }

    @GetMapping("/showUserHomePage")
    public String showUserHomePage() {
        return "public/home";
    }

    @GetMapping("/showAdminHomePage")
    public String showAdminHomePage() {
        return "admin/home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/showMyLoginPage";
    }


}
