package com.queue.process.kafka.controller;

import com.alibaba.fastjson.JSON;
import com.queue.process.kafka.config.sensors.SensorsConfig;
import com.queue.process.kafka.entity.SensorsReceiveEntity;
import com.queue.process.kafka.service.SensorsService;
import com.queue.process.kafka.service.impl.SensorsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;


@Component
@Slf4j
public class SensorsController {
    @Resource
    SensorsConfig sensorsConfig;
    @Resource
    @Qualifier("hiveDruidTemplate")
    JdbcTemplate jdbcTemplate;
    @KafkaListener(topics = {"cece_sensors","oversea_sensors"})
    @Async
    public void process(String message) {
        SensorsReceiveEntity sensorsReceiveEntity = JSON.parseObject(message , SensorsReceiveEntity.class);
        SensorsService service = new SensorsServiceImpl();
        service.setSensorsConfig(sensorsConfig);
        service.setJdbcTemplate(jdbcTemplate);
        service.send(sensorsReceiveEntity);
        service.save(sensorsReceiveEntity);
    }
}
