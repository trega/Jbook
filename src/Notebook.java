import java.io.IOException;
import java.io.Serializable;

import javax.swing.tree.DefaultMutableTreeNode;

public class Notebook extends DefaultMutableTreeNode implements Serializable{
	private static final long serialVersionUID = 167415613186037229L;
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
