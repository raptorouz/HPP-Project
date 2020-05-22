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

		
		String path = "../data/5000/";
		
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

}
