package Model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

import Model.Forest.Country;
import Utils.Utilities;

public class Top3 {
	private TopItem items[]; //Decreasing order 0 is Top1
	
	public Top3() {
		items = new TopItem[3];
	}
	
	public Top3(Top3 other) {
		items = new TopItem[3];
		for(int i = 0; i < 3; ++i) {
			items[i] = other.items[i];
		}
	}
	
	public Country getCountry() {
		if(items[0] == null) {
			return Country.NONE;
		}
		else return items[0].getCountry();
	}
	
	public void clear() {
		for(int i = 0; i < 3; ++i) {
			items[i] = null;
		} 
	}
	
	public TopItem itemAt(int index) {
		return items[index];
	}
	
	public Integer minScore() {
		return items[2] == null ? 0 : items[2].getScore();
	}
	
	//Use country to skip checking for insertion/deletion mode
	public boolean update(TreeNode<DataRow> lastNode, int newScore, Country country) {
		TreeNode<DataRow> lastNonZeroNode = lastNode.getRootWithScoreNonNull();
		TopItem item = new TopItem(lastNonZeroNode.getData().getId(), country, newScore, lastNode.getData().getId(), lastNonZeroNode.getData().getDiagnosedTs());
		return this.insert(item);
	}
	
	public boolean insert(TopItem newItem) {
		boolean modified = true;
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
		else {
			modified = false;
		}
		
		return modified;
	}
	
	private void swap(int i, int j) {
		Utilities.swap(items, i, j);
	}

	@Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        int i;
        for(i = 0; i < 2; ++i) {
        	if(items[i] != null) {
    			res.append(items[i].toString());
    			res.append("\n");
        	}
        }
        if(items[2] != null)
        	res.append(items[i].toString());
        return res.toString();
    }
	
	
}
