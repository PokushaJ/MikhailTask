package ru.sberbank.interview.task.controller.dto.res;

import lombok.Setter;
import ru.sberbank.interview.task.controller.dto.support.Entity;

import java.util.List;

@Setter
public class GetListRes {

    private List<Entity> items;
    private Integer unread;

}
