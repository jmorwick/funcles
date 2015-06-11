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

package info.kendall_morwick.function;

import java.util.function.Function;

import info.kendall_morwick.funcles.Funcles;
import info.kendall_morwick.funcles.tuple.Tuple2;

/** 
 *
 * @author Joseph Kendall-Morwick <jmorwick@indiana.edu>
 * @version 2.0
 */
@FunctionalInterface
public abstract interface Function2<P1,P2,R> extends Function<Tuple2<P1,P2>,R> {
	public default R apply(Tuple2<P1,P2> args) {
		return apply(args.a1(), args.a2());
	}

	public R apply(P1 a1,P2 a2);
	
	public default Function2<P1,P2,R> applyHigherOrderTo(Function<? super Function2<P1,P2,R>, 
				                                                ? extends Function<Tuple2<P1,P2>,R>> hof) {
		return toFunction2(hof.apply(this));
	}
	
	public static <P1, P2, R> Function2<P1,P2,R> 
		toFunction2(Function<Tuple2<P1, P2>, R> f) {
		return (arg1, arg2) -> Funcles.apply(f, arg1, arg2);
	}
	
	public static <P1, P2, R> Function2<P1,P2,R>
		 applyHigherOrder(Function<? super Function2<P1,P2,R>, 
				                   ? extends Function<Tuple2<P1,P2>,R>> hof,
				          Function2<P1,P2,R> f) {
		return toFunction2(hof.apply(f));
	}
			
}
