package com.queue.process.kafka.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

@Data
public class SensorsSendEntity {
    private String distinctId;
    private Boolean login;
    private JSONObject properties;
    private String event;
}
