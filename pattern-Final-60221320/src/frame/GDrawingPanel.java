package frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JPanel;

import main.GConstants;
import main.GConstants.EAnchors;
import main.GConstants.EShape;
import main.GConstants.EUserAction;
import shapes.GPolygon;
import shapes.GShape;
import subFrame.GInfoFrame;
import transformer.Drawer;
import transformer.GMover;
import transformer.GResizor;
import transformer.GRotate;
import transformer.GSelect;
import transformer.GTransformer;
import transformer.NPDrawer;

public class GDrawingPanel extends JPanel {

	private enum EDrawingEvent{
		eStart,
		eMoving,
		eCont,
		eEnd,
	}
	private enum EDrawingState {
		eIdel,
		eTransforming
	}

	private EDrawingState eDrawingState;
	public Vector<GShape> shapes;
	private Vector<GShape> beforeAfterBox;
	private GShape currentShape;
	private GToolBar toolBar;
	private GToolBar2 toolBar2;
	private GToolBar3 toolBar3;
	private GMenuBar menuBar;
	private Cursor cursor;
	//
	public int clickInt = 0;
	public int click2Int =0;
	//
    private Image offScreenImage;
    private Graphics offScreenGraphics;
    public Dimension panelSize;

	public void setToolBar(GToolBar toolBar) {
		this.toolBar = toolBar;
	}
	public void setMenuBar(GMenuBar menuBar) {
		this.menuBar = menuBar;
	}
	
	private GTransformer transformer;

	public GDrawingPanel() {
		super();
		this.eDrawingState = EDrawingState.eIdel;
		this.shapes = new Vector<GShape>();
		this.beforeAfterBox = new Vector<GShape>();
		this.currentShape = null;
		//
		this.setLayout(new BorderLayout());
		
		this.toolBar2 =new GToolBar2();
		this.add(toolBar2, BorderLayout.EAST);
		
		this.toolBar3 =new GToolBar3();
		this.add(toolBar3, BorderLayout.WEST);
		//

		this.setBackground(Color.WHITE);
		MouseEventHandler mouseEventHandler = new MouseEventHandler();
		this.addMouseListener(mouseEventHandler);
		this.addMouseMotionListener(mouseEventHandler);
	}

	public void paint(Graphics graphics) {
        offScreenImage = createImage(getWidth(), getHeight());
        offScreenGraphics = offScreenImage.getGraphics();

        super.paint(offScreenGraphics);

        for (GShape shape : shapes) {
            shape.draw(offScreenGraphics);
        }

        if (currentShape != null) {
            currentShape.draw(offScreenGraphics);
        }

        graphics.drawImage(offScreenImage, 0, 0, this);
        
        // 기존의 페인트
//		super.paint(graphics);
//		for (GShape shape : this.shapes) {
//			shape.draw((Graphics2D) graphics);
//		}

	}

	// 임시 - 이 포인터 밑에 도형이 있나 물어봐야함.
	public GShape onShape(Point point) {
		for (GShape shape : shapes) {
			if (shape.onShape(point)) {
				return shape;
			}
		}
		return null;
	}


	public void initTransforming(int x, int y) { //어떤 트랜스포머 쓸지를 고른다.
		Graphics2D graphics2d = (Graphics2D) this.getGraphics();
		if (this.toolBar.getESelectedShape() == EShape.eSelect) {
			EAnchors eSelectedAnchor = this.onShape(x, y);
			if (eSelectedAnchor == null) {
				clearSelection();
				this.currentShape = this.toolBar.getESelectedShape().getGShape().clone();
				this.transformer = new GSelect(this.currentShape);
				this.transformer.initTransform(x,y, graphics2d);
			} else {
				switch (eSelectedAnchor) {
				case MM:
					this.transformer = new GMover(this.currentShape);
					this.transformer.initTransform(x,y, graphics2d);
					break;
				case RR:
					this.transformer = new GRotate(this.currentShape);
					this.transformer.initTransform(x,y, graphics2d);
					break;
				default: //다 아니라면 리사이즈의 경우이다.
					this.transformer = new GResizor(this.currentShape);
					this.transformer.initTransform(x,y, graphics2d);
					break;
				}
			}
		} else {
			if (this.toolBar.getESelectedShape().getEUserAction() == EUserAction.e2Point) {
				this.currentShape = this.toolBar.getESelectedShape().getGShape().clone();
				this.currentShape.setThickness(menuBar.thickness);
				this.transformer = new Drawer(this.currentShape);
				this.transformer.initTransform(x,y, graphics2d);
			} else {
				this.currentShape = this.toolBar.getESelectedShape().getGShape().clone();
				this.currentShape.setThickness(menuBar.thickness);
				this.transformer = new NPDrawer(this.currentShape);
				this.transformer.initTransform(x,y, graphics2d);
				eDrawingState = EDrawingState.eTransforming;
			}
		}
		JComponent parent = (JComponent) this.getParent();
		this.panelSize = parent.getSize();
	}

