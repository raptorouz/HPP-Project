package Default;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;

import Model.DataRow;
import Model.Forest;
import Model.Forest.Country;

public class App {
	
	private static final boolean DISPLAY_ALL_CHAINS = true;
	private static final boolean MEASURE_TIME  = false;
	private static final boolean REDIRECT_OUTPUT_TO_FILE = true;

	public static void main(String[] args) throws IOException {
		Country countries[] = {Country.FRANCE, Country.ITALY, Country.SPAIN};
		String path = "../data/20/";
		
		long start = System.nanoTime();
		
		if(REDIRECT_OUTPUT_TO_FILE) {
			File f = new File("output.log");
			f.delete();
	        PrintStream o = new PrintStream(new File("output.log")); 
	        System.setOut(o); 
		}

		for(Country country : countries) {
			Forest forest = new Forest(country);
			String countryString = country.toString().toLowerCase();
			countryString = Character.toUpperCase(countryString.charAt(0)) + countryString.substring(1);
			
			String fullPath = path + countryString + ".csv";
			File f = new File(fullPath);
			
			Files.lines(f.toPath()).forEach(line -> {
				DataRow row = new DataRow(line);
				forest.insert(row);
			});
			
			if(MEASURE_TIME) {
				System.out.println(forest);
			}
			
			if(DISPLAY_ALL_CHAINS) {
				System.out.println(countryString);
				forest.displayAllChains();
				System.out.println("#############################\n");
			}
			
		}
		
		long end = System.nanoTime();
		if(MEASURE_TIME) {
			float ms = (end - start) / 1000000;
			System.out.println( ms + " ms");
		}


	}

}
