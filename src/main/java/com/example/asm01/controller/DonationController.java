package com.example.asm01.controller;
import com.example.asm01.entity.Donation;
import com.example.asm01.entity.UserDonation;
import com.example.asm01.service.DonationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class DonationController {
    private DonationService donationService;
    public DonationController(DonationService theDonationService) {
        donationService = theDonationService;
    }

    @GetMapping("/donation")
    public String listDonation(Model theModel) {
        // get list of account
        List<Donation> theDonations = donationService.findAllDonation();

        // create new donation
        Donation newDonation = new Donation();

        // set user in the model to prepopulate the form
        theModel.addAttribute("donations", theDonations);
        theModel.addAttribute("newDonation", newDonation);

        return "admin/donation";
    }

    @PostMapping("/saveDonation")
    public String saveDonation(@ModelAttribute("newDonation") Donation theDonation, RedirectAttributes redirectAttributes) {

        // create date to account
        LocalDate created = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = created.format(formatter);
        theDonation.setCreated(formattedDate); // set date to account
        theDonation.setStatus(1); // set status to account

        redirectAttributes.addFlashAttribute("successFlag", true);


        donationService.saveDonation(theDonation);
        return "redirect:/donation";
    }
    @PostMapping("/updateDonation")
    public String updateDonation(@ModelAttribute("newDonation") Donation theDonation) {

        // create date to account
        LocalDate created = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = created.format(formatter);
        theDonation.setCreated(formattedDate);

        // update when user donate
        Donation existingDonation = donationService.findDonationById(theDonation.getId());
        theDonation.setStatus(existingDonation.getStatus());
        theDonation.setMoney(existingDonation.getMoney());

        donationService.updateDonation(theDonation);
        return "redirect:/donation";

    }

    @GetMapping("/showFormForDetail")
    public String showFormForDetail(@RequestParam("donationId") int theId, Model theModel) {

        // get the list of User Donation
        List<UserDonation> theUserDonations = donationService.findListUserDonationById(theId);
        theModel.addAttribute("userDonations", theUserDonations);

        // get particular donation by service
        Donation theDonation = donationService.findDonationById(theId);

        // set donation in the model to pre-populate
        theModel.addAttribute("donation", theDonation);

        // send over to our form
        return "admin/detail";
    }

    @PostMapping("/ql-donation/delete")
    public String deleteDonation(@RequestParam("idDonation") int theId) {
        donationService.deleteDonationById(theId);
        return "redirect:/donation";
    }

    @PostMapping("/ql-donation/donate")
    public String donateDonation(@RequestParam("idD") int theId) {
        Donation donation = donationService.findDonationById(theId);
        donation.setStatus(2);
        donationService.saveDonation(donation);
        return "redirect:/donation";
    }

    @PostMapping("/ql-donation/finish")
    public String finishDonation(@RequestParam("idD") int theId) {
        Donation donation = donationService.findDonationById(theId);
        donation.setStatus(3);
        donationService.saveDonation(donation);
        return "redirect:/donation";
    }

    @PostMapping("/ql-user-donation/accept")
    public String acceptUserDonation(@RequestParam("idUserDonation") int theUserDonationId,
                                     @RequestParam("donationId") int theDonationId) {

        // find user donation
        UserDonation userDonation = donationService.findUserDonationById(theUserDonationId);
        userDonation.setStatus(1);
        int donatedMoney = userDonation.getMoney();

        // find donating donation
        Donation donatingDonation = donationService.findDonationById(theDonationId);
        int updatedMoney = donatingDonation.getMoney() + donatedMoney;
        donatingDonation.setMoney(updatedMoney);

        // update donation when user is accepted
        donationService.updateDonation(donatingDonation);

        // redirect to the form
        return "redirect:/showFormForDetail?donationId=" + theDonationId;

    }

    @PostMapping("/ql-user-donation/decline")
    public String declineUserDonation(@RequestParam("idUserDonation") int theUserDonationId,
                                      @RequestParam("donationId") int theDonationId) {
        // find user donation
        UserDonation userDonation = donationService.findUserDonationById(theUserDonationId);
        userDonation.setStatus(2);

        // update donation when user is declined
        Donation decliningDonation = donationService.findDonationById(theDonationId);
        donationService.updateDonation(decliningDonation);

        // redirect to the form
        return "redirect:/showFormForDetail?donationId=" + theDonationId;
    }
}
