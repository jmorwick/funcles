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

package info.kendall_morwick.funcles.tuple;

//TODO: add a copy-on-modify method to modify tuples. 

import java.io.Serializable;

/** Abstract class for all tuples

  @author Joseph Kendall-Morwick <jmorwick@indiana.edu>
  @version 2.1
 */
public class Tuple implements Serializable {
    private boolean mutable; 
    private int size;

    /** constructor for abstract tuple class which tracks tuple properties
     *
     * @param mutable whether or not this tuple can be altered
     * @param size number of elements in this tuple
     */
    public Tuple(boolean mutable, int size) {
        this.size = size;
        this.mutable = mutable;
    }

    /** the number of elements in this tuple
     *
     * @return the number of elements in this tuple
     */
    public int size() { return size; }

    /** whether or not this tuple can be altered
     *
     * @return true iff this tuple can be altered
     */
    public boolean isMutable() { return mutable; }

    /** creates a new tuple with 2 elements.
     * By default, tuples are immutable
     * 
     * @param <A>
     * @param <B>
     * @param a
     * @param b
     * @return
     */
    public static <A,B> Tuple2<A,B> makeTuple(A a, B b) {
        return new Tuple2<A,B>(a,b,false);
    }
    /** creates a new tuple with 3 elements
     * By default, tuples are immutable
     *
     * @param <A>
     * @param <B>
     * @param a
     * @param b
     * @return
     */
    public static <A,B,C> Tuple3<A,B,C> makeTuple(A a, B b, C c) {
        return new Tuple3<A,B,C>(a,b,c,false);
    }
    /** creates a new tuple with 4 elements
     * By default, tuples are immutable
     *
     * @param <A>
     * @param <B>
     * @param a
     * @param b
     * @return
     */
    public static <A,B,C,D> Tuple4<A,B,C,D> makeTuple(A a, B b, C c, D d) {
        return new Tuple4<A,B,C,D>(a,b,c,d,false);
    }
    /** creates a new tuple with 5 elements
     * By default, tuples are immutable
     *
     * @param <A>
     * @param <B>
     * @param a
     * @param b
     * @return
     */
    public static <A,B,C,D,E> Tuple5<A,B,C,D,E> makeTuple(A a, B b, C c, D d, E e) {
        return new Tuple5<A,B,C,D,E>(a,b,c,d,e,false);
    }

    /** creates a new tuple with 2 elements which cannot be altered
     *
     * @param <A>
     * @param <B>
     * @param a
     * @param b
     * @return
     */
    public static <A,B> Tuple2<A,B> makeMutableTuple(A a, B b) {
        Tuple2<A, B> t = new Tuple2<A,B>(null,null,true) {
        	//mutable variables overriding default final variables
        	private A a1; 
        	private B a2; 
        	
        	@Override
            public A a1() { return this.a1; }
        	
        	@Override
            public B a2() { return this.a2; }

            public void setA1(A a1) { 
            	this.a1 = a1;
            }
            public void setA2(B a2) { 
            	this.a2 = a2;
            }
        };
        t.setA1(a);
        t.setA2(b);
        return t;
    }
    /** creates a new tuple with 3 elements which cannot be altered
     *
     * @param <A>
     * @param <B>
     * @param a
     * @param b
     * @return
     */
    public static <A,B,C> Tuple3<A,B,C> makeMutableTuple(A a, B b, C c) {
        Tuple3<A, B, C> t = new Tuple3<A,B,C>(null,null,null,true) {
        	//mutable variables overriding default final variables
        	private A a1; 
        	private B a2; 
        	private C a3; 
        	
            public A a1() { return a1; }
            public B a2() { return a2; }
            public C a3() { return a3; }

            public void setA1(A a1) { 
            	this.a1 = a1;
            }
            public void setA2(B a2) { 
            	this.a2 = a2;
            }
            public void setA3(C a3) { 
            	this.a3 = a3;
            }
        };
        t.setA1(a);
        t.setA2(b);
        t.setA3(c);
        return t;
    }
    /** creates a new tuple with 4 elements which cannot be altered
     *
     * @param <A>
     * @param <B>
     * @param a
     * @param b
     * @return
     */
    public static <A,B,C,D> Tuple4<A,B,C,D> makeMutableTuple(A a, B b, C c, D d) {
        Tuple4<A, B, C, D> t = new Tuple4<A,B,C,D>(null,null,null,null,true) {
        	//mutable variables overriding default final variables
        	private A a1; 
        	private B a2; 
        	private C a3; 
        	private D a4;
        	
            public A a1() { return a1; }
            public B a2() { return a2; }
            public C a3() { return a3; }
            public D a4() { return a4; }

            public void setA1(A a1) { 
            	this.a1 = a1;
            }
            public void setA2(B a2) { 
            	this.a2 = a2;
            }
            public void setA3(C a3) { 
            	this.a3 = a3;
            }
            public void setA4(D a4) { 
            	this.a4 = a4;
            }
        };
        t.setA1(a);
        t.setA2(b);
        t.setA3(c);
        t.setA4(d);
        return t;
    }
    /** creates a new tuple with 5 elements which cannot be altered
     *
     * @param <A>
     * @param <B>
     * @param a
     * @param b
     * @return
     */
    public static <A,B,C,D,E> Tuple5<A,B,C,D,E> makeMutableTuple(A a, B b, C c, D d, E e) {
        Tuple5<A, B, C, D, E> t = new Tuple5<A,B,C,D,E>(null,null,null,null,null,true) {
        	//mutable variables overriding default final variables
        	private A a1; 
        	private B a2; 
        	private C a3; 
        	private D a4; 
        	private E a5; 
        	
            public A a1() { return a1; }
            public B a2() { return a2; }
            public C a3() { return a3; }
            public D a4() { return a4; }
            public E a5() { return a5; }

            public void setA1(A a1) { 
            	this.a1 = a1;
            }
            public void setA2(B a2) { 
            	this.a2 = a2;
            }
            public void setA3(C a3) { 
            	this.a3 = a3;
            }
            public void setA4(D a4) { 
            	this.a4 = a4;
            }
            public void setA5(E a5) { 
            	this.a5 = a5;
            }
        };
        t.setA1(a);
        t.setA2(b);
        t.setA3(c);
        t.setA4(d);
        t.setA5(e);
        return t;
    }
}
