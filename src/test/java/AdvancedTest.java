import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import Model.Top3Chains;
import Model.Forest.Country;

public class AdvancedTest {

	@Test
	public void fewPeople() throws IOException {
		
		Country countries[] = { Country.SPAIN, Country.FRANCE, Country.ITALY };
		Country countries1[] = { Country.SPAIN, Country.ITALY, Country.FRANCE };
		Country countries2[] = { Country.FRANCE, Country.ITALY, Country.SPAIN };
		Country countries3[] = { Country.FRANCE, Country.SPAIN, Country.ITALY };

		Country countries4[] = { Country.ITALY, Country.FRANCE, Country.SPAIN };
		Country countries5[] = { Country.ITALY, Country.SPAIN, Country.FRANCE };

		Country[][] countries_ = { countries, countries1, countries2, countries3, countries4, countries5 };

		String path = "resources/data/20_2/";

		for (Country tab[] : countries_) {
			Top3Chains top3 = new Top3Chains(tab, path);
			top3.debug(true);

			String expected_result = "chainRootId=4, country=FRANCE, score=14\n"
					+ "chainRootId=1, country=ITALY, score=10\n";// + "chainRootId=32, country=FRANCE, score=10";
			System.out.println(top3.toString());
			// System.out.println(expected_result);

			assertEquals(expected_result, top3.toString());
		}
		
	}
	
	@Test
	public void sameRootInTop3() throws IOException {
	
		Country countries[] = { Country.SPAIN, Country.FRANCE, Country.ITALY };
		Country countries1[] = { Country.SPAIN, Country.ITALY, Country.FRANCE };
		Country countries2[] = { Country.FRANCE, Country.ITALY, Country.SPAIN };
		Country countries3[] = { Country.FRANCE, Country.SPAIN, Country.ITALY };

		Country countries4[] = { Country.ITALY, Country.FRANCE, Country.SPAIN };
		Country countries5[] = { Country.ITALY, Country.SPAIN, Country.FRANCE };

		Country[][] countries_ = { countries, countries1, countries2, countries3, countries4, countries5 };

		String path = "resources/data/20_3/";

		for (Country tab[] : countries_) {
			Top3Chains top3 = new Top3Chains(tab, path);
			top3.debug(true);

			String expected_result = "chainRootId=4, country=FRANCE, score=20\n"
					+ "chainRootId=4, country=FRANCE, score=20\n" + "chainRootId=4, country=FRANCE, score=20";
			System.out.println(top3.toString());
			// System.out.println(expected_result);

			assertEquals(expected_result, top3.toString());
		}
		
	}

}
