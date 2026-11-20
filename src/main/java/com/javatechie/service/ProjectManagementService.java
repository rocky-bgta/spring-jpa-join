package com.javatechie.service;

import com.javatechie.bo.ProjectEngineerResponseBO;
import com.javatechie.entity.Engineer;
import com.javatechie.entity.Project;
import com.javatechie.repository.EngineerRepository;
import com.javatechie.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectManagementService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private EngineerRepository engineerRepository;


    public Project saveProject(Project project){
        return projectRepository.save(project);
    }

    public List<Project> getProjects(){
        return projectRepository.findAll();
    }

    public List<Engineer> getEngineers(){
        return engineerRepository.findAll();
    }

    public String deleteProject(int projectId){
        try {
            projectRepository.deleteById(projectId);
        } catch (EmptyResultDataAccessException exception) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Project " + projectId + " not found",
                    exception
            );
        }
        return "project " + projectId + " deleted !!!";
    }


    public List<ProjectEngineerResponseBO> getProjectSpecificInfoSQL(){
        return projectRepository.getProjectSpecificInfoWithSQL();
    }

    public List<ProjectEngineerResponseBO> getProjectSpecificInfoJPQL(){
        return projectRepository.getProjectSpecificInfoWithJPQL();
    }




}
