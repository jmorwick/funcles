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
import net.sourcedestination.funcles.tuple.Tuple3;

/** 
 *
 * @author Joseph Kendall-Morwick <jmorwick@indiana.edu>
 * @version 2.0
 */
@FunctionalInterface
public abstract interface Consumer3<A1, A2, A3> extends Consumer<Tuple3<A1, A2, A3>> {
	
	public default void accept(Tuple3<A1, A2, A3> args) {
		accept(args._1, args._2, args._3);
	}
	
	public void accept(A1 arg1, A2 arg2, A3 arg3);
	
	public default Consumer3<A1, A2, A3> applyHigherOrderTo(Function< ? super Consumer3<A1, A2, A3>, 
				                                                ? extends Consumer<Tuple3<A1, A2, A3>>> hof) {
		return toConsumer3(hof.apply(this));
	}
	
	public static <A1, A2, A3> Consumer3<A1, A2, A3> 
		toConsumer3(Consumer<Tuple3<A1, A2, A3>> f) {
		return (arg1, arg2, arg3) -> Funcles.accept(f, arg1, arg2, arg3);
	}
	
	public static <A1, A2, A3> Consumer3<A1, A2, A3>
		 applyHigherOrder(Function< ? super Consumer3<A1, A2, A3>, 
				                   ? extends Consumer<Tuple3<A1, A2, A3>>> hof,
				                Consumer3<A1, A2, A3> f) {
		return toConsumer3(hof.apply(f));
	}
			
}
