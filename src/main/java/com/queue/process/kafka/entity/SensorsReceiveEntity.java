package com.queue.process.kafka.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SensorsReceiveEntity {

    @NotBlank
    @NotNull
    private String source;
    @NotBlank
    @NotNull
    private String distinctId;
    private Boolean login;
    @NotBlank
    @NotNull
    private JSONObject properties;
    @NotNull
    @NotBlank
    private String event;
}
