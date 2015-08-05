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

package net.sourcedestination.funcles.relation;

	

import net.sourcedestination.funcles.function.Function4;
import net.sourcedestination.funcles.tuple.Tuple4;

/** This class provides a clean abstraction for implementing binary relations
 *
 * @author Joseph Kendall-Morwick <jmorwick@indiana.edu>
 * @version 2.0
 */
@FunctionalInterface
public abstract interface Relation4<A1, A2, A3, A4> extends Relation<Tuple4<A1, A2, A3, A4>>, 
											   Function4<A1, A2, A3, A4,Boolean> {
	
	public default Boolean apply(A1 arg1, A2 arg2, A3 arg3, A4 arg4) {
		return test(arg1, arg2, arg3, arg4);
	}

	public default boolean test(Tuple4<A1, A2, A3, A4> args) {
		return test(args._1, args._2, args._3, args._4);
	}

	public default Boolean apply(Tuple4<A1, A2, A3, A4> args) {
		return test(args._1, args._2, args._3, args._4);
	}

	public boolean test(A1 arg1, A2 arg2, A3 arg3, A4 arg4);
	
}
