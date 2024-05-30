package shapes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;

public class GDiamond extends GShape{
	private int px, py;

	@Override
	public GShape clone() {
		return new GDiamond();
	}
	public int[] x = new int[4];
	public int[] y = new int[4];
	
	int firtsX;
	int firtsY;

	public GDiamond() {
		for(int i = 0; i < 4; i++) {
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
		int polyM = (int) (0.5*(firtsY+lastY));
		int polyM2 = (int) (0.5*(lastX-firtsX));
		
		polygon.xpoints[0] = (firtsX+lastX)/2;
		polygon.xpoints[1] = lastX;
		polygon.xpoints[2] = (firtsX+lastX)/2;
		polygon.xpoints[3] = firtsX;

		polygon.ypoints[0] = firtsY;
		polygon.ypoints[1] = (firtsY+lastY)/2;
		polygon.ypoints[2] = lastY;
		polygon.ypoints[3] = (firtsY+lastY)/2;
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
