import java.io.IOException;
import java.io.Serializable;
import java.net.URL;

import javax.swing.JEditorPane;


public class Note extends JEditorPane implements Serializable{

	private static final long serialVersionUID = 430310972298382568L;
	private String title;

	public Note() {
		// TODO Auto-generated constructor stub
	}

	public Note(URL initialPage) throws IOException {
		super(initialPage);
		// TODO Auto-generated constructor stub
	}

	public Note(String url) throws IOException {
		super(url);
		// TODO Auto-generated constructor stub
	}

	public Note(String type, String text) {
		super(type, text);
		title=text;
		// TODO Auto-generated constructor stub
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