	public void keepTransforming(int x, int y) {
		Graphics2D graphics2d = (Graphics2D) this.getGraphics();
		graphics2d.setXORMode(this.getBackground());
		JComponent parent = (JComponent) this.getParent();
		Dimension panelSize = parent.getSize();
		if (currentShape.isGrouping != true) {
			this.transformer.keepTransform(x, y, graphics2d, panelSize);
		} else {
			this.transformer.keepTransform2(x, y, graphics2d, this.shapes);
		}
		repaint();
	}
	
	public void continueTransforming(int x, int y) {
		Graphics2D graphics2d = (Graphics2D) this.getGraphics();
		this.transformer.continueTransform(x, y, graphics2d);
		repaint();
	}
	public void finalizeTransforming(int x, int y) {
		Graphics2D graphics2d = (Graphics2D) this.getGraphics();
		this.transformer.finalizeTransform(x, y, graphics2d, this.shapes);
		currentShape = null;
		this.toolBar.resetESelectedShape();
		repaint();
	}
	public void changeCursors(int x, int y) {
		EAnchors eAnchors = this.onShape(x, y);
		if (eAnchors == null) {
			cursor = new Cursor(Cursor.DEFAULT_CURSOR);
		} else {
			switch (eAnchors) {
			case MM:
				cursor = new Cursor(Cursor.CROSSHAIR_CURSOR);
				break;
			case RR:
				cursor = new Cursor(Cursor.HAND_CURSOR);
				break;
			case NW:
				cursor = new Cursor(Cursor.NW_RESIZE_CURSOR);
				break;
			case NN:
				cursor = new Cursor(Cursor.N_RESIZE_CURSOR);
				break;
			case NE:
				cursor = new Cursor(Cursor.NE_RESIZE_CURSOR);
				break;
			case EE:
				cursor = new Cursor(Cursor.E_RESIZE_CURSOR);
				break;
			case SE:
				cursor = new Cursor(Cursor.SE_RESIZE_CURSOR);
				break;
			case SS:
				cursor = new Cursor(Cursor.S_RESIZE_CURSOR);
				break;
			case SW:
				cursor = new Cursor(Cursor.SW_RESIZE_CURSOR);
				break;
			case WW:
				cursor = new Cursor(Cursor.W_RESIZE_CURSOR);
				break;
			default: //다 아니라면 리사이즈의 경우이다.
				cursor = new Cursor(Cursor.DEFAULT_CURSOR);
				break;
			}
		} 
		this.setCursor(cursor);
		

	}
	
	private EAnchors onShape(int x, int y) {

		for(GShape gShape: this.shapes) {
			EAnchors eAnchors = gShape.onShape(x,y);
			if(eAnchors != null) {
				this.currentShape = gShape;
				return eAnchors;
			}
		}
		return null;
	}
	public int onShapeIndex(Point point) {
		int index = 0;
		for (GShape shape : shapes) {
			if (shape.onShape(point)) {
				return index;
			}
			index++;
		}
		return -1;
	}

	public void clearSelection() {
		for (GShape shape : this.shapes) {
			shape.setSelected(false);
			shape.isGrouping = false;
		}
		repaint();
	}

