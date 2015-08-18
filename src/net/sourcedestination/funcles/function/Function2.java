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

package net.sourcedestination.funcles.function;

import java.util.function.Function;
import java.util.function.BiFunction;

import net.sourcedestination.funcles.Funcles;
import net.sourcedestination.funcles.tuple.*;

import static net.sourcedestination.funcles.tuple.Tuple.makeTuple;


/** 
 *
 * @author Joseph Kendall-Morwick <jmorwick@indiana.edu>
 * @version 2.0
 */
@FunctionalInterface
public abstract interface Function2<A1, A2, R> extends Function<Tuple2<A1, A2>, R>,
														BiFunction<A1,A2,R>  {
	public default R apply(Tuple2<A1, A2> args) {
		return apply(args._1, args._2);
	}

	public R apply(A1 arg1, A2 arg2);
	
	
	public default Function<A2, R> bind1(A1 arg) {
	    return (a2) -> apply(arg, a2);
	}
	
	public default Function<A1, R> bind2(A2 arg) {
	    return (a1) -> apply(a1, arg);
	}
	
	public static <A1, A2,R> Function2<A1, A2,R> 
		toFunction2(Function<Tuple2<A1, A2>, R> f) {
		return (arg1, arg2) -> 
		  f.apply(makeTuple(arg1, arg2));
	}
	
	public static <A1, A2, R> Function2<A1, A2,R>
		 applyHigherOrder(Function< ? super Function2<A1, A2,R>, 
				                   ? extends Function<Tuple2<A1, A2>,R>> hof,
				          Function2<A1, A2,R> f) {
		return toFunction2(hof.apply(f));
	}
	
	public default <V> Function2<A1, A2, V> andThen(Function< ? super R, ? extends V> after) {
		return (x, y) -> after.apply(apply(x, y));
		
	}
			
}
