package com.example.coolplanetdemo.service;

import com.example.coolplanetdemo.entity.ExecutionTime;
import com.example.coolplanetdemo.entity.Task;
import com.example.coolplanetdemo.exception.TaskCalculateCurrentAverageTimeException;
import com.example.coolplanetdemo.exception.TaskNotFoundException;
import com.example.coolplanetdemo.exception.TaskSaveException;
import com.example.coolplanetdemo.repository.ExecutionTimeRepository;
import com.example.coolplanetdemo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ExecutionTimeRepository executionTimeRepository;

    @Override
    public List<Task> getTasks() {

        return taskRepository.findAll();
    }

    @Override
    public List<ExecutionTime> getExecutionTimes() {

        return executionTimeRepository.findAll();
    }

    @Override
    public Task saveTask(Task task) {

        try {

            Long executionTimeValue = task.getExecutionTime();

            Task savedTask = taskRepository.findByUid(task.getUid())
                    .orElseGet(() -> taskRepository.save(task));

            ExecutionTime executionTime = ExecutionTime
                    .builder()
                    .value(executionTimeValue)
                    .task(savedTask)
                    .build();

            executionTimeRepository.save(executionTime);

            return savedTask;

        } catch (Exception ex) {

            throw new TaskSaveException(task.getUid(), ex.getMessage());
        }
    }

    @Override
    public Task getCurrentAverageTime(String uid) {

        try {

            Task task = taskRepository.findByUid(uid)
                    .orElseThrow(() -> new TaskNotFoundException(uid));

            List<ExecutionTime> executionTimes = executionTimeRepository.findAllByTask(task);

            BigDecimal totalCalculationTime = BigDecimal.valueOf(
                    executionTimes.stream()
                            .mapToLong(ExecutionTime::getValue)
                            .sum()
            );

            BigDecimal totalTaskCount = BigDecimal.valueOf(
                    executionTimes.size()
            );

            return Task.builder()
                    .uid(uid)
                    .currentAverageTime(
                            calculateCurrentAverageTime(
                                    totalCalculationTime,
                                    totalTaskCount
                            )
                    )
                    .build();

        } catch (Exception ex) {

            throw new TaskCalculateCurrentAverageTimeException(uid, ex.getMessage());
        }
    }
}
