import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
//import javax.swing.event.MenuKeyEvent;
//import javax.swing.event.MenuKeyListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;


public class NotebookTree extends JTree {
	private static final long serialVersionUID = 1L;
	private NotebookTree this_tree;
	private MainWindow main_window;
	private JPopupMenu popup_menu;

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
	public NotebookTree(MainWindow mainWindow, TreeNode root){
		super(root);
		main_window = mainWindow;
		initialize();
		fillDefaultContent();
	}

	public NotebookTree(Vector<?> value) {
		super(value);
		// TODO Auto-generated constructor stub
	}

	public NotebookTree(TreeNode root) {
		super(root);
		initialize();
		fillDefaultContent();
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
		Notebook javaNotebook;
		try {
			javaNotebook = new Notebook("Java");
			Notebook swingNotebook = new Notebook("Swing");
			root.add(javaNotebook);
			javaNotebook.add(swingNotebook);
			Notebook otherNotebook = new Notebook("Other");
			root.add(otherNotebook);
			Notebook todoNotebook = new Notebook("TODO");
			otherNotebook.add(todoNotebook);	
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private void registerEventHandlers(){
		registerTreeSelectionListener();
		registerMouseListener();
		registerTreeModelListener();
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
		        if(node.isLeaf()){
		        	main_window.setCurrentNote(node.getNote());
		        }
		    }
		});
	}
	
	private void registerMouseListener(){
		this_tree.addMouseListener ( new MouseAdapter ()
	    {
	        public void mousePressed ( MouseEvent e )
	        {
	            if ( SwingUtilities.isRightMouseButton ( e ) )
	            {
	                TreePath path = this_tree.getPathForLocation ( e.getX (), e.getY () );
	                Rectangle pathBounds = this_tree.getUI ().getPathBounds ( this_tree, path );
	                if ( pathBounds != null && pathBounds.contains ( e.getX (), e.getY () ) )
	                {
	                	popup_menu = new JPopupMenu();
	                	JMenuItem mi1 = new JMenuItem ( "Add");
	                	MenuItemeEventHandler ehandle = new MenuItemeEventHandler(this_tree,
	                			(Notebook)path.getLastPathComponent());
	                	mi1.addActionListener(ehandle);
	                	popup_menu.add ( mi1);
	                	popup_menu.show ( this_tree, pathBounds.x, pathBounds.y + pathBounds.height );
	                }
	            }
	        }
	    } );
	}
	
	private void registerTreeModelListener(){
		this.getModel().addTreeModelListener(new TreeModelEventHandler(main_window));
	}
	
	static class MenuItemeEventHandler implements ActionListener{
		private NotebookTree parent_tree;
		private Notebook notebook;
		
		MenuItemeEventHandler(NotebookTree a_tree, Notebook a_notebook){
			parent_tree = a_tree;
			notebook = a_notebook;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				Notebook n = new Notebook("new"); 
				notebook.add(n);
				parent_tree.setEditable(true);
				DefaultTreeModel a_model = (DefaultTreeModel)parent_tree.getModel();
				a_model.reload();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	static class TreeModelEventHandler implements TreeModelListener{
		MainWindow mainWindow;
		TreeModelEventHandler(MainWindow window){
			mainWindow = window;
		}
		@Override
		public void treeNodesChanged(TreeModelEvent arg0) {
			TreePath path = arg0.getTreePath();
			Notebook n = (Notebook)path.getLastPathComponent();
			Notebook renamed = (Notebook)n.getLastLeaf();
			renamed.getNote().setTitle((String)renamed.getUserObject());
			mainWindow.noteHasChanged(renamed.getNote());
		}

		@Override
		public void treeNodesInserted(TreeModelEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void treeNodesRemoved(TreeModelEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void treeStructureChanged(TreeModelEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
