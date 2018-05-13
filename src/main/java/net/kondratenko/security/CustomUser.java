package net.kondratenko.security;

import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUser extends User {
    private static final long serialVersionUID = -3531439484732724601L;

    private final String firstName;

    private final String lastName;

    private final String role;

    private final String email;

    private final int id;

    public CustomUser(String username, String password, boolean enabled,
                    boolean accountNonExpired, boolean credentialsNonExpired,
                    boolean accountNonLocked,
                    Collection authorities,
                    String firstName, String lastName, String role, int id) {

        super(username, password, enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked, authorities);

        this.email = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.id = id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public int getId() {
        return id;
    }
}