import static org.junit.Assert.*;

import org.junit.Test;

import Model.*;

public class TreeTest {
	/**
	 * Test to validate the constructor of Tree, the result will be the creation of
	 * a Tree in a good format, with no errors
	 * 
	 * @result a Tree will be create in a good way with no errors
	 */
	@Test
	public void testTree() {
		Tree testTree = null;
		// Creation of the root DataRow
		String dataToCreateRoot = "0, \"Patricia\", \"PEREZ\", 1956-01-11 00:00:00, 1573945200.0, unknown, \"course à pieds avec le chien à la campagne\"";
		DataRow dataRoot = new DataRow(dataToCreateRoot);
		testTree = new Tree(dataRoot, null);
		// Tests if the tree is created and that the root is the root we want ( with the
		// good data );
		assertNotNull(testTree);
		assertTrue(testTree.getRoot().getData().equals(dataRoot));
	}

	@Test
	public void testInsert() {
		/**
		 * Test to validate the insertion of data in a Tree at the valid position and
		 * with valid data
		 * 
		 * @result a node will be added to the root of an existing Tree
		 */
		String dataToCreateRoot = "0, \"Patricia\", \"PEREZ\", 1956-01-11 00:00:00, 1573945200.0, unknown, \"course à pieds avec le chien à la campagne\"";
		DataRow dataRoot = new DataRow(dataToCreateRoot);
		Tree testTree = new Tree(dataRoot, null);
		String dataToCreateChild = "1, \"Nicholas\", \"BAILEY\", 1965-11-20 00:00:00, 1575189760.380942, 0, \"kung-fu avec un collègue au super maché\"";
		DataRow dataChild = new DataRow(dataToCreateChild);
		TreeNode<DataRow> rootNode = testTree.getRoot();
		testTree.insert(dataChild, rootNode);
		// Checks that the root now has a child and the data of its child is valid
		assertFalse(testTree.getRoot().getChildren().isEmpty());
		assertTrue(testTree.getRoot().getChildren().get(0).getData().equals(dataChild));

		// Creation of a second child to verify multi insert in a node
		dataToCreateChild = "7, \"Scott\", \"MORALES\", 2000-11-07 00:00:00, 1577678881.1428266, 0, \"sport avec ma fille au super maché\"";
		dataChild = new DataRow(dataToCreateChild);
		testTree.insert(dataChild, rootNode);
		// Checks that the root now has 2 children, and that the data of the second is
		// valid too
		assertTrue(testTree.getRoot().getChildren().size() == 2);
		assertTrue(testTree.getRoot().getChildren().get(1).getData().equals(dataChild));
	}

}
