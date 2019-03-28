
import java.util.Date;

public abstract class Process {
    static int created = 0;
    int pid;
    String state;
    boolean critical;
    int duration;

    public Process(int pid, boolean critical, int duration) {
        this.pid = ++created;
        this.critical = critical;
        this.duration = duration;
        state = "new";
        if (critical) Scheduler.critical.add(this);
        else Scheduler.noncritical.add(this);
        state = "ready";
    }
    abstract void run();

}
