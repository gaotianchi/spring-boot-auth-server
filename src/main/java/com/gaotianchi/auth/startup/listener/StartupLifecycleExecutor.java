package com.gaotianchi.auth.startup.listener;

import com.gaotianchi.auth.startup.task.StartupTaskManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

/**
 * @author gaotianchi
 * @since 2024/12/1 10:55
 **/
@Component
@Slf4j
public class StartupLifecycleExecutor implements SmartLifecycle {

    private final StartupTaskManager taskManager;
    private boolean isRunning = false;

    public StartupLifecycleExecutor(StartupTaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @Override
    public void start() {
        log.info("Starting startup listener");
        taskManager.executeAllTasks();
        isRunning = true;
    }

    @Override
    public void stop() {
        log.info("Stopping tasks if needed...");
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }
}