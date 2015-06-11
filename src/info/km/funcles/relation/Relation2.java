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

package info.km.funcles.relation;

import info.km.funcles.function.Function2;
import info.km.funcles.tuple.Pair;
import info.km.funcles.tuple.Tuple2;

import java.util.function.BiPredicate;

/** This class provides a clean abstraction for implementing binary relations
 *
 * @author Joseph Kendall-Morwick <jmorwick@indiana.edu>
 * @version 2.1
 */
@FunctionalInterface
public abstract interface Relation2<T> extends Relation<Pair<T>>, 
// TODO: determine if Pair<T> above should be Tuple2<T,T> instead
											   Function2<T,T,Boolean> {
	
	public default Boolean apply(T arg1, T arg2) {
		return test(arg1, arg2);
	}

	public default boolean test(Pair<T> args) {
		return test(args.a1(), args.a2());
	}

	public default Boolean apply(Tuple2<T, T> args) {
		return test(args.a1(), args.a2());
	}
	
	/** Converts this relation in to a java.util.function.BiPredicate.
	 * 
	 * This method is necessary due to a conflict with the negate method: 
	 * Relation2 cannot extend both java.util.Function.Predicate and 
	 * java.util.function.BiPredicate due to this conflict. 
	 * 
	 * @return this predicate represented as a BiPredicate
	 */
	public default BiPredicate<T,T> toBiPredicate() {
		return this::test;
	}

	public boolean test(T a1,T a2);
	
}
