package com.example.coolplanetdemo.controller;

import com.example.coolplanetdemo.entity.Task;
import com.example.coolplanetdemo.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
// @WebMvcTest(TaskController.class)
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
                .id(1)
                .uid("TEST_TASK_01")
                .executionTime(100L)
                .build();

        when(service.getCurrentAverageTime("TEST_TASK_01")).thenReturn(task);

        mockMvc.perform(get("/task/TEST_TASK_01")
                .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uid").value("TEST_TASK_01"))
                .andExpect(jsonPath("$.executionTime").value(100L));

        verify(service, times(1)).getCurrentAverageTime("TEST_TASK_01");
    }

    @Test
    void shouldCreateAccountProfile() throws Exception {

        Task task = Task.builder()
                .id(1)
                .uid("TEST_TASK_01")
                .executionTime(100L)
                .build();

        when(service.saveTaskPerformed(any(Task.class))).thenReturn(task);


        mockMvc.perform(post("/task")
                        .content(objectMapper.writeValueAsString(task))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.executionTime").value(100L))
                .andExpect(jsonPath("$.uid").value("TEST_TASK_01"));

        verify(service, times(1)).saveTaskPerformed(task);
    }
}
