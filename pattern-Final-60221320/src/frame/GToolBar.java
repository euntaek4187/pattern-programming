package frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

import main.GConstants.EShape;
import shapes.GLine;
import shapes.GOval;
import shapes.GPolygon;
import shapes.GRectangle;
import shapes.GSelect;
import shapes.GShape;

public class GToolBar extends JToolBar {
	private static final long serialVersionUID = 1L;
	


	public EShape eSelectedShape;
	
	ButtonGroup buttonGroup;
	
	public EShape getESelectedShape() {
		return this.eSelectedShape;
	}
	public void resetESelectedShape() {
		this.buttonGroup.clearSelection();
		eSelectedShape = EShape.eSelect;
		
	}

	public GToolBar() {
		super();
		this.buttonGroup = new ButtonGroup();
		ActionHandler actionHandler = new ActionHandler();
		
		for(EShape eButtonShape: EShape.values()) {
			if(eButtonShape != EShape.eSelect) {
			JRadioButton buttonShape = new JRadioButton(eButtonShape.getName());
			this.add(buttonShape);
			buttonGroup.add(buttonShape);
			buttonShape.addActionListener(actionHandler);
			buttonShape.setActionCommand(eButtonShape.toString());
			}
		}
		resetESelectedShape();
	}
	
	private class ActionHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			eSelectedShape = EShape.valueOf(e.getActionCommand());
		}
		
	}
}
