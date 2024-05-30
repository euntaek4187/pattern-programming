package transformer;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.Vector;

import shapes.GShape;

public class GSelect extends GTransformer {

	public GSelect(GShape Shape) {
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
		double x1 = this.shape.getShape().getBounds().getX();
		double y1 = this.shape.getShape().getBounds().getY();
		double x2 = x1 + this.shape.getShape().getBounds().getWidth();
		double y2 = y1 + this.shape.getShape().getBounds().getHeight();
		
		for (GShape shape : shapes) {
			double shapex1 = shape.getShape().getBounds().getX();
			double shapey1 = shape.getShape().getBounds().getY();
			double shapex2 = shapex1 + shape.getShape().getBounds().getWidth();
			double shapey2 = shapey1 + shape.getShape().getBounds().getHeight();
			
			if ((shapex1 >= x1 && shapex2 <= x2)&&(shapey1 >= y1 && shapey2 <= y2)) {
				shape.setSelected(true);
				shape.isGrouping = true;
			}
		}
	}

}
