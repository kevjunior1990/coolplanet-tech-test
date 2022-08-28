package com.example.coolplanetdemo.exception;

public class TaskCalculateAverageException extends RuntimeException {
    public TaskCalculateAverageException(String uid, String message) {
        super("Failed to calculate average time for task with unique identifier " + uid + ": " + message);
    }
}
