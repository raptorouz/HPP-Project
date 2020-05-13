package Model;

import java.util.ArrayList;
import java.util.Objects;

public class TreeNode<T> {
	
    private T data;
    private TreeNode<T> parent;
    private ArrayList<TreeNode<T>> children;
    private int score;
    
    public TreeNode() {
    	data = null;
    	parent = null;
    	children = null;
    	score = 10;
    }
    
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public TreeNode<T> getParent() {
		return parent;
	}
	@Override
	public int hashCode() {
		return Objects.hash(children, data, parent, score);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof TreeNode)) {
			return false;
		}
		TreeNode<T> other = (TreeNode<T>) obj;
		return Objects.equals(data, other.data) && score == other.score;
	}
	public void setParent(TreeNode<T> parent) {
		this.parent = parent;
	}
	public ArrayList<TreeNode<T>> getChildren() {
		return children;
	}
	public void setChildren(ArrayList<TreeNode<T>> children) {
		this.children = children;
	}
	
	
    
}