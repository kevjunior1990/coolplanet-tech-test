package com.example.coolplanetdemo.controller;


import com.example.coolplanetdemo.entity.Task;
import com.example.coolplanetdemo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/task")
public class TaskController {

    @Autowired
    private TaskService service;

    @GetMapping
    public List<Task> getTasks() {

        return service.getTasks();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task addTask(@RequestBody Task task) {

        return service.saveTaskPerformed(task);
    }

    @GetMapping("/{uid}")
    public Task getCurrentTaskExecution(@PathVariable String uid) {

        return service.getCurrentAverageTime(uid);
    }
}
