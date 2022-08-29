package com.example.coolplanetdemo.service;

import com.example.coolplanetdemo.entity.ExecutionTime;
import com.example.coolplanetdemo.entity.Task;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public interface TaskService {

    List<Task> getTasks();

    List<ExecutionTime> getExecutionTimes();

    Task saveTask(Task task);

    Task getCurrentAverageTime(String uid);

    default BigDecimal calculateCurrentAverageTime(BigDecimal totalExecutionTime, BigDecimal totalTasks) {

        return totalExecutionTime.divide(totalTasks, 2, RoundingMode.HALF_EVEN);
    }
}
