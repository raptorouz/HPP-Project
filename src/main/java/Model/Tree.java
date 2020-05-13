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
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((children == null) ? 0 : children.hashCode());
			result = prime * result + ((data == null) ? 0 : data.hashCode());
			result = prime * result + ((parent == null) ? 0 : parent.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (children == null) {
				if (other.children != null)
					return false;
			} else if (!children.equals(other.children))
				return false;
			if (data == null) {
				if (other.data != null)
					return false;
			} else if (!data.equals(other.data))
				return false;
			if (parent == null) {
				if (other.parent != null)
					return false;
			} else if (!parent.equals(other.parent))
				return false;
			return true;
		}
        
    }
    
    long getRootDiagnosedTs() {
    	return root.data.getDiagnosedTs();
    }
    
    public Node<DataRow> insert(DataRow data, Node<DataRow> parent) {
    	Node<DataRow> node = new Node<DataRow>();
    	node.data = data;
    	
    	if(parent.children == null) {
    		parent.children = new ArrayList<Node<DataRow>>();
    	}
    	
    	parent.children.add(node);
    	return node;
    }

	public Node<DataRow> getRoot() {
		return root;
	}

	public void setRoot(Node<DataRow> root) {
		this.root = root;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((root == null) ? 0 : root.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tree other = (Tree) obj;
		if (root == null) {
			if (other.root != null)
				return false;
		} else if (!root.equals(other.root))
			return false;
		return true;
	}

}
