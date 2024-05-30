package transformer;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.Vector;

import shapes.GShape;

public class NPDrawer extends GTransformer {

	public NPDrawer(GShape Shape) {
		super(Shape);
	}

	public void initTransform(int x, int y, Graphics2D graphics2D) {
		this.shape.setShape(x, y, x, y);
		
	}

	public void keepTransform(int x, int y, Graphics2D graphics2D, Dimension panelSize) {
		this.shape.draw(graphics2D);
		this.shape.resizePoint(x, y);
		this.shape.draw(graphics2D);
		
	}

	public void finalizeTransform(int x, int y, Graphics2D graphics2D, Vector<GShape> shapes) {
		shapes.add(this.shape);
		this.shape.setSelected(true);
		this.shape.draw(graphics2D);
	}
	
	
	public void continueTransform(int x, int y, Graphics2D graphics2d) {
		this.shape.addPoint(x,y);
		this.shape.draw(graphics2d);
	}
}
