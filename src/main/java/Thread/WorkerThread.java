package Thread;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ConcurrentLinkedQueue;

import Interface.Top9UpdateAvailableListener;
import Model.DataRow;
import Model.Forest;
import Model.Top3;
import Utils.Utilities;
import Model.Forest.Country;

public class WorkerThread implements Runnable, Top9UpdateAvailableListener {
	
	private Forest forest;
	private String filepath;
	private ConcurrentLinkedQueue<Top3> top3Fifo;
	
	public WorkerThread(Country country, String folderPath, ConcurrentLinkedQueue<Top3> top3Fifo) {
		super();
		this.top3Fifo = top3Fifo;
		
		int freeEachInserts;
		try {
			int nbCasesInFile = Integer.parseInt(folderPath.split("/")[2].split("_")[0]);
			freeEachInserts  = Utilities.computeFreeEachInsert(nbCasesInFile);
		}
		catch(NumberFormatException ex) {
			freeEachInserts = 1;
		}
		
		forest = new Forest(country, freeEachInserts);
		filepath = new String();
		forest.setUpdateAvailableListener(this);

		String countryString = country.toString().toLowerCase();
		countryString = Character.toUpperCase(countryString.charAt(0)) + countryString.substring(1);

		filepath = folderPath + countryString + ".csv";
	}

	@Override
	public void run() {
		File f = new File(filepath);
		
		try(BufferedReader in = 
				new BufferedReader(new InputStreamReader(new FileInputStream(f), "UTF8"))) {
			
			in.lines().forEach(line -> {
				DataRow row = new DataRow(line);
				forest.insert(row);
			});
		} catch (IOException  e) {
			e.printStackTrace();
		}

	}

	@Override
	public void updateAvailable(Top3 top3) {
		this.top3Fifo.add(new Top3(top3));
//		this.top3Fifo.add(top3);

	}
}
