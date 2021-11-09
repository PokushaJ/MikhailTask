package ru.sberbank.interview.task.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.health.Health;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.interview.task.controller.dto.res.GetListRes;
import ru.sberbank.interview.task.controller.dto.support.EntityDto;
import ru.sberbank.interview.task.service.Service;

import java.util.List;

@RestController
@RequestMapping("${spring.application.name}/mikhail-task")
@RequiredArgsConstructor
@Slf4j
public class ServiceController {

    private final String JSON = "application/json;charset=UTF-8";
    private final Service service;

    @GetMapping("/live")
    public Health liveness() {
        try {
            return Health.up().withDetail("version", "1.0.0").build();
        } catch (Exception ex) {
            return Health.down(ex).build();
        }
    }

    @GetMapping(value = "/ids", produces = JSON)
    public ResponseEntity<List<EntityDto>> getEntitiesByIds(@RequestHeader("ids") List<Long> ids) {
        return ResponseEntity.ok().body(service.getEntitiesByIds(ids));
    }

    @GetMapping(value = "/code-and-name", produces = JSON)
    public List<EntityDto> getEntitiesByCodeAndSysname(@RequestParam(value = "code", required = false) Integer code,
                                                       @RequestParam(value = "sysname", required = false) String sysName) {
        return service.getEntitiesByCodeAndSysname(code, sysName);
    }

    @GetMapping(value = "/getList/{sysname}", produces = JSON)
    public GetListRes getList(@PathVariable(value = "sysname") String sysName) {
        return service.getList(sysName);
    }
}
