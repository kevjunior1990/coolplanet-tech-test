package com.example.coolplanetdemo.controller;

import com.example.coolplanetdemo.entity.Task;
import com.example.coolplanetdemo.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
class TaskControllerTest {

    @MockBean
    private TaskService service;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldGetAccountProfiles() throws Exception {

        Task task = Task.builder()
                .uid("TEST_TASK_O1")
                .currentAverageTime(BigDecimal.valueOf(100L))
                .build();

        when(service.getCurrentAverageTime("TEST_TASK_O1")).thenReturn(task);

        mockMvc.perform(get("/api/v1/task/TEST_TASK_01").contextPath("/api/v1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uid").value("TEST_TASK_O1"))
                .andExpect(jsonPath("$.executionTime").value(100L));

        verify(service, times(1)).getCurrentAverageTime("TEST_TASK_O1");
    }

    @Test
    void shouldCreateAccountProfile() throws Exception {

        Task task = Task.builder()
                .uid("TEST_TASK_01")
                .executionTime(100L)
                .build();

        when(service.saveTaskPerformed(task)).thenReturn(task);


        mockMvc.perform(post("/api/v1/").contextPath("/api/v1")
                        .content(objectMapper.writeValueAsString(task))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.uid").value("TEST_TASK_01"));

        verify(service, times(1)).saveTaskPerformed(task);
    }
}
