package net.kondratenko.security;

import net.kondratenko.model.User;
import net.kondratenko.model.UserRoleName;
import net.kondratenko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class CurrentAuthentication {

    @Autowired
    private UserService userService;

    public String getCurrentEmail() {

        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            String email;
            if (principal instanceof UserDetails) {
                email = ((UserDetails)principal).getUsername();

            } else {
                email = principal.toString();
            }

            return !email.equals("anonymousUser") ? email : null;
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public String getRoleName() {

        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            String roleName;
            if (principal instanceof UserDetails) {
                roleName = ((CustomUser)principal).getRole();

            } else {
                roleName = principal.toString();
            }

            return !roleName.equals("anonymousUser") ? roleName : null;
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public User getCurrentUser() {
        String email = getCurrentEmail();
        return (email != null) ? this.userService.getByEmail(email) : null;
    }
}
