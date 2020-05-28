package com.queue.process.kafka.service.impl;

import com.queue.process.kafka.config.sensors.SensorsConfig;
import com.queue.process.kafka.dao.SensorsDao;
import com.queue.process.kafka.dao.impl.SensorsDaoImpl;
import com.queue.process.kafka.dao.impl.SensorsHiveDaoImpl;
import com.queue.process.kafka.entity.SensorsReceiveEntity;
import com.queue.process.kafka.service.SensorsService;
import com.sensorsdata.analytics.javasdk.SensorsAnalytics;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;


@Slf4j
@Data
@Service
public class SensorsServiceImpl implements SensorsService {
    SensorsConfig sensorsConfig;
    JdbcTemplate jdbcTemplate;
    @Override
    public void send(SensorsReceiveEntity sensorsReceiveEntity) {
        log.info("Report Sensors Start: ");
        String source = sensorsReceiveEntity.getSource();
        try {
            String target = "";
            switch (source){
                case "cece":
                    target = sensorsConfig.getCeceUrl();
                    break;
                case "oversea":
                    target = sensorsConfig.getOverseaUrl();
                    break;
            }
            log.info("Report Sensors target: " + target);
            SensorsDao sensorsDao = new SensorsDaoImpl();
            SensorsAnalytics sensorsAnalytics = new SensorsAnalytics(new SensorsAnalytics.BatchConsumer(
                    target,
                    sensorsConfig.getBulkSize(),
                    sensorsConfig.getCacheSize(),
                    sensorsConfig.getException()
            ));
            sensorsDao.setSensorsAnalytics(sensorsAnalytics);
            sensorsDao.send(sensorsReceiveEntity);
        }catch (Exception e){
            log.info("Report Sensors Error: " + e.getMessage());
        }
        log.info("Report Sensors End; ");
    }
    @Override
    public void save(SensorsReceiveEntity sensorsReceiveEntity){
        try{
            String source = sensorsReceiveEntity.getSource();
            log.info("Synchronize Report Data To Hive Start: ");
            SensorsHiveDaoImpl sensorsHiveDao = new SensorsHiveDaoImpl();
            sensorsHiveDao.setTable(source + "_data");
            sensorsHiveDao.setJdbcTemplate(this.jdbcTemplate);
            sensorsHiveDao.insert(sensorsReceiveEntity);
            log.info("Synchronize Report Data To Hive End; ");
        }catch (Exception e){
            log.info("Synchronize Report Data To Hive Error: " + e.getMessage());
        }
    }
}
