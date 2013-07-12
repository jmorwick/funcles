/* Copyright 2011-2013 Joseph Kendall-Morwick

     This file is part of the Funcles library.

    Funcles is free software: you can redistribute it and/or modify
    it under the terms of the Lesser GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Funcles is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    Lesser GNU General Public License for more details.

    You should have received a copy of the Lesser GNU General Public License
    along with Funcles.  If not, see <http://www.gnu.org/licenses/>.

 */

package info.km.funcles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *  Implements a collection for caching that utilizes least-recently-used 
 *  deletion.  
 *
  @author Joseph Kendall-Morwick <jmorwick@indiana.edu>
  @version 0.1
 */
public class Cache<K,V> {

    private Map<K,V> cache = new HashMap<K,V>();
    private Map<K, Integer> usage = new HashMap<K, Integer>();
    private int maxSize;
    private int startVal=0;
    private int ageEvery=0;
    private int ageAgain=0;
    private boolean keepMostRecent = false; //the alternative is to keep the most used values

    public Cache(int maxSize) {
        this.maxSize = maxSize;
    }

    public void set(K k, V v) {
        if(!cache.containsKey(k)) {
            if(maxSize > 0 && cache.size() == maxSize) {
                K toRemove = getRandomMostFrequentKey();
                cache.remove(toRemove);
                usage.remove(toRemove);
            }
            cache.put(k, v);
            usage.put(k, startVal);
        }
    }

    private K getRandomMostFrequentKey() {
    	int max = Integer.MIN_VALUE;
    	List<K> best = new ArrayList<K>();
    	for(K k : usage.keySet()) {
    		int freq = usage.get(k);
    		if(freq > max) { 
    			max = usage.get(k);
    			best.clear();
    		}
    		if(freq == max)
    			best.add(k);
    	}
    	if(best.size() == 0) return null;
    	return best.get((int)(Math.random()*best.size()));
	}

	public V get(K k) {
        if(ageEvery>0) { //age all other cached items
            if(ageAgain == 0) {
                ageAgain = ageEvery;
                age();
            } else ageAgain--;
        }
        if(cache.containsKey(k)) {
        	if(!usage.containsKey(k))
        		usage.put(k,  0);
            usage.put(k, usage.get(k)-1); //decrement by 1
            return cache.get(k);
        }
        return null;
    }

    public void remove(K k) {
        cache.remove(k);
        usage.remove(k);
    }

    public boolean contains(K k) {
        return cache.containsKey(k);
    }

    public void clear() {
        cache.clear();
        usage.clear();
    }

    /** increase the age of cached items every 'steps' times the cache is accessed.
     */
    public void ageEvery(int steps) {
        ageEvery = steps;
    }

    /** increase the age of all cached items */
    public void age() {
        if(startVal == Integer.MAX_VALUE) { 
            //this will rarely ever happen, but if it does, the cache starting
            //age resets and all existing cached values get the maximum age. 
            for(K k : new HashSet<K>(usage.keySet())) {
                usage.put(k, Integer.MIN_VALUE);
                startVal = 0;
            }
        }
        startVal++;
    }
}