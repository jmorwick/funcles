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

import static net.sourcedestination.funcles.tuple.Tuple.makeTuple;


/** 
 *
 * @author Joseph Kendall-Morwick <jmorwick@indiana.edu>
 * @version 2.0
 */
@FunctionalInterface
public abstract interface Function5<A1, A2, A3, A4, A5, R> extends Function<Tuple5<A1, A2, A3, A4, A5>, R> {
	public default R apply(Tuple5<A1, A2, A3, A4, A5> args) {
		return apply(args._1, args._2, args._3, args._4, args._5);
	}

	public R apply(A1 arg1, A2 arg2, A3 arg3, A4 arg4, A5 arg5);
	
	
	public default Function4<A2, A3, A4, A5, R> bind1(A1 arg) {
	    return (a2, a3, a4, a5) -> apply(arg, a2, a3, a4, a5);
	}
	
	public default Function4<A1, A3, A4, A5, R> bind2(A2 arg) {
	    return (a1, a3, a4, a5) -> apply(a1, arg, a3, a4, a5);
	}
	
	public default Function4<A1, A2, A4, A5, R> bind3(A3 arg) {
	    return (a1, a2, a4, a5) -> apply(a1, a2, arg, a4, a5);
	}
	
	public default Function4<A1, A2, A3, A5, R> bind4(A4 arg) {
	    return (a1, a2, a3, a5) -> apply(a1, a2, a3, arg, a5);
	}
	
	public default Function4<A1, A2, A3, A4, R> bind5(A5 arg) {
	    return (a1, a2, a3, a4) -> apply(a1, a2, a3, a4, arg);
	}
	
	public static <A1, A2, A3, A4, A5,R> Function5<A1, A2, A3, A4, A5,R> 
		toFunction5(Function<Tuple5<A1, A2, A3, A4, A5>, R> f) {
		return (arg1, arg2, arg3, arg4, arg5) -> 
		  f.apply(makeTuple(arg1, arg2, arg3, arg4, arg5));
	}
	
	public static <A1, A2, A3, A4, A5, R> Function5<A1, A2, A3, A4, A5,R>
		 applyHigherOrder(Function< ? super Function5<A1, A2, A3, A4, A5,R>, 
				                   ? extends Function<Tuple5<A1, A2, A3, A4, A5>,R>> hof,
				          Function5<A1, A2, A3, A4, A5,R> f) {
		return toFunction5(hof.apply(f));
	}
			
}
