package Utils;

public class Utilities {
	
	private static final long HOURS_168 = 168 * 3600;
	private static final long HOURS_336 = 336 * 3600;
	
	public static int getScore(long latestDate, long nodeDate) {
		long diff = latestDate - nodeDate;
		int score = 0;
		
		if(diff <= HOURS_168) {
			score = 10; 
		}
		else if(diff <= HOURS_336) {
			score = 4;
		}
		
		return score;
	}

}
