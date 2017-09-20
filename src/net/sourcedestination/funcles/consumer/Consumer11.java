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

import net.sourcedestination.funcles.tuple.Tuple11;

import static net.sourcedestination.funcles.tuple.Tuple.makeTuple;

/** 
 *
 * @author Joseph Kendall-Morwick &lt;jmorwick@indiana.edu&gt;
 * @version 2.0
 */
@FunctionalInterface
public interface Consumer11<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11> extends Consumer<Tuple11<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11>> {
	
	default void accept(Tuple11<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11> args) {
		accept(args._1, args._2, args._3, args._4, args._5, args._6, args._7, args._8, args._9, args._10, args._11);
	}
	
	void accept(A1 arg1, A2 arg2, A3 arg3, A4 arg4, A5 arg5, A6 arg6, A7 arg7, A8 arg8, A9 arg9, A10 arg10, A11 arg11);
	
	default Consumer11<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11> applyHigherOrderTo(Function< ? super Consumer11<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11>,
				                                                ? extends Consumer<Tuple11<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11>>> hof) {
		return toConsumer11(hof.apply(this));
	}
	
	static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11> Consumer11<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11>
		toConsumer11(Consumer<Tuple11<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11>> f) {
		return (arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11) -> f.accept(makeTuple(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11));
	}
	
	static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11> Consumer11<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11>
		 applyHigherOrder(Function< ? super Consumer11<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11>, 
				                   ? extends Consumer<Tuple11<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11>>> hof,
				                Consumer11<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11> f) {
		return toConsumer11(hof.apply(f));
	}

}
