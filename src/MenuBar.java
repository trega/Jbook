import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;


public class MenuBar extends JMenuBar {

	private static final long serialVersionUID = -2516280312435022773L;
	
	private JMenu menu;
	private JMenuItem menuItem;

	public MenuBar() {
		initialize();
	}
	private void initialize(){
		menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription(
				"The only menu in this program that has menu items");
		this.add(menu);

		initializeMenu();
	}
	private void initializeMenu() {
		menuItem = new JMenuItem("Save",
				KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription(
				"This doesn't really do anything");
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Load",
				KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_2, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription(
				"This doesn't really do anything");
		menu.add(menuItem);
	}

}