	public void resetPage() {
		repaint();
		for(int i = 0; i < 10; i++) {
			shapes.clear();
			beforeAfterBox.clear();
			repaint();
		}
		repaint();
	}
	public void beforePage() {
		if (shapes.size() != 0) {
			beforeAfterBox.add(shapes.get(shapes.size()-1));
			shapes.remove(shapes.size()-1);
		}
		repaint();
	}
	public void afterPage() {
		if (beforeAfterBox.size() != 0) {
			shapes.add(beforeAfterBox.get(beforeAfterBox.size()-1));
			beforeAfterBox.remove(beforeAfterBox.size()-1);
		}
		repaint();
	}
	public void copyAndPaste(MouseEvent e) {
		currentShape = onShape(e.getPoint());
		if(currentShape != null) {
			menuBar.shape = currentShape.clone();
			menuBar.shape.setShape(currentShape.getShape());
		}
	}
	public void EraserTransforming(MouseEvent e) {
		int index = onShapeIndex(e.getPoint());
		if(index != -1) {
			shapes.remove(index);
		}
	}
	public void ColorTransforming(MouseEvent e, Color color) {
		currentShape = onShape(e.getPoint());
		if(currentShape != null) {
			currentShape.settingColor(color);
			menuBar.colorBoxBTN.setSelected(false);
		}
	}
	private class MouseEventHandler implements MouseListener, MouseMotionListener {

		int x1, y1, x2, y2;

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 1) {
				mouse1Clicked(e);
			} else if (e.getClickCount() == 2) {
				click2Int++;
				toolBar3.now2Click.setText(Integer.toString(click2Int));
				mouse2Clicked(e);
			}
		}
		
		private void mouse1Clicked(MouseEvent e) {
			if (eDrawingState == EDrawingState.eIdel) {
				initTransforming(e.getX(), e.getY());
				copyAndPaste(e);
			} else if (eDrawingState == EDrawingState.eTransforming) {
				continueTransforming(e.getX(), e.getY());
			}
			if(menuBar.colorBoxBTN.isSelected() && menuBar.getColor() != null) {
				if(!(toolBar.getESelectedShape().getGShape() instanceof GPolygon)) {
				ColorTransforming(e, menuBar.getColor());
				}
			}
			
		}

		private void mouse2Clicked(MouseEvent e) {
			if (eDrawingState == EDrawingState.eIdel) {
				currentShape = onShape(e.getPoint());
				if(currentShape != null) {
					GInfoFrame gInfoFrame = new GInfoFrame(currentShape, shapes);
					gInfoFrame.setVisible(true);
				}
			}
			if (eDrawingState == EDrawingState.eTransforming) {
				finalizeTransforming(e.getX(), e.getY());
				eDrawingState = EDrawingState.eIdel;
			}
		}
		
		@Override
		public void mouseMoved(MouseEvent e) {
			if (eDrawingState == EDrawingState.eTransforming) {
				keepTransforming(e.getX(), e.getY());
			} else if(eDrawingState == EDrawingState.eIdel){
				changeCursors(e.getX(), e.getY());
			}
			toolBar3.nowX.setText(Integer.toString(e.getX()));
			toolBar3.nowY.setText(Integer.toString(e.getY()));
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (eDrawingState == EDrawingState.eIdel) {
				initTransforming(e.getX(), e.getY());
				eDrawingState = EDrawingState.eTransforming;
			}
			clickInt++;
			toolBar3.nowClick.setText(Integer.toString(clickInt));
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			if (eDrawingState == EDrawingState.eTransforming) {
				keepTransforming(e.getX(), e.getY());
			}
			currentShape.bSelected = false;
			if(menuBar.eraserBTN.isSelected()) {
				EraserTransforming(e);
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (eDrawingState == EDrawingState.eTransforming && toolBar.getESelectedShape() != EShape.ePolygon) {
				finalizeTransforming(e.getX(), e.getY());
				eDrawingState = EDrawingState.eIdel;
			}
			menuBar.eraserBTN.setSelected(false);
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}
	}
}
