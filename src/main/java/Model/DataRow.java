package Model;

import java.util.Date;

public class DataRow {
	
	private int id;
	
	private String firstName;
	private String lastName;
	private Date diagnosedTs;
	private int contaminatedBy;
	
	public DataRow(String line) {
		String tokens[] = line.split(", ");
		this.id = Integer.parseInt(tokens[0]);
		
		this.firstName = tokens[1];
		this.lastName = tokens[2];
		
	    this.diagnosedTs = new Date(1000 * Integer.parseInt(tokens[4]));
	    this.contaminatedBy = Integer.parseInt(tokens[5]);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDiagnosedTs() {
		return diagnosedTs;
	}
	public void setDiagnosedTs(Date diagnosedTs) {
		this.diagnosedTs = diagnosedTs;
	}
	public int getContaminatedBy() {
		return contaminatedBy;
	}
	public void setContaminatedBy(int contaminatedBy) {
		this.contaminatedBy = contaminatedBy;
	}
	

}
//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD HH-mm-ss");	
//Date birthdate = null;
//try {
//	birthdate = sdf.parse(tokens[3]);
//} catch (ParseException e) {
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//}
//
//long diffInMillies = Math.abs(new Date().getTime() - birthdate.getTime());
//long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
