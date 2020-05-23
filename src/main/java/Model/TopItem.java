package Model;

import java.util.Objects;

import Model.Forest.Country;

public class TopItem implements Comparable<TopItem>{
	private int chainRootId;
	private Country country;
	private int score;
	
	private int chainLeafId; //Used to check if the chain to be inserted is new or not
	private long rootTs;

	public TopItem(int chainRootId, Country country, int score, int chainLeafId, long rootTs) {
		super();
		this.chainRootId = chainRootId;
		this.country = country;
		this.score = score;
		this.chainLeafId = chainLeafId;
		this.rootTs = rootTs;
	}

	@Override
	public int compareTo(TopItem other) {
		if(other != null)
			return this.score != other.score ? this.score - other.score : -(int)(this.getRootTs() - other.getRootTs());
		else
			return 1;
	}

	@Override
	public int hashCode() {
		return Objects.hash(chainRootId, country, score, chainLeafId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof TopItem)) {
			return false;
		}
		TopItem other = (TopItem) obj;
		return chainLeafId == other.chainLeafId && chainRootId == other.chainRootId;
	}

	public int getChainRootId() {
		return chainRootId;
	}

	public Country getCountry() {
		return country;
	}
	public int getScore() {
		return score;
	}

	public int getChainLeafId() {
		return chainLeafId;
	}

	public long getRootTs() {
		return rootTs;
	}

	@Override
	public String toString() {
		return "chainRootId=" + chainRootId + ", " + "country=" + country + ", "
				+ "score=" + score;
	}
	
}
