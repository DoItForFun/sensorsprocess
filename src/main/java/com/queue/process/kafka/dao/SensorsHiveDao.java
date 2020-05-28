package com.queue.process.kafka.dao;

import com.queue.process.kafka.entity.SensorsReceiveEntity;
import org.springframework.scheduling.annotation.Async;


public interface SensorsHiveDao {
    void setTable(String table);
    void insert(SensorsReceiveEntity sensorsReceiveEntity) throws Exception;
}
