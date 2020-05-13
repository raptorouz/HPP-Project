package Model;

import java.util.Date;

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
	public long getDiagnosedTs() {
		return diagnosedTs;
	}
	public void setDiagnosedTs(long diagnosedTs) {
	public void setDiagnosedTs(long diagnosedTs) {
		this.diagnosedTs = diagnosedTs;
	}

	public int getContaminatedBy() {
		return contaminatedBy;
	}

	public void setContaminatedBy(int contaminatedBy) {
		this.contaminatedBy = contaminatedBy;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + contaminatedBy;
		result = prime * result + (int) (diagnosedTs ^ (diagnosedTs >>> 32));
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DataRow other = (DataRow) obj;
		if (contaminatedBy != other.contaminatedBy)
			return false;
		if (diagnosedTs != other.diagnosedTs)
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
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
