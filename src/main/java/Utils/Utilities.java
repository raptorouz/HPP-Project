package Utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	
	public static String timestampToString(long timestamp) {
		DateFormat dateFormat = new SimpleDateFormat("d MMM y hh:mm:ss");  
		Date date = new Date((long) timestamp * 1000);
		return dateFormat.format(date) ;
	}
	
	public static String distanceToLatestDate(long currentRowTs, long latestTs) {
		return  String.format("%.2f", (latestTs - currentRowTs) / 3600.0);
	}
	
	public static final <T> void swap (T[] a, int i, int j) {
		  T t = a[i];
		  a[i] = a[j];
		  a[j] = t;
	}
	
	public static int computeFreeEachInsert(int nbOfCasesInFile) {
		return (int) Math.pow(0.4012 * nbOfCasesInFile, 0.7031);
	}

}
