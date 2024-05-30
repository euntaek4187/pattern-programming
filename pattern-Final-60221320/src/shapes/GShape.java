package shapes;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;

import main.GConstants.EAnchors;

abstract public class GShape{
	//protected int x1, y1, x2, y2;
	
	protected Shape shape;
	public boolean bSelected;
	private GAnchors gAnchors;
	public boolean isGrouping;
	public Color color;
	public int thickInt;

	
	public GShape(){
		this.gAnchors = new GAnchors();
		this.bSelected = false;
		this.isGrouping = false;
	}
	
	abstract public GShape clone();
	
	public void setShape(Shape shape) {this.shape = shape;}
	
	public Shape getShape() {
		return this.shape;
	}
	
	public boolean onShape(Point p) {
		return shape.contains(p);
	}

	public void draw(Graphics graphics) {
		Graphics2D graphics2d = (Graphics2D) graphics;
		if (color != null) {
			graphics2d.setColor(color);
			graphics2d.fill(shape);
		} else {
			graphics.setColor(Color.black);
		}
		graphics2d.setStroke(new BasicStroke(thickInt));
		graphics2d.draw(shape);
		if(this.bSelected) {
			this.gAnchors.draw(graphics2d, this.shape.getBounds());
		}
	}

	public void setSelected(boolean bSelected) {
		this.bSelected = bSelected;
	}
	public EAnchors onShape(int x, int y) {
		if(this.bSelected) {
			 EAnchors eAnchor = this.gAnchors.onShape(x,y);
			if(eAnchor != null) {
				return eAnchor;
			}
		}
		if (this.shape.getBounds().contains(x,y)) {
			return EAnchors.MM;
		}
		return null;
	}

	public abstract void setShape(int x1, int y1, int x2, int y2);
	public abstract void resizePoint(int x, int y);
	public void addPoint(int x, int y) {}
	public abstract void movePoint(int x, int y);
	public abstract void setPoint(int x, int y);

	public void settingColor(Color color) {
		this.color = color;
	}

	public void setThickness(int thickness) {
		this.thickInt = thickness;
	}

}
