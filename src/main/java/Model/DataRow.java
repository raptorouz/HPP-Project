package Model;

import java.util.Objects;

public class DataRow {

	private int id;

	private String firstName;
	private String lastName;
	private long diagnosedTs;
	private int contaminatedBy;

	public DataRow(String line) {
		String tokens[] = line.split(", ");
		this.id = Integer.parseInt(tokens[0]);

		this.firstName = tokens[1];
		this.lastName = tokens[2];

		// .diagnosedTs = new Date(1000 * Integer.parseInt(tokens[4]))
	    this.diagnosedTs = (long) Double.parseDouble(tokens[4]);

		try {
			this.contaminatedBy = Integer.parseInt(tokens[5]);
		} catch (NumberFormatException ex) {
			this.contaminatedBy = -1;
		}

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
	public long getDiagnosedTs() {
		return diagnosedTs;
	}
	public void setDiagnosedTs(long diagnosedTs) {
		this.diagnosedTs = diagnosedTs;
	}

	public int getContaminatedBy() {
		return contaminatedBy;
	}



	@Override
	public String toString() {
		return "[id=" + id + ", " + (firstName != null ? "firstName=" + firstName + ", " : "")
				+ (lastName != null ? "lastName=" + lastName + ", " : "") + "diagnosedTs=" + 
				Utils.Utilities.timestampToString(diagnosedTs)
				+ ", contaminatedBy=" + contaminatedBy + "]";
	}

	public void setContaminatedBy(int contaminatedBy) {
		this.contaminatedBy = contaminatedBy;
	}

	@Override
	public int hashCode() {
		return Objects.hash(contaminatedBy, diagnosedTs, firstName, id, lastName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof DataRow)) {
			return false;
		}
		DataRow other = (DataRow) obj;
		return contaminatedBy == other.contaminatedBy && diagnosedTs == other.diagnosedTs
				&& Objects.equals(firstName, other.firstName) && id == other.id
				&& Objects.equals(lastName, other.lastName);
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
