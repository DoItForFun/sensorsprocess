package com.queue.process;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@SpringBootTest
@EnableConfigurationProperties
@EnableAutoConfiguration
class QueueApplicationTests {

    @Test
    void contextLoads() {

    }
}
