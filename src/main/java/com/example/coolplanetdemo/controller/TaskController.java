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
@RequestMapping(path = "/api/v1/task", produces = "application/json")
public class TaskController {

    @Autowired
    private TaskService service;

    @GetMapping
    public ResponseEntity<List<Task>> getTasks() {

        return ResponseEntity.ok(service.getTasks());
    }

    @PostMapping
    public Task saveTask(@RequestBody Task task) {

        return service.saveTask(task);
    }

    @GetMapping("/{uid}/current-average-time")
    public ResponseEntity<Task> getCurrentAverageTime(@PathVariable String uid) {

        return ResponseEntity.ok(
                service.getCurrentAverageTime(uid)
        );
    }
}
