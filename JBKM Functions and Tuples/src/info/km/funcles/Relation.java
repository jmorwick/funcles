/* Copyright 2011-2013 Joseph Kendall-Morwick

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

package info.km.funcles;

import info.km.funcles.tuples.T2;

/** This class provides a clean abstraction for implementing binary relations
 *
 * @author jmorwick
 * @version 0.1
 */
public abstract class Relation<T> extends Function<Boolean,T2<T,T>> {

    /** defers to 'relates' */
    @Override
    public Boolean implementation(T2<T,T> args) {
        return relates(args.a1(), args.a2());
    }

    /** Relations must implement this method, which determines if two objects
     * are related.
     * @param arg1
     * @param arg2
     * @return
     */
    public abstract boolean relates(T arg1, T arg2);

}
