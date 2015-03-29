import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;


public class MenuBar extends JMenuBar implements ActionListener {

	private static final long serialVersionUID = -2516280312435022773L;
	
	private JMenu menu;
	private JMenuItem menuItem;
	private MainWindow main_window;

	public MenuBar(MainWindow window) {
		main_window = window;
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
		menuItem = new JMenuItem("Save", KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription(
				"This doesn't really do anything");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Load",
				KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_2, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription(
				"This doesn't really do anything");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menu.addSeparator();
		menuItem = new JMenuItem("New user", KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_3, ActionEvent.ALT_MASK));
		menuItem.addActionListener(this);
		menu.add(menuItem);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand() == "Save"){
			serviceSaveRequest();
		}else if(arg0.getActionCommand() == "Load"){
			serviceLoadRequest();
		}else if(arg0.getActionCommand() == "New user"){
			serviceNewUserRequest();
		}
	}
	
	private void serviceNewUserRequest() {
		main_window.addNewUser();
		
	}
	private void serviceSaveRequest(){
		main_window.saveFiles();
	}
	
	private void serviceLoadRequest(){
		main_window.LoadNotebookTree();
	}

}
