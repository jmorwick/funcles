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

package info.kendall_morwick.funcles;

public class Pair<T> extends T2<T,T> {

	public Pair(T a1, T a2, boolean mutable) {
		super(a1, a2, mutable);
	}

	/** creates a new mutable pair with the given arguments
	 */
	public static <T> Pair<T> makePair(T a1, T a2) {
		return new Pair<T>(a1, a2, true);
	}
	
	/** creates a new immutable pair with the given arguments
	 */
	public static <T> Pair<T> makeImmutablePair(T a1, T a2) {
		return new Pair<T>(a1, a2, false);
	}

}
