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

package net.sourcedestination.funcles;

import java.util.stream.Stream;

import net.sourcedestination.funcles.consumer.*;
import net.sourcedestination.funcles.tuple.*;


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

	
	public static <A1, A2> void forEach(
			Stream<Tuple2<A1, A2>> s, 
			Consumer2<A1, A2> c) {
		s.forEach(t -> c.accept(t));
	}

	
	public static <A1, A2, A3> void forEach(
			Stream<Tuple3<A1, A2, A3>> s, 
			Consumer3<A1, A2, A3> c) {
		s.forEach(t -> c.accept(t));
	}

	
	public static <A1, A2, A3, A4> void forEach(
			Stream<Tuple4<A1, A2, A3, A4>> s, 
			Consumer4<A1, A2, A3, A4> c) {
		s.forEach(t -> c.accept(t));
	}

	
	public static <A1, A2, A3, A4, A5> void forEach(
			Stream<Tuple5<A1, A2, A3, A4, A5>> s, 
			Consumer5<A1, A2, A3, A4, A5> c) {
		s.forEach(t -> c.accept(t));
	}

	
	public static <A1, A2, A3, A4, A5, A6> void forEach(
			Stream<Tuple6<A1, A2, A3, A4, A5, A6>> s, 
			Consumer6<A1, A2, A3, A4, A5, A6> c) {
		s.forEach(t -> c.accept(t));
	}

	
	public static <A1, A2, A3, A4, A5, A6, A7> void forEach(
			Stream<Tuple7<A1, A2, A3, A4, A5, A6, A7>> s, 
			Consumer7<A1, A2, A3, A4, A5, A6, A7> c) {
		s.forEach(t -> c.accept(t));
	}

	
	public static <A1, A2, A3, A4, A5, A6, A7, A8> void forEach(
			Stream<Tuple8<A1, A2, A3, A4, A5, A6, A7, A8>> s, 
			Consumer8<A1, A2, A3, A4, A5, A6, A7, A8> c) {
		s.forEach(t -> c.accept(t));
	}

	
	public static <A1, A2, A3, A4, A5, A6, A7, A8, A9> void forEach(
			Stream<Tuple9<A1, A2, A3, A4, A5, A6, A7, A8, A9>> s, 
			Consumer9<A1, A2, A3, A4, A5, A6, A7, A8, A9> c) {
		s.forEach(t -> c.accept(t));
	}

	
	public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> void forEach(
			Stream<Tuple10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10>> s, 
			Consumer10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> c) {
		s.forEach(t -> c.accept(t));
	}

	
	public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11> void forEach(
			Stream<Tuple11<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11>> s, 
			Consumer11<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11> c) {
		s.forEach(t -> c.accept(t));
	}

	
	public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12> void forEach(
			Stream<Tuple12<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12>> s, 
			Consumer12<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12> c) {
		s.forEach(t -> c.accept(t));
	}


	
   
}
