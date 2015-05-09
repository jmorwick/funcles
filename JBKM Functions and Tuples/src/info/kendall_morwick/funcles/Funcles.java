/* Copyright 2011-2014 Joseph Kendall-Morwick

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

package info.kendall_morwick.funcles;


import info.kendall_morwick.funcles.tuple.Tuple2;
import info.kendall_morwick.funcles.tuple.Tuple3;
import info.kendall_morwick.funcles.tuple.Tuple4;
import info.kendall_morwick.funcles.tuple.Tuple5;
import info.kendall_morwick.relation.Relation2;
import info.kendall_morwick.relation.Relation3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import static info.kendall_morwick.funcles.tuple.Pair.makePair;
import static info.kendall_morwick.funcles.tuple.Triple.makeTriple;
import static info.kendall_morwick.funcles.tuple.Tuple.makeTuple;

/** A utility class which add useful functionality to existing guava Function 
 * implementations
 * 
 * @author Joseph Kendall-Morwick <jmorwick@indiana.edu>
 * @version 2.1
 *
 */
public class Funcles {

	/** a simple way to call apply for a function with 2 arguments 
	 * 
	 * @param f
	 * @param arg1
	 * @param arg2
	 * @return
	 */
	public static <F1,F2,T> T apply(Function<Tuple2<F1,F2>,T> f, F1 arg1, 
			F2 arg2) {
		return f.apply(makeTuple(arg1, arg2));
	}
	
	/** a simple way to call apply for a function with 3 arguments 
	 * 
	 * @param f
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @return
	 */
	public static <F1,F2,F3,T> T apply(Function<Tuple3<F1,F2,F3>,T> f, F1 arg1, 
			F2 arg2, F3 arg3) {
		return f.apply(makeTuple(arg1, arg2, arg3));
	}

	/** a simple way to call apply for a function with 4 arguments 
	 * 
	 * @param f
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @param arg4
	 * @return
	 */
	public static <F1,F2,F3,F4,T> T apply(Function<Tuple4<F1,F2,F3,F4>,T> f, 
			F1 arg1, F2 arg2, F3 arg3, F4 arg4) {
		return f.apply(makeTuple(arg1, arg2, arg3, arg4));
	}

	/** a simple way to call apply for a function with 5 arguments
	 * 
	 * @param f
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @param arg4
	 * @return
	 */
	public static <F1,F2,F3,F4,F5,T> T apply(Function<Tuple5<F1,F2,F3,F4,F5>,T> f, 
			F1 arg1, F2 arg2, F3 arg3, F4 arg4, F5 arg5) {
		return f.apply(makeTuple(arg1, arg2, arg3, arg4, arg5));
	}
	
	/** a simple way to call apply for a relation with 2 arguments
	 * 
	 * @param f
	 * @param arg1
	 * @param arg2
	 * @return
	 */
	public static <F,T> boolean apply(Relation2<F> r, F arg1, F arg2) {
		return r.test(makePair(arg1, arg2));
	}
	
	/** a simple way to call apply for a relation with 3 arguments
	 * 
	 * @param f
	 * @param arg1
	 * @param arg2
	 * @return
	 */
	public static <F,T> boolean apply(Relation3<F> r, F arg1, F arg2, 
			F arg3) {
		return r.apply(makeTriple(arg1, arg2, arg3));
	}
	
	
	/** modifies a function to cache its results
	 * 
	 * @param f function to be memoized
	 * @return memoized function f
	 */
	public static <F,T> MemoizedFunction<F,T> memoize(final Function<F,T> f) {
		return memoize(f, CacheBuilder.newBuilder());
	}
	
	
	/** modifies a function to cache its results
	 * 
	 * @param f function to be memoized
	 * @param builder a cache builder which creates a cache for mapping inputs to outputs
	 * @return memozied function f
	 */
	public static <F,T> MemoizedFunction<F,T> memoize(final Function<F,T> f, 
				final CacheBuilder<? super F, ? super T> builder) {
		final LoadingCache<F,T> cache = builder.build(CacheLoader.from(f::apply));
		return new MemoizedFunction<F,T>() {
			
			public T apply(final F input) {
				try {
					return cache.get(input);
				} catch (ExecutionException e) {
					//Functions can't throw checked exceptions, so this never happens
					return null;
				}
			}

			@Override
			public LoadingCache<F, T> getCache() {
				return cache;
			}

			@Override
			public Function<F, T> getOriginalFunction() {
				return f;
			}
		};
	}
	


    /** returns the argument which maximizes this function
     *
     * @param inputs all arguments to be evaluated with this function
     * @return the argument from 'inputs' maximizing this function
     */
    @SafeVarargs
	public static <F, T extends Comparable<T>> F argmax(Function<F,T> f, 
    		F ... inputs) {
        List<F> ls = new ArrayList<F>();
        for(F i : inputs) ls.add(i);
        return argmax(f, ls);
    }

    /** returns the argument from the collection which maximizes the function f
     *
     * @param inputs all arguments to be evaluated with this function
     * @return the argument from 'inputs' maximizing this function
     */
    public static <F,T extends Comparable<T>> F argmax(Function<F,T> f, 
    		Collection<F> inputs) {
        T max = null;
        F maxarg = null;
        try { 
        	for(F input : inputs) {
                T cur = f.apply(input);
                if(max == null || cur.compareTo(max) > 0) {
                    max = cur;
                    maxarg = input;
                }
            }
            return maxarg;
        } catch(ClassCastException e) {
            throw new RuntimeException(
            		"Argmax called on a funnction without comparable output");
        }

    }


    
    /** partitions the set s according to the relation r.
     *  this method assumes r is an equivalence relation.
     * @param s
     * @return a set of the partitions formed from the set s
     */
    public static <T> Set<Set<T>> partition(Relation2<T> r, Set<T> s) {
    	Set<Set<T>> sets = new HashSet<Set<T>>();
    	for(T x : s) {
    		boolean foundSet = false;
    		for(Set<T> p : sets) {
    			if(apply(r, x, p.iterator().next())) {
    				p.add(x); //found a compatible partition
    				foundSet = true;
    				break; //don't add a new partition later
    			}
    		}
    		if(!foundSet) {// no compatible partition found, add a new one
    			Set<T> p = new HashSet<T>();
    			p.add(x);
    			sets.add(p);
    		}
    	}
    	return sets;
    }
    
   
}
