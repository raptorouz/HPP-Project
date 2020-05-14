package Default;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import Model.DataRow;
import Model.Forest;
import Model.Forest.Country;

public class App {

	public static void main(String[] args) throws IOException {
		Country countries[] = {Country.FRANCE, Country.ITALY, Country.SPAIN};
		String path = "../data/5000/";
		
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
			
			System.out.println(forest);
			
		}

	}

}
