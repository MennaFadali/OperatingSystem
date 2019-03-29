import java.util.Date;

public abstract class Process implements Runnable {
    int pid;
    String state;
    boolean critical;

    public Process(int pid, boolean critical) {
        this.pid = pid;
        this.critical = critical;
        state = "new";
        if (critical) Scheduler.critical.add(this);
        else Scheduler.noncritical.add(this);
        state = "ready";
        if (pid != -1)
            System.out.println("The process with id " + pid + " is created and it was added to the " + (critical ? "critical" : "non-critical") + " queue at time " + (new Date()));
    }

    public abstract void run();

}