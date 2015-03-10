import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.io.IOException;

public class MainWindow {

	private JFrame frame;
	private Notebook defaultNotebook;
	private Note currentNote;

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
		
		try {
			defaultNotebook = new Notebook("Default Notebook");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		NotebookTree notebooks_tree = new NotebookTree(this, defaultNotebook);
		frame.getContentPane().add(notebooks_tree, BorderLayout.WEST);		
		
	}

	void setCurrentNote(Note note){
		currentNote=note;
		frame.getContentPane().add(currentNote, BorderLayout.CENTER);	
	}
	
}
