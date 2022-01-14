package com.njkol.references.soft;

import java.util.HashMap;
import java.lang.ref.SoftReference;

/**
 * 
 * @author Nilanjan Sarkar
 *
 * @param <K>
 * @param <V>
 */
public class SoftReferenceCache<K extends Comparable<K>, V> {

	private HashMap<K, SoftReference<V>> map = new HashMap<K, SoftReference<V>>();

	public V get(K key) {
		SoftReference<V> softReference = map.get(key);

		if (softReference == null) {
			return null;
		}
		return softReference.get();
	}

	public V put(K key, V value) {

		// the previous value associated with key, or null if there was no mapping for
		// key.
		// (A null return can also indicate that the mappreviously associated null with
		// key.)
		SoftReference<V> softRef = map.put(key, new SoftReference<V>(value));

		if (softRef == null) {
			return null;
		}

		V oldValue = softRef.get();
		softRef.clear();

		return oldValue;
	}

	public V remove(K key) {
		
		SoftReference<V> softRef = map.remove(key);

		if (softRef == null)
			return null;

		V oldValue = softRef.get();
		softRef.clear();

		return oldValue;
	}
}