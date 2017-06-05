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

package net.sourcedestination.funcles.consumer;

import java.util.function.Consumer;
import java.util.function.Function;

import net.sourcedestination.funcles.tuple.Tuple6;

import static net.sourcedestination.funcles.tuple.Tuple.makeTuple;

/** 
 *
 * @author Joseph Kendall-Morwick <jmorwick@indiana.edu>
 * @version 2.0
 */
@FunctionalInterface
public interface Consumer6<A1, A2, A3, A4, A5, A6> extends Consumer<Tuple6<A1, A2, A3, A4, A5, A6>> {
	
	default void accept(Tuple6<A1, A2, A3, A4, A5, A6> args) {
		accept(args._1, args._2, args._3, args._4, args._5, args._6);
	}
	
	void accept(A1 arg1, A2 arg2, A3 arg3, A4 arg4, A5 arg5, A6 arg6);
	
	default Consumer6<A1, A2, A3, A4, A5, A6> applyHigherOrderTo(Function< ? super Consumer6<A1, A2, A3, A4, A5, A6>,
				                                                ? extends Consumer<Tuple6<A1, A2, A3, A4, A5, A6>>> hof) {
		return toConsumer6(hof.apply(this));
	}
	
	static <A1, A2, A3, A4, A5, A6> Consumer6<A1, A2, A3, A4, A5, A6>
		toConsumer6(Consumer<Tuple6<A1, A2, A3, A4, A5, A6>> f) {
		return (arg1, arg2, arg3, arg4, arg5, arg6) -> f.accept(makeTuple(arg1, arg2, arg3, arg4, arg5, arg6));
	}
	
	static <A1, A2, A3, A4, A5, A6> Consumer6<A1, A2, A3, A4, A5, A6>
		 applyHigherOrder(Function< ? super Consumer6<A1, A2, A3, A4, A5, A6>, 
				                   ? extends Consumer<Tuple6<A1, A2, A3, A4, A5, A6>>> hof,
				                Consumer6<A1, A2, A3, A4, A5, A6> f) {
		return toConsumer6(hof.apply(f));
	}

}
