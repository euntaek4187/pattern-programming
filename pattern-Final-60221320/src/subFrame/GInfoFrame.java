package subFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import global.LocaleData;
import shapes.GShape;

public class GInfoFrame extends JFrame {
	Color color;
	
	public GInfoFrame(GShape currentShape, Vector<GShape> shapes){
		this.setSize(400, 200);
		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width - frameSize.width)/2, (screenSize.height - frameSize.height)/2);
		color = currentShape.color;
		this.setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 2));
		JPanel panelUp = new JPanel();
		JPanel panelLeft = new JPanel();
		JPanel panelRight = new JPanel();
		JLabel PanelUpTitle = new JLabel(LocaleData.GInfoFrame.PAINTUPTITLE);
		JLabel gongbaek = new JLabel(LocaleData.GInfoFrame.GONBAEK);
		JLabel gongbaek2 = new JLabel(LocaleData.GInfoFrame.GONBAEK);
		
		
		
		//
		JLabel colorLabel = new JLabel(LocaleData.GInfoFrame.COLORLABEL);
		JButton colorInfo = new JButton();
		if (color != null) {
			colorInfo.setBackground(color);
		} else {
			colorInfo.setBackground(Color.white);
		}
		
		//
		JLabel shapeLabel = new JLabel(LocaleData.GInfoFrame.SHAPELABEL);
		JLabel shapeInfo = new JLabel(currentShape.getClass().getName());
		//
		JLabel thickLabel = new JLabel(LocaleData.GInfoFrame.THICKLABEL);
		JLabel thickInfo = new JLabel(Integer.toString(currentShape.thickInt));
		//
		JLabel widthLabel = new JLabel(LocaleData.GInfoFrame.WIDTHLABEL);
		JLabel widthInfo = new JLabel(Integer.toString(currentShape.getShape().getBounds().width));
		//
		JLabel heightLabel = new JLabel(LocaleData.GInfoFrame.HEIGHTLABEL);
		JLabel heightInfo = new JLabel(Integer.toString(currentShape.getShape().getBounds().height));
		//
		panel.add(colorLabel);
		panel.add(colorInfo);
		//
		panel.add(shapeLabel);
		panel.add(shapeInfo);
		//
		panel.add(thickLabel);
		panel.add(thickInfo);
		//
		panel.add(widthLabel);
		panel.add(widthInfo);
		//
		panel.add(heightLabel);
		panel.add(heightInfo);
		
		panelUp.add(PanelUpTitle);
		panelLeft.add(gongbaek2);
		panelRight.add(gongbaek);
		
		this.add(panel, BorderLayout.CENTER);
		this.add(panelUp, BorderLayout.NORTH);
		this.add(panelLeft, BorderLayout.WEST);
		this.add(panelRight, BorderLayout.EAST);
		
	}

}
