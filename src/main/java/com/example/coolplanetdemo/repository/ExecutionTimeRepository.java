package com.example.coolplanetdemo.repository;

import com.example.coolplanetdemo.entity.ExecutionTime;
import com.example.coolplanetdemo.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExecutionTimeRepository extends JpaRepository<ExecutionTime, Long> {

    List<ExecutionTime> findAllByTask(Task task);
}
