import java.sql.Date;
import java.util.LinkedList;
import java.util.Queue;

public class Scheduler {
    static Queue<Process> critical;
    static Queue<Process> noncritical;

    public static void main(String[] args) {
        critical = new LinkedList<>();
        noncritical = new LinkedList<>();
        while (true) {
            Process cur = null;
            if (!critical.isEmpty())
                cur = critical.poll();
            else
                cur = noncritical.poll();
            if (cur != null) {
                cur.state = "running";
                cur.run();
                cur.state = "finished";
            }
        }
    }
}
