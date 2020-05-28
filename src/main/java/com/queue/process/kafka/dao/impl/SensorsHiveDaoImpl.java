package com.queue.process.kafka.dao.impl;

import com.queue.process.kafka.dao.SensorsHiveDao;
import com.queue.process.kafka.entity.SensorsReceiveEntity;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.hive.jdbc.HiveDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.Statement;

@Data
@Slf4j
public class SensorsHiveDaoImpl implements SensorsHiveDao {
    String table;
    JdbcTemplate jdbcTemplate;
    @Override
    public void insert(SensorsReceiveEntity sensorsReceiveEntity) throws Exception {
//        String sql = "insert into Table " + this.table + "(distinct_id,event,login,properties) values("
//                + "'" +  sensorsReceiveEntity.getDistinctId() + "',"
//                + "'" + sensorsReceiveEntity.getEvent() + "',"
//                + sensorsReceiveEntity.getLogin() + ","
//                + "'" + sensorsReceiveEntity.getProperties().toString() + "')";
        String sql = "insert into " + this.table + " select "
                + "row_number() over() as id,"
                + "'" + sensorsReceiveEntity.getDistinctId() + "' distinct_id,"
                + "'" + sensorsReceiveEntity.getEvent() + "' event,"
                + sensorsReceiveEntity.getLogin() + " login,"
                + "'" + sensorsReceiveEntity.getProperties() + "' properties";
        jdbcTemplate.execute(sql);

    }
}
