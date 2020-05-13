package Model;

import java.util.ArrayList;
import java.util.Objects;

public class Forest {
	private ArrayList<Tree> trees;
	
	public enum Country {
		FRANCE,
		ITALY,
		SPAIN
	};
	private Country country;
	
	public Forest(Country country) {
		trees = new ArrayList<Tree>();
		this.country = country;
	}
	
	public Forest(Country country, ArrayList<Tree> forest) { 
		
		trees = forest;
		this.country = country;
	}
	
	public TreeNode<DataRow> insert(DataRow row) {
		
		TreeNode<DataRow> insertedNode = null;
		if(row.getContaminatedBy() == -1) { //Root id
			Tree tree = new Tree(row);
			this.trees.add(tree);
			insertedNode = tree.getRoot();
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
