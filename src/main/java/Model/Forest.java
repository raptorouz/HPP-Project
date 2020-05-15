package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class Forest {
	private ArrayList<Tree> trees;
	
	public enum Country {
		FRANCE,
		ITALY,
		SPAIN
	};
	private Country country;
	
	private ArrayList<TreeNode<DataRow>> top3; // 0 is TOP1, etc...
	private int top3Scores[];
	
	@Override
	public String toString() {
		String str = country + " ";
		for(int i = 0; i < 3; ++i) {
			str += "[";
			int idRoot = top3.get(i).getRootWithScoreNonNull().getData().getId();
			int scoreTotal = top3Scores[i];
			str += idRoot + ", " + scoreTotal + "]" + (i < 2 ? "; " : "");
		}
		
		return str;
	}

	public Forest(Country country) {
		trees = new ArrayList<Tree>();
		this.country = country;
		top3 = new ArrayList<TreeNode<DataRow>>(3);
		for(int i = 0; i < 3; ++i)
			top3.add(null);
		top3Scores = new int[3];
	}
	
	public Forest(Country country, ArrayList<Tree> forest) { 
		
		trees = forest;
		this.country = country;
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
		if(newScore > top3Scores[2]) {
		      top3Scores[2] = newScore;
		      top3.set(2, lastNode);
		}
		if(newScore > top3Scores[1]){
		  top3Scores[2] = top3Scores[1];
		  top3Scores[1] = newScore;
		  
		  top3.set(2, top3.get(1));
		  top3.set(1, lastNode);
		}
		if(newScore > top3Scores[0]) {
		  top3Scores[1] = top3Scores[0];
		  top3Scores[0] = newScore;
		  
		  top3.set(1, top3.get(0));
		  top3.set(0, lastNode);
		}
	}
	
	public void updateAllScores(long lastestDiagnosedTs) {
		for(Tree tree : trees) {
			tree.updateFromAllLeaves(lastestDiagnosedTs);
		}
	}
	
	public TreeNode<DataRow> insert(DataRow row) {
		
		TreeNode<DataRow> insertedNode = null;
		
		//Reset top3
		top3.clear();
		for(int i  = 0; i < 3; ++i) {
			top3.add(null);
			top3Scores[i] = -1;
		}
		
		if(row.getContaminatedBy() == -1) { //Root id
			Tree tree = new Tree(row, this);
			this.trees.add(tree);
			insertedNode = tree.getRoot();
			
			//Update all scores
            this.updateAllScores(insertedNode.getData().getDiagnosedTs());
		}
		else {
			TreeNode<DataRow> nodeToInsertAfter = null;
			for(Tree tree : trees) {
				TreeNode<DataRow> current = tree.getRoot();
				nodeToInsertAfter = searchForNode(current, row.getContaminatedBy(), row.getDiagnosedTs());

				if(nodeToInsertAfter != null) {
					insertedNode = tree.insert(row, nodeToInsertAfter);
				}
			}
		}
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

}
