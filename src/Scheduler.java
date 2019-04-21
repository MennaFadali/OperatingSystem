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
    static PrintWriter ttfile;
    static PrintWriter out;
    static Date start;
    static double executing;
    static File graph;
    static PrintWriter outG;

    Scheduler() throws FileNotFoundException {
        critical = new LinkedList<>();
        noncritical = new LinkedList<>();
        file = new File("output.txt");
        out = new PrintWriter(file);
        graph = new File("graph.csv");
        outG = new PrintWriter(graph);
        ttfile = new PrintWriter(new File("turnaroundtime.txt"));
        ttfile.write("Turn Around Time");
        start = new Date();

    }

    @Override
    public void run() {
        System.out.println("The Scheduler is Up and Running");
        critical = new LinkedList<>();
        noncritical = new LinkedList<>();
        int i = 0;
        Process cur = null;
        while (true) {
            if (i == 0) {
                System.out.print("");
            }
            i++;
            if (!critical.isEmpty()) {
                cur = critical.poll();
            } else if (!noncritical.isEmpty()) {
                cur = noncritical.poll();
            }
            if (cur != null) {
//            	outG.println((new Date()).getTime()%1000000 +", 0");
                outG.println((System.nanoTime()%100000000000000L)/1000+",0");
                if (cur.pid == 0) continue;
                cur.state = "running";
                Date start = new Date();

                outG.println((System.nanoTime()%100000000000000L)/1000+",1");
                out.println("Process with id " + cur.pid + " is starting its execution at time " + start);
                OSApp.processlog.setText(OSApp.processlog.getText() + "\n" + "Process with id " + cur.pid + " is starting its execution at time " + start);
                cur.run();
                cur.state = "finished";
                if (!cur.critical) {
                    OSApp.deallocateMemory(cur.pid);
                    cur.memAccess += 2;
                }
                Date finish = new Date();

                outG.println((System.nanoTime()%100000000000000L)/1000+",1");
                OSApp.processlog.setText(OSApp.processlog.getText() + "\n" + "Process with id " + cur.pid + " just finished its execution at time " + finish);
                out.println("Process with id " + cur.pid + " just finished its execution at time " + finish);
                executing += (finish.getTime() - start.getTime()) / 1000.0;
                long diff = finish.getTime() - cur.begin.getTime();
                ttfile.write("\nProcess with id " + cur.pid + ": " + (diff / 1000.0) + " seconds");
                ttfile.flush();
                cur = null;
                out.flush();

                outG.println((System.nanoTime()%100000000000000L)/1000+",0");
//                outG.println(start.getTime()%1000000 +", 1");
//                outG.println(finish.getTime()%1000000 +", 1");
//                outG.println((new Date()).getTime()%1000000 +", 0");
                outG.flush();

            }
        }

    }
}