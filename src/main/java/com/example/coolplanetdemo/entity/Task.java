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
public class Task {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = SEQUENCE, generator = "TASK_ID")
    @SequenceGenerator(name = "CAMPAIGN_ID", sequenceName = "ta_ID_SEQ", allocationSize = 1)
    private Integer id;

    @Column(name = "UID")
    private String uid;

    @Column(name = "EXECUTION_TIME")
    private Long executionTime;

    @Transient
    private BigDecimal currentAverageTime;
}
