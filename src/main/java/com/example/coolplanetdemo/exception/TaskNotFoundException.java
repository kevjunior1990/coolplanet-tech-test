package com.example.coolplanetdemo.exception;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(String uid) {
        super("Task not found with uid: " + uid);
    }
}
