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

package net.sourcedestination.funcles.consumer;

import java.util.function.Consumer;
import java.util.function.Function;

import net.sourcedestination.funcles.Funcles;
import net.sourcedestination.funcles.tuple.Tuple4;

/** 
 *
 * @author Joseph Kendall-Morwick <jmorwick@indiana.edu>
 * @version 2.0
 */
@FunctionalInterface
public abstract interface Consumer4<P1,P2,P3,P4> extends Consumer<Tuple4<P1,P2,P3,P4>> {
	
	public default void accept(Tuple4<P1,P2,P3,P4> args) {
		accept(args.a1(), args.a2(), args.a3(), args.a4());
	}
	
	public void accept(P1 a1,P2 a2, P3 a3, P4 a4);
	
	public default Consumer4<P1,P2,P3,P4> applyHigherOrderTo(Function<? super Consumer4<P1,P2,P3,P4>, 
				                                                ? extends Consumer<Tuple4<P1,P2,P3,P4>>> hof) {
		return toConsumer4(hof.apply(this));
	}
	
	public static <P1, P2, P3, P4> Consumer4<P1,P2,P3,P4> 
		toConsumer4(Consumer<Tuple4<P1, P2, P3, P4>> f) {
		return (arg1, arg2, arg3, arg4) -> Funcles.accept(f, arg1, arg2, arg3, arg4);
	}
	
	public static <P1, P2, P3, P4> Consumer4<P1,P2,P3,P4>
		 applyHigherOrder(Function<? super Consumer4<P1,P2,P3,P4>, 
				                   ? extends Consumer<Tuple4<P1,P2,P3,P4>>> hof,
				                Consumer4<P1,P2,P3,P4> f) {
		return toConsumer4(hof.apply(f));
	}
			
}
