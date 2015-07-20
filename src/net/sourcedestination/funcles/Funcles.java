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

import net.sourcedestination.funcles.consumer.*;
import net.sourcedestination.funcles.relation.*;
import net.sourcedestination.funcles.tuple.*;
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
	 */
	public static <A1, A2> void accept(Consumer<Tuple2<A1, A2>> c, 
		A1 arg1, A2 arg2) {
		c.accept(makeTuple(arg1, arg2));
	}
	
	/** a simple way to call apply for a function with 2 arguments 
	 */
	public static <A1, A2, R> R apply(Function<Tuple2<A1, A2>,R> c, 
		A1 arg1, A2 arg2) {
		return c.apply(makeTuple(arg1, arg2));
	}
	
	public static <A1, A2> void forEach(
			Stream<Tuple2<A1, A2>> s, 
			Consumer2<A1, A2> c) {
		s.forEach(t -> c.accept(t));
	}
	
	/** a simple way to call apply for a relation with 2 arguments
	 */
	public static <A1, A2> boolean apply(Relation2<A1, A2> r,  
	    A1 arg1, A2 arg2) {
		return r.apply(makeTuple(arg1, arg2));
	}

	/** a simple way to call accept for a consumer with 3 arguments 
	 */
	public static <A1, A2, A3> void accept(Consumer<Tuple3<A1, A2, A3>> c, 
		A1 arg1, A2 arg2, A3 arg3) {
		c.accept(makeTuple(arg1, arg2, arg3));
	}
	
	/** a simple way to call apply for a function with 3 arguments 
	 */
	public static <A1, A2, A3, R> R apply(Function<Tuple3<A1, A2, A3>,R> c, 
		A1 arg1, A2 arg2, A3 arg3) {
		return c.apply(makeTuple(arg1, arg2, arg3));
	}
	
	public static <A1, A2, A3> void forEach(
			Stream<Tuple3<A1, A2, A3>> s, 
			Consumer3<A1, A2, A3> c) {
		s.forEach(t -> c.accept(t));
	}
	
	/** a simple way to call apply for a relation with 3 arguments
	 */
	public static <A1, A2, A3> boolean apply(Relation3<A1, A2, A3> r,  
	    A1 arg1, A2 arg2, A3 arg3) {
		return r.apply(makeTuple(arg1, arg2, arg3));
	}

	/** a simple way to call accept for a consumer with 4 arguments 
	 */
	public static <A1, A2, A3, A4> void accept(Consumer<Tuple4<A1, A2, A3, A4>> c, 
		A1 arg1, A2 arg2, A3 arg3, A4 arg4) {
		c.accept(makeTuple(arg1, arg2, arg3, arg4));
	}
	
	/** a simple way to call apply for a function with 4 arguments 
	 */
	public static <A1, A2, A3, A4, R> R apply(Function<Tuple4<A1, A2, A3, A4>,R> c, 
		A1 arg1, A2 arg2, A3 arg3, A4 arg4) {
		return c.apply(makeTuple(arg1, arg2, arg3, arg4));
	}
	
	public static <A1, A2, A3, A4> void forEach(
			Stream<Tuple4<A1, A2, A3, A4>> s, 
			Consumer4<A1, A2, A3, A4> c) {
		s.forEach(t -> c.accept(t));
	}
	
	/** a simple way to call apply for a relation with 4 arguments
	 */
	public static <A1, A2, A3, A4> boolean apply(Relation4<A1, A2, A3, A4> r,  
	    A1 arg1, A2 arg2, A3 arg3, A4 arg4) {
		return r.apply(makeTuple(arg1, arg2, arg3, arg4));
	}

	/** a simple way to call accept for a consumer with 5 arguments 
	 */
	public static <A1, A2, A3, A4, A5> void accept(Consumer<Tuple5<A1, A2, A3, A4, A5>> c, 
		A1 arg1, A2 arg2, A3 arg3, A4 arg4, A5 arg5) {
		c.accept(makeTuple(arg1, arg2, arg3, arg4, arg5));
	}
	
	/** a simple way to call apply for a function with 5 arguments 
	 */
	public static <A1, A2, A3, A4, A5, R> R apply(Function<Tuple5<A1, A2, A3, A4, A5>,R> c, 
		A1 arg1, A2 arg2, A3 arg3, A4 arg4, A5 arg5) {
		return c.apply(makeTuple(arg1, arg2, arg3, arg4, arg5));
	}
	
	public static <A1, A2, A3, A4, A5> void forEach(
			Stream<Tuple5<A1, A2, A3, A4, A5>> s, 
			Consumer5<A1, A2, A3, A4, A5> c) {
		s.forEach(t -> c.accept(t));
	}
	
	/** a simple way to call apply for a relation with 5 arguments
	 */
	public static <A1, A2, A3, A4, A5> boolean apply(Relation5<A1, A2, A3, A4, A5> r,  
	    A1 arg1, A2 arg2, A3 arg3, A4 arg4, A5 arg5) {
		return r.apply(makeTuple(arg1, arg2, arg3, arg4, arg5));
	}


	
   
}
