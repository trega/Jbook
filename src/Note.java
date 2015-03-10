import java.io.IOException;
import java.net.URL;

import javax.swing.JEditorPane;


public class Note extends JEditorPane {

	private static final long serialVersionUID = 1L;

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
		// TODO Auto-generated constructor stub
	}

}
