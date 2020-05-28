package com.queue.process.kafka.dao;

import com.queue.process.kafka.entity.SensorsReceiveEntity;
import com.sensorsdata.analytics.javasdk.SensorsAnalytics;
import com.sensorsdata.analytics.javasdk.exceptions.InvalidArgumentException;

public interface SensorsDao {
    void setSensorsAnalytics(SensorsAnalytics sensorsAnalytics);
    void send(SensorsReceiveEntity sensorsReceiveEntity) throws InvalidArgumentException;
}
