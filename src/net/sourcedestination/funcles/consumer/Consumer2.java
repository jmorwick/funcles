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
import java.util.function.BiConsumer;
import java.util.function.Function;

import net.sourcedestination.funcles.tuple.Tuple2;

import static net.sourcedestination.funcles.tuple.Tuple.makeTuple;

/** 
 *
 * @author Joseph Kendall-Morwick &lt;jmorwick@indiana.edu&gt;
 * @version 2.0
 */
@FunctionalInterface
public interface Consumer2<A1, A2> extends Consumer<Tuple2<A1, A2>>,
														BiConsumer<A1,A2>  {
	
	default void accept(Tuple2<A1, A2> args) {
		accept(args._1, args._2);
	}
	
	void accept(A1 arg1, A2 arg2);
	
	default Consumer2<A1, A2> applyHigherOrderTo(Function< ? super Consumer2<A1, A2>,
				                                                ? extends Consumer<Tuple2<A1, A2>>> hof) {
		return toConsumer2(hof.apply(this));
	}
	
	static <A1, A2> Consumer2<A1, A2>
		toConsumer2(Consumer<Tuple2<A1, A2>> f) {
		return (arg1, arg2) -> f.accept(makeTuple(arg1, arg2));
	}
	
	static <A1, A2> Consumer2<A1, A2>
		 applyHigherOrder(Function< ? super Consumer2<A1, A2>, 
				                   ? extends Consumer<Tuple2<A1, A2>>> hof,
				                Consumer2<A1, A2> f) {
		return toConsumer2(hof.apply(f));
	}
	
	default <V> Consumer2<A1, A2> andThen(Consumer2< ? super A1, ? super A2> after) {
		return (x, y) -> {
		    accept(x, y);
			after.accept(x, y);
		};
		
	}

}
