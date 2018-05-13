package net.kondratenko.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "firstName")
    @Size(max = 20, message = "{Validator.long}")
    private String firstName;

    @Column(name = "lastName")
    @Size(max = 20, message = "{Validator.long}")
    private String lastName;

    @Column(name = "password")
    @Size(min = 8, max = 80, message = "{Validator.size}")
    private String password;

    @Column(name = "email")
    @Email
    @NotEmpty(message = "{Validator.empty}")
    private String email;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "activation_token")
    private String activationToken;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    @Transient
    private String selectedRoleName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
    @OrderBy("id DESC")
    List<Project> ownProjects;

}