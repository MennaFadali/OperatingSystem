import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

public class Scheduler implements Runnable {
    static Queue<Process> critical;
    static Queue<Process> noncritical;
    static File file;
    static PrintWriter out;
    Scheduler() throws FileNotFoundException {
        critical = new LinkedList<>();
        noncritical = new LinkedList<>();
        file = new File("output.txt");
        out = new PrintWriter(file);
    }

    @Override
    public void run() {
        System.out.println("The Scheduler is Up and Running");
        critical = new LinkedList<>();
        noncritical = new LinkedList<>();
        int i=0;
        Process cur = null;
        while (true) {
            if(i==0) {
                System.out.print("");
            }
            i++;
            if (!critical.isEmpty()){
                cur = critical.poll();}
            else if (!noncritical.isEmpty()) {cur = noncritical.poll();}
            if (cur != null) {
                if (cur.pid == 0) continue;
                cur.state = "running";
                out.println("Process with id " + cur.pid + " is starting its execution at time " + new Date());
                cur.run();
                cur.state = "finished";
                if(!cur.critical) {
                    OSApp.deallocateMemory(cur.pid);
                    cur.memAccess += 2;
                }
                out.println("Process with id " + cur.pid + " just finished its execution at time " + new Date());
                cur = null;
                out.flush();
            }
        }

    }
}