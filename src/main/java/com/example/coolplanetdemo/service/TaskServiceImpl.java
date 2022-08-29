package com.example.coolplanetdemo.service;

import com.example.coolplanetdemo.entity.Task;
import com.example.coolplanetdemo.exception.TaskCalculateCurrentAverageTimeException;
import com.example.coolplanetdemo.exception.TaskSaveException;
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

    @Override
    public List<Task> getTasks() {

        return taskRepository.findAll();
    }

    @Override
    public Task saveTask(Task task) {

        try {

            return taskRepository.save(task);

        } catch (Exception ex) {

            throw new TaskSaveException(task.getUid(), ex.getMessage());
        }
    }

    @Override
    public Task getCurrentAverageTime(String uid) {

        try {

            List<Task> tasks = taskRepository.findAllByUid(uid);

            BigDecimal totalCalculationTime = BigDecimal.valueOf(
                    tasks.stream()
                            .mapToLong(Task::getExecutionTime)
                            .sum()
            );

            BigDecimal totalTaskCount = BigDecimal.valueOf(
                    tasks.size()
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
