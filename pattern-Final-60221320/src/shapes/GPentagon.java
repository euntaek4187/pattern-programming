package shapes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;

public class GPentagon extends GShape{
	private int px, py;

	@Override
	public GShape clone() {
		return new GPentagon();
	}
	public int[] x = new int[5];
	public int[] y = new int[5];
	
	int firtsX;
	int firtsY;

	public GPentagon() {
		for(int i = 0; i < 5; i++) {
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
		
		polygon.xpoints[0] = (firtsX+lastX)/2;
		polygon.xpoints[1] = lastX;
		polygon.xpoints[2] = (firtsX+lastX)/2+(lastX-(firtsX+lastX)/2)/2;
		polygon.xpoints[3] = (firtsX+lastX)/2-(lastX-(firtsX+lastX)/2)/2;
		polygon.xpoints[4] = firtsX;

		polygon.ypoints[0] = firtsY;
		polygon.ypoints[1] = (int) ((firtsY+lastY)/2);
		polygon.ypoints[2] = lastY;
		polygon.ypoints[3] = lastY;
		polygon.ypoints[4] = (int) ((firtsY+lastY)/2);
	}

	
	@Override
	public void setPoint(int x, int y) {
		this.px = x;
		this.py = y;
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

