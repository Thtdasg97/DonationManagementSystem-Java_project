package com.example.asm01.controller;
import com.example.asm01.entity.Donation;
import com.example.asm01.entity.UserDonation;
import com.example.asm01.service.DonationService;
import com.example.asm01.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class HomeController {
    private DonationService donationService;
    private UserService userService;
    @Autowired
    public HomeController(DonationService theDonationService) {
        donationService = theDonationService;
    }
    @RequestMapping("/home")
    public String showHome(Model theModel, @RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "5") int size) {
        // show date type
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.of(Year.now().getValue(), 12, 31);
        theModel.addAttribute("startDate", startDate);
        theModel.addAttribute("endDate", endDate);

        // calculate total number of pages
        int totalPages = donationService.getTotalPages(size);

        // validate current page
        if (page < 0 || page >= totalPages) {
            page = 0; // set current page to the first page
        }

        // show paginated list of donations
        List<Donation> donations = donationService.getPaginatedDonations(page, size);
        theModel.addAttribute("donations", donations);
        theModel.addAttribute("currentPage", page);
        theModel.addAttribute("totalPages", totalPages);

        // create new user donation, new user donation detail
        UserDonation userDonation = new UserDonation();
        theModel.addAttribute("newUserDonation", userDonation);

        UserDonation userDonationDetail = new UserDonation();
        theModel.addAttribute("newUserDonationDetail", userDonationDetail);

        return "public/home";
    }

    @RequestMapping("/donation/detail")
    public String showDetailForm(@RequestParam("donationId") int theId, Model theModel) {

        // find list of donation
        List<Donation> theDonations = donationService.findAllDonation();

        theModel.addAttribute("donations", theDonations);

        // find donation
        Donation donation = donationService.findDonationById(theId);

        theModel.addAttribute("donation", donation);

        // find user donation
        List<UserDonation> theUserDonations = donationService.findListUserDonationById(theId);
        theModel.addAttribute("userDonations", theUserDonations);

        // create new user donation
        UserDonation newUserDonationInDetail = new UserDonation();
        theModel.addAttribute("newUserDonationInDetail", newUserDonationInDetail);

        return "public/detail";
    }

    @PostMapping("/saveUserDonation")
    public String saveUserDonation(@ModelAttribute("newUserDonation") UserDonation theUserDonation,
                                   HttpSession session,
                                   @RequestParam("idDonation") int idDonation) {
        // create date to user donation
        LocalDate created = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = created.format(formatter);

        // cast userid to int type form session and set attributes
        int theUserId = (int) session.getAttribute("userId");
        theUserDonation.setCreated(formattedDate);
        theUserDonation.setStatus(1);

        // save user donation
        donationService.saveUserDonation(theUserDonation, idDonation, theUserId);

        // redirect to the form
        return "redirect:/home";
    }

    @PostMapping("/saveUserDonationInDetail")
    public String saveUserDonationInDetail(@ModelAttribute("newUserDonationInDetail") UserDonation userDonation,
                                           HttpSession session,
                                           @RequestParam("idDonation") int idDonation) {
        // create date to user donation
        LocalDate created = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = created.format(formatter);

        // cast userid to int type form session and set attributes
        int theUserId = (int) session.getAttribute("userId");
        userDonation.setCreated(formattedDate);
        userDonation.setStatus(0);

        // save user donation
        donationService.saveUserDonation(userDonation, idDonation, theUserId);

        // redirect to the form
        return "redirect:/donation/detail?donationId=" + idDonation;
    }
}