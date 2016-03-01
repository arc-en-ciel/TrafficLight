package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.TimerTask;

/**
 * Created by Оксана on 01.03.2016.
 */
public class TrafficFrame extends JFrame {

    final static int SIZE = 100;
    static Icon[] icons = new Icon[4];
    static {
        icons[0] = new curveIcon(Color.red, SIZE);
        icons[1] = new curveIcon(Color.yellow, SIZE);
        icons[2] = new curveIcon(Color.GREEN, SIZE);
        icons[3] = new curveIcon(Color.BLACK, SIZE);
    }
    public JLabel top, mid, bottom;
    public ArrayList<JLabel> list = new ArrayList<JLabel>();
    public TimerTask taskRed, taskYellow, taskGreen;


    public void init() {
        setSize(250, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Traffic Light");
        JPanel panel = new JPanel();
        JPanel panel1 = new JPanel();
        Button buttonStart = new Button("СТАРТ");
        buttonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timerLight();
                java.util.Timer timer1 = new java.util.Timer();
                timer1.schedule(taskRed, 0, 9000);
                timerLight();
                java.util.Timer timer2 = new java.util.Timer();
                timer2.schedule(taskYellow, 2000, 9000);
                timerLight();
                java.util.Timer timer3 = new java.util.Timer();
                timer3.schedule(taskGreen, 5000, 9000);
            }
        });
        Button buttonEnd = new Button("СТОП");
        buttonEnd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(panel, BorderLayout.WEST);
        panel1.add(buttonStart, BorderLayout.NORTH);
        panel1.add(buttonEnd, BorderLayout.SOUTH);
        add(panel1, BorderLayout.EAST);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        top = new JLabel(icons[3]);
        list.add(top);
        mid = new JLabel(icons[3]);
        list.add(mid);
        bottom = new JLabel(icons[3]);
        list.add(bottom);
        panel.add(top);
        panel.add(mid);
        panel.add(bottom);
        setVisible(true);
    }

    public ArrayList getList() {
        return list;
    }

    private void timerLight() {
        ArrayList lst = getList();
        curveIcon cI1 = (curveIcon) ((JLabel) lst.get(0)).getIcon();
        curveIcon cI2 = (curveIcon) ((JLabel) lst.get(1)).getIcon();
        curveIcon cI3 = (curveIcon) ((JLabel) lst.get(2)).getIcon();
        taskRed = new TimerTask() {
            @Override
            public void run() {
                if (cI1.getColor().equals(Color.black)) {
                    ((JLabel) lst.get(0)).setIcon(icons[0]);
                    ((JLabel) lst.get(1)).setIcon(icons[3]);
                    ((JLabel) lst.get(2)).setIcon(icons[3]);
                }
            }
        };

        taskYellow = new TimerTask() {
            @Override
            public void run() {
                if (cI2.getColor().equals(Color.black)) {
                    ((JLabel) lst.get(0)).setIcon(icons[3]);
                    ((JLabel) lst.get(1)).setIcon(icons[1]);
                    ((JLabel) lst.get(2)).setIcon(icons[3]);
                }
            }
        };

        taskGreen = new TimerTask() {
            @Override
            public void run() {
                if (cI3.getColor().equals(Color.black)) {
                    ((JLabel) lst.get(0)).setIcon(icons[3]);
                    ((JLabel) lst.get(1)).setIcon(icons[3]);
                    ((JLabel) lst.get(2)).setIcon(icons[2]);
                }
            }
        };
    }

    static class curveIcon implements Icon {
        int width;
        int height;
        Color useColor;

        curveIcon(Color c, int s) {
            this.useColor = c;
            this.width = s;
            this.height = s;
        }

        public void paintIcon(Component c, Graphics g, int x, int y) {
            g.setColor(useColor);
            g.drawOval(x, y, width, height);
            g.setColor(useColor);
            g.fillOval(x, y, width, height);
        }

        public int getIconWidth() {
            return width;
        }

        public int getIconHeight() {
            return height;
        }

        public Color getColor() {
            return useColor;
        }
    }
}