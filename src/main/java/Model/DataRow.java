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

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public long getDiagnosedTs() {
		return diagnosedTs;
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
	
	public String toDebugString(long latestTs) {
		return "" + id + ", " + (firstName != null ? firstName + ", " : "")
				+ (lastName != null ? lastName + ", " : "") + 
				Utils.Utilities.distanceToLatestDate(diagnosedTs, latestTs)
				+ ", " + contaminatedBy;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
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
		return id == other.id;

	}

}
