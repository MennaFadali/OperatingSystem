import javax.swing.*;
import java.applet.AudioClip;
import java.awt.*;
import java.io.File;


public class App1 extends Process {
    static final Color blue = Color.BLUE;
    static final Color red = Color.RED;
    static final Color black = Color.BLACK;
    static final Color white = Color.WHITE;
    static final Font font = new Font("", Font.BOLD, 50);
    JFrame frame;
    JPanel panel;
    JLabel label;
//    private AudioClip sound;
//    private File wavFile = new File("siren.wav");

    public App1(int id ) {
        super(id, true);

        frame = new JFrame();
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        label = new JLabel("                      RUNN");
        label.setFont(font);
        panel.add(label, BorderLayout.CENTER);
        label.setForeground(black);
        frame.setSize(new Dimension(1000, 1000));
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
    }


//    public AudioClip getSound() {
//        return sound;
//    }


    //    @Override
    public void run() {
//        try {
//            sound = Applet.newAudioClip(wavFile.toURL());
//            sound.play();
//        } catch (Exception e1) {
//            e1.printStackTrace();
//        }

        panel.setBackground(red);
        int cnt = 0;
        frame.setVisible(true);
        while (cnt < 30) {
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (cnt % 2 == 0) {
                panel.setBackground(blue);
                label.setForeground(white);
            } else {
                panel.setBackground(red);
                label.setForeground(black);
            }
            cnt++;
        }
        sound.stop();
        frame.dispose();
    }
}
