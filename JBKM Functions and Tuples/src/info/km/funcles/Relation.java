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

import java.util.HashSet;
import java.util.Set;

import info.km.funcles.tuples.T2;

/** This class provides a clean abstraction for implementing binary relations
 *
 * @author Joseph Kendall-Morwick <jmorwick@indiana.edu>
 * @version 1.0
 */
public abstract class Relation<T> extends Predicate<T2<T,T>> {

    /** defers to 'relates' */
    @Override
    public Boolean implementation(T2<T,T> args) {
        return relates(args.a1(), args.a2());
    }

    /** Relations must implement this method, which determines if two objects
     * are related.
     * @param arg1
     * @param arg2
     * @return
     */
    public abstract boolean relates(T arg1, T arg2);

    
    
    /** partitions the set s according to the relation r.
     *  this method assumes r is an equivalence relation.
     * @param s
     * @return a set of the partitions formed from the set s
     */
    public static <T> Set<Set<T>> partition(Relation<T> r, Set<T> s) {
    	Set<Set<T>> sets = new HashSet<Set<T>>();
    	for(T x : s) {
    		for(Set<T> p : sets) {
    			if(r.relates(x, p.iterator().next())) {
    				p.add(x); //found a compatible partition
    				break; //don't add a new partition later
    			}
    		}
    		// no compatible partition found, add a new one
    		Set<T> p = new HashSet<T>();
    		p.add(x);
    		sets.add(p);
    	}
    	return sets;
    }
}
