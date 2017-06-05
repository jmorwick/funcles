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

package net.sourcedestination.funcles.predicate;

	

import java.util.function.Predicate;
import net.sourcedestination.funcles.function.Function5;
import net.sourcedestination.funcles.tuple.Tuple5;

/** This class provides a clean abstraction for implementing binary relations
 *
 * @author Joseph Kendall-Morwick <jmorwick@indiana.edu>
 * @version 2.0
 */
@FunctionalInterface
public interface Predicate5<A1, A2, A3, A4, A5> extends Predicate<Tuple5<A1, A2, A3, A4, A5>>,
											   Function5<A1, A2, A3, A4, A5,Boolean> {
	
	default Boolean apply(A1 arg1, A2 arg2, A3 arg3, A4 arg4, A5 arg5) {
		return test(arg1, arg2, arg3, arg4, arg5);
	}

	default boolean test(Tuple5<A1, A2, A3, A4, A5> args) {
		return test(args._1, args._2, args._3, args._4, args._5);
	}

	default Boolean apply(Tuple5<A1, A2, A3, A4, A5> args) {
		return test(args._1, args._2, args._3, args._4, args._5);
	}

	boolean test(A1 arg1, A2 arg2, A3 arg3, A4 arg4, A5 arg5);
	
}
