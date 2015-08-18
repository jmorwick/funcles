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

import static net.sourcedestination.funcles.tuple.Tuple.makeTuple;

/** 
 *
 * @author Joseph Kendall-Morwick <jmorwick@indiana.edu>
 * @version 2.0
 */
@FunctionalInterface
public abstract interface Consumer4<A1, A2, A3, A4> extends Consumer<Tuple4<A1, A2, A3, A4>> {
	
	public default void accept(Tuple4<A1, A2, A3, A4> args) {
		accept(args._1, args._2, args._3, args._4);
	}
	
	public void accept(A1 arg1, A2 arg2, A3 arg3, A4 arg4);
	
	public default Consumer4<A1, A2, A3, A4> applyHigherOrderTo(Function< ? super Consumer4<A1, A2, A3, A4>, 
				                                                ? extends Consumer<Tuple4<A1, A2, A3, A4>>> hof) {
		return toConsumer4(hof.apply(this));
	}
	
	public static <A1, A2, A3, A4> Consumer4<A1, A2, A3, A4> 
		toConsumer4(Consumer<Tuple4<A1, A2, A3, A4>> f) {
		return (arg1, arg2, arg3, arg4) -> f.accept(makeTuple(arg1, arg2, arg3, arg4));
	}
	
	public static <A1, A2, A3, A4> Consumer4<A1, A2, A3, A4>
		 applyHigherOrder(Function< ? super Consumer4<A1, A2, A3, A4>, 
				                   ? extends Consumer<Tuple4<A1, A2, A3, A4>>> hof,
				                Consumer4<A1, A2, A3, A4> f) {
		return toConsumer4(hof.apply(f));
	}

}
