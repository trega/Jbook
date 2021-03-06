import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import java.awt.BorderLayout;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainWindow {

	private JFrame frame;
	private Notebook defaultNotebook;
	private Note currentNote;
	private JLayeredPane desktop;
	private Map<Note, SelfInternalFrame> opened_notes;
	private NotebookTree notebooks_tree;
	private MenuBar main_menu;
	private Users users;
	private User current_user = null;
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
		users = new Users();
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
		
		showLoginDialog();
		
		main_menu = new MenuBar(this);
		frame.add(main_menu, BorderLayout.NORTH);
		
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
		notebooks_tree = new NotebookTree(this, defaultNotebook);
		frame.getContentPane().add(notebooks_tree, BorderLayout.WEST);		
		
	}
	
	private void showLoginDialog() {
		LoginDialog login_dlg = new LoginDialog(this);
		login_dlg.setVisible(true);
	}
	
	public boolean verifyLogin(String login, String pass){
		if(users.verifyLogin(login, pass)){
			current_user = new User(login, pass);
			return true;
		}else{
			return false;
		}
	}

	public void saveFiles(){
		Serializer out = new Serializer(current_user.getUsername()+"_notes.out");
		out.serializeTree(notebooks_tree);
	}
	
	public void addNewUser() {
		NewUserDialog dialog = new NewUserDialog(this);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}
	
	public void registerUser(User user) {
		if(users.addUser(user)){
			JOptionPane.showMessageDialog(frame, "User added successfully","Add new User",JOptionPane.INFORMATION_MESSAGE);
		}else{
			JOptionPane.showMessageDialog(frame, "User already exists","Add new User",JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public void LoadNotebookTree(){
		Serializer ser = new Serializer(current_user.getUsername()+"_notes.out");
		notebooks_tree.setVisible(false);
		frame.getContentPane().remove(notebooks_tree);
		notebooks_tree = ser.deserializeTree();
		notebooks_tree.postDeserialization(this);
		frame.getContentPane().add(notebooks_tree, BorderLayout.WEST);	
	}

	void setCurrentNote(Note note){
		currentNote = note;
		if(opened_notes.containsKey(currentNote) == false){
			SelfInternalFrame new_note_frame = createLayer(note.getTitle());
			new_note_frame.setNote(currentNote);
			opened_notes.put(currentNote, new_note_frame);
			
			this.getDesktop().add(new_note_frame, JLayeredPane.POPUP_LAYER);
			new_note_frame.addInternalFrameListener(new InternalFrameEventHandler(opened_notes, currentNote) );
			try {
				new_note_frame.setMaximum(true);
			} catch (PropertyVetoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		desktop.moveToFront(opened_notes.get(currentNote));
	}

	public JLayeredPane getDesktop() {
		return desktop;
	}
	public JFrame getFrame(){
		return frame;
	}
	
	public Users getUsers() {
		return users;
	}

	public void noteHasChanged(Note n){
		SelfInternalFrame frame = this.opened_notes.get(n);
		frame.setTitle(n.getTitle());
	}
	
	public void noteHasBeenRemoved(Note n){
		SelfInternalFrame frame = (SelfInternalFrame)opened_notes.get(n);
		getDesktop().remove(frame);
		opened_notes.remove(n);
		desktop.repaint();
	}
	
	public static SelfInternalFrame createLayer(String label) {
	    return new SelfInternalFrame(label);
	}
	
	static class SelfInternalFrame extends JInternalFrame{
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
	
	static class InternalFrameEventHandler implements InternalFrameListener {
		private Map<Note, SelfInternalFrame> opened_notes;
		private Note note;
		
		public InternalFrameEventHandler( Map<Note, SelfInternalFrame> a_notes, Note a_note){
			opened_notes = a_notes;
			note = a_note;
		}
		
		@Override
		public void internalFrameActivated(InternalFrameEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void internalFrameClosed(InternalFrameEvent arg0) {
			opened_notes.remove(note);
			
		}

		@Override
		public void internalFrameClosing(InternalFrameEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void internalFrameDeactivated(InternalFrameEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void internalFrameDeiconified(InternalFrameEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void internalFrameIconified(InternalFrameEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void internalFrameOpened(InternalFrameEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
