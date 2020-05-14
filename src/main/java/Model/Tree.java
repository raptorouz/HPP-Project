package Model;

import java.util.ArrayList;
import java.util.Objects;

public class Tree {
	
    private TreeNode<DataRow> root;
    private Forest parentForest;

    public Tree(DataRow rootData, Forest parentForest) {
        root = new TreeNode<DataRow>();
        root.setData(rootData);
        root.setChildren(new ArrayList<TreeNode<DataRow>>());
        root.setParent(null);
     
        this.parentForest = parentForest;
    }

    
    long getRootDiagnosedTs() {
    	return root.getData().getDiagnosedTs();
    }
    
    public TreeNode<DataRow> insert(DataRow data, TreeNode<DataRow> parent) {
    	TreeNode<DataRow> node = new TreeNode<DataRow>();
    	node.setData(data);
    	node.setParent(parent);
    	
    	if(parent.getChildren() == null) {
    		parent.setChildren(new ArrayList<TreeNode<DataRow>>());
    	}
    	
    	parent.getChildren().add(node);
    	int totalScore = updatePreviousScoresAndComputeTotalChainScore(node, node.getData().getDiagnosedTs());
    	
    	if(this.parentForest != null) {
        	this.parentForest.updateTop3IfNeeded(node, totalScore);
    	}
    	return node;
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
