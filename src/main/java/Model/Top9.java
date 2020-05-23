package Model;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import Model.Forest.Country;

public class Top9 {
	private TopItem items[][];
	
	public Top9() {
		items = new TopItem[3][3];
	}
	
	public void updatePartOfTop9(Top3 top3OfCountry) {
		int index = top3OfCountry.getCountry().ordinal();
		
		if(index < 3) {
			for(int i = 0; i < 3; ++i) {
				items[index][i] = top3OfCountry.itemAt(i);
			}
		}
		
	}
	
	public Top3 toTop3() {
		Top3 result = new Top3();
		for(int i = 0; i < 9; ++i) {
			if(items[i/3][i%3] != null)
				result.insert(items[i/3][i%3]);
		}
		return result;
	}
}
