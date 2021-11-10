package ru.sberbank.interview.task.dao.model;

import lombok.Data;
import ru.sberbank.interview.task.controller.dto.support.EntityDto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Test_task")
@Data
public class EntityDao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id; // PK
    @Column(name = "CODE")
    private Integer code;
    @Column(name = "SYSNAME")
    private String sysName;
    @Column(name = "WATCHEDDTTM")
    private Date watchedDttm;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "DATA")
    private String data;

    public EntityDto convertToDto() {
        EntityDto dto = new EntityDto();
        dto.setId(this.id);
        dto.setCode(this.code);
        dto.setSysname(this.sysName);
        dto.setWatchedDttm(this.watchedDttm);
        dto.setDescription(this.description);
        dto.setData(this.data);
        return dto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityDao entityDao = (EntityDao) o;
        return code.equals(entityDao.code) && sysName.equals(entityDao.sysName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, sysName);
    }
}
