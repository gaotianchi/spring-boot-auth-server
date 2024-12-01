package com.gaotianchi.auth.startup.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

/**
 * @author gaotianchi
 * @since 2024/12/1 11:00
 **/
@Data
@Configuration
@ConfigurationProperties("startup")
public class StartupConfig {
    private boolean resetDatabase = true;
    private boolean loadMockData = false;
    private Path path;

    @Data
    public static class Path {
        private Resource dropSchemaSql;
        private Resource createSchemaSql;
        private Resource initialDataSql;
        private Resource loadMockDataSql;
    }
}
