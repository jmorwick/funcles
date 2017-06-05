/* Copyright 2011-2017 Joseph Kendall-Morwick

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

import net.sourcedestination.funcles.tuple.*;

import static net.sourcedestination.funcles.tuple.Tuple.makeTuple;


/** 
 *
 * @author Joseph Kendall-Morwick <jmorwick@indiana.edu>
 * @version 2.0
 */
@FunctionalInterface
public interface Function4<A1, A2, A3, A4, R> extends Function<Tuple4<A1, A2, A3, A4>, R> {
	default R apply(Tuple4<A1, A2, A3, A4> args) {
		return apply(args._1, args._2, args._3, args._4);
	}

	R apply(A1 arg1, A2 arg2, A3 arg3, A4 arg4);
	
	
	default Function3<A2, A3, A4, R> bind1(A1 arg) {
	    return (a2, a3, a4) -> apply(arg, a2, a3, a4);
	}
	
	default Function3<A1, A3, A4, R> bind2(A2 arg) {
	    return (a1, a3, a4) -> apply(a1, arg, a3, a4);
	}
	
	default Function3<A1, A2, A4, R> bind3(A3 arg) {
	    return (a1, a2, a4) -> apply(a1, a2, arg, a4);
	}
	
	default Function3<A1, A2, A3, R> bind4(A4 arg) {
	    return (a1, a2, a3) -> apply(a1, a2, a3, arg);
	}
	
	static <A1, A2, A3, A4,R> Function4<A1, A2, A3, A4,R>
		toFunction4(Function<Tuple4<A1, A2, A3, A4>, R> f) {
		return (arg1, arg2, arg3, arg4) -> 
		  f.apply(makeTuple(arg1, arg2, arg3, arg4));
	}
	
	static <A1, A2, A3, A4, R> Function4<A1, A2, A3, A4,R>
		 applyHigherOrder(Function< ? super Function4<A1, A2, A3, A4,R>, 
				                   ? extends Function<Tuple4<A1, A2, A3, A4>,R>> hof,
				          Function4<A1, A2, A3, A4,R> f) {
		return toFunction4(hof.apply(f));
	}
			
}
