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

package net.sourcedestination.funcles.tuple;

import java.io.Serializable;

/** Abstract class for all tuples

  @author Joseph Kendall-Morwick <jbmorwick@gmail.com>
  @version 2.0
 */
public abstract class Tuple<T> implements Serializable, Comparable<T> {
	private static final long serialVersionUID = 1L;
    private int size;

    /** constructor for abstract tuple class which tracks tuple properties
     *
     * @param size number of elements in this tuple
     */
    public Tuple(int size) {
        this.size = size;
    }

    /** the number of elements in this tuple
     *
     * @return the number of elements in this tuple
     */
    public int size() { return size; }

    
    
    public static <A1, A2> Tuple2<A1, A2> makeTuple(A1 _1, A2 _2) {
        return new Tuple2<>(_1, _2);
    }
    
    public static <A1, A2, A3> Tuple3<A1, A2, A3> makeTuple(A1 _1, A2 _2, A3 _3) {
        return new Tuple3<>(_1, _2, _3);
    }
    
    public static <A1, A2, A3, A4> Tuple4<A1, A2, A3, A4> makeTuple(A1 _1, A2 _2, A3 _3, A4 _4) {
        return new Tuple4<>(_1, _2, _3, _4);
    }
    
    public static <A1, A2, A3, A4, A5> Tuple5<A1, A2, A3, A4, A5> makeTuple(A1 _1, A2 _2, A3 _3, A4 _4, A5 _5) {
        return new Tuple5<>(_1, _2, _3, _4, _5);
    }
    
    public static <A1, A2, A3, A4, A5, A6> Tuple6<A1, A2, A3, A4, A5, A6> makeTuple(A1 _1, A2 _2, A3 _3, A4 _4, A5 _5, A6 _6) {
        return new Tuple6<>(_1, _2, _3, _4, _5, _6);
    }
    
    public static <A1, A2, A3, A4, A5, A6, A7> Tuple7<A1, A2, A3, A4, A5, A6, A7> makeTuple(A1 _1, A2 _2, A3 _3, A4 _4, A5 _5, A6 _6, A7 _7) {
        return new Tuple7<>(_1, _2, _3, _4, _5, _6, _7);
    }
    
    public static <A1, A2, A3, A4, A5, A6, A7, A8> Tuple8<A1, A2, A3, A4, A5, A6, A7, A8> makeTuple(A1 _1, A2 _2, A3 _3, A4 _4, A5 _5, A6 _6, A7 _7, A8 _8) {
        return new Tuple8<>(_1, _2, _3, _4, _5, _6, _7, _8);
    }
    
    public static <A1, A2, A3, A4, A5, A6, A7, A8, A9> Tuple9<A1, A2, A3, A4, A5, A6, A7, A8, A9> makeTuple(A1 _1, A2 _2, A3 _3, A4 _4, A5 _5, A6 _6, A7 _7, A8 _8, A9 _9) {
        return new Tuple9<>(_1, _2, _3, _4, _5, _6, _7, _8, _9);
    }
    
    public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> Tuple10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> makeTuple(A1 _1, A2 _2, A3 _3, A4 _4, A5 _5, A6 _6, A7 _7, A8 _8, A9 _9, A10 _10) {
        return new Tuple10<>(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10);
    }
    
    public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11> Tuple11<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11> makeTuple(A1 _1, A2 _2, A3 _3, A4 _4, A5 _5, A6 _6, A7 _7, A8 _8, A9 _9, A10 _10, A11 _11) {
        return new Tuple11<>(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11);
    }
    
    public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12> Tuple12<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12> makeTuple(A1 _1, A2 _2, A3 _3, A4 _4, A5 _5, A6 _6, A7 _7, A8 _8, A9 _9, A10 _10, A11 _11, A12 _12) {
        return new Tuple12<>(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12);
    }
    
}
