package Model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

public class Tree {
	
    private TreeNode<DataRow> root;
    private Forest parentForest;
    private HashSet<TreeNode<DataRow>> leaves;

    public Tree(DataRow rootData, Forest parentForest) {
        root = new TreeNode<DataRow>();
        root.setData(rootData);
        root.setChildren(new ArrayList<TreeNode<DataRow>>());
        root.setParent(null);
     
        this.parentForest = parentForest;
        this.leaves = new HashSet<TreeNode<DataRow>>();
        this.leaves.add(root);
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
    	
    	//Update all leaves
    	if(this.parentForest != null) {
        	this.parentForest.updateAllScores(node.getData().getDiagnosedTs());
    	}
    	
    	return node;
    }
    
    public void updateFromAllLeaves(long lastestDiagnosedTs) {
    	for(TreeNode<DataRow> leaf : leaves) {
    		int chainScore = updatePreviousScoresAndComputeTotalChainScore(leaf, lastestDiagnosedTs);
    		//Update top3
        	if(this.parentForest != null) {
            	this.parentForest.updateTop3IfNeeded(leaf, chainScore);
        	}
    	}
    }
    
    private int updatePreviousScoresAndComputeTotalChainScore(TreeNode<DataRow> currentNode, long lastestDiagnosedTs) {
    	int score = Utils.Utilities.getScore(lastestDiagnosedTs, 
				currentNode.getData().getDiagnosedTs());
		currentNode.setScore(score);
		
    	if(currentNode.getParent() != null) {
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
