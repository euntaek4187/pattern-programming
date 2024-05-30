package shapes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;

public class GTalkBox extends GShape {

	private int px, py;

	@Override
	public GShape clone() {
		return new GTalkBox();
	}
	public int[] x = new int[7];
	public int[] y = new int[7];
	
	int firtsX;
	int firtsY;

	public GTalkBox() {
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
		
		int minX = (lastX-(firtsX + lastX)/2)/2/2;
		int minY = (lastY-(firtsY + lastY)/2)/2/2;
		int middleX = (firtsX + lastX)/2;
		int middleY = (firtsY + lastY)/2;
		
		polygon.xpoints[0] = firtsX;
		polygon.xpoints[1] = lastX;
		polygon.xpoints[2] = lastX;
		polygon.xpoints[3] = middleX-minX;
		polygon.xpoints[4] = firtsX+minX;
		polygon.xpoints[5] = firtsX+minX;
		polygon.xpoints[6] = firtsX;


		polygon.ypoints[0] = firtsY;
		polygon.ypoints[1] = firtsY;
		polygon.ypoints[2] = lastY - minY;
		polygon.ypoints[3] = lastY - minY;
		polygon.ypoints[4] = lastY;
		polygon.ypoints[5] = lastY - minY;
		polygon.ypoints[6] = lastY - minY;

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
