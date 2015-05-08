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

import info.kendall_morwick.funcles.Funcles;
import info.kendall_morwick.funcles.tuple.Tuple2;
import info.kendall_morwick.funcles.tuple.Tuple3;

import com.google.common.base.Function;

/** 
 *
 * @author Joseph Kendall-Morwick <jmorwick@indiana.edu>
 * @version 2.0
 */
@FunctionalInterface
public abstract interface Function3<P1,P2,P3,R> extends Function<Tuple3<P1,P2,P3>,R> {
	public default R apply(Tuple3<P1,P2,P3> args) {
		return apply(args.a1(), args.a2(), args.a3());
	}

	public R apply(P1 a1, P2 a2, P3 a3);
	
	public static <P1,P2,P3,R> Function3<P1,P2,P3,R> 
		toFunction3(Function<Tuple3<P1,P2,P3>, R> f) {
		return (arg1, arg2, arg3) -> Funcles.apply(f, arg1, arg2, arg3);
	}

	
	public static <P1, P2, P3, R> Function3<P1,P2,P3,R>
		 applyHigherOrder(Function<? super Function3<P1,P2,P3,R>, 
				                   ? extends Function<Tuple3<P1,P2,P3>,R>> hof,
				          Function3<P1,P2,P3,R> f) {
		return toFunction3(hof.apply(f));
	}
			
}
