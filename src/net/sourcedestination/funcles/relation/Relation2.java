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

import java.util.function.BiPredicate;	

import net.sourcedestination.funcles.function.Function2;
import net.sourcedestination.funcles.tuple.Tuple2;

/** This class provides a clean abstraction for implementing binary relations
 *
 * @author Joseph Kendall-Morwick <jmorwick@indiana.edu>
 * @version 2.0
 */
@FunctionalInterface
public abstract interface Relation2<A1, A2> extends Relation<Tuple2<A1, A2>>, 
											   BiPredicate<A1, A2>,
											   Function2<A1, A2,Boolean> {
	
	public default Boolean apply(A1 arg1, A2 arg2) {
		return test(arg1, arg2);
	}

	public default boolean test(Tuple2<A1, A2> args) {
		return test(args._1, args._2);
	}

	public default Boolean apply(Tuple2<A1, A2> args) {
		return test(args._1, args._2);
	}

	
	/** returns the negation of this Relation2 as a Relation2 
	* @return returns the negation of this Relation2 as a Relation2 
	*/
	public default Relation2<A1, A2> negate() {
		return (x,y) -> !test(x,y);
	}
	public boolean test(A1 arg1, A2 arg2);
	
}
