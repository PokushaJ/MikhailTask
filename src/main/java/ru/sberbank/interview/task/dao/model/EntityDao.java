package ru.sberbank.interview.task.dao.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Test_task")
@Data
public class EntityDao {

    private static final long serialVersionUID = 6274056306765590733L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK
    @Column(name = "CODE")
    private Integer code;
    @Column(name = "SYSNAME")
    private String sysName;
    @Column(name = "WATCHEDDTTM")
    private Date watchedDttm;
    @Column(name = "description")
    private String description;
    @Column(name = "DATA")
    private String data;

}
