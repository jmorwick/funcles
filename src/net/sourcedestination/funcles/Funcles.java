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

package net.sourcedestination.funcles;


import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

import net.sourcedestination.funcles.relation.Relation2;
import net.sourcedestination.funcles.relation.Relation3;
import net.sourcedestination.funcles.tuple.Tuple2;
import net.sourcedestination.funcles.tuple.Tuple3;
import net.sourcedestination.funcles.tuple.Tuple4;
import net.sourcedestination.funcles.tuple.Tuple5;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Sets;

import static net.sourcedestination.funcles.tuple.Pair.makePair;
import static net.sourcedestination.funcles.tuple.Triple.makeTriple;
import static net.sourcedestination.funcles.tuple.Tuple.makeTuple;


/** A utility class which add useful functionality to existing guava Function 
 * implementations
 * 
 * @author Joseph Kendall-Morwick <jmorwick@indiana.edu>
 * @version 2.1
 *
 */
public class Funcles {

	/** a simple way to call accept for a consumer with 2 arguments 
	 * 
	 * @param f
	 * @param arg1
	 * @param arg2
	 * @return
	 */
	public static <F1,F2> void accept(Consumer<Tuple2<F1,F2>> c, F1 arg1, 
			F2 arg2) {
		c.accept(makeTuple(arg1, arg2));
	}
	
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
	
	



    /** returns the argument from the collection which maximizes the function f
     *
     * @param inputs all arguments to be evaluated with this function
     * @return the argument from 'inputs' maximizing this function
     */
    public static <F,T extends Comparable<T>> F argmax(Function<F,T> f, 
    		Collection<F> inputs) {
    	return argmax(f, inputs.parallelStream());
    }
    
    /** returns the argument which maximizes this function
    *
    * @param inputs all arguments to be evaluated with this function
    * @return the argument from 'inputs' maximizing this function
    */
   @SafeVarargs
	public static <F, T extends Comparable<T>> F argmax(Function<F,T> f, 
   		F ... inputs) {
       return argmax(f, Arrays.stream(inputs).parallel());
    }
    

    /** returns the argument from the collection which maximizes the function f
     *
     * @param inputs all arguments to be evaluated with this function
     * @return the argument from 'inputs' maximizing this function
     */
    public static <F,T extends Comparable<T>> F argmax(Function<F,T> f, 
    		Stream<F> inputs) {
    	return inputs.map(x -> makeTuple(x, f.apply(x))) 
    			.max((x,y) -> x.a2().compareTo(y.a2())).get().a1();
    }

   
}
