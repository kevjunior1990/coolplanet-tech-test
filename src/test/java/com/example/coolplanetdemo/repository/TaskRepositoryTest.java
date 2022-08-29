package com.example.coolplanetdemo.repository;

import com.example.coolplanetdemo.entity.Task;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        taskRepository.deleteAll();
    }

    @Test
    public void testFindByUid() {

        Optional<Task> existingTask = taskRepository.findByUid("TEST_UID_1");

        Optional<Task> nonExistentTask = taskRepository.findByUid("TEST_UID_7");

        assertThat(existingTask, is(not(equalTo(Optional.empty()))));

        assertThat(nonExistentTask, is(equalTo(Optional.empty())));
    }
}
