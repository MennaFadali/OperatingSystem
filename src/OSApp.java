import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Date;

public class OSApp extends JFrame {
    public static Date[] dateMemory;
    public static Integer[] dataMemory;
    static JTextArea memorylog;
    static JTextArea processlog;
    static Dimension size = new Dimension(2000, 2000);
    static Font font = new Font("Segoe Script", Font.BOLD, 20);

    public OSApp() throws HeadlessException, FileNotFoundException {
        super();
        dateMemory = new Date[(int) 1e6 + 2];
        dataMemory = new Integer[(int) 2e6 + 4];
        Scheduler s = new Scheduler();
        Thread mainthread = new Thread(s);
        mainthread.start();
        this.setSize(size);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setBackground(Color.lightGray);
        panel.setLayout(new GridLayout(2, 2));
        panel.setSize(size);
        memorylog = new JTextArea("Memory Log");
        memorylog.setFont(font);
        memorylog.setForeground(Color.black);
        memorylog.setBackground(Color.white);
        memorylog.setEditable(false);
        processlog = new JTextArea("Process Log");
        processlog.setFont(font);
        processlog.setForeground(Color.black);
        processlog.setBackground(Color.white);
        processlog.setEditable(false);
        JButton one = new JButton("Alarm");
        one.setFont(new Font("", Font.BOLD, 100));
        one.setBackground(Color.lightGray);
        one.setForeground(Color.red);
        JPanel app2 = new JPanel();
        app2.setLayout(new GridLayout(4, 1));
        JLabel enterdata = new JLabel("Please Enter Two Numbers");
        enterdata.setFont(font);
        enterdata.setForeground(Color.red);
        JTextField n1 = new JTextField();
        JTextField n2 = new JTextField();
        n1.setFont(font);
        n2.setFont(font);
        n1.setForeground(Color.red);
        n2.setForeground(Color.red);
        app2.add(enterdata);
        app2.add(n1);
        app2.add(n2);
        Button two = new Button("ADD");
        two.setBackground(Color.lightGray);
        two.setForeground(Color.red);
        app2.add(two);
        two.setFont(new Font("", Font.BOLD, 70));
        app2.setBackground(Color.lightGray);
        app2.setVisible(true);
        panel.add(one);
        panel.add(app2);
        panel.add(memorylog);
        panel.add(processlog);
        this.setContentPane(panel);
        app2.setVisible(true);
        panel.setVisible(true);
        this.setVisible(true);
        one.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(new App1(getRandomId()));
            }
        });
        two.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int pid2 = getRandomId();
                    int x = Integer.parseInt(n1.getText());
                    int y = Integer.parseInt(n2.getText());
                    allocateMemory(pid2, x, y);
                    new Thread(new App2(pid2));
                } catch (Exception excep) {

                }
            }
        });
    }

    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        new OSApp();
    }

    static int getRandomId() {
        return (int) (Math.random() * 1e6) + 1;
    }

    static void allocateMemory(int pid, int x, int y) {
        dataMemory[pid * 2] = x;
        Scheduler.out.println("The process with id : " + pid + " just accessed the memory!");
        dataMemory[pid * 2 + 1] = y;
        Scheduler.out.println("The process with id : " + pid + " just accessed the memory!");
        Scheduler.out.flush();
    }

    static void deallocateMemory(int pid) {
        dataMemory[pid * 2] = null;
        Scheduler.out.println("The process with id : " + pid + " just accessed the memory!");
        dataMemory[pid * 2 + 1] = null;
        Scheduler.out.println("The process with id : " + pid + " just accessed the memory!");
        Scheduler.out.flush();
    }
}