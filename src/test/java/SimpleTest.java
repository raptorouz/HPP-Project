import static org.junit.Assert.*;

import java.io.IOException;

import Default.App;
import Model.*;
import Model.Forest.Country;

import org.junit.Test;

public class SimpleTest {

	/**
	 * Performs a multithreading test comparing the similarity between the output of method {@link #toString()} of DeserializationThread and an
	 * expected string for the data test 40.
	 *
	 * @throws IOException
	 */
	@Test
	public void multithreadingTest() throws IOException, InterruptedException {
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
			String result = App.process(path,tab);

			String expected_result = "chainRootId=34, country=ITALY, score=20\n"
					+ "chainRootId=32, country=FRANCE, score=10\n"
					+ "chainRootId=35, country=ITALY, score=10";

			assertEquals(expected_result, result);
		}
	}

	/**
	 * Performs a multithreading test comparing the similarity between the output of method {@link #toString()} of DeserializationThread and an
	 * expected string for the data test 40_1.
	 *
	 * @throws IOException
	 */
	@Test
	public void multithreadingTest40_1() throws IOException, InterruptedException {

		Country countries[] = {Country.SPAIN, Country.FRANCE, Country.ITALY};
		Country countries1[] = {Country.SPAIN, Country.ITALY, Country.FRANCE};
		Country countries2[] = {Country.FRANCE, Country.ITALY, Country.SPAIN};
		Country countries3[] = {Country.FRANCE, Country.SPAIN, Country.ITALY};

		Country countries4[] = {Country.ITALY, Country.FRANCE, Country.SPAIN};
		Country countries5[] = {Country.ITALY, Country.SPAIN, Country.FRANCE};

		Country[][] countries_ = {countries, countries1, countries2, countries3, countries4,
				countries5};

		String path = "resources/data/40_1/";

		for (Country tab[] : countries_) {
			String result = App.process(path,tab);

			String expected_result = "chainRootId=34, country=ITALY, score=20\n" +
							"chainRootId=32, country=FRANCE, score=10\n" +
							"chainRootId=35, country=ITALY, score=10";

			assertEquals(expected_result, result);
		}
		
	}

	/**
	 * Performs a multithreading test comparing the similarity between the output of method {@link #toString()} of DeserializationThread and an
	 * expected string for the data test 40_2.
	 *
	 * @throws IOException
	 */
	@Test
	public void multithreadingTest40_2() throws IOException, InterruptedException {

		Country countries[] = {Country.SPAIN, Country.FRANCE, Country.ITALY};
		Country countries1[] = {Country.SPAIN, Country.ITALY, Country.FRANCE};
		Country countries2[] = {Country.FRANCE, Country.ITALY, Country.SPAIN};
		Country countries3[] = {Country.FRANCE, Country.SPAIN, Country.ITALY};

		Country countries4[] = {Country.ITALY, Country.FRANCE, Country.SPAIN};
		Country countries5[] = {Country.ITALY, Country.SPAIN, Country.FRANCE};

		Country[][] countries_ = {countries, countries1, countries2, countries3, countries4,
				countries5};

		String path = "resources/data/40_2/";

		for (Country tab[] : countries_) {
			String result = App.process(path,tab);


			String expected_result = "chainRootId=34, country=ITALY, score=20\n"
					+ "chainRootId=33, country=SPAIN, score=14\n"
					+ "chainRootId=32, country=FRANCE, score=10";

			assertEquals(expected_result, result);
		}

	}

	/**
	 * Performs a multithreading test comparing the similarity between the output of method {@link #toString()} of DeserializationThread and an
	 * expected string for the data test 40_3.
	 *
	 * @throws IOException
	 */
	@Test
	public void multithreadingTest40_3() throws IOException, InterruptedException {

		Country countries[] = {Country.SPAIN, Country.FRANCE, Country.ITALY};
		Country countries1[] = {Country.SPAIN, Country.ITALY, Country.FRANCE};
		Country countries2[] = {Country.FRANCE, Country.ITALY, Country.SPAIN};
		Country countries3[] = {Country.FRANCE, Country.SPAIN, Country.ITALY};

		Country countries4[] = {Country.ITALY, Country.FRANCE, Country.SPAIN};
		Country countries5[] = {Country.ITALY, Country.SPAIN, Country.FRANCE};

		Country[][] countries_ = {countries, countries1, countries2, countries3, countries4,
				countries5};

		String path = "resources/data/40_3/";

		for (Country tab[] : countries_) {
			String result = App.process(path,tab);


			String expected_result = "chainRootId=31, country=ITALY, score=14\n"
					+ "chainRootId=32, country=FRANCE, score=10\n"
					+ "chainRootId=34, country=ITALY, score=10";

			assertEquals(expected_result, result);
		}

	}
	
	
}
