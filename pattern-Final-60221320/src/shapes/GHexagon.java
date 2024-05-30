package shapes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

public class GHexagon extends GShape {
	private int px, py;

	@Override
	public GShape clone() {
		return new GHexagon();
	}
	public int[] x = new int[6];
	public int[] y = new int[6];
	
	int firtsX;
	int firtsY;

	public GHexagon() {
		for(int i = 0; i < 6; i++) {
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
		
		polygon.xpoints[0] = firtsX;
		polygon.xpoints[1] = lastX;
		polygon.xpoints[2] = lastX+polyM2;
		polygon.xpoints[3] = lastX;
		polygon.xpoints[4] = firtsX;
		polygon.xpoints[5] = firtsX-polyM2;

		polygon.ypoints[0] = lastY;
		polygon.ypoints[1] = lastY;
		polygon.ypoints[2] = polyM;
		polygon.ypoints[3] = firtsY;
		polygon.ypoints[4] = firtsY;
		polygon.ypoints[5] = polyM;
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




