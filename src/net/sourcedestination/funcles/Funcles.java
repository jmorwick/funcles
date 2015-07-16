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

package net.sourcedestination.funcles;


import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

import net.sourcedestination.funcles.consumer.Consumer2;
import net.sourcedestination.funcles.consumer.Consumer3;
import net.sourcedestination.funcles.consumer.Consumer4;
import net.sourcedestination.funcles.relation.Relation2;
import net.sourcedestination.funcles.relation.Relation3;
import net.sourcedestination.funcles.tuple.Tuple2;
import net.sourcedestination.funcles.tuple.Tuple3;
import net.sourcedestination.funcles.tuple.Tuple4;
import net.sourcedestination.funcles.tuple.Tuple5;

import static net.sourcedestination.funcles.tuple.Pair.makePair;
import static net.sourcedestination.funcles.tuple.Triple.makeTriple;
import static net.sourcedestination.funcles.tuple.Tuple.makeTuple;


/** A utility class which provides static methods for shrinking common
 * function and consumer operations. Similar methods for tuples and 
 * relations are defined in their respective interfaces. Since the 
 * Java 1.8 Function and Consumer interfaces are used with this library,
 * this utility class was necessary to add. 
 * 
 * @author Joseph Kendall-Morwick <jbmorwick@gmail.com>
 * @version 2.0
 *
 */
public class Funcles {

	/** a simple way to call accept for a consumer with 2 arguments 
	 * 
	 * @param f
	 * @param arg1
	 * @param arg2
	 * @return
	 */
	public static <F1,F2> void accept(Consumer<Tuple2<F1,F2>> c, F1 arg1, 
			F2 arg2) {
		c.accept(makeTuple(arg1, arg2));
	}


	/** a simple way to call accept for a consumer with 3 arguments 
	 * 
	 * @param f
	 * @param arg1
	 * @param arg2
	 * @return
	 */
	public static <F1,F2,F3> void accept(Consumer<Tuple3<F1,F2,F3>> c, F1 arg1, 
			F2 arg2, F3 arg3) {
		c.accept(makeTuple(arg1, arg2, arg3));
	}

	/** a simple way to call accept for a consumer with 3 arguments 
	 * 
	 * @param f
	 * @param arg1
	 * @param arg2
	 * @return
	 */
	public static <F1,F2,F3,F4> void accept(Consumer<Tuple4<F1,F2,F3,F4>> c, 
			F1 arg1, F2 arg2, F3 arg3, F4 arg4) {
		c.accept(makeTuple(arg1, arg2, arg3, arg4));
	}


	/** a simple way to call accept for a consumer with 3 arguments 
	 * 
	 * @param f
	 * @param arg1
	 * @param arg2
	 * @return
	 */
	public static <F1,F2,F3,F4,F5> void accept(Consumer<Tuple5<F1,F2,F3,F4,F5>> c, 
			F1 arg1, F2 arg2, F3 arg3, F4 arg4, F5 arg5) {
		c.accept(makeTuple(arg1, arg2, arg3, arg4, arg5));
	}
	

	/** 
	 * 
	 * @param s
	 * @param f
	 * @return
	 */
	public static <F1,F2> void forEach(
			Stream<Tuple2<F1,F2>> s, 
			Consumer2<F1,F2> c) {
		s.forEach(t -> c.accept(t));
	}
	/** 
	 * 
	 * @param s
	 * @param f
	 * @return
	 */
	public static <F1,F2> void forEach(
			Collection<Tuple2<F1,F2>> s, 
			Consumer2<F1,F2> c) {
		forEach(s.stream(), c);
	}

	/** 
	 * 
	 * @param s
	 * @param f
	 * @return
	 */
	public static <F1,F2,F3> void forEach(
			Stream<Tuple3<F1,F2,F3>> s, 
			Consumer3<F1,F2,F3> c) {
		s.forEach(t -> c.accept(t));
	}
	/** 
	 * 
	 * @param s
	 * @param f
	 * @return
	 */
	public static <F1,F2,F3> void forEach(
			Collection<Tuple3<F1,F2,F3>> s, 
			Consumer3<F1,F2,F3> c) {
		forEach(s.stream(), c);
	}
	

	/** 
	 * 
	 * @param s
	 * @param f
	 * @return
	 */
	public static <F1,F2,F3,F4> void forEach(
			Stream<Tuple4<F1,F2,F3,F4>> s, 
			Consumer4<F1,F2,F3,F4> c) {
		s.forEach(t -> c.accept(t));
	}
	
	/** 
	 * 
	 * @param s
	 * @param f
	 * @return
	 */
	public static <F1,F2,F3,F4> void forEach(
			Collection<Tuple4<F1,F2,F3,F4>> s, 
			Consumer4<F1,F2,F3,F4> c) {
		forEach(s.stream(), c);
	}
	
	/** a simple way to call apply for a function with 2 arguments 
	 * 
	 * @param f
	 * @param arg1
	 * @param arg2
	 * @return
	 */
	public static <F1,F2,T> T apply(Function<Tuple2<F1,F2>,T> f, F1 arg1, 
			F2 arg2) {
		return f.apply(makeTuple(arg1, arg2));
	}
	
	/** a simple way to call apply for a function with 3 arguments 
	 * 
	 * @param f
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @return
	 */
	public static <F1,F2,F3,T> T apply(Function<Tuple3<F1,F2,F3>,T> f, F1 arg1, 
			F2 arg2, F3 arg3) {
		return f.apply(makeTuple(arg1, arg2, arg3));
	}

	/** a simple way to call apply for a function with 4 arguments 
	 * 
	 * @param f
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @param arg4
	 * @return
	 */
	public static <F1,F2,F3,F4,T> T apply(Function<Tuple4<F1,F2,F3,F4>,T> f, 
			F1 arg1, F2 arg2, F3 arg3, F4 arg4) {
		return f.apply(makeTuple(arg1, arg2, arg3, arg4));
	}

	/** a simple way to call apply for a function with 5 arguments
	 * 
	 * @param f
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @param arg4
	 * @return
	 */
	public static <F1,F2,F3,F4,F5,T> T apply(Function<Tuple5<F1,F2,F3,F4,F5>,T> f, 
			F1 arg1, F2 arg2, F3 arg3, F4 arg4, F5 arg5) {
		return f.apply(makeTuple(arg1, arg2, arg3, arg4, arg5));
	}
	
	/** a simple way to call apply for a relation with 2 arguments
	 * 
	 * @param f
	 * @param arg1
	 * @param arg2
	 * @return
	 */
	public static <F,T> boolean apply(Relation2<F> r, F arg1, F arg2) {
		return r.test(makePair(arg1, arg2));
	}
	
	/** a simple way to call apply for a relation with 3 arguments
	 * 
	 * @param f
	 * @param arg1
	 * @param arg2
	 * @return
	 */
	public static <F,T> boolean apply(Relation3<F> r, F arg1, F arg2, 
			F arg3) {
		return r.apply(makeTriple(arg1, arg2, arg3));
	}
   
}
