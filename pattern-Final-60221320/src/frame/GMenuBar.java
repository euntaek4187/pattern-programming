package frame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import global.LocaleData;
import shapes.GShape;


public class GMenuBar extends JMenuBar {
	private static final long serialVersionUID = 1L;
	
	private GFileJMenu fileMenu;
	public Color nowColor;
	public boolean isCopy;
	public GShape shape;
	public Shape shapeInfo;
	public Polygon PolygonshapeInfo;
	
	public JRadioButton colorBoxBTN;
	public JRadioButton eraserBTN;
	public int thickness;
	private GDrawingPanel drawingPanel;
	private JLabel timeLabel;

	public GMenuBar() {
		this.fileMenu = new GFileJMenu(LocaleData.GMenuBar.FILENAME);
		this.add(fileMenu);
		
		// 한세트
		SimpleDateFormat simple = new SimpleDateFormat(LocaleData.GMenuBar.DATE);
		this.timeLabel = new JLabel(simple.format(new Date()));
		this.add(timeLabel);
		//
		thickness = 1;
        JLabel jcolor = new JLabel(LocaleData.GMenuBar.THICK);
		this.add(jcolor);
        JComboBox<Integer> thickInt = new JComboBox<>();
        thickInt.addItem(1);
        thickInt.addItem(2);
        thickInt.addItem(3);
        thickInt.addItem(4);
        thickInt.addItem(5);
        thickInt.addItem(6);
        thickInt.addItem(7);
        thickInt.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
            	thickness = (int) thickInt.getSelectedItem();
            }
        });
        this.add(thickInt);
        //
        ActionListener actionListener = new ActionListener();
		JPanel colorPanel = new JPanel();
		//
		this.colorBoxBTN = new JRadioButton();
		this.add(colorBoxBTN);
		JLabel colorBox = new JLabel(LocaleData.GMenuBar.FEATURE1);
		this.add(colorBox);
		//
		this.eraserBTN = new JRadioButton();
		this.add(eraserBTN);
		JLabel eraser = new JLabel(LocaleData.GMenuBar.FEATURE2);
		this.add(eraser);
		//
		JButton paste = new JButton(LocaleData.GMenuBar.FEATURE3);
		this.add(paste);
		JButton sizeUp = new JButton(LocaleData.GMenuBar.FEATURE4);
		this.add(sizeUp);
		JButton sizeDown = new JButton(LocaleData.GMenuBar.FEATURE5);
		this.add(sizeDown);
		JButton resetBtn = new JButton(LocaleData.GMenuBar.FEATURE6);
		this.add(resetBtn);
		JButton beforBtn = new JButton(LocaleData.GMenuBar.FEATURE7);
		this.add(beforBtn);
		JButton afterBtn = new JButton(LocaleData.GMenuBar.FEATURE8);
		this.add(afterBtn);
        JButton gravity1 = new JButton(LocaleData.GMenuBar.FEATURE9);
        this.add(gravity1);
        JButton gravity2 = new JButton(LocaleData.GMenuBar.FEATURE9_1);
        this.add(gravity2);
        JButton gravity3 = new JButton(LocaleData.GMenuBar.FEATURE9_2);
        this.add(gravity3);
		JButton end = new JButton(LocaleData.GMenuBar.FEATURE10);
		this.add(end);
		
		colorBoxBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(colorBoxBTN.isSelected()) {
	                Color selectedColor = JColorChooser.showDialog(colorPanel, LocaleData.GMenuBar.CC, colorPanel.getBackground());
	                if (selectedColor != null) {
	                	nowColor = selectedColor;
	                }
            	}
            }
        });
		
		paste.addActionListener(actionListener);
		sizeUp.addActionListener(actionListener);
		sizeDown.addActionListener(actionListener);
		gravity1.addActionListener(actionListener);
		gravity2.addActionListener(actionListener);
		gravity3.addActionListener(actionListener);
		resetBtn.addActionListener(actionListener);
		beforBtn.addActionListener(actionListener);
		afterBtn.addActionListener(actionListener);
		end.addActionListener(actionListener);
	}
	public Color getColor() {
		return nowColor;
	}
    public void setDrawingPanel(GDrawingPanel drawingPanel) {
        this.drawingPanel = drawingPanel;
    }
    Timer timer;
    private void gravityUp() {
    	
        this.timer = new Timer(5, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (drawingPanel.shapes.size() > 0) {
                    for (GShape shape : drawingPanel.shapes) {
                    	if (shape.getShape().getBounds().x + shape.getShape().getBounds().width < drawingPanel.panelSize.width && shape.getShape().getBounds().y + shape.getShape().getBounds().height < drawingPanel.panelSize.height-33&&shape.getShape().getBounds().x > 0&&shape.getShape().getBounds().y > 0) {
                    		AffineTransform transform = new AffineTransform();
                            transform.translate(0, -1);
                            Shape transformedShape = transform.createTransformedShape(shape.getShape());
                            shape.setShape(transformedShape);
						} else {
		                    
		                }
                        
                    }
                    drawingPanel.repaint();
                } else {
                    timer.stop();
                }
            }
        });
        timer.start();
    }
    private void gravityDown() {
    	
        this.timer = new Timer(5, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (drawingPanel.shapes.size() > 0) {
                    for (GShape shape : drawingPanel.shapes) {
                    	if (shape.getShape().getBounds().x + shape.getShape().getBounds().width < drawingPanel.panelSize.width && shape.getShape().getBounds().y + shape.getShape().getBounds().height < drawingPanel.panelSize.height-33&&shape.getShape().getBounds().x > 0&&shape.getShape().getBounds().y > 0) {
                    		AffineTransform transform = new AffineTransform();
                            transform.translate(0, +1);
                            Shape transformedShape = transform.createTransformedShape(shape.getShape());
                            shape.setShape(transformedShape);
						} else {
		                    
		                }
                        
                    }
                    drawingPanel.repaint();
                } else {
                    timer.stop();
                }
            }
        });
        timer.start();
    }
    private void timerAllStop() {
    	if (timer != null) {
    		timer.stop();
		}
    	
    }
	public class ActionListener implements java.awt.event.ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals(LocaleData.GMenuBar.FEATURE3)) {
				if (shape != null) {
					AffineTransform transform = new AffineTransform();
                    transform.translate(+10, +10);
                    Shape transformedShape = transform.createTransformedShape(shape.getShape());
                    shape.setShape(transformedShape);
					drawingPanel.shapes.add(shape);
					shape = null;
					getParent().repaint();
				}
				
			 } else if(e.getActionCommand().equals(LocaleData.GMenuBar.FEATURE4)){
				drawingPanel.clearSelection();
	            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(GMenuBar.this);
                parentFrame.setSize(parentFrame.getWidth() + 50, parentFrame.getHeight() + 30);

			 } else if(e.getActionCommand().equals(LocaleData.GMenuBar.FEATURE5)){
				drawingPanel.clearSelection();
				JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(GMenuBar.this);
                parentFrame.setSize(parentFrame.getWidth() - 50, parentFrame.getHeight() - 30);

			 } else if(e.getActionCommand().equals(LocaleData.GMenuBar.FEATURE6)){
				 drawingPanel.clearSelection();
				 drawingPanel.resetPage();

			 } else if(e.getActionCommand().equals(LocaleData.GMenuBar.FEATURE7)){
				 drawingPanel.clearSelection();
				 drawingPanel.beforePage();

			 } else if(e.getActionCommand().equals(LocaleData.GMenuBar.FEATURE8)){
				 drawingPanel.clearSelection();
				 drawingPanel.afterPage();

			 } else if(e.getActionCommand().equals(LocaleData.GMenuBar.FEATURE9)){
				 timerAllStop();
				 gravityUp();

			 }  else if(e.getActionCommand().equals(LocaleData.GMenuBar.FEATURE9_1)){
				 timerAllStop();
				 gravityDown();

			 } else if(e.getActionCommand().equals(LocaleData.GMenuBar.FEATURE9_2)){
				 timerAllStop();

			 } else if(e.getActionCommand().equals(LocaleData.GMenuBar.FEATURE10)){
				 System.exit(0);

			 }
		}
		
	}
}
