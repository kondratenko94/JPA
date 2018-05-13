package net.kondratenko.controller;

import net.kondratenko.dto.ProjectDto;
import net.kondratenko.model.Project;
import net.kondratenko.model.User;
import net.kondratenko.model.UserRole;
import net.kondratenko.security.CurrentAuthentication;
import net.kondratenko.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ProjectController {

    @Autowired
    CurrentAuthentication currentAuthentication;

    @Autowired
    ProjectService projectService;

    @ResponseBody
    @GetMapping("/api/project/")
    public List<ProjectDto> getProjects() {

        UserRole role = UserRole.valueOf( this.currentAuthentication.getRoleName() );
        User user = this.currentAuthentication.getCurrentUser();

        switch (role) {

            case Developer: {
                return null;
            }

            case Manager: {

                return user.getOwnProjects().stream()
                        .map(ProjectDto::new)
                        .collect(Collectors.toList());
            }

            default: return null;
        }
    }

    @ResponseBody
    @PutMapping("/api/project/")
    public Long saveProject(@RequestBody @Valid Project project) {

        UserRole role = UserRole.valueOf( this.currentAuthentication.getRoleName() );

        if (role == UserRole.Manager) {
            User user = this.currentAuthentication.getCurrentUser();

            project.setAuthor(user);
            this.projectService.saveProject(project);

            return project.getId();
        }


        return null;
    }

    @GetMapping("/project")
    public String projects() {
        return "project";
    }

    @GetMapping("/")
    public String mainPage() {
        return "redirect:/project/";
    }
}