package com.queue.process.kafka.service;

import com.queue.process.kafka.config.sensors.SensorsConfig;
import com.queue.process.kafka.entity.SensorsReceiveEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;


public interface SensorsService {
    void setSensorsConfig(SensorsConfig sensorsConfig);
    void setJdbcTemplate(JdbcTemplate jdbcTemplate);
    void send(SensorsReceiveEntity sensorsReceiveEntity);
    void save(SensorsReceiveEntity sensorsReceiveEntity);
}
