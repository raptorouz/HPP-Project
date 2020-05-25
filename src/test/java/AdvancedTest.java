import static org.junit.Assert.*;

import java.io.IOException;

import Default.App;
import Model.*;
import Model.Forest.Country;

import org.junit.Test;

public class AdvancedTest {
    @Test
    public void test_onlyroots() throws IOException, InterruptedException {

        Country countries[] = { Country.SPAIN, Country.FRANCE, Country.ITALY };
        Country countries1[] = { Country.SPAIN, Country.ITALY, Country.FRANCE };
        Country countries2[] = { Country.FRANCE, Country.ITALY, Country.SPAIN };
        Country countries3[] = { Country.FRANCE, Country.SPAIN, Country.ITALY };

        Country countries4[] = { Country.ITALY, Country.FRANCE, Country.SPAIN };
        Country countries5[] = { Country.ITALY, Country.SPAIN, Country.FRANCE };

        Country[][] countries_ = { countries, countries1, countries2, countries3, countries4, countries5 };

        String path = "resources/data/onlyroots/";

        for (Country tab[] : countries_) {

            String result = App.process(path,tab);

            String expected_result = "chainRootId=7, country=SPAIN, score=10\n"
                    + "chainRootId=12, country=ITALY, score=10\n"
                    + "chainRootId=14, country=FRANCE, score=10";


            assertEquals(expected_result, result);
        }
    }

    @Test
    public void test_contaminatedByMultipleRootsWithScore0Chain() throws IOException, InterruptedException {

        Country countries[] = { Country.SPAIN, Country.FRANCE, Country.ITALY };
        Country countries1[] = { Country.SPAIN, Country.ITALY, Country.FRANCE };

        Country countries2[] = { Country.FRANCE, Country.ITALY, Country.SPAIN };
        Country countries3[] = { Country.FRANCE, Country.SPAIN, Country.ITALY };

        Country countries4[] = { Country.ITALY, Country.FRANCE, Country.SPAIN };
        Country countries5[] = { Country.ITALY, Country.SPAIN, Country.FRANCE };

        Country[][] countries_ = { countries, countries1, countries2, countries3, countries4, countries5 };
        String path = "resources/data/contaminatedby0score/";

        for (Country tab[] : countries_) {

            String result = App.process(path,tab);

            String expected_result = "chainRootId=7, country=SPAIN, score=10\n"
                    + "chainRootId=12, country=ITALY, score=10\n"
                    + "chainRootId=17, country=FRANCE, score=10";

            assertEquals(expected_result, result);
        }
    }

    @Test
    public void fewPeople() throws IOException, InterruptedException {

        Country countries[] = { Country.SPAIN, Country.FRANCE, Country.ITALY };
        Country countries1[] = { Country.SPAIN, Country.ITALY, Country.FRANCE };
        Country countries2[] = { Country.FRANCE, Country.ITALY, Country.SPAIN };
        Country countries3[] = { Country.FRANCE, Country.SPAIN, Country.ITALY };

        Country countries4[] = { Country.ITALY, Country.FRANCE, Country.SPAIN };
        Country countries5[] = { Country.ITALY, Country.SPAIN, Country.FRANCE };

        Country[][] countries_ = { countries, countries1, countries2, countries3, countries4, countries5 };

        String path = "resources/data/20_2/";

        for (Country tab[] : countries_) {

            String result = App.process(path,tab);

            String expected_result = "chainRootId=4, country=FRANCE, score=14\n"
                    + "chainRootId=1, country=ITALY, score=10\n";// + "chainRootId=32, country=FRANCE, score=10";

            assertEquals(expected_result, result);
        }

    }

    @Test
    public void sameRootInTop3() throws IOException, InterruptedException {

        Country countries[] = { Country.SPAIN, Country.FRANCE, Country.ITALY };
		Country countries1[] = { Country.SPAIN, Country.ITALY, Country.FRANCE };
		Country countries2[] = { Country.FRANCE, Country.ITALY, Country.SPAIN };
		Country countries3[] = { Country.FRANCE, Country.SPAIN, Country.ITALY };

		Country countries4[] = { Country.ITALY, Country.FRANCE, Country.SPAIN };
		Country countries5[] = { Country.ITALY, Country.SPAIN, Country.FRANCE };

        Country[][] countries_ = { countries, countries1, countries2, countries3, countries4, countries5 };

        for (Country tab[] : countries_) {
            String path = "resources/data/20_3/";

            String result = App.process(path, tab);

            String expected_result = "chainRootId=4, country=FRANCE, score=20\n"
                    + "chainRootId=4, country=FRANCE, score=20\n"
                    + "chainRootId=4, country=FRANCE, score=20";

            assertEquals(expected_result, result);
        }

    }

    /**
     * Test multithreading borderlines cases, i.e. boundaries data rows.
     *
     * France is used to test 168H boundaries
     * Italy is used to test 336H boundaries
     * Spain is used to test 168H and 168H boundaries
     *
     * @throws IOException
     */
    @Test
    public void testBorderlinesCases() throws IOException, InterruptedException {
        // Countries tested
        Country countries[] = {Country.SPAIN, Country.FRANCE, Country.ITALY};

        String path = "resources/data/boundaries/";

        // Top3
        String result = App.process(path, countries);

        // String of expected result
        String expected_result =
                "chainRootId=22, country=SPAIN, score=42\n" +
                        "chainRootId=10, country=FRANCE, score=34\n" +
                        "chainRootId=21, country=ITALY, score=18";

        // Equal assertion
        assertEquals(expected_result, result);

    }

    @Test
    public void testLotsOfCases() throws IOException, InterruptedException {
        // Countries tested
        Country countries[] = {Country.SPAIN, Country.FRANCE, Country.ITALY};

        String path = "resources/data/50000/";

        // Top3
        String result = App.process(path, countries);

        // String of expected result
        String expected_result = "chainRootId=24010, country=ITALY, score=72\n" +
                "chainRootId=25654, country=ITALY, score=64\n" +
                "chainRootId=30326, country=FRANCE, score=64";

        // Equal assertion
        assertEquals(expected_result, result);

    }
}


