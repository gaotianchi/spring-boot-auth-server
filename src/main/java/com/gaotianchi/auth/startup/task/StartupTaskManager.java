package com.gaotianchi.auth.startup.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author gaotianchi
 * @since 2024/12/1 10:54
 **/
@Component
@Slf4j
public class StartupTaskManager {

    private final List<StartupTask> startupTasks;

    public StartupTaskManager(List<StartupTask> startupTasks) {
        this.startupTasks = startupTasks;
    }

    public void executeAllTasks() {
        log.info("Executing startup tasks...");
        for (StartupTask task : startupTasks) {
            try {
                task.execute();
            } catch (Exception e) {
                log.error("Startup task failed: {}", task.getClass().getSimpleName());
                throw new IllegalStateException("Failed to execute startup tasks.", e);
            }
        }
        log.info("All startup tasks completed successfully.");
    }
}
