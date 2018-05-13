package net.kondratenko.service;

import net.kondratenko.model.Project;
import net.kondratenko.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository roleRepository;

    @Override
    public void saveProject(Project project) {
        this.roleRepository.save(project);
    }
}