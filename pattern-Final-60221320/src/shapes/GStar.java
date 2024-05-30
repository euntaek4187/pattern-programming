package shapes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;

public class GStar extends GShape{
	private int px, py;

	@Override
	public GShape clone() {
		return new GStar();
	}
	public int[] x = new int[10];
	public int[] y = new int[10];
	
	int firtsX;
	int firtsY;

	public GStar() {
		for(int i = 0; i < 10; i++) {
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
		polygon.xpoints[1] = middleX-minX;
		polygon.xpoints[2] = middleX;
		polygon.xpoints[3] = middleX+minX;
		polygon.xpoints[4] = lastX;;
		polygon.xpoints[5] = middleX+minX*2;
		polygon.xpoints[6] = middleX+minX*3;
		polygon.xpoints[7] = middleX;
		polygon.xpoints[8] = middleX-minX*3;
		polygon.xpoints[9] = middleX-minX*2;

		polygon.ypoints[0] = middleY-minY;
		polygon.ypoints[1] = middleY-minY;
		polygon.ypoints[2] = firtsY;
		polygon.ypoints[3] = middleY-minY;
		polygon.ypoints[4] = middleY-minY;
		polygon.ypoints[5] = middleY+minY;
		polygon.ypoints[6] = lastY;
		polygon.ypoints[7] = middleY+minY*2;
		polygon.ypoints[8] = lastY;
		polygon.ypoints[9] = middleY+minY;
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
