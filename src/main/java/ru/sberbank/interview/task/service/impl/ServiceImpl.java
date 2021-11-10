package ru.sberbank.interview.task.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import ru.sberbank.interview.task.controller.dto.res.GetListRes;
import ru.sberbank.interview.task.controller.dto.support.EntityDto;
import ru.sberbank.interview.task.dao.model.EntityDao;
import ru.sberbank.interview.task.dao.repository.EntityRepository;
import ru.sberbank.interview.task.exception.MyException;
import ru.sberbank.interview.task.service.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Component
@Slf4j
public class ServiceImpl implements Service {

    private final EntityRepository entityRepository;

    @Override
    public List<EntityDto> getEntitiesByIds(List<Long> ids) {
        List<EntityDto> entities = entityRepository.findAllByIdIn(ids).stream().map(EntityDao::convertToDto).collect(Collectors.toList());
        List<Long> checkList = entities.stream().map(EntityDto::getId).collect(Collectors.toList());
        if (!checkList.containsAll(ids)) {
            throw new MyException(404, HttpStatus.BAD_REQUEST, "Не все id есть в БД: " + CollectionUtils.subtract(ids, entities.stream()
                    .map(EntityDto::getId)
                    .collect(Collectors.toList())));
        }
        return entities;
    }

    @Override
    public List<EntityDto> getEntitiesByCodeAndSysname(Integer code, String sysName) {
        List<EntityDao> result;
        if (code != null) {
            result = entityRepository.findAllByCode(code);
        } else if (sysName != null) {
            result = entityRepository.findAllBySysName(sysName);
        } else {
            result = StreamSupport.stream(entityRepository.findAll().spliterator(), false).distinct().collect(Collectors.toList());
        }
        return result.stream().map(EntityDao::convertToDto).collect(Collectors.toList());
    }

    @Override
    public GetListRes getList(String sysname) {
        GetListRes res = new GetListRes();
        res.setItems(entityRepository.findAllBySysName(sysname).stream().map(EntityDao::convertToDto).collect(Collectors.toList()));
        res.setUnread(entityRepository.findAllByWatchedDttm());
        if (res.getUnread().equals(0))
            return new GetListRes(new ArrayList<>());
        return res;
    }
}
