package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;

import ru.kata.spring.boot_security.demo.services.UserService;


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
    public String showUser(@AuthenticationPrincipal UserDetails userDetails, Model model) {

        model.addAttribute("user", userService.findByUserName(userDetails.getUsername()));
        model.addAttribute("users", userService.findAll());
        return "admin";
    }

    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable(value = "id", required = false) int id, Model model) {
        User user = userService.getById(id);
        if (user == null) {
            return "redirect:/admin";
        }
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.findAllRoles());
        return "edit";
    }

    @PatchMapping("/{id}/update")
    public String update(@PathVariable(value = "id", required = false) int id,
                         @ModelAttribute("User") User user,
                         @RequestParam(value = "rol", required = false) String rol,
                         @RequestParam(value = "pass", required = false) String pass) {
        user.setRoles(roleService.getByName(rol));
        user.setPassword(pass);
        userService.update(user, id);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable(value = "id", required = false) int id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.findAllRoles());
        return "create";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute("user") User user,
                             @RequestParam(value = "nameRole", required = false) String nameRole) {

        user.setRoles(roleService.getByName(nameRole));
        userService.save(user);
        return "redirect:/admin";
    }

}
