package subFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import global.LocaleData;

public class GTimer extends JFrame{
	
	public JLabel dateTimeLabel;
	public JPanel panel;
	
	public GTimer() {
		this.setSize(200, 200);
		this.setLocation(260, 250);
		this.panel = new JPanel();
		this.panel.setBackground(Color.CYAN);
	}
	
	public void startTimer() {
		this.setTitle(LocaleData.GTimer.TITLE);
		
		try {
			int input = Integer.parseInt(JOptionPane.showInputDialog(LocaleData.GTimer.SD1))+1;
			dateTimeLabel = new JLabel(Integer.toString(input));
			dateTimeLabel.setFont(dateTimeLabel.getFont().deriveFont(70f));
		} catch (NumberFormatException e1) {
    		JOptionPane.showMessageDialog(null, LocaleData.GTimer.SD2);
    		startTimer();
    		return;
		}
		
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
	            	int time = Integer.parseInt(dateTimeLabel.getText())-1;
	            	if(time == -1) {
	            		System.exit(0);
	            	}
	                dateTimeLabel.setText(Integer.toString(time));
            }
        });

        timer.start();

        this.panel.add(dateTimeLabel);
        this.add(panel);
	}
}
