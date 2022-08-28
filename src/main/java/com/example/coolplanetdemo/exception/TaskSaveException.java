package com.example.coolplanetdemo.exception;

public class TaskSaveException extends RuntimeException {
    public TaskSaveException(String uid, String message) {
        super("Task with uid " + uid + " failed to save: " + message);
    }
}
