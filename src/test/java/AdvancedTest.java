import static org.junit.Assert.*;

import java.io.IOException;

import Model.*;
import Model.Forest.Country;

import org.junit.Test;

public class AdvancedTest {

	@Test
	public void test_onlyroots() throws IOException {

		Country countries[] = { Country.SPAIN, Country.FRANCE, Country.ITALY };
		Country countries1[] = { Country.SPAIN, Country.ITALY, Country.FRANCE };
		Country countries2[] = { Country.FRANCE, Country.ITALY, Country.SPAIN };
		Country countries3[] = { Country.FRANCE, Country.SPAIN, Country.ITALY };

		Country countries4[] = { Country.ITALY, Country.FRANCE, Country.SPAIN };
		Country countries5[] = { Country.ITALY, Country.SPAIN, Country.FRANCE };

		Country[][] countries_ = { countries, countries1, countries2, countries3, countries4, countries5 };

		String path = "resources/data/onlyroots/";

		for (Country tab[] : countries_) {
			Top3Chains top3 = new Top3Chains(tab, path);
			top3.debug(true);

			String expected_result = "chainRootId=7, country=SPAIN, score=10\n"
					+ "chainRootId=12, country=ITALY, score=10\n" + "chainRootId=14, country=FRANCE, score=10";
			System.out.println(top3.toString());
			// System.out.println(expected_result);

			assertEquals(expected_result, top3.toString());
		}
	}
	
	@Test
	public void test_contaminatedByMultipleRootsWithScore0Chain() throws IOException {

		Country countries[] = { Country.SPAIN, Country.FRANCE, Country.ITALY };
		Country countries1[] = { Country.SPAIN, Country.ITALY, Country.FRANCE };
		Country countries2[] = { Country.FRANCE, Country.ITALY, Country.SPAIN };
		Country countries3[] = { Country.FRANCE, Country.SPAIN, Country.ITALY };

		Country countries4[] = { Country.ITALY, Country.FRANCE, Country.SPAIN };
		Country countries5[] = { Country.ITALY, Country.SPAIN, Country.FRANCE };

		Country[][] countries_ = { countries, countries1, countries2, countries3, countries4, countries5 };

		String path = "resources/data/contaminatedby0score/";

		for (Country tab[] : countries_) {
			Top3Chains top3 = new Top3Chains(tab, path);
			top3.debug(true);

			String expected_result = "chainRootId=7, country=SPAIN, score=10\n"
					+ "chainRootId=12, country=ITALY, score=10\n" + "chainRootId=17, country=FRANCE, score=10";
			System.out.println(top3.toString());
			// System.out.println(expected_result);

			assertEquals(expected_result, top3.toString());
		}
	}

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

	/**
	 * Test Borderlines cases, i.e. boundaries
	 *
	 * France is used to test 168H boundaries
	 * Italy is used to test 336H boundaries
	 * Spain is used to test 168H and 168H boundaries
	 *
	 * @throws IOException
	 */
	@Test
	public void testBorderlinesCases() throws IOException {
		// Countries tested
		Country countries[] = {Country.SPAIN, Country.FRANCE, Country.ITALY};

		// Data path
		String path = "resources/data/boundaries/";

		// Top3
		Top3Chains top3 = new Top3Chains(countries, path);
		top3.debug(true);

		// String of expected result
		String expected_result =
				"chainRootId=22, country=SPAIN, score=42\n" +
						"chainRootId=10, country=FRANCE, score=34\n" +
						"chainRootId=21, country=ITALY, score=18";

		// Equal assertion
		assertEquals(expected_result, top3.toString());

	}
}


