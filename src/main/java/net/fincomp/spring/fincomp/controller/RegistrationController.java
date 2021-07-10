package net.fincomp.spring.fincomp.controller;

import net.fincomp.spring.fincomp.model.Role;
import net.fincomp.spring.fincomp.model.User;
import net.fincomp.spring.fincomp.repository.UserRepository;
import net.fincomp.spring.fincomp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@Valid User user, BindingResult bindingResult, Model model) {
        if (user.getPassword() != null && !user.getPassword().equals(user.getPassword2())){
            model.addAttribute("passwordError", "Пароли не совпадают!");
        }

        if (bindingResult.hasErrors()) {
            Map<String,String> errors = ControllerUtils.getErrors(bindingResult);
            return "registration";
        }

        if (!userService.addUser(user)) {
            model.addAttribute("usernameError", "Пользователь существует!");
            return "registration";
        }

        return "redirect:/login";
    }
}
