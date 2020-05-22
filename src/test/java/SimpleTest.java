import static org.junit.Assert.*;

import java.io.IOException;

import Model.*;
import Model.Forest.Country;

import org.junit.Test;

public class SimpleTest {

	@Test
	public void naiveTest() throws IOException {
		Country countries[] = {Country.SPAIN, Country.FRANCE, Country.ITALY};
		Country countries1[] = {Country.SPAIN, Country.ITALY, Country.FRANCE};
		Country countries2[] = {Country.FRANCE, Country.ITALY, Country.SPAIN};
		Country countries3[] = {Country.FRANCE, Country.SPAIN, Country.ITALY};
		
		Country countries4[] = {Country.ITALY, Country.FRANCE, Country.SPAIN};
		Country countries5[] = {Country.ITALY, Country.SPAIN, Country.FRANCE};
		
		Country[][] countries_ = {countries, countries1, countries2, countries3, countries4,
				countries5};

		String path = "resources/data/40/";

		for (Country tab[] : countries_) {
			Top3Chains top3 = new Top3Chains(tab, path);
			top3.debug(true);

			String expected_result = "chainRootId=34, country=ITALY, score=20\n"
					+ "chainRootId=32, country=FRANCE, score=10\n" + "chainRootId=35, country=ITALY, score=10";
			System.out.println(top3.toString());
			// System.out.println(expected_result);

			assertEquals(expected_result, top3.toString());
		}
	}

	/**
	 * Performs a test comparing the similarity between the output of method {@link Top3Chains#toString()} and an
	 * expected string for the data test 40_1.
	 *
	 *
	 * @throws IOException
	 */
	@Test
	public void naiveTest1() throws IOException {

		// Countries tested		
		Country countries[] = {Country.SPAIN, Country.FRANCE, Country.ITALY};

		// Data path
		String path = "resources/data/40_1/";

		// Top3
		Top3Chains top3 = new Top3Chains(countries, path);
		top3.debug(true);

		// String of expected result
		String expected_result =
				"chainRootId=34, country=ITALY, score=20\n" +
						"chainRootId=32, country=FRANCE, score=10\n" +
						"chainRootId=35, country=ITALY, score=10";

		// Equal assertion
		assertEquals(expected_result, top3.toString());
		
	}
	
	
	@Test
	public void naiveTest3() throws IOException {

		Country countries[] = { Country.SPAIN, Country.FRANCE, Country.ITALY };
		Country countries1[] = { Country.SPAIN, Country.ITALY, Country.FRANCE };
		Country countries2[] = { Country.FRANCE, Country.ITALY, Country.SPAIN };
		Country countries3[] = { Country.FRANCE, Country.SPAIN, Country.ITALY };

		Country countries4[] = { Country.ITALY, Country.FRANCE, Country.SPAIN };
		Country countries5[] = { Country.ITALY, Country.SPAIN, Country.FRANCE };

		Country[][] countries_ = { countries, countries1, countries2, countries3, countries4, countries5 };

		String path = "resources/data/40_3/";

		for (Country tab[] : countries_) {
			Top3Chains top3 = new Top3Chains(tab, path);
			top3.debug(true);

			String expected_result = "chainRootId=31, country=ITALY, score=14\n"
					+ "chainRootId=32, country=FRANCE, score=10\n" + "chainRootId=34, country=ITALY, score=10";
			System.out.println(top3.toString());
			// System.out.println(expected_result);

			assertEquals(expected_result, top3.toString());
		}
	}
	
	
}
