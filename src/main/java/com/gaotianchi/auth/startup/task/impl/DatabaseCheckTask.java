package com.gaotianchi.auth.startup.task.impl;

import com.gaotianchi.auth.startup.task.StartupTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * @author gaotianchi
 * @since 2024/12/1 10:51
 **/
@Component
@Slf4j
public class DatabaseCheckTask implements StartupTask {
    private final DataSource dataSource;

    public DatabaseCheckTask(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void execute() throws Exception {
        log.info("Checking database connection...");
        try (Connection connection = dataSource.getConnection()) {
            if (connection.isValid(1000)) {
                log.info("Database connection is valid.");
            } else {
                throw new IllegalStateException("Database connection is invalid.");
            }
        }
    }
}
