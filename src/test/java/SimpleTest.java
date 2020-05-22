import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import Model.*;
import Model.Forest.Country;

import org.junit.Test;

public class SimpleTest {

    @Test
    public void naiveTest() throws IOException {

        Country countries[] = { Country.FRANCE, Country.ITALY, Country.SPAIN };
        String path = "../data/40/";
        String result = "";
        long start = System.nanoTime();
        int index = 0;

        for (Country country : countries) {
            Forest forest = new Forest(country);
            String countryString = country.toString().toLowerCase();
            countryString = Character.toUpperCase(countryString.charAt(0)) + countryString.substring(1);

            String fullPath = path + countryString + ".csv";
            File f = new File(fullPath);

            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(f), "UTF8"));

            String line;

            while ((line = in.readLine()) != null) {
                DataRow row = new DataRow(line);
                forest.insert(row);
            }
            if(index<2)
            {
                result = result + forest.toString() + "\n";
            }
            else
            {
                result = result + forest.toString();
            }

            in.close();
            index ++;

        }


        String expected_result = "FRANCE [32, 10]; [36, 10]; [28, 4]\n" + 
                "ITALY [34, 20]; [35, 10]; [31, 4]\n" + 
                "SPAIN [38, 10]; [39, 10]; [33, 4]";
        System.out.println(result);
        //System.out.println(expected_result);

        assertEquals(expected_result, result);



    }

}