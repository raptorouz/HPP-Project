package Model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

import Interface.Top3UpdateAvailableListener;
import Interface.Top9UpdateAvailableListener;

public class Forest implements Top3UpdateAvailableListener {
	private ArrayList<Tree> trees;
	private Top3 top3;
	
	public enum Country {
		FRANCE,
		ITALY,
		SPAIN
	};
	private Country country;
	private Top9UpdateAvailableListener updateListener;

	public Forest(Country country) {
		trees = new ArrayList<Tree>();
		this.country = country;
		top3 = new Top3();
	}
	
	public Forest(Country country, ArrayList<Tree> forest) { 
		
		trees = forest;
		this.country = country;
	}
	
	public void setUpdateAvailableListener(Top9UpdateAvailableListener listener) {
		this.updateListener = listener;
	}
	
	public void displayAllChains() {
		int i = 0;
		for(Tree tree : trees) {
			System.out.println("Arbre n°" + (++i) + 
					(i == 1 ? " ###################" : ""));
			tree.displayFromAllLeaves();
		}
	}
	
	public void updateTop3IfNeeded(TreeNode<DataRow> lastNode, int newScore) {	
		
	}
	
	public void updateAllScores(long lastestDiagnosedTs) {
		for(Tree tree : trees) {
			tree.updateFromAllLeaves(lastestDiagnosedTs);
		}
	}
	
	public TreeNode<DataRow> insert(DataRow row) {
		
		TreeNode<DataRow> insertedNode = null;
		
		if(row.getContaminatedBy() == -1) { //Root id
			insertedNode = insertNewTree(row);
		}
		else {
			TreeNode<DataRow> nodeToInsertAfter = null;
			Iterator<Tree> iter = trees.iterator();
			Tree currentTree = null;
			while(iter.hasNext() && nodeToInsertAfter == null) {
				currentTree = iter.next();
				nodeToInsertAfter = searchForNode(currentTree.getRoot(), row.getContaminatedBy(), row.getDiagnosedTs());
			}
			
			if(nodeToInsertAfter == null) { //New tree
				insertedNode = insertNewTree(row);
			}
			else {
				int updatedScoreOfParentNode = Utils.Utilities.getScore(row.getDiagnosedTs(), 
						nodeToInsertAfter.getData().getDiagnosedTs());
				if(updatedScoreOfParentNode > 0) {
					insertedNode = currentTree.insert(row, nodeToInsertAfter);
				}
				else {
					insertedNode = insertNewTree(row);
				}
			}
			
			
		}
		top3.clear();
		
		//Update all leaves
    	this.updateAllScores(insertedNode.getData().getDiagnosedTs());
    	
    	//Free empty trees
    	freeEmptyTrees();
		
		return insertedNode;
	}
	
	public Country getCountry() {
		return country;
	}

	private void freeEmptyTrees() {
		trees.removeIf((Tree tree) -> tree.areAllNodesZero());
	}
	
	private TreeNode<DataRow> insertNewTree(DataRow row) {
		Tree tree = new Tree(row);
		tree.setUpdateAvailableListener(this);
		this.trees.add(tree);
		TreeNode<DataRow> insertedNode = tree.getRoot();
        return insertedNode;
	}
	
	private TreeNode<DataRow> searchForNode(TreeNode<DataRow> currentNode, int parentId, long nodeToInsertDiagnosedTs ) {
		
		TreeNode<DataRow> resultNode = null;
		if(currentNode.getData().getId() == parentId) {
			resultNode = currentNode;
		}
		else {
			if(currentNode.getChildren() != null) {
				for(TreeNode<DataRow> child : currentNode.getChildren()) {
					if(child.getData().getDiagnosedTs() > nodeToInsertDiagnosedTs) {
						continue;
					}
					
					TreeNode<DataRow> node = searchForNode(child, parentId, nodeToInsertDiagnosedTs);
					if(node != null) {
						resultNode = node;
						break;
					}
				}
				
			}
			else {
				resultNode = null;
			}
		}
		return resultNode;
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(country, trees);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Forest)) {
			return false;
		}
		Forest other = (Forest) obj;
		return country == other.country && Objects.equals(trees, other.trees);
	}

	@Override
	public void updateAvailable(TreeNode<DataRow> lastNode, int newScore) {
		
		//SHould return a boolean to check if there was effectively an update
		boolean wasUpdated = top3.update(lastNode, newScore, this.country);
		
		//Update Global Top3
		if(updateListener != null && wasUpdated) {
			updateListener.updateAvailable(top3);
		}
		
	}

}
