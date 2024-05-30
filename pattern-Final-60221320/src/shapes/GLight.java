package shapes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;

public class GLight extends GShape{
	private int px, py;

	@Override
	public GShape clone() {
		return new GLight();
	}
	public int[] x = new int[11];
	public int[] y = new int[11];
	
	int firtsX;
	int firtsY;

	public GLight() {
		for(int i = 0; i < 11; i++) {
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
		polygon.xpoints[2] = middleX+minX/2;
		polygon.xpoints[3] = middleX;
		polygon.xpoints[4] = middleX+minX*2;
		polygon.xpoints[5] = middleX+minX;
		polygon.xpoints[6] = lastX;
		polygon.xpoints[7] = middleX-minX;
		polygon.xpoints[8] = middleX;
		polygon.xpoints[9] = middleX-minX*3;
		polygon.xpoints[10] = middleX-minX*2;

		polygon.ypoints[0] = firtsY+minY;
		polygon.ypoints[1] = firtsY;
		polygon.ypoints[2] = middleY-minY*2;
		polygon.ypoints[3] = middleY-minY;
		polygon.ypoints[4] = middleY+minY;
		polygon.ypoints[5] = middleY+minY*2;
		polygon.ypoints[6] = lastY;
		polygon.ypoints[7] = middleY+minY*3;
		polygon.ypoints[8] = middleY+minY*2;
		polygon.ypoints[9] = middleY;
		polygon.ypoints[10] = middleY-minY;
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
