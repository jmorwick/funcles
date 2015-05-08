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

package info.kendall_morwick.relation;

import info.kendall_morwick.funcles.tuple.Triple;
import info.kendall_morwick.function.Function3;

/** This class provides a clean abstraction for implementing ternary relations
 *
 * @author Joseph Kendall-Morwick <jmorwick@indiana.edu>
 * @version 2.1
 */
@FunctionalInterface
public abstract interface Relation3<T> extends Relation<Triple<T>>,
											   Function3<T,T,T,Boolean> {
	
	public default boolean test(Triple<T> args) {
		return test(args.a1(), args.a2(), args.a3());
	}
	
	public default Boolean apply(T arg1, T arg2, T arg3) {
		return test(arg1, arg2, arg3);
	}

	public boolean test(T a1, T a2, T a3);
	
}
