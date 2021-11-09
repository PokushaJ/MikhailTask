package ru.sberbank.interview.task.controller.dto.res;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.sberbank.interview.task.controller.dto.support.EntityDto;

import java.util.List;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class GetListRes {

    private List<EntityDto> items;
    private Integer unread;

    public GetListRes(List<EntityDto> items) {
        this.items = items;
    }
}
