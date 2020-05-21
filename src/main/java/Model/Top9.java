package Model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

import Model.Forest.Country;

public class Top9 {
	private static final int INVALID_ID = -39;
	private TopItem items[][];
	
	public Top9() {
		items = new TopItem[3][3];
		for(int i = 0; i < 3; ++i) {
			for(int j = 0; j < 3; ++j) {
				items[i][j] =  new TopItem(INVALID_ID, Country.FRANCE, -1, INVALID_ID, 
						new Date().getTime() / 1000);
			}
		}
	}
	
	public void updatePartOfTop9(Top3 top3OfCountry) {
		int index = top3OfCountry.itemAt(0).getCountry().ordinal();
		for(int i = 0; i < 3; ++i) {
			items[index][i] = top3OfCountry.itemAt(i);
		}
	}
	
	public Top3 toTop3() {
		TopItem tempTop9[] = new TopItem[9];
		for(int i = 0; i < 9; ++i) {
			tempTop9[i] = items[i/3][i%3];
		}
		
		Arrays.sort(tempTop9, new Comparator<TopItem>() {
			@Override
			public int compare(TopItem o1, TopItem o2) {
				return - o1.compareTo(o2);
			}
		});
		

		Top3 result = new Top3(tempTop9);
		return result;
	}
}
