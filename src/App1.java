import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class App1 extends Process {
    static final Color blue = Color.BLUE;
    static final Color red = Color.RED;
    static final Color black = Color.BLACK;
    static final Color white = Color.WHITE;
    static final Font font = new Font("", Font.BOLD, 50);
    JFrame frame;
    JPanel panel;
    JLabel label;
    Clip clip;

    public App1(int id) {
        super(id, true);
    }


    @Override
    public void run() {

        frame = new JFrame();
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        label = new JLabel("                      RUNN " + this.pid);
        label.setFont(font);
        panel.add(label, BorderLayout.CENTER);
        label.setForeground(black);
        frame.setSize(new Dimension(1000, 1000));
        frame.setLocation(500, 500);
        File yourFile = new File("siren.wav");
        AudioInputStream stream;
        AudioFormat format;
        DataLine.Info info;
        try {
            stream = AudioSystem.getAudioInputStream(yourFile);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        frame.setContentPane(panel);
        panel.setBackground(red);
        int cnt = 0;
        clip.start();
        frame.setVisible(true);
        while (cnt < 15) {
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
        clip.stop();
        frame.dispose();
    }
}