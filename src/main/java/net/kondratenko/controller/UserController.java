package net.kondratenko.controller;

import net.kondratenko.model.User;
import net.kondratenko.service.EmailService;
import net.kondratenko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.UUID;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    EmailService emailService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration(ModelMap model) {

        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping("/registration")
    public String registerNewUser(@ModelAttribute("user") @Valid User user,
                                  BindingResult result,
                                  final HttpServletRequest request,
                                  ModelMap model) {

        if (!result.hasErrors()) {

            String email = user.getEmail();
            if ( this.userService.emailExists(email) ) {
                result.rejectValue("email", "Validator.unique.email");

            } else {

                String token = UUID.randomUUID().toString();
                user.setActivationToken(token);

                this.userService.saveUser(user);

                String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
                String msgBody = appUrl + "/activation/" + token;

                this.emailService.sendEmail(user.getEmail(), "Активация аккаунта : ", msgBody);

                return "/registration";
            }

        }

        return "/registration";
    }

    @GetMapping("/activation/{token}")
    public String activation(@PathVariable("token") String token, ModelMap model) {

        boolean success = false;

        if (!StringUtils.isEmpty(token)) {
            success = this.userService.activateUser(token);
        }

        model.addAttribute("success", success);

        return "activation_page";
    }

}