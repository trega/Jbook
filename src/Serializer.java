import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class Serializer {
	private FileOutputStream fileOut;
	public Serializer(String filename){
		try {
			fileOut = new FileOutputStream(filename);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void serializeTree(NotebookTree tree){
		try {
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(tree);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
