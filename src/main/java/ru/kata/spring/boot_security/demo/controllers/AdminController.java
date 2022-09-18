package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;

import ru.kata.spring.boot_security.demo.services.UserService;

import javax.validation.Valid;
import java.security.Principal;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;

    private RoleService roleService;


    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String showUser(Principal principal, Model model) {
        StringBuilder roles = new StringBuilder();
        for (Role role : userService.findByUseremail(principal.getName()).getRoles()) {
            roles.append(role.toString());
            roles.append(" ");
        }
        model.addAttribute("thisRole", roles);
        model.addAttribute("newUser", new User());
        model.addAttribute("this_user", userService.findByUseremail(principal.getName()));
        model.addAttribute("roles", roleService.findAllRoles());
        model.addAttribute("users", userService.findAll());
        return "admin";
    }

    @PatchMapping("/edit/{id}")
    public String update(@PathVariable int id,
                         @ModelAttribute @Valid User user,
                         @RequestParam(value = "editRoles", required = false) String[] role) {
        user.setRoles(roleService.getSetOfRoles(role));
       userService.update(user);

        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@ModelAttribute("user") User user,
                         @PathVariable(value = "id", required = false) int id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }

    @PostMapping("/create")
    String createUser(@ModelAttribute("user") User user,
                      @RequestParam(value = "nameRoles") String [] role) {
       user.setRoles(roleService.getSetOfRoles(role));
       userService.save(user);

        return "redirect:/admin";
    }

}
