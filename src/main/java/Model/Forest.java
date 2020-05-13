package Model;

import java.util.ArrayList;

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
	
	public Tree.Node<DataRow> insert(DataRow row) {
		
		Tree.Node<DataRow> insertedNode = null;
		if(row.getContaminatedBy() == -1) { //Root id
			Tree tree = new Tree(row);
			this.trees.add(tree);
			insertedNode = tree.getRoot();
		}
		else {
			Tree.Node<DataRow> nodeToInsertAfter = null;
			for(Tree tree : trees) {
				Tree.Node<DataRow> current = tree.getRoot();
				nodeToInsertAfter = searchForNode(current, row.getContaminatedBy(), row.getDiagnosedTs());
				if(nodeToInsertAfter != null) {
					insertedNode = tree.insert(row, nodeToInsertAfter);
				}
			}
		}
		return insertedNode;
	}

	
	private Tree.Node<DataRow> searchForNode(Tree.Node<DataRow> currentNode, int parentId, long nodeToInsertDiagnosedTs ) {
		
		Tree.Node<DataRow> resultNode = null;
		if(currentNode.data.getId() == parentId) {
			resultNode = currentNode;
		}
		else {
			if(currentNode.children != null) {
				for(Tree.Node<DataRow> child : currentNode.children) {
					if(child.data.getDiagnosedTs() > nodeToInsertDiagnosedTs) {
						continue;
					}
					
					Tree.Node<DataRow> node = searchForNode(child, parentId, nodeToInsertDiagnosedTs);
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
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	
	
}
