import java.util.Hashtable;
import java.util.Vector;

import javax.swing.JTree;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;


public class NotebookTree extends JTree {
	private static final long serialVersionUID = 1L;

	public NotebookTree() {
		super();
		fillDefaultContent();
	}

	public NotebookTree(Object[] value) {
		super(value);
		// TODO Auto-generated constructor stub
	}

	public NotebookTree(Vector<?> value) {
		super(value);
		// TODO Auto-generated constructor stub
	}

	public NotebookTree(Hashtable<?, ?> value) {
		super(value);
		// TODO Auto-generated constructor stub
	}

	public NotebookTree(TreeNode root) {
		super(root);
		fillDefaultContent();
	}

	public NotebookTree(TreeModel newModel) {
		super(newModel);
		// TODO Auto-generated constructor stub
	}

	public NotebookTree(TreeNode root, boolean asksAllowsChildren) {
		super(root, asksAllowsChildren);
		// TODO Auto-generated constructor stub
	}
	
	private void fillDefaultContent(){
		Notebook root = (Notebook)getModel().getRoot();
		Notebook javaNotebook = new Notebook("Java");
		Notebook swingNotebook = new Notebook("Swing");
		root.add(javaNotebook);
		javaNotebook.add(swingNotebook);
		Notebook otherNotebook = new Notebook("Other");
		root.add(otherNotebook);
		Notebook todoNotebook = new Notebook("TODO");
		otherNotebook.add(todoNotebook);			
	}

}
