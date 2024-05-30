package main;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.MenuBar;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import frame.GDrawingPanel;
import frame.GMenuBar;
import frame.GToolBar;
import global.LocaleData;

public class GMainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private GMenuBar menuBar;
	private GToolBar toolBar;
	private GDrawingPanel drawingPanel;
	
	public GMainFrame() {
		// attributes	
		this.setSize(GConstants.CMainFrame.w, GConstants.CMainFrame.h);
		this.setTitle(LocaleData.GMainFrame.FRAMETITLE);
		
		ImageIcon icon = new ImageIcon(LocaleData.GMainFrame.IMAGEURL);
		this.setIconImage(icon.getImage());
		
		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width - frameSize.width)/2, (screenSize.height - frameSize.height)/2);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// components
		this.menuBar =new GMenuBar();
		this.setJMenuBar(menuBar);
		
		BorderLayout borderLayout = new BorderLayout();
		this.setLayout(borderLayout);
		
		this.toolBar =new GToolBar();
		this.add(toolBar, BorderLayout.NORTH);
		
		this.drawingPanel =new GDrawingPanel();
		this.add(drawingPanel, BorderLayout.CENTER);
		
		//association
		this.drawingPanel.setToolBar(toolBar);
		this.drawingPanel.setMenuBar(menuBar);
		this.menuBar.setDrawingPanel(drawingPanel);
	}
}
