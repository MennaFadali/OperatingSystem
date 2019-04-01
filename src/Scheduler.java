import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

public class Scheduler implements Runnable {
    static Queue<Process> critical;
    static Queue<Process> noncritical;

    Scheduler() {
        critical = new LinkedList<>();
        noncritical = new LinkedList<>();
    }

    @Override
    public void run() {
        System.out.println("The Scheduler is Up and Running");
        critical.add(new App1(0));
        noncritical.add(new App2(0));
        Process cur = null;
        while (true) {
            if (!critical.isEmpty())
                cur = critical.poll();
            else if (!noncritical.isEmpty()) cur = noncritical.poll();
            if (cur != null) {
                if (cur.pid == 0) continue;
                cur.state = "running";
                System.out.println("Process with id " + cur.pid + " is starting its execution at time " + new Date());
                cur.run();
                cur.state = "finished";
                OSApp.deallocateMemory(cur.pid);
                System.out.println("Process with id " + cur.pid + " just finished its execution at time " + new Date());
                cur = null;
            }
        }

    }
}