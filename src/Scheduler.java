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
        while (true) {
            Process cur = null;
            if (!critical.isEmpty())
                cur = critical.poll();
            else
                cur = noncritical.poll();
            if (cur != null) {
                cur.state = "running";
                System.out.println("Process with id " + cur.pid + " is starting its execution at time " + new Date());
                cur.run();
                cur.state = "finished";
                System.out.println("Process with id " + cur.pid + " just finished its execution at time " + new Date());
            }
        }

    }
}
