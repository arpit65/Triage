package com.hackathon.triage.scheduler;

/**
 * @author <a href="mailto:arpit.srivastava@navis.com">Arpit Srivastava</a>
 */
public abstract class AbstractBaseScheduler implements IScheduler {

    public abstract void execute();

    @Override
    public void run() {
        execute();
    }
}
