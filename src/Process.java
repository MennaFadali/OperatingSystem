import java.util.Date;

public abstract class Process implements Runnable {
    int pid;
    String state;
    boolean critical;
    int memAccess;
    Date begin;

    public Process(int pid, boolean critical) {
        this.pid = pid;
        this.critical = critical;
        state = "new";
        OSApp.dateMemory[pid] = new Date();
        begin = OSApp.dateMemory[pid];
        this.memAccess += 1;
        Scheduler.out.println("The process with id : " + this.pid + " just accessed the memory!");
        OSApp.memorylog.setText(OSApp.memorylog.getText() + "\n" + "The process with id : " + this.pid + " just accessed the memory!");
        OSApp.processlog.setText(OSApp.processlog.getText() + "\n" + "The process with id " + pid + " is created and it was added to the " + (critical ? "critical" : "non-critical") + " queue at time " + OSApp.dateMemory[pid]);
        Scheduler.out.println("The process with id " + pid + " is created and it was added to the " + (critical ? "critical" : "non-critical") + " queue at time " + OSApp.dateMemory[pid]);
        if (critical) Scheduler.critical.add(this);
        else Scheduler.noncritical.add(this);
        state = "ready";
        Scheduler.out.flush();
    }
    public abstract void run();

}