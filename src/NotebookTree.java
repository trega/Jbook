import java.util.Hashtable;
import java.util.Vector;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;


public class NotebookTree extends JTree {
	private static final long serialVersionUID = 1L;
	private NotebookTree this_tree;

	public NotebookTree() {
		super();
		initialize();
		fillDefaultContent();
		
	}

	public NotebookTree(Object[] value) {
		super(value);
		initialize();
		// TODO Auto-generated constructor stub
	}

	public NotebookTree(Vector<?> value) {
		super(value);
		// TODO Auto-generated constructor stub
	}

	public NotebookTree(Hashtable<?, ?> value) {
		super(value);
		initialize();
		// TODO Auto-generated constructor stub
	}

	public NotebookTree(TreeNode root) {
		super(root);
		initialize();
		fillDefaultContent();
	}

	public NotebookTree(TreeModel newModel) {
		super(newModel);
		initialize();
		// TODO Auto-generated constructor stub
	}

	public NotebookTree(TreeNode root, boolean asksAllowsChildren) {
		super(root, asksAllowsChildren);
		initialize();
		// TODO Auto-generated constructor stub
	}
	
	private void initialize(){
		this_tree = this;
		registerEventHandlers();
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
	
	private void registerEventHandlers(){
		registerTreeSelectionListener();
	}
	
	
	
	private void registerTreeSelectionListener(){		
		this_tree.addTreeSelectionListener(new TreeSelectionListener() {
		    public void valueChanged(TreeSelectionEvent e) {
		    	Notebook node = (Notebook)this_tree.getLastSelectedPathComponent();
		    	
		    /* if nothing is selected */ 
		        if (node == null) return;

		    /* retrieve the node that was selected */ 
		    //    Object nodeInfo = node.getUserObject();
		        //...
		    /* React to the node selection. */
		        //...
		    }
		});
	}

}
