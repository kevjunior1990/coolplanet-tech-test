package com.example.coolplanetdemo.service;

import com.example.coolplanetdemo.entity.ExecutionTime;
import com.example.coolplanetdemo.entity.Task;
import com.example.coolplanetdemo.exception.TaskCalculateCurrentAverageTimeException;
import com.example.coolplanetdemo.exception.TaskNotFoundException;
import com.example.coolplanetdemo.exception.TaskSaveException;
import com.example.coolplanetdemo.repository.ExecutionTimeRepository;
import com.example.coolplanetdemo.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
@Slf4j
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

            log.info("Attempting to save task with unique identifier: " + task.getUid());

            Task savedTask = taskRepository.findByUid(task.getUid())
                    .orElseGet(() -> taskRepository.save(task));

            log.info("Task is saved");

            log.info("Saving execution time for task with uid: " + task.getUid());

            ExecutionTime executionTime = ExecutionTime
                    .builder()
                    .value(task.getExecutionTime())
                    .task(savedTask)
                    .build();

            executionTimeRepository.save(executionTime);

            log.info("Execution time saved for task with uid: " + task.getUid());

            return savedTask;

        } catch (Exception ex) {

            throw new TaskSaveException(task.getUid(), ex.getMessage());
        }
    }

    @Override
    public Task getCurrentAverageTime(String uid) {

        log.info("Retrieving task with uid: " + uid);

        Task task = taskRepository.findByUid(uid)
                .orElseThrow(() -> new TaskNotFoundException(uid));

        try {

            List<ExecutionTime> executionTimes = executionTimeRepository.findAllByTask(task);

            BigDecimal totalExecutionTimeCalculated = BigDecimal.valueOf(
                    executionTimes.stream()
                            .mapToLong(ExecutionTime::getValue)
                            .sum()
            );

            BigDecimal totalExecutionTimesCount = BigDecimal.valueOf(
                    executionTimes.size()
            );

            return Task.builder()
                    .uid(uid)
                    .currentAverageTime(
                            calculateCurrentAverageTime(
                                    totalExecutionTimeCalculated,
                                    totalExecutionTimesCount
                            )
                    )
                    .build();

        } catch (Exception ex) {

            throw new TaskCalculateCurrentAverageTimeException(uid, ex.getMessage());
        }
    }
}
