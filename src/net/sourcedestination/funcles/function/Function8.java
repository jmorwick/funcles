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
public abstract interface Function8<A1, A2, A3, A4, A5, A6, A7, A8, R> extends Function<Tuple8<A1, A2, A3, A4, A5, A6, A7, A8>, R> {
	public default R apply(Tuple8<A1, A2, A3, A4, A5, A6, A7, A8> args) {
		return apply(args._1, args._2, args._3, args._4, args._5, args._6, args._7, args._8);
	}

	public R apply(A1 arg1, A2 arg2, A3 arg3, A4 arg4, A5 arg5, A6 arg6, A7 arg7, A8 arg8);
	
	
	public default Function7<A2, A3, A4, A5, A6, A7, A8, R> bind1(A1 arg) {
	    return (a2, a3, a4, a5, a6, a7, a8) -> apply(arg, a2, a3, a4, a5, a6, a7, a8);
	}
	
	public default Function7<A1, A3, A4, A5, A6, A7, A8, R> bind2(A2 arg) {
	    return (a1, a3, a4, a5, a6, a7, a8) -> apply(a1, arg, a3, a4, a5, a6, a7, a8);
	}
	
	public default Function7<A1, A2, A4, A5, A6, A7, A8, R> bind3(A3 arg) {
	    return (a1, a2, a4, a5, a6, a7, a8) -> apply(a1, a2, arg, a4, a5, a6, a7, a8);
	}
	
	public default Function7<A1, A2, A3, A5, A6, A7, A8, R> bind4(A4 arg) {
	    return (a1, a2, a3, a5, a6, a7, a8) -> apply(a1, a2, a3, arg, a5, a6, a7, a8);
	}
	
	public default Function7<A1, A2, A3, A4, A6, A7, A8, R> bind5(A5 arg) {
	    return (a1, a2, a3, a4, a6, a7, a8) -> apply(a1, a2, a3, a4, arg, a6, a7, a8);
	}
	
	public default Function7<A1, A2, A3, A4, A5, A7, A8, R> bind6(A6 arg) {
	    return (a1, a2, a3, a4, a5, a7, a8) -> apply(a1, a2, a3, a4, a5, arg, a7, a8);
	}
	
	public default Function7<A1, A2, A3, A4, A5, A6, A8, R> bind7(A7 arg) {
	    return (a1, a2, a3, a4, a5, a6, a8) -> apply(a1, a2, a3, a4, a5, a6, arg, a8);
	}
	
	public default Function7<A1, A2, A3, A4, A5, A6, A7, R> bind8(A8 arg) {
	    return (a1, a2, a3, a4, a5, a6, a7) -> apply(a1, a2, a3, a4, a5, a6, a7, arg);
	}
	
	public static <A1, A2, A3, A4, A5, A6, A7, A8,R> Function8<A1, A2, A3, A4, A5, A6, A7, A8,R> 
		toFunction8(Function<Tuple8<A1, A2, A3, A4, A5, A6, A7, A8>, R> f) {
		return (arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8) -> 
		  f.apply(makeTuple(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8));
	}
	
	public static <A1, A2, A3, A4, A5, A6, A7, A8, R> Function8<A1, A2, A3, A4, A5, A6, A7, A8,R>
		 applyHigherOrder(Function< ? super Function8<A1, A2, A3, A4, A5, A6, A7, A8,R>, 
				                   ? extends Function<Tuple8<A1, A2, A3, A4, A5, A6, A7, A8>,R>> hof,
				          Function8<A1, A2, A3, A4, A5, A6, A7, A8,R> f) {
		return toFunction8(hof.apply(f));
	}
			
}
