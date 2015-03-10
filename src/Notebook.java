import java.io.IOException;

import javax.swing.tree.DefaultMutableTreeNode;

public class Notebook extends DefaultMutableTreeNode{
	private static final long serialVersionUID = 1L;
	private Note note;
	
	public Notebook(String nodeName) throws IOException{
		super(nodeName);
		setNote(new Note("text/html",nodeName));
	}

	public Note getNote() {
		return note;
	}

	public void setNote(Note note) {
		this.note = note;
	}
	
}
