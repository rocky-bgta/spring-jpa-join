package com.javatechie.service;

import com.javatechie.repository.EngineerRepository;
import com.javatechie.repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ProjectManagementServiceTest {

    private ProjectRepository projectRepository;
    private ProjectManagementService service;

    @BeforeEach
    void setUp() {
        projectRepository = mock(ProjectRepository.class);
        service = new ProjectManagementService();
        ReflectionTestUtils.setField(service, "projectRepository", projectRepository);
        ReflectionTestUtils.setField(service, "engineerRepository", mock(EngineerRepository.class));
    }

    @Test
    void deleteProjectReturnsConfirmationWhenRepositoryDeletesProject() {
        String result = service.deleteProject(42);

        assertEquals("project 42 deleted !!!", result);
        verify(projectRepository).deleteById(42);
    }

    @Test
    void deleteProjectReturnsNotFoundWhenProjectDoesNotExist() {
        doThrow(new EmptyResultDataAccessException(1))
                .when(projectRepository).deleteById(42);

        ResponseStatusException exception = assertThrows(
                ResponseStatusException.class,
                () -> service.deleteProject(42)
        );

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        verify(projectRepository).deleteById(42);
    }
}
