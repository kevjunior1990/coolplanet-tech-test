package com.example.coolplanetdemo.exception;

public class TaskCalculateCurrentAverageTimeException extends RuntimeException {
    public TaskCalculateCurrentAverageTimeException(String uid, String message) {
        super("Failed to calculate current average time for task with unique identifier " + uid + ": " + message);
    }
}
