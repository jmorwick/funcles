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

import net.sourcedestination.funcles.Funcles;
import net.sourcedestination.funcles.tuple.*;


/** 
 *
 * @author Joseph Kendall-Morwick <jmorwick@indiana.edu>
 * @version 2.0
 */
@FunctionalInterface
public abstract interface Function6<A1, A2, A3, A4, A5, A6, R> extends Function<Tuple6<A1, A2, A3, A4, A5, A6>, R> {
	public default R apply(Tuple6<A1, A2, A3, A4, A5, A6> args) {
		return apply(args._1, args._2, args._3, args._4, args._5, args._6);
	}

	public R apply(A1 arg1, A2 arg2, A3 arg3, A4 arg4, A5 arg5, A6 arg6);
	
	
	public default Function5<A2, A3, A4, A5, A6, R> bind1(A1 arg) {
	    return (a2, a3, a4, a5, a6) -> apply(arg, a2, a3, a4, a5, a6);
	}
	
	public default Function5<A1, A3, A4, A5, A6, R> bind2(A2 arg) {
	    return (a1, a3, a4, a5, a6) -> apply(a1, arg, a3, a4, a5, a6);
	}
	
	public default Function5<A1, A2, A4, A5, A6, R> bind3(A3 arg) {
	    return (a1, a2, a4, a5, a6) -> apply(a1, a2, arg, a4, a5, a6);
	}
	
	public default Function5<A1, A2, A3, A5, A6, R> bind4(A4 arg) {
	    return (a1, a2, a3, a5, a6) -> apply(a1, a2, a3, arg, a5, a6);
	}
	
	public default Function5<A1, A2, A3, A4, A6, R> bind5(A5 arg) {
	    return (a1, a2, a3, a4, a6) -> apply(a1, a2, a3, a4, arg, a6);
	}
	
	public default Function5<A1, A2, A3, A4, A5, R> bind6(A6 arg) {
	    return (a1, a2, a3, a4, a5) -> apply(a1, a2, a3, a4, a5, arg);
	}
	
	public static <A1, A2, A3, A4, A5, A6,R> Function6<A1, A2, A3, A4, A5, A6,R> 
		toFunction6(Function<Tuple6<A1, A2, A3, A4, A5, A6>, R> f) {
		return (arg1, arg2, arg3, arg4, arg5, arg6) -> 
		  Funcles.apply(f, arg1, arg2, arg3, arg4, arg5, arg6);
	}
	
	public static <A1, A2, A3, A4, A5, A6, R> Function6<A1, A2, A3, A4, A5, A6,R>
		 applyHigherOrder(Function< ? super Function6<A1, A2, A3, A4, A5, A6,R>, 
				                   ? extends Function<Tuple6<A1, A2, A3, A4, A5, A6>,R>> hof,
				          Function6<A1, A2, A3, A4, A5, A6,R> f) {
		return toFunction6(hof.apply(f));
	}
			
}
