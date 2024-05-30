package shapes;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

public class GSelect extends GRectangle{
		
		public GSelect(){
		}
		
		
		//점선으로 바꿔보자
		public void draw(Graphics graphics) {
			Graphics2D graphics2d = (Graphics2D) graphics;
			graphics2d.setColor(Color.CYAN);
			graphics2d.draw(shape);
		}
	}
