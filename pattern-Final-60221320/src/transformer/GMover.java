package transformer;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.Vector;

import shapes.GShape;

public class GMover extends GTransformer {

	private AffineTransform affineTransform;
	private int px, py;
	
	public GMover(GShape Shape) {
		super(Shape);
		this.affineTransform = new AffineTransform();
	}

	public void initTransform(int x, int y, Graphics2D graphics2D) {
		px = x;
		py = y;
	}

	public void keepTransform(int x, int y, Graphics2D graphics2D, Dimension panelSize) {
	    this.shape.draw(graphics2D);

	    int dx = x - px;
	    int dy = y - py;

	    int nextX = shape.getShape().getBounds().x + dx;
	    int nextY = shape.getShape().getBounds().y + dy;
	    if (nextX + shape.getShape().getBounds().width > panelSize.width) {
	        dx = panelSize.width - shape.getShape().getBounds().width - shape.getShape().getBounds().x;
	    }
	    if (nextY + shape.getShape().getBounds().height > panelSize.height-33) {
	        dy = panelSize.height - 33 - shape.getShape().getBounds().height - shape.getShape().getBounds().y;
	    }
	    if (nextX < 0) {
	        dx = shape.getShape().getBounds().x;
	    }
	    if (nextY < 0) {
	        dy = shape.getShape().getBounds().y;
	    }
	    this.affineTransform.setToTranslation(dx, dy);
	    Shape transformedShape = this.affineTransform.createTransformedShape(this.shape.getShape());
	    this.shape.setShape(transformedShape);
	    this.shape.draw(graphics2D);
	    px = x;
	    py = y;
	}

	public void keepTransform2(int x, int y, Graphics2D graphics2d, Vector<GShape> shapes) {
		
		for (GShape shape : shapes) {
			if (shape.isGrouping == true) {
				shape.draw(graphics2d);
				this.affineTransform.setToTranslation(x-px, y-py);
				Shape transformedShape = this.affineTransform.createTransformedShape(shape.getShape());
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
