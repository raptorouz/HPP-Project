package Model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

import Model.Forest.Country;
import Utils.Utilities;

public class Top3 {
	private TopItem items[]; //Decreasing order 0 is Top1
	private final int INVALID_ID = -39;
	
	public Top3() {
		items = new TopItem[3];
		for(int i = 0; i < 3; ++i) {
			items[i] = new TopItem(INVALID_ID, Country.FRANCE, -1, INVALID_ID, new Date().getTime() / 1000);
		}
	}
	
	public Top3(TopItem top9[]) {
		items = new TopItem[3];
		for(int i = 0; i < 3; ++i) {
			items[i] = top9[i];
		}
	}
	
	public TopItem itemAt(int index) {
		return items[index];
	}
	
	//Use country to skip checking for insertion/deletion mode
	public void update(TreeNode<DataRow> lastNode, int newScore, Country country) {
		Integer top3Index = null;
		TreeNode<DataRow> currentNode = lastNode;
		TreeNode<DataRow> lastNonZeroNode = null;
		
		do {
			int i = 0;
			while(top3Index == null && i < items.length) {
				if(currentNode.getData().getId() == items[i].getChainLeafId()) {
					top3Index = i;
				}
				++i;
			}
			currentNode = currentNode.getParent();
		} while(currentNode != null && currentNode.getParent() != null);
		
		lastNonZeroNode = lastNode.getRootWithScoreNonNull();
		
		//CurrentNode is now root node
		//top3Index is either null or in [0, 2]
		
		if(top3Index == null) {
			TopItem item = new TopItem(lastNonZeroNode.getData().getId(), country, newScore, lastNode.getData().getId(), lastNonZeroNode.getData().getDiagnosedTs());
			this.insert(item);
			
		}
		else { //Update value
			TopItem item = new TopItem(lastNonZeroNode.getData().getId(), country, newScore, 
					lastNode.getData().getId(), lastNonZeroNode.getData().getDiagnosedTs());
			items[top3Index] = item;
			sort();
		}
		
	}
	
	public void insert(TopItem newItem) {
		if(newItem.compareTo(items[2]) > 0) {
			if(newItem.compareTo(items[1]) > 0) {
				if(newItem.compareTo(items[0]) > 0) {
					swap(1, 2);
					swap(0, 1);
					items[0] = newItem;
				}
				else {
					swap(2, 1);
					items[1] = newItem;
				}
			}
			else {
				items[2] = newItem;
			}
		}
	}
	
	private void sort() {
		Arrays.sort(items, new Comparator<TopItem>() {
			@Override
			public int compare(TopItem o1, TopItem o2) {
				return - o1.compareTo(o2);
			}
		});
	}
	
	private void swap(int i, int j) {
		Utilities.swap(items, i, j);
	}

	@Override
	public String toString() {
		String res = "";
		for(int i = 0; i < 3; ++i) {
			res += items[i].toString() + "\n";
		}
		return res;
	}
	
	
}