package Model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;

import javax.xml.crypto.Data;

import Interface.Top3UpdateAvailableListener;

public class Tree {
	
    private TreeNode<DataRow> root;
    private HashSet<TreeNode<DataRow>> leaves;
    
    private Top3UpdateAvailableListener updateListener = null;

    public Tree(DataRow rootData) {
        root = new TreeNode<DataRow>();
        root.setData(rootData);
        root.setChildren(new ArrayList<TreeNode<DataRow>>());
        root.setParent(null);
    
        this.leaves = new HashSet<TreeNode<DataRow>>();
        this.leaves.add(root);
    }
    
    public void setUpdateAvailableListener(Top3UpdateAvailableListener listener) {
    	this.updateListener = listener;
    }

    
    long getRootDiagnosedTs() {
    	return root.getData().getDiagnosedTs();
    }
    
    public TreeNode<DataRow> insert(DataRow data, TreeNode<DataRow> parent) {
    	//Create Node
    	TreeNode<DataRow> node = new TreeNode<DataRow>();
    	node.setData(data);
    	node.setParent(parent);
    	
    	//Lazy loading for children list
    	if(parent.getChildren() == null) {
    		parent.setChildren(new ArrayList<TreeNode<DataRow>>());
    	}
    	
    	//Update parent
    	parent.getChildren().add(node);
    	
    	//Update leaves
    	this.leaves.remove(node.getParent());
    	this.leaves.add(node);
    	
    	return node;
    }
    
    public boolean areAllNodesZero() {
    	boolean res = true;
    	Iterator<TreeNode<DataRow>> iter = leaves.iterator();
    	while(iter.hasNext() && res == true) {
    		res = res && iter.next().getScore() == 0;
    	}
    	return res;
    }
    
    public void updateFromAllLeaves(long lastestDiagnosedTs) {
    	for(TreeNode<DataRow> leaf : leaves) {
    		int chainScore = updatePreviousScoresAndComputeTotalChainScore(leaf, lastestDiagnosedTs);
    		
    		if(this.updateListener != null) {
    			this.updateListener.updateAvailable(leaf, chainScore);
    		}
    	}
    }
    
    private int updatePreviousScoresAndComputeTotalChainScore(TreeNode<DataRow> currentNode, long lastestDiagnosedTs) {
    	int score = Utils.Utilities.getScore(lastestDiagnosedTs, 
				currentNode.getData().getDiagnosedTs());
		currentNode.setScore(score);
		
    	if(currentNode.getParent() != null && score > 0) {
    		return score + updatePreviousScoresAndComputeTotalChainScore(currentNode.getParent(), lastestDiagnosedTs);
    	}
    	else {
    		return score;
    	}
    }
    
    public TreeNode<DataRow> searchForNode(int parentId) {
		for(TreeNode<DataRow> leaf : leaves) {
			if(leaf.getData().getId() == parentId) {
    			return leaf;
    		}
		}
    	return null;
    }
    
    public void displayFromAllLeaves() {
    	int i = 0;
    	
    	for(TreeNode<DataRow> leaf : leaves) {
    		System.out.println("Chain n°" + (++i));
    		leaf.applyFunctionToAllNodesFromThisOne( 
    				(TreeNode<DataRow> node) -> {
    					System.out.println(node.toString());
    				});
    		System.out.println();
    	}
    }

	public TreeNode<DataRow> getRoot() {
		return root;
	}

	public void setRoot(TreeNode<DataRow> root) {
		this.root = root;
	}


	@Override
	public int hashCode() {
		return Objects.hash(root);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Tree)) {
			return false;
		}
		Tree other = (Tree) obj;
		return Objects.equals(root, other.root);
	}

}
