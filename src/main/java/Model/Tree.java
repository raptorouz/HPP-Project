package Model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.TreeSet;

import Utils.Entry;

import Interface.Top3UpdateAvailableListener;

public class Tree {
	
    private TreeNode<DataRow> root;
    private SortedMap<TreeNode<DataRow>, Integer> leaves; //with score now
    
    private Top3UpdateAvailableListener updateListener = null;

    public Tree(DataRow rootData) {
        root = new TreeNode<DataRow>();
        root.setData(rootData);
        root.setChildren(new ArrayList<TreeNode<DataRow>>());
        root.setParent(null);
    
        this.leaves = new SortedMap<TreeNode<DataRow>, Integer>();

        this.leaves.add(root, 10);

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
    	Integer oldLeafScore = this.leaves.remove(node.getParent());
    	if(oldLeafScore == null) {
    		oldLeafScore = 0;
    		//Get brother 
//    		ArrayList<TreeNode<DataRow>> brothers = node.getParent().getChildren();
//    		TreeNode<DataRow> brother = brothers.get(brothers.size() - 2);
//    		if(brother == null) {
//    			int a = 0;
//    		}
//    		oldLeafScore = leaves.get(brother) == null ? 0 : leaves.get(brother);
    		oldLeafScore = computeChainExactScore(node);
    	}
    	
    	int score = node.getScore();
    	this.leaves.add(node, oldLeafScore + score);
    	
    	return node;
    }
    
    public boolean areAllNodesZero() {
    	return leaves.AreAllValuesZero();
    }
    
    public void updateFromAllLeaves(long lastestDiagnosedTs, final Top3 currentTop3) {
    		leaves.set().forEach( (Entry<TreeNode<DataRow>, Integer> entry) -> {
    		TreeNode<DataRow> key = entry.getKey();
    		Integer value = entry.getValue();
    		
    		//boolean thisNodeIsTheLastInserted = key.getData().getDiagnosedTs() == lastestDiagnosedTs;
    		Integer maxPossibleChainScore = value ;//+ (thisNodeIsTheLastInserted ? 10 : 0);
    		
    		if(maxPossibleChainScore >= currentTop3.minScore()) {
    			int chainScore = updatePreviousScoresAndComputeTotalChainScore(key, lastestDiagnosedTs);
        		if(this.updateListener != null) {
        			this.updateListener.updateAvailable(key, chainScore);
        		}
    		} 
    		
    	});
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
    
    private int computeChainExactScore(TreeNode<DataRow> leaf) {
    	int totalScore = leaf.getScore();
    	TreeNode<DataRow> currentNode = leaf.getParent();
    	
    	while(currentNode != null && currentNode.getScore() > 0) {
    		totalScore += currentNode.getScore();
    		currentNode = currentNode.getParent();
    	}
    	
    	return totalScore;
    }
    
    public TreeNode<DataRow> searchForNode(int parentId) {
		for(TreeNode<DataRow> leaf : leaves.keySet()) {
			if(leaf.getData().getId() == parentId) {
    			return leaf;
    		}
		}
    	return null;
    }
    
    public void displayFromAllLeaves() {
    	int i = 0;
    	
    	for(TreeNode<DataRow> leaf : leaves.keySet()) {
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
