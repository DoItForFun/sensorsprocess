package com.queue.process.kafka.dao.impl;

import com.alibaba.fastjson.JSONObject;
import com.queue.process.kafka.dao.SensorsDao;
import com.queue.process.kafka.entity.SensorsReceiveEntity;
import com.sensorsdata.analytics.javasdk.SensorsAnalytics;
import com.sensorsdata.analytics.javasdk.exceptions.InvalidArgumentException;
import lombok.Data;
import org.springframework.scheduling.annotation.Async;

@Data
public class SensorsDaoImpl implements SensorsDao {
    SensorsAnalytics sensorsAnalytics;
    @Override
    @Async
    public void send(SensorsReceiveEntity sensorsReceiveEntity) throws InvalidArgumentException {
        String distinctId = sensorsReceiveEntity.getDistinctId();
        String event = sensorsReceiveEntity.getEvent();
        Boolean login = sensorsReceiveEntity.getLogin();
        JSONObject properties = sensorsReceiveEntity.getProperties();
        this.sensorsAnalytics.track(distinctId,login,event,properties);
    }
}
