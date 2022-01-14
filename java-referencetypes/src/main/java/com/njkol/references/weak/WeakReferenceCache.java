package com.njkol.references.weak;

import java.lang.ref.WeakReference;
import java.util.HashMap;

/**
 * An implementation of weak reference cache
 * 
 * @author Nilanjan Sarkar
 *
 * @param <K>
 * @param <V>
 */
public class WeakReferenceCache<K extends Comparable<K>, V> {

	private HashMap<K, WeakReference<V>> map = new HashMap<K, WeakReference<V>>();

	public V get(K key) {
		WeakReference<V> WeakReference = map.get(key);

		if (WeakReference == null) {
			return null;
		}
		return WeakReference.get();
	}

	public void put(K key, V value) {
		map.put(key, new WeakReference<V>(value));
	}
}