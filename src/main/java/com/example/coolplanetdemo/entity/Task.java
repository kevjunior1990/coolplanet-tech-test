package com.example.coolplanetdemo.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.With;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;

import static javax.persistence.GenerationType.SEQUENCE;

@Data
@SuperBuilder
@With
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "TASK")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Task extends DateEntity {

    @Id
    @Column(name = "ID", nullable = false)
    @SequenceGenerator(name = "TASK_SEQ", sequenceName = "TASK_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "TASK_SEQ")
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(name = "UID", nullable = false)
    private String uid;

    @Column(name = "EXECUTION_TIME", nullable = false)
    private Long executionTime;

    @Transient
    private BigDecimal currentAverageTime;
}
