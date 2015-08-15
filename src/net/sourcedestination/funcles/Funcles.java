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
import net.sourcedestination.funcles.predicate.*;
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
	public static <A1, A2> boolean apply(Predicate2<A1, A2> r,  
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
	public static <A1, A2, A3> boolean apply(Predicate3<A1, A2, A3> r,  
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
	public static <A1, A2, A3, A4> boolean apply(Predicate4<A1, A2, A3, A4> r,  
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
	public static <A1, A2, A3, A4, A5> boolean apply(Predicate5<A1, A2, A3, A4, A5> r,  
	    A1 arg1, A2 arg2, A3 arg3, A4 arg4, A5 arg5) {
		return r.apply(makeTuple(arg1, arg2, arg3, arg4, arg5));
	}

	/** a simple way to call accept for a consumer with 6 arguments 
	 */
	public static <A1, A2, A3, A4, A5, A6> void accept(Consumer<Tuple6<A1, A2, A3, A4, A5, A6>> c, 
		A1 arg1, A2 arg2, A3 arg3, A4 arg4, A5 arg5, A6 arg6) {
		c.accept(makeTuple(arg1, arg2, arg3, arg4, arg5, arg6));
	}
	
	/** a simple way to call apply for a function with 6 arguments 
	 */
	public static <A1, A2, A3, A4, A5, A6, R> R apply(Function<Tuple6<A1, A2, A3, A4, A5, A6>,R> c, 
		A1 arg1, A2 arg2, A3 arg3, A4 arg4, A5 arg5, A6 arg6) {
		return c.apply(makeTuple(arg1, arg2, arg3, arg4, arg5, arg6));
	}
	
	public static <A1, A2, A3, A4, A5, A6> void forEach(
			Stream<Tuple6<A1, A2, A3, A4, A5, A6>> s, 
			Consumer6<A1, A2, A3, A4, A5, A6> c) {
		s.forEach(t -> c.accept(t));
	}
	
	/** a simple way to call apply for a relation with 6 arguments
	 */
	public static <A1, A2, A3, A4, A5, A6> boolean apply(Predicate6<A1, A2, A3, A4, A5, A6> r,  
	    A1 arg1, A2 arg2, A3 arg3, A4 arg4, A5 arg5, A6 arg6) {
		return r.apply(makeTuple(arg1, arg2, arg3, arg4, arg5, arg6));
	}

	/** a simple way to call accept for a consumer with 7 arguments 
	 */
	public static <A1, A2, A3, A4, A5, A6, A7> void accept(Consumer<Tuple7<A1, A2, A3, A4, A5, A6, A7>> c, 
		A1 arg1, A2 arg2, A3 arg3, A4 arg4, A5 arg5, A6 arg6, A7 arg7) {
		c.accept(makeTuple(arg1, arg2, arg3, arg4, arg5, arg6, arg7));
	}
	
	/** a simple way to call apply for a function with 7 arguments 
	 */
	public static <A1, A2, A3, A4, A5, A6, A7, R> R apply(Function<Tuple7<A1, A2, A3, A4, A5, A6, A7>,R> c, 
		A1 arg1, A2 arg2, A3 arg3, A4 arg4, A5 arg5, A6 arg6, A7 arg7) {
		return c.apply(makeTuple(arg1, arg2, arg3, arg4, arg5, arg6, arg7));
	}
	
	public static <A1, A2, A3, A4, A5, A6, A7> void forEach(
			Stream<Tuple7<A1, A2, A3, A4, A5, A6, A7>> s, 
			Consumer7<A1, A2, A3, A4, A5, A6, A7> c) {
		s.forEach(t -> c.accept(t));
	}
	
	/** a simple way to call apply for a relation with 7 arguments
	 */
	public static <A1, A2, A3, A4, A5, A6, A7> boolean apply(Predicate7<A1, A2, A3, A4, A5, A6, A7> r,  
	    A1 arg1, A2 arg2, A3 arg3, A4 arg4, A5 arg5, A6 arg6, A7 arg7) {
		return r.apply(makeTuple(arg1, arg2, arg3, arg4, arg5, arg6, arg7));
	}

	/** a simple way to call accept for a consumer with 8 arguments 
	 */
	public static <A1, A2, A3, A4, A5, A6, A7, A8> void accept(Consumer<Tuple8<A1, A2, A3, A4, A5, A6, A7, A8>> c, 
		A1 arg1, A2 arg2, A3 arg3, A4 arg4, A5 arg5, A6 arg6, A7 arg7, A8 arg8) {
		c.accept(makeTuple(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8));
	}
	
	/** a simple way to call apply for a function with 8 arguments 
	 */
	public static <A1, A2, A3, A4, A5, A6, A7, A8, R> R apply(Function<Tuple8<A1, A2, A3, A4, A5, A6, A7, A8>,R> c, 
		A1 arg1, A2 arg2, A3 arg3, A4 arg4, A5 arg5, A6 arg6, A7 arg7, A8 arg8) {
		return c.apply(makeTuple(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8));
	}
	
	public static <A1, A2, A3, A4, A5, A6, A7, A8> void forEach(
			Stream<Tuple8<A1, A2, A3, A4, A5, A6, A7, A8>> s, 
			Consumer8<A1, A2, A3, A4, A5, A6, A7, A8> c) {
		s.forEach(t -> c.accept(t));
	}
	
	/** a simple way to call apply for a relation with 8 arguments
	 */
	public static <A1, A2, A3, A4, A5, A6, A7, A8> boolean apply(Predicate8<A1, A2, A3, A4, A5, A6, A7, A8> r,  
	    A1 arg1, A2 arg2, A3 arg3, A4 arg4, A5 arg5, A6 arg6, A7 arg7, A8 arg8) {
		return r.apply(makeTuple(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8));
	}

	/** a simple way to call accept for a consumer with 9 arguments 
	 */
	public static <A1, A2, A3, A4, A5, A6, A7, A8, A9> void accept(Consumer<Tuple9<A1, A2, A3, A4, A5, A6, A7, A8, A9>> c, 
		A1 arg1, A2 arg2, A3 arg3, A4 arg4, A5 arg5, A6 arg6, A7 arg7, A8 arg8, A9 arg9) {
		c.accept(makeTuple(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9));
	}
	
	/** a simple way to call apply for a function with 9 arguments 
	 */
	public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, R> R apply(Function<Tuple9<A1, A2, A3, A4, A5, A6, A7, A8, A9>,R> c, 
		A1 arg1, A2 arg2, A3 arg3, A4 arg4, A5 arg5, A6 arg6, A7 arg7, A8 arg8, A9 arg9) {
		return c.apply(makeTuple(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9));
	}
	
	public static <A1, A2, A3, A4, A5, A6, A7, A8, A9> void forEach(
			Stream<Tuple9<A1, A2, A3, A4, A5, A6, A7, A8, A9>> s, 
			Consumer9<A1, A2, A3, A4, A5, A6, A7, A8, A9> c) {
		s.forEach(t -> c.accept(t));
	}
	
	/** a simple way to call apply for a relation with 9 arguments
	 */
	public static <A1, A2, A3, A4, A5, A6, A7, A8, A9> boolean apply(Predicate9<A1, A2, A3, A4, A5, A6, A7, A8, A9> r,  
	    A1 arg1, A2 arg2, A3 arg3, A4 arg4, A5 arg5, A6 arg6, A7 arg7, A8 arg8, A9 arg9) {
		return r.apply(makeTuple(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9));
	}

	/** a simple way to call accept for a consumer with 10 arguments 
	 */
	public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> void accept(Consumer<Tuple10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10>> c, 
		A1 arg1, A2 arg2, A3 arg3, A4 arg4, A5 arg5, A6 arg6, A7 arg7, A8 arg8, A9 arg9, A10 arg10) {
		c.accept(makeTuple(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10));
	}
	
	/** a simple way to call apply for a function with 10 arguments 
	 */
	public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, R> R apply(Function<Tuple10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10>,R> c, 
		A1 arg1, A2 arg2, A3 arg3, A4 arg4, A5 arg5, A6 arg6, A7 arg7, A8 arg8, A9 arg9, A10 arg10) {
		return c.apply(makeTuple(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10));
	}
	
	public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> void forEach(
			Stream<Tuple10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10>> s, 
			Consumer10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> c) {
		s.forEach(t -> c.accept(t));
	}
	
	/** a simple way to call apply for a relation with 10 arguments
	 */
	public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> boolean apply(Predicate10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> r,  
	    A1 arg1, A2 arg2, A3 arg3, A4 arg4, A5 arg5, A6 arg6, A7 arg7, A8 arg8, A9 arg9, A10 arg10) {
		return r.apply(makeTuple(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10));
	}

	/** a simple way to call accept for a consumer with 11 arguments 
	 */
	public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11> void accept(Consumer<Tuple11<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11>> c, 
		A1 arg1, A2 arg2, A3 arg3, A4 arg4, A5 arg5, A6 arg6, A7 arg7, A8 arg8, A9 arg9, A10 arg10, A11 arg11) {
		c.accept(makeTuple(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11));
	}
	
	/** a simple way to call apply for a function with 11 arguments 
	 */
	public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, R> R apply(Function<Tuple11<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11>,R> c, 
		A1 arg1, A2 arg2, A3 arg3, A4 arg4, A5 arg5, A6 arg6, A7 arg7, A8 arg8, A9 arg9, A10 arg10, A11 arg11) {
		return c.apply(makeTuple(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11));
	}
	
	public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11> void forEach(
			Stream<Tuple11<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11>> s, 
			Consumer11<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11> c) {
		s.forEach(t -> c.accept(t));
	}
	
	/** a simple way to call apply for a relation with 11 arguments
	 */
	public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11> boolean apply(Predicate11<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11> r,  
	    A1 arg1, A2 arg2, A3 arg3, A4 arg4, A5 arg5, A6 arg6, A7 arg7, A8 arg8, A9 arg9, A10 arg10, A11 arg11) {
		return r.apply(makeTuple(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11));
	}

	/** a simple way to call accept for a consumer with 12 arguments 
	 */
	public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12> void accept(Consumer<Tuple12<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12>> c, 
		A1 arg1, A2 arg2, A3 arg3, A4 arg4, A5 arg5, A6 arg6, A7 arg7, A8 arg8, A9 arg9, A10 arg10, A11 arg11, A12 arg12) {
		c.accept(makeTuple(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12));
	}
	
	/** a simple way to call apply for a function with 12 arguments 
	 */
	public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, R> R apply(Function<Tuple12<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12>,R> c, 
		A1 arg1, A2 arg2, A3 arg3, A4 arg4, A5 arg5, A6 arg6, A7 arg7, A8 arg8, A9 arg9, A10 arg10, A11 arg11, A12 arg12) {
		return c.apply(makeTuple(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12));
	}
	
	public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12> void forEach(
			Stream<Tuple12<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12>> s, 
			Consumer12<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12> c) {
		s.forEach(t -> c.accept(t));
	}
	
	/** a simple way to call apply for a relation with 12 arguments
	 */
	public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12> boolean apply(Predicate12<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12> r,  
	    A1 arg1, A2 arg2, A3 arg3, A4 arg4, A5 arg5, A6 arg6, A7 arg7, A8 arg8, A9 arg9, A10 arg10, A11 arg11, A12 arg12) {
		return r.apply(makeTuple(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12));
	}


	
   
}
