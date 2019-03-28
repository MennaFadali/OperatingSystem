import javax.swing.*;
import java.awt.*;


public class App1 extends Process {
    static final Color blue = Color.BLUE;
    static final Color red = Color.RED;
    static final Font font = new Font("", Font.BOLD, 50);
    JFrame frame;
    JPanel panel;
//    private AudioClip sound;
//    private File wavFile = new File("/home/mennafadali/OperatingSystem/siren.wav");

    public App1(int id) {
        super(id, true);

        frame = new JFrame();
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JLabel label = new JLabel("RUNN");
        label.setFont(font);
        JLabel tmp = new JLabel("                                                                                           ");
        panel.add(tmp, BorderLayout.WEST);
        panel.add(label, BorderLayout.CENTER);
        frame.setSize(new Dimension(1000, 1000));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
    }


//    public AudioClip getSound() {
//        try {
//            sound = Applet.newAudioClip(wavFile.toURL());
//            sound.play();
//        } catch (Exception e1) {
//            e1.printStackTrace();
//        }
//        return sound;
//    }
//

    //    @Override
    public void run() {
        panel.setBackground(red);
        int cnt = 0;
        frame.setVisible(true);
        while (cnt < 30) {
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (cnt % 2 == 0) panel.setBackground(blue);
            else panel.setBackground(red);
            cnt++;
        }
        frame.dispose();
    }
}
