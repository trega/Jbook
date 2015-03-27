import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class Serializer {
	private String filename;
	
	public Serializer(String filename){
		this.filename = filename;
	}
	
	public void serializeTree(NotebookTree tree){
		try {
			FileOutputStream fileOut = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(tree);
			out.flush();
			out.close();
			fileOut.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public NotebookTree deserializeTree(){
		NotebookTree tree = null;
		ObjectInputStream in = null;
		try {
			FileInputStream file = new FileInputStream(filename);
			in = new ObjectInputStream(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			tree = (NotebookTree)in.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		 try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 return tree;
	}
}
