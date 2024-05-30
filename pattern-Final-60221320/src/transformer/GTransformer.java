package transformer;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.Vector;

import shapes.GShape;

abstract public class GTransformer {
	
	protected GShape shape;
	
	public GTransformer(GShape Shape) {
		this.shape = Shape;
	}

	abstract public void initTransform(int x, int y, Graphics2D graphics2d);
	abstract public void keepTransform(int x, int y, Graphics2D graphics2d, Dimension panelSize);
	public void continueTransform(int x, int y, Graphics2D graphics2d) {}
	abstract public void finalizeTransform(int x, int y, Graphics2D graphics2d, Vector<GShape> shapes);

	public void keepTransform2(int x, int y, Graphics2D graphics2d, Vector<GShape> shapes) {}
}
