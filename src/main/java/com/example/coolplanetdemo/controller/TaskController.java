package com.example.coolplanetdemo.controller;


import com.example.coolplanetdemo.entity.Task;
import com.example.coolplanetdemo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1", produces = "application/json")
public class TaskController {

    @Autowired
    private TaskService service;

    @GetMapping("tasks")
    public ResponseEntity<List<Task>> getTasks() {

        return ResponseEntity.ok(service.getTasks());
    }

    @PostMapping("/tasks")
    public Task addTask(@RequestBody Task task) {

        return service.saveTaskPerformed(task);
    }

    @GetMapping("/task/{uid}")
    public ResponseEntity<Task> getCurrentTaskExecution(@PathVariable String uid) {

        return ResponseEntity.ok(
                service.getCurrentAverageTime(uid)
        );
    }
}
