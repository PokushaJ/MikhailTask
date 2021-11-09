package ru.sberbank.interview.task.dao.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.sberbank.interview.task.dao.model.EntityDao;

import java.util.List;

@Repository
public interface EntityRepository extends CrudRepository<EntityDao, Long> {
    List<EntityDao> findAllByIdIn(List<Long> ids);

    List<EntityDao> findAllByCode(Integer code);

    List<EntityDao> findAllBySysName(String sysName);

    @Query(value = "select count(*) from test_task t where t.WATCHEDDTTM is null ", nativeQuery = true)
    Integer findAllByWatchedDttm();

}
