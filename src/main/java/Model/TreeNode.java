package Model;

import java.util.ArrayList;
import java.util.Objects;

public class TreeNode<T extends Object> {
	
    private T data;
    private TreeNode<T> parent;
    private ArrayList<TreeNode<T>> children;
    private int score;
    
    public interface VoidFunction<T extends Object> {
    	public void applyTo(TreeNode<T> node);
    }
    
    public TreeNode() {
    	data = null;
    	parent = null;
    	children = null;
    	score = 10;
    }
    
    @Override
	public String toString() {
		return data.toString() + " Score: " + this.score;
	}

	public TreeNode<T> getRootWithScoreNonNull() {
    	if(this.parent == null || this.parent.score == 0 ) {
    		return this;
    	}
    	else {
   		 	return this.parent.getRootWithScoreNonNull();
    	}
    }
	
	public void applyFunctionToAllNodesFromThisOne(VoidFunction<T> func) {
		func.applyTo(this);
		if(this.parent != null) {
   		 	this.parent.applyFunctionToAllNodesFromThisOne(func);
		}
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
		return Objects.hash(data);
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
		return Objects.equals(data, other.data);
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