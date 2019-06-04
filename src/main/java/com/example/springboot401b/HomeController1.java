package com.example.springboot401b;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class HomeController1 {

    @Autowired
    UserService userService;

    @Autowired
    Postingrepository postingrepository;
    @Autowired
    User1Repository user1Repository;

    @GetMapping("/register")
    public String showRegistrationPage(Model model) {
        model.addAttribute("user", new User1());
        return "registration";
    }

    @PostMapping("/register")
    public String processRegistrationPage(@Valid
                                          @ModelAttribute("user") User1 user, BindingResult result, Model model) {
        model.addAttribute("user", user);
        if (result.hasErrors()) {
            return "registration";
        } else {
            userService.saveUser(user);
            model.addAttribute("message", "user account created");
        }
        return "index";
    }

    //    @RequestMapping("/")
//    public String index(){
//        return "index";
//    }
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    //    @RequestMapping("/admin")
//    public String admin(){
//        return "admin";
//    }
    @RequestMapping("/secure")
    public String secure(Principal principal, Model model) {
        User1 myuser = ((CustomUserDetails) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getUser();
        model.addAttribute("myuser", myuser);
        return "secure";
    }


    @RequestMapping("/")
    public String listPostings(Model model) {
        model.addAttribute("postings", postingrepository.findAll());
        if (userService.getUser() != null) {
            model.addAttribute("user_id", userService.getUser().getId());
        }
        return "list";
    }

    @GetMapping("/add")
    public String postingform(Model model) {
        model.addAttribute("posting", new Posting());
        return "postingform";
    }

    @PostMapping("/process")
    public String postingform(@Valid Posting posting,
                              BindingResult result) {
        if (result.hasErrors()) {
            return "postingform";
        }
        String username = userService.getUser().getUsername();
        posting.setUser(user1Repository.findByUsername(username));
        postingrepository.save(posting);
        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String showposting(@PathVariable("id") long id, Model model) {
        model.addAttribute("posting", postingrepository.findById(id).get());
        return "show";
    }

    @RequestMapping("/update/{id}")
    public String updatePosting(@PathVariable("id") long id,
                                Model model) {
        model.addAttribute("posting", postingrepository.findById(id).get());
        return "postingform";
    }

    @RequestMapping("/delete/{id}")
    public String deletePosting(@PathVariable("id") long id) {
        postingrepository.deleteById(id);
        return "redirect:/";
    }

    @RequestMapping("/logout")
    public String logout() {
        return "login";
    }
}
