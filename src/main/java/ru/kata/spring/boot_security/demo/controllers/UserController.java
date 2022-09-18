package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.services.UserServiceImp;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {
    private UserServiceImp userServiceImp;

    public UserController(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @GetMapping()
    public String showUserInfo(Principal principal, Model model) {
       StringBuilder roles= new StringBuilder();
       for (Role role: userServiceImp.findByUseremail(principal.getName()).getRoles()){
           roles.append(role.toString());
           roles.append(" ");
       }
       model.addAttribute("thisRole",roles);
       model.addAttribute("thisUser",userServiceImp.findByUseremail(principal.getName()));
        return "user";
    }
}
