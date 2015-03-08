import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTree;
import java.awt.BorderLayout;
import javax.swing.JEditorPane;
import javax.swing.JScrollBar;


public class MainWindow {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTree tree = new JTree();
		frame.getContentPane().add(tree, BorderLayout.WEST);
		
		JEditorPane dtrpnEditorPane = new JEditorPane();
		dtrpnEditorPane.setText("Editor Pane");
		frame.getContentPane().add(dtrpnEditorPane, BorderLayout.CENTER);
		
		JScrollBar scrollBar = new JScrollBar();
		frame.getContentPane().add(scrollBar, BorderLayout.EAST);
	}

}
