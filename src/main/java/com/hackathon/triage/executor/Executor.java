package com.hackathon.triage.executor;

import com.hackathon.triage.scheduler.IScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:arpit.srivastava@navis.com">Arpit Srivastava</a>
 */
@Component
public class Executor {

//    private ScheduledExecutorService executor;

    private ExecutorService executor;

    @Autowired
    private List<IScheduler> schedulers;

    public void execute() {
        System.out.println(schedulers.size());
        System.out.println(schedulers);
        executor = Executors.newScheduledThreadPool(schedulers.size());
        for (IScheduler scheduler : schedulers) {
//            executor.scheduleWithFixedDelay(scheduler, 0, 5000, TimeUnit.MILLISECONDS);
            executor.execute(scheduler);
        }
    }
}
