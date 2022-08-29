package com.example.coolplanetdemo.service;

import com.example.coolplanetdemo.ApplicationTestConfiguration;
import com.example.coolplanetdemo.entity.Task;
import com.example.coolplanetdemo.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.when;


@SpringBootTest(classes = ApplicationTestConfiguration.class)
public class TaskServiceTest {

    @MockBean
    private TaskServiceImpl service;

    @Mock
    private TaskRepository repository;

    @Test
    public void test1() {

        when(repository.findAllByUid("TEST_UID")).thenReturn(Collections.emptyList());

        List<Task> result = service.getTasks();

        assertThat(result, is(notNullValue()));
    }
}
