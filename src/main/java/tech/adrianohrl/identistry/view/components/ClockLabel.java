/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.identistry.view.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/**
 *
 * @author adrianohrl
 */
public class ClockLabel extends JLabel implements ActionListener {

    private String type;
    private SimpleDateFormat format;

    public ClockLabel(String type) {
        this.type = type;
        switch (type) {
            case "date":
                format = new SimpleDateFormat("  MMMM dd, yyyy");
                super.setFont(new Font("sans-serif", Font.PLAIN, 12));
                super.setHorizontalAlignment(SwingConstants.LEFT);
                break;
            case "time":
                format = new SimpleDateFormat("HH:mm:ss a");
                super.setFont(new Font("sans-serif", Font.PLAIN, 40));
                super.setHorizontalAlignment(SwingConstants.CENTER);
                break;
            case "day":
                format = new SimpleDateFormat("EEEE  ");
                super.setFont(new Font("sans-serif", Font.PLAIN, 16));
                super.setHorizontalAlignment(SwingConstants.RIGHT);
                break;
            default:
                format = new SimpleDateFormat();
                break;
        }
        Timer timer = new Timer(1000, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        super.setText(format.format(new Date()));
    }

    public static void main(String[] arguments) {
        ClockLabel dateLable = new ClockLabel("date");
        dateLable.setForeground(Color.GREEN);
        ClockLabel timeLable = new ClockLabel("time");
        timeLable.setForeground(Color.GREEN);
        ClockLabel dayLable = new ClockLabel("day");
        dayLable.setForeground(Color.GREEN);
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Digital Clock");
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 1));
        frame.add(dateLable);
        frame.add(timeLable);
        frame.add(dayLable);
        frame.getContentPane().setBackground(Color.black);
        frame.setVisible(true);
    }
}
