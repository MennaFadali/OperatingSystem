import javax.swing.*;
import java.awt.*;

public class App2 extends Process {
    static final Font font = new Font("", Font.BOLD, 50);
    int x, y;
    JFrame frame;

    public App2(int pid, int one, int two) {
        super(pid, false);
        x = one;
        y = two;
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
