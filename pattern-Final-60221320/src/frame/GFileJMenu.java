package frame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class GFileJMenu extends JMenu {
	private static final long serialVersionUID = 1L;
	
	private JMenuItem itemNew;

	public GFileJMenu(String title) {
		super(title);
		this.itemNew = new JMenuItem("new");
		this.add(itemNew);
	}

}
