import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Model.*;

public class ForestTest {

//	@Test
//	public void test() {
//		fail("Not yet implemented");
//
//	}
	
	@Test
	public void forest_test()
	{
		DataRow fr_1 = new DataRow("4, Daniel, ROBINSON, 1995-08-21 00:00:00, 1582161158.5235808, unknown, course à pieds avec la grand-mère au marché");
		DataRow fr_2 = new DataRow("5, Jessica, WATSON, 1968-11-21 00:00:00, 1583091884.9200459, 4, courses avec ma fille au super maché");
		DataRow fr_3 = new DataRow("9, Stephanie, MITCHELL, 1924-03-17 00:00:00, 1585699579.2617905, unknown, promenade avec mon fils à la campagne");
		
		Forest fr_forest_test = new Forest(Forest.Country.FRANCE);
		Tree.Node<DataRow> fr_node_1 = fr_forest_test.insert(fr_1);
		Tree.Node<DataRow> fr_node_2 = fr_forest_test.insert(fr_2);
		Tree.Node<DataRow> fr_node_3 = fr_forest_test.insert(fr_3);
		
		Tree fr_tree_1 = new Tree(fr_1);
		fr_tree_1.insert(fr_2, fr_node_1);
		Tree fr_tree_2 = new Tree(fr_3);
		
		ArrayList<Tree> fr_forest = new ArrayList<Tree>();
		fr_forest.add(fr_tree_1);
		fr_forest.add(fr_tree_2);
		
		
		Forest fr_forest_true = new Forest(Forest.Country.FRANCE, fr_forest);
		
		assertEquals(fr_forest_test, fr_forest_true);
		
	}

}
