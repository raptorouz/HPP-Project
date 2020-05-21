package Interface;

import Model.DataRow;
import Model.Forest.Country;
import Model.TreeNode;

public interface Top3UpdateAvailableListener {
	public void updateAvailable(TreeNode<DataRow> lastNode, int newScore);
}
