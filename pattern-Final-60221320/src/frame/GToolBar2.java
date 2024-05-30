package frame;

import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import global.LocaleData;
import shapes.GLine;
import shapes.GOval;
import shapes.GPolygon;
import shapes.GRectangle;
import shapes.GSelect;
import shapes.GShape;
import subFrame.GNote;
import subFrame.GTimer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class GToolBar2 extends JPanel {
    public GToolBar2() {
        GridLayout gridLayout = new GridLayout(0, 1);
        this.setLayout(gridLayout);
        
        ActionListener actionListener = new ActionListener();

        JButton textBox = new JButton(LocaleData.GToolBar2.TEXTBOX);
        this.add(textBox);
        JButton TimeOutBTN = new JButton(LocaleData.GToolBar2.TIMER);
        this.add(TimeOutBTN);
        JButton NoteBTN = new JButton(LocaleData.GToolBar2.NOTE);
        this.add(NoteBTN);
        JButton linkBTN = new JButton(LocaleData.GToolBar2.LINKBTN);
        this.add(linkBTN);
        JButton chageBackBTN = new JButton(LocaleData.GToolBar2.CBBTN);
        this.add(chageBackBTN);
        JButton BeforeBackBTN = new JButton(LocaleData.GToolBar2.BBBTN);
        this.add(BeforeBackBTN);

        textBox.addActionListener(actionListener);
        TimeOutBTN.addActionListener(actionListener);
        NoteBTN.addActionListener(actionListener);
        linkBTN.addActionListener(actionListener);
        chageBackBTN.addActionListener(actionListener);
        BeforeBackBTN.addActionListener(actionListener);
        
    }
    private void addTextBox() {
        JTextField textField = new JTextField(LocaleData.GToolBar2.TEXTFIELD);
        textField.setColumns(10);
        textField.setSize(textField.getPreferredSize());
        textField.setLocation(50, 50);

        textField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                textField.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                textField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
            }
            @Override
            public void mouseClicked(MouseEvent e) {
            	if (e.getClickCount() == 2) {
            		getParent().remove(textField);
            		getParent().revalidate();
                    getParent().repaint();
    			}
            }
        });
        textField.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
            	textField.setBounds(textField.getX() , textField.getY() , 200, 30);
            	textField.setLocation(textField.getX() + e.getX() - textField.getWidth() / 2, textField.getY() + e.getY() - textField.getHeight() / 2);
            }
        });

        getParent().add(textField);
        getParent().revalidate();
        getParent().repaint();
    }
    
	public class ActionListener implements java.awt.event.ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			 if(e.getActionCommand().equals(LocaleData.GToolBar2.TEXTBOX)) {
				addTextBox();
			 } else if(e.getActionCommand().equals(LocaleData.GToolBar2.TIMER)){
					
					GTimer timer = new GTimer();
	            	timer.setVisible(true);
	            	timer.startTimer();

			 } else if(e.getActionCommand().equals(LocaleData.GToolBar2.NOTE)){
					
					GNote note = new GNote();
					note.setVisible(true);

			 } else if(e.getActionCommand().equals(LocaleData.GToolBar2.LINKBTN)){
					String link = LocaleData.GToolBar2.LINKURL;
					try {
						Desktop.getDesktop().browse(new URI(link));
					} catch (IOException ex) {
			            ex.printStackTrace();
			        } catch (URISyntaxException ex) {
			            ex.printStackTrace();
			        }
			 } else if(e.getActionCommand().equals(LocaleData.GToolBar2.CBBTN)){
	                Random random = new Random();
	                int r = random.nextInt(256);
	                int g = random.nextInt(256);
	                int b = random.nextInt(256);
	                getParent().setBackground(new Color(r,g,b));
			 } else if(e.getActionCommand().equals(LocaleData.GToolBar2.BBBTN)){
	                getParent().setBackground(Color.WHITE);
			 }
		}
	}
}
