package transformer;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.Vector;

import shapes.GShape;

public class GResizor extends GTransformer {

	private AffineTransform affineTransform;
	private double startX, startY;
	private double startWidth, startHeight;
	private Shape initialShape;
	private int px, py;
	
	public GResizor(GShape Shape) {
		super(Shape);
		this.affineTransform = new AffineTransform();
	}

	Rectangle2D bounds;
	public void initTransform(int x, int y, Graphics2D graphics2D) {
		bounds = shape.getShape().getBounds2D();
		this.startX = bounds.getX();
		this.startY = bounds.getY();
		this.startWidth = bounds.getWidth();
		this.startHeight = bounds.getHeight();
		this.initialShape = new AffineTransform().createTransformedShape(shape.getShape());
		px = x;
		py = y;
	}

	public void keepTransform(int x, int y, Graphics2D graphics2D, Dimension panelSize) {
		shape.draw(graphics2D);
		double sx = ((double)x - startX) / startWidth;
		double sy = ((double)y - startY) / startHeight;
		affineTransform.setTransform(sx, 0, 0, sy, (1 - sx) * startX, (1 - sy) * startY);
		shape.setShape(affineTransform.createTransformedShape(initialShape));
		shape.draw(graphics2D);
	}
	
	public void keepTransform2(int x, int y, Graphics2D graphics2d, Vector<GShape> shapes) {
	    for (GShape shape : shapes) {
	        if (shape.isGrouping == true) {

	            Rectangle2D bounds = shape.getShape().getBounds2D();
	            double shapeX = bounds.getX();
	            double shapeY = bounds.getY();

	            affineTransform.setToTranslation(-shapeX, -shapeY);
	            Shape translatedShape = affineTransform.createTransformedShape(shape.getShape());

	            shape.draw(graphics2d);
	            double sx = (double)x / px;
	            double sy = (double)y / py;
	            this.affineTransform.setToScale(sx, sy);
	            Shape scaledShape = this.affineTransform.createTransformedShape(translatedShape);

	            affineTransform.setToTranslation(shapeX, shapeY);
	            Shape transformedShape = this.affineTransform.createTransformedShape(scaledShape);

	            shape.setShape(transformedShape);
	            shape.draw(graphics2d);
	        }
	    }
	    px = x;
	    py = y;
	}

	
	public void finalizeTransform(int x, int y, Graphics2D graphics2D, Vector<GShape> shapes) {
		this.shape.setSelected(true);
		this.shape.draw(graphics2D);
	}
}
