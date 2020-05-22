package Default;

import Model.Top3Chains;

import java.io.IOException;
import Model.Forest.Country;

public class App {
	
	public static void main(String[] args) {
		Country countries[] = {Country.SPAIN, Country.FRANCE, Country.ITALY};
		Country countries1[] = {Country.SPAIN, Country.ITALY, Country.FRANCE};
		Country countries2[] = {Country.FRANCE, Country.ITALY, Country.SPAIN};
		Country countries3[] = {Country.FRANCE, Country.SPAIN, Country.ITALY};
		
		Country countries4[] = {Country.ITALY, Country.FRANCE, Country.SPAIN};
		Country countries5[] = {Country.ITALY, Country.SPAIN, Country.FRANCE};
		
		Country[][] countries_ = {countries, countries1, countries2, countries3, countries4,
				countries5};

		
		String path = "resources/data/5000/";
		
		for(Country tab[] : countries_) {
			Top3Chains top3 = new Top3Chains(tab, path);
			try {
				top3.debug(false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
//	public static void main(String[] args) throws IOException {
//		File f = new File("resources/data/40_3/Spain.csv");
//		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(f), "UTF8"));
//
//		File fo = new File("resources/data/40_3/Spain_output.csv");
//		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fo), "UTF8"));
//
//		String lastLine = "";
//		String sCurrentLine;
//		
//	    while ((sCurrentLine = in.readLine()) != null) 
//	    {
//	        lastLine = sCurrentLine;
//	    }
//	    
//	    long lastTimeStamp = new DataRow(lastLine).getDiagnosedTs();
//	    in.close();
//	    BufferedReader in2 = new BufferedReader(new InputStreamReader(new FileInputStream(f), "UTF8"));
//		
//		in2.lines().forEach(line -> {
//			DataRow row = new DataRow(line);
//			try {
//				out.write(row.toDebugString(lastTimeStamp) + "\n");
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		});
//		
//		in2.close();
//		out.close();
//	}
}

