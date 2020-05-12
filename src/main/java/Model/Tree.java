package Model;

import java.util.ArrayList;

public class Tree {
	
    private Node<DataRow> root;

    public Tree(DataRow rootData) {
        root = new Node<DataRow>();
        root.data = rootData;
        root.children = new ArrayList<Node<DataRow>>();
    }

    public static class Node<T> {
        public T data;
        public Node<T> parent = null;
        public ArrayList<Node<T>> children = null;
    }
    
    int getRootDiagnosedTs() {
    	return root.data.getDiagnosedTs();
    }
    
    public void insert(DataRow data, Node<DataRow> parent) {
    	Node<DataRow> node = new Node<DataRow>();
    	node.data = data;
    	
    	if(parent.children == null) {
    		parent.children = new ArrayList<Node<DataRow>>();
    	}
    	
    	parent.children.add(node);
    }

	public Node<DataRow> getRoot() {
		return root;
	}

	public void setRoot(Node<DataRow> root) {
		this.root = root;
	}

}
