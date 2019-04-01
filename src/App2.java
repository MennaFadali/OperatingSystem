import javax.swing.*;
import java.awt.*;

public class App2 extends Process {
    static final Font font = new Font("", Font.BOLD, 50);
    int x, y;
    JFrame frame;

    public App2(int pid) {
        super(pid, false);
        x = (OSApp.dataMemory[pid*2]==null?0:OSApp.dataMemory[pid*2]);
        y = (OSApp.dataMemory[pid*2+1]==null?0:OSApp.dataMemory[pid*2+1]);
    }


    @Override
    public void run() {
        frame = new JFrame();
        JLabel res = new JLabel();
        frame.setContentPane(res);
        frame.setLayout(new BorderLayout());
        res.setSize(999, 999);
        res.setFont(font);
        frame.setSize(2000, 2000);
        res.setText("                                                             " + (x + y));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
