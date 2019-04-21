import javax.swing.*;
import java.awt.*;

public class App2 extends Process {
    static final Font font = new Font("", Font.BOLD, 50);
    int x, y;
    JFrame frame;

    public App2(int pid) {
        super(pid, false);
        x = (OSApp.dataMemory[pid*2]==null?0:OSApp.dataMemory[pid*2]);
        Scheduler.out.println("The process with id : "+this.pid +" just accessed the memory!");
        OSApp.memorylog.setText(OSApp.memorylog.getText()+"\n"+"The process with id : "+this.pid +" just accessed the memory!");
        y = (OSApp.dataMemory[pid*2+1]==null?0:OSApp.dataMemory[pid*2+1]);
        Scheduler.out.println("The process with id : "+this.pid +" just accessed the memory!");
        OSApp.memorylog.setText(OSApp.memorylog.getText()+"\n"+"The process with id : "+this.pid +" just accessed the memory!");
        this.memAccess += 2;
        Scheduler.out.flush();

    }


    @Override
    public void run() {
        frame = new JFrame();
        JLabel res = new JLabel();
        frame.setContentPane(res);
        frame.setLayout(new BorderLayout());
        res.setSize(999, 999);
        res.setFont(font);
        frame.setLocation(400,400);
        frame.setSize(new Dimension(500,500));
        res.setText("          " + (x + y));
        frame.setVisible(true);
    }
}