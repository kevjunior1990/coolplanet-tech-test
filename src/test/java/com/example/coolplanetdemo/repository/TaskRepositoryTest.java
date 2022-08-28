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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @BeforeEach
    void setUp() {
        taskRepository.saveAll(
                Arrays.asList(
                        Task.builder()
                                .uid("TEST_UID_1")
                                .executionTime(100L)
                                .build(),
                        Task.builder()
                                .uid("TEST_UID_1")
                                .executionTime(100L)
                                .build(),
                        Task.builder()
                                .uid("TEST_UID_1")
                                .executionTime(100L)
                                .build(),
                        Task.builder()
                                .uid("TEST_UID_2")
                                .executionTime(100L)
                                .build(),
                        Task.builder()
                                .uid("TEST_UID_2")
                                .executionTime(100L)
                                .build(),
                        Task.builder()
                                .uid("TEST_UID_3")
                                .executionTime(100L)
                                .build(),
                        Task.builder()
                                .uid("TEST_UID_4")
                                .executionTime(100L)
                                .build(),
                        Task.builder()
                                .uid("TEST_UID_5")
                                .executionTime(100L)
                                .build(),
                        Task.builder()
                                .uid("TEST_UID_6")
                                .executionTime(100L)
                                .build()
                )
        );
    }

    @AfterEach
    void tearDown() {
        taskRepository.deleteAll();
    }

    @Test
    public void testFindAllByUid() {

        List<Task> existingStats = taskRepository.findAllByUid("TEST_UID_1");

        List<Task> nonExistentStats = taskRepository.findAllByUid("TEST_UID_7");

        assertThat(existingStats, is(notNullValue()));

        assertThat(nonExistentStats, is(Collections.emptyList()));
    }
}
