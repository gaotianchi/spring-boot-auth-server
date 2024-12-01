package com.gaotianchi.auth.startup.task.impl;

import com.gaotianchi.auth.startup.config.StartupConfig;
import com.gaotianchi.auth.startup.task.StartupTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;


/**
 * @author gaotianchi
 * @since 2024/12/1 10:52
 **/
@Component
@Slf4j
public class DataInitializationTask implements StartupTask {

    private final StartupConfig startupConfig;
    private final DataSource dataSource;

    public DataInitializationTask(StartupConfig startupConfig, DataSource dataSource) {
        this.startupConfig = startupConfig;
        this.dataSource = dataSource;
    }

    @Override
    public void execute() throws Exception {
        log.info("DataInitializationTask start");
        try (Connection connection = dataSource.getConnection()) {
            // 如果配置为清空数据库
            if (startupConfig.isResetDatabase()) {
                log.info("Resetting database: dropping and recreating schema");
                ScriptUtils.executeSqlScript(connection, startupConfig.getPath().getDropSchemaSql());
                ScriptUtils.executeSqlScript(connection, startupConfig.getPath().getCreateSchemaSql());
            }

            // 初始化必要数据
            log.info("Initializing essential data");
            ScriptUtils.executeSqlScript(connection, startupConfig.getPath().getInitialDataSql());

            // 如果配置为加载 mock 测试数据
            if (startupConfig.isLoadMockData()) {
                log.info("Loading mock data");
                ScriptUtils.executeSqlScript(connection, startupConfig.getPath().getLoadMockDataSql());
            }
        } catch (Exception e) {
            log.error("Error during database initialization", e);
            throw e;
        }
        log.info("DataInitializationTask completed");
    }
}
