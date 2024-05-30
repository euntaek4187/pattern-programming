package frame;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

import global.LocaleData;
import shapes.GLine;
import shapes.GOval;
import shapes.GPolygon;
import shapes.GRectangle;
import shapes.GSelect;
import shapes.GShape;

public class GToolBar3 extends JPanel {
	public JLabel nowX;
	public JLabel nowY;
	public JLabel now2Click;
	public JLabel nowClick;
	
	public GToolBar3() {
		GridLayout gridLayout = new GridLayout(0, 1);
		this.setLayout(gridLayout);
		
		GridLayout gridLayout2 = new GridLayout(2, 1);
		GridLayout gridLayout3 = new GridLayout(1, 0);
		JPanel panel1 = new JPanel();
		panel1.setLayout(gridLayout2);
		JPanel panel2 = new JPanel();
		panel2.setLayout(gridLayout3);
		JPanel panel3 = new JPanel();
		panel3.setLayout(gridLayout3);
		panel1.setBackground(new Color(204,255,255));
		panel2.setBackground(new Color(204,235,255));
		panel3.setBackground(new Color(204,215,255));
		
		JLabel now1 = new JLabel(LocaleData.GToolBar3.NOW1);
		this.nowX = new JLabel(LocaleData.GToolBar3.ZERO);
		JLabel now2 = new JLabel(LocaleData.GToolBar3.NOW2);
		this.nowY = new JLabel(LocaleData.GToolBar3.ZERO);
		//
		JLabel now3 = new JLabel(LocaleData.GToolBar3.NOW3);
		this.now2Click = new JLabel(LocaleData.GToolBar3.ZERO);
		//
		JLabel now4 = new JLabel(LocaleData.GToolBar3.NOW4);
		this.nowClick = new JLabel(LocaleData.GToolBar3.ZERO);
		
		panel1.add(now1);
		panel1.add(nowX);
		panel1.add(now2);
		panel1.add(nowY);
		panel2.add(now3);
		panel2.add(now2Click);
		panel3.add(now4);
		panel3.add(nowClick);
		
		this.add(panel1);
		this.add(panel2);
		this.add(panel3);
		
	}
}
