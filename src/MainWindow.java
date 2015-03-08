import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTree;

import java.awt.BorderLayout;

import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.UIManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;


public class MainWindow {

	private JFrame frame;
	private JLabel selectedLabel;
	private Notebook defaultNotebook;;

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
		frame.setTitle("Jbook ver1.0");
		
		defaultNotebook = new Notebook("Default Notebook");
		
		NotebookTree notebooks_tree = new NotebookTree(defaultNotebook);
		frame.getContentPane().add(notebooks_tree, BorderLayout.WEST);
		
		JEditorPane dtrpnEditorPane = new JEditorPane();
		dtrpnEditorPane.setText("Type here");
		frame.getContentPane().add(dtrpnEditorPane, BorderLayout.CENTER);
		
		selectedLabel = new JLabel();
		frame.add(selectedLabel, BorderLayout.SOUTH);
		
	}
}
