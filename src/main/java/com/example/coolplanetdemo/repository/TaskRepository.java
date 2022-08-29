package com.example.coolplanetdemo.repository;



import com.example.coolplanetdemo.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<Task> findByUid(String uid);
}
