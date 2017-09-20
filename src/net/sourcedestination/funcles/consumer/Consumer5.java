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

import net.sourcedestination.funcles.tuple.Tuple5;

import static net.sourcedestination.funcles.tuple.Tuple.makeTuple;

/** 
 *
 * @author Joseph Kendall-Morwick &lt;jmorwick@indiana.edu&gt;
 * @version 2.0
 */
@FunctionalInterface
public interface Consumer5<A1, A2, A3, A4, A5> extends Consumer<Tuple5<A1, A2, A3, A4, A5>> {
	
	default void accept(Tuple5<A1, A2, A3, A4, A5> args) {
		accept(args._1, args._2, args._3, args._4, args._5);
	}
	
	void accept(A1 arg1, A2 arg2, A3 arg3, A4 arg4, A5 arg5);
	
	default Consumer5<A1, A2, A3, A4, A5> applyHigherOrderTo(Function< ? super Consumer5<A1, A2, A3, A4, A5>,
				                                                ? extends Consumer<Tuple5<A1, A2, A3, A4, A5>>> hof) {
		return toConsumer5(hof.apply(this));
	}
	
	static <A1, A2, A3, A4, A5> Consumer5<A1, A2, A3, A4, A5>
		toConsumer5(Consumer<Tuple5<A1, A2, A3, A4, A5>> f) {
		return (arg1, arg2, arg3, arg4, arg5) -> f.accept(makeTuple(arg1, arg2, arg3, arg4, arg5));
	}
	
	static <A1, A2, A3, A4, A5> Consumer5<A1, A2, A3, A4, A5>
		 applyHigherOrder(Function< ? super Consumer5<A1, A2, A3, A4, A5>, 
				                   ? extends Consumer<Tuple5<A1, A2, A3, A4, A5>>> hof,
				                Consumer5<A1, A2, A3, A4, A5> f) {
		return toConsumer5(hof.apply(f));
	}

}
