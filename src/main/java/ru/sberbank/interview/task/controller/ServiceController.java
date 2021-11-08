package ru.sberbank.interview.task.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.health.Health;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${spring.application.name}/mikhail-task")
@RequiredArgsConstructor
@Slf4j
public class ServiceController {

    @GetMapping("/live")
    public Health liveness() {
        try {
            return Health.up().withDetail("version", "1.0.0").build();
        } catch (Exception ex) {
            return Health.down(ex).build();
        }
    }
}
