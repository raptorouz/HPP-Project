import static org.junit.Assert.*;

import org.junit.Test;

import Model.DataRow;

public class DataRowTest {
	/**
	 * Test to validate DataRow Model Constructor
	 * 
	 * @result DataRows will be created in a valid format, with no errors,
	 */
	@Test
	public void testDataRowConstructor() {
		String dataToCreateRandom = "135, \"Julie\", \"PRICE\", 2010-01-08 00:00:00, 1582765975.45362, 82, \"basket avec les enfants au marché\"";
		DataRow data = null;
		data = new DataRow(dataToCreateRandom);
		assertNotNull(data);
		assertTrue(data.getId() == 135);
		assertTrue(data.getContaminatedBy() == 82);
		assertTrue(data.getDiagnosedTs() == 1582765975);
	}
	/**
	 * Test to validate that persons contaminated by "unknown"  get a "contaminatedBy" id of -1
	 * 
	 * @result a DataRow will be created with a contaminated id of -1 
	 */
	@Test
	public void testDataRowRoot() {
		String dataToCreateRoot = "0, \"Patricia\", \"PEREZ\", 1956-01-11 00:00:00, 1573945200.0, unknown, \"course à pieds avec le chien à la campagne\"";
		DataRow data = null;
		data = new DataRow(dataToCreateRoot);
		assertNotNull(data);
		assertTrue(data.getContaminatedBy() == -1);
	}
}
