package com.queue.process.kafka.config.sensors;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "sensors")
public class SensorsConfig {
     public String ceceUrl;
     private String overseaUrl;
     private int bulkSize;
     private int cacheSize;
     private Boolean exception;
}
