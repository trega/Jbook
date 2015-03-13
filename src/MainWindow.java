import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import java.awt.BorderLayout;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainWindow {

	private JFrame frame;
	private Notebook defaultNotebook;
	private Note currentNote;
	private JLayeredPane desktop;
	private Map<Note, SelfInternalFrame> opened_notes;

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
		opened_notes = new HashMap<Note, SelfInternalFrame>();
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
		
		desktop = new JLayeredPane();
		desktop.setOpaque(false);
		//desktop.add(createLayer("Open 1"), JLayeredPane.POPUP_LAYER);
		desktop.setPreferredSize(new Dimension(100, 100));
		desktop.setBorder(BorderFactory.createTitledBorder("Desktop"));
		frame.add(this.desktop, BorderLayout.CENTER);
		
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
		currentNote = note;
		if(opened_notes.containsKey(currentNote) == false){
			SelfInternalFrame new_note_frame = createLayer("Open 1");
			new_note_frame.setNote(currentNote);
			opened_notes.put(currentNote, new_note_frame);
			this.getDesktop().add(new_note_frame, JLayeredPane.POPUP_LAYER);
		}
	}

	public JLayeredPane getDesktop() {
		return desktop;
	}
	
	public static SelfInternalFrame createLayer(String label) {
	    return new SelfInternalFrame(label);
	}
	
	static class SelfInternalFrame extends JInternalFrame {
		private static final long serialVersionUID = 1L;
		private Note note;
		public SelfInternalFrame(String s) {
	     // getContentPane().add(new JLabel(s), BorderLayout.CENTER);
	      setBounds(50, 50, 100, 100);
	      setTitle(s);
	      setResizable(true);
	      setClosable(true);
	      setMaximizable(true);
	      setIconifiable(true);
	      setTitle(s);
	      setVisible(true);
	    }
		public Note getNote() {
			return note;
		}
		public void setNote(Note note) {
			this.note = note;
			add(note);
		}
	 }
	
}
