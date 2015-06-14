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
import net.sourcedestination.funcles.tuple.Tuple2;

/** 
 *
 * @author Joseph Kendall-Morwick <jmorwick@indiana.edu>
 * @version 2.0
 */
@FunctionalInterface
public abstract interface Consumer2<P1,P2> extends Consumer<Tuple2<P1,P2>> {
	
	public default void accept(Tuple2<P1,P2> args) {
		accept(args.a1(), args.a2());
	}
	
	public void accept(P1 a1,P2 a2);
	
	public default Consumer2<P1,P2> applyHigherOrderTo(Function<? super Consumer2<P1,P2>, 
				                                                ? extends Consumer<Tuple2<P1,P2>>> hof) {
		return toConsumer2(hof.apply(this));
	}
	
	public static <P1, P2> Consumer2<P1,P2> 
		toConsumer2(Consumer<Tuple2<P1, P2>> f) {
		return (arg1, arg2) -> Funcles.accept(f, arg1, arg2);
	}
	
	public static <P1, P2> Consumer2<P1,P2>
		 applyHigherOrder(Function<? super Consumer2<P1,P2>, 
				                   ? extends Consumer<Tuple2<P1,P2>>> hof,
				                Consumer2<P1,P2> f) {
		return toConsumer2(hof.apply(f));
	}
			
}
