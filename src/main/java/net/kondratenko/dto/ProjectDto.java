package net.kondratenko.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.kondratenko.model.Project;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class ProjectDto implements Serializable{

    private long id;

    private String name;

    private String description;

    private String author_firstName;

    private String author_lastName;

    private String author_email;

    public ProjectDto() {}

    public ProjectDto(Project project) {
        this.id = project.getId();
        this.name = project.getName();
        this.description = project.getDescription();
        this.author_firstName = project.getAuthor().getFirstName();
        this.author_lastName = project.getAuthor().getLastName();
        this.author_email = project.getAuthor().getEmail();
    }

}