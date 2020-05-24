package Model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import Utils.Entry;

public class SortedMap <K, V extends Comparable<V>> {
	private TreeSet<Entry<K, V>> leafScoreSet;
	private HashMap<K, V> leafScoreMap;
	
	public SortedMap() {
		leafScoreSet = new TreeSet<Entry<K,V>>();
		leafScoreMap = new HashMap<K, V>();
	}
	
	public void add(K key, V value) {
		leafScoreSet.add(new Entry<K, V>(key, value));
		leafScoreMap.put(key, value);
	}
	
	public V get(K key) {
		return leafScoreMap.get(key);
	}
	
	public V remove(K key) {
		V value = get(key);
		if(value != null) {
			leafScoreMap.remove(key);
			//System.out.println(Thread.currentThread().getId() + ": " + value);
			leafScoreSet.remove(new Entry<K, V>(key, value));
		}
		
		return value;
	}
	
	public Iterator<Entry<K, V>> iterator() {
		return leafScoreSet.iterator();
	}
	
	public TreeSet<Entry<K, V>> set() {
		return this.leafScoreSet;
	}
	
	public Set<K> keySet() {
		return leafScoreMap.keySet();
	}
	
	public boolean AreAllValuesZero() {
		boolean allZero = true;
		Iterator<V> iter = leafScoreMap.values().iterator();
		while(iter.hasNext() && allZero)
		{
			allZero = allZero && iter.next().equals(0); 
		}
		return allZero;
	}
}
