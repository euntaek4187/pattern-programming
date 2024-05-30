package transformer;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.Vector;

import shapes.GShape;

public class GRotate extends GTransformer {
    private AffineTransform affineTransform;
    private double initialAngle;

    public GRotate(GShape shape) {
        super(shape);
        this.affineTransform = new AffineTransform();
    }

    public void initTransform(int x, int y, Graphics2D graphics2D) {
        Rectangle bounds = this.shape.getShape().getBounds();
        double centerX = bounds.getX() + bounds.getWidth() / 2;
        double centerY = bounds.getY() + bounds.getHeight() / 2;
        this.initialAngle = Math.atan2(centerY - y, centerX - x);
    }

    public void keepTransform(int x, int y, Graphics2D graphics2D, Dimension panelSize) {
        Rectangle bounds = this.shape.getShape().getBounds();
        double centerX = bounds.getX() + bounds.getWidth() / 2;
        double centerY = bounds.getY() + bounds.getHeight() / 2;
        double currentAngle = Math.atan2(centerY - y, centerX - x);
        double rotation = currentAngle - initialAngle;

        this.affineTransform.setToRotation(rotation, centerX, centerY);
        Shape transformedShape = this.affineTransform.createTransformedShape(this.shape.getShape());
        this.shape.setShape(transformedShape);
        this.shape.draw(graphics2D);
        this.initialAngle = currentAngle;
    }

    public void keepTransform2(int x, int y, Graphics2D graphics2D, Vector<GShape> shapes) {
        Rectangle baseBounds = this.shape.getShape().getBounds();
        double baseCenterX = baseBounds.getX() + baseBounds.getWidth() / 2;
        double baseCenterY = baseBounds.getY() + baseBounds.getHeight() / 2;
        double baseCurrentAngle = Math.atan2(baseCenterY - y, baseCenterX - x);
        double baseRotation = baseCurrentAngle - this.initialAngle;
        
        for (GShape shape : shapes) {
            Rectangle bounds = shape.getShape().getBounds();
            double centerX = bounds.getX() + bounds.getWidth() / 2;
            double centerY = bounds.getY() + bounds.getHeight() / 2;
            this.affineTransform.setToRotation(baseRotation, centerX, centerY);
            Shape transformedShape = this.affineTransform.createTransformedShape(shape.getShape());
            shape.setShape(transformedShape);
            shape.draw(graphics2D);
        }
        this.initialAngle = baseCurrentAngle;
    }


    public void finalizeTransform(int x, int y, Graphics2D graphics2D, Vector<GShape> shapes) {
        this.shape.setSelected(true);
        this.shape.draw(graphics2D);
    }
}








