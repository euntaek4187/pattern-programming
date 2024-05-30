package shapes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;

public class GBottom extends GShape{
	private int px, py;

	@Override
	public GShape clone() {
		return new GBottom();
	}
	public int[] x = new int[7];
	public int[] y = new int[7];
	
	int firtsX;
	int firtsY;

	public GBottom() {
		for(int i = 0; i < 7; i++) {
			x[i] = 10;
			y[i] = 10;
		}
	}
	@Override
	public void setShape(int x1, int y1, int x2, int y2) {
		this.shape = new Polygon(x, y, x.length);
		firtsX = x1;
		firtsY = y1;
	}
	

	@Override
	public void resizePoint(int lastX, int lastY) {
		Polygon polygon = ((Polygon)shape);
		
		polygon.xpoints[0] = firtsX;
		polygon.xpoints[1] = (firtsX+lastX)/2-(lastX-(firtsX+lastX)/2)/2;
		polygon.xpoints[2] = (firtsX+lastX)/2-(lastX-(firtsX+lastX)/2)/2;
		polygon.xpoints[3] = (firtsX+lastX)/2+(lastX-(firtsX+lastX)/2)/2;
		polygon.xpoints[4] = (firtsX+lastX)/2+(lastX-(firtsX+lastX)/2)/2;
		polygon.xpoints[5] = lastX;
		polygon.xpoints[6] = (firtsX+lastX)/2;

		polygon.ypoints[0] = (firtsY+lastY)/2+(lastY-(firtsY+lastY)/2)/2;
		polygon.ypoints[1] = (firtsY+lastY)/2+(lastY-(firtsY+lastY)/2)/2;
		polygon.ypoints[2] = firtsY;
		polygon.ypoints[3] = firtsY;
		polygon.ypoints[4] = (firtsY+lastY)/2+(lastY-(firtsY+lastY)/2)/2;
		polygon.ypoints[5] = (firtsY+lastY)/2+(lastY-(firtsY+lastY)/2)/2;
		polygon.ypoints[6] = lastY;
	}

	

	@Override
	public void setPoint(int x, int y) {
		this.px = x;
		this.py = y;
	}

	public void fillColor(Color color, Graphics graphics) {
		this.color = color;
		Polygon polygon = (Polygon)shape;
		Graphics2D graphics2d = (Graphics2D) graphics;
		graphics.setColor(color);
		graphics.fillPolygon(polygon);
		graphics2d.draw(shape);
	}
	

	@Override
	public void setThickness(int thickness) {
		this.thickInt = thickness;
	}
	@Override
	public void movePoint(int x, int y) {
		// TODO Auto-generated method stub
		
	}
}
