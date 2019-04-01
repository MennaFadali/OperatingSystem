import java.util.Date;

public abstract class Process implements Runnable {
    int pid;
    String state;
    boolean critical;

    public Process(int pid, boolean critical) {
        this.pid = pid;
        this.critical = critical;
        state = "new";
        OSApp.dateMemory[pid] = new Date();
        if (critical) Scheduler.critical.add(this);
        else Scheduler.noncritical.add(this);
        state = "ready";
        if (pid != 0)
            System.out.println("The process with id " + pid + " is created and it was added to the " + (critical ? "critical" : "non-critical") + " queue at time " + OSApp.dateMemory[pid]);
    }

    public abstract void run();

}