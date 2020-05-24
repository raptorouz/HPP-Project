package Utils;

import java.util.Objects;

public class Entry <K extends Object, V extends Comparable<V>> implements Comparable<Entry<K, V>> {
	private K key;
	private V value;
	
	public Entry(K key, V value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public int compareTo(Entry<K, V> other) {
		if(other != null) {
			int res = - this.value.compareTo(other.value);
			//System.out.println(Thread.currentThread().getName() + ": " + res);
			return res;
		}
		else {
			return 1;
		}
		
	}

	public K getKey() {
		return key;
	}

	public V getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(key, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Entry)) {
			return false;
		}
		Entry<K, V> other = (Entry<K, V>) obj;
		return Objects.equals(key, other.key) && Objects.equals(value, other.value);
	}

	@Override
	public String toString() {
		return "Entry [" + (key != null ? "key=" + key + ", " : "") + (value != null ? "value=" + value : "") + "]";
	}
	
}
