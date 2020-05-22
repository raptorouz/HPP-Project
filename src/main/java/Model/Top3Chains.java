package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.TreeSet;

import Interface.Top3UpdateAvailableListener;
import Interface.Top9UpdateAvailableListener;
import Model.Forest.Country;

public class Top3Chains implements Top9UpdateAvailableListener {
	private ArrayList<Forest> forests;
	private String[] filepaths;

	private Top9 top9;

	private final boolean DISPLAY_ALL_CHAINS = false;
	private final boolean MEASURE_TIME = true;
	private final boolean REDIRECT_OUTPUT_TO_FILE = false;

	public Top3Chains(Country countries[], String folderPath) {
		forests = new ArrayList<Forest>(3);
		filepaths = new String[3];
		top9 = new Top9();

		int i = 0;
		for (Country country : countries) {
			Forest f = new Forest(country);
			f.setUpdateAvailableListener(this);
			forests.add(f);

			String countryString = country.toString().toLowerCase();
			countryString = Character.toUpperCase(countryString.charAt(0)) + countryString.substring(1);

			filepaths[i++] = folderPath + countryString + ".csv";
		}
	}

	public void debug(boolean redirectToFile ) throws IOException {
		long start = System.nanoTime();

		if (redirectToFile) {
			File f = new File("output.log");
			f.delete();
			PrintStream o = new PrintStream(new File("output.log"));
			System.setOut(o);
		}
		
		populate();
		
		if (redirectToFile) {
			for(int i=0; i<forests.size(); i++)
			{
				System.out.println(forests.get(i).getCountry());
				forests.get(i).displayAllChains();
				System.out.println("#############################\n");
			}
				
		}

		long end = System.nanoTime();
		if (!redirectToFile) {
			System.out.println(this);
			float ms = (end - start) / 1000000;
			System.out.println(ms + " ms");
		}
	}

	public void populate() throws IOException {
		for (int i = 0; i < forests.size(); ++i) {
			File f = new File(filepaths[i]);
			final int index = i;
			
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(f), "UTF8"));

			in.lines().forEach(line -> {
				DataRow row = new DataRow(line);
				forests.get(index).insert(row);
			});

			
			
			in.close();
		}
	}

	@Override
	public String toString() {
		return top9.toTop3().toString();
	}

	@Override
	public void updateAvailable(Top3 top3) {
		top9.updatePartOfTop9(top3);
		top9.toTop3();

	}

}
