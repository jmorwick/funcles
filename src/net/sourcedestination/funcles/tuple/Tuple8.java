/* Copyright 2011-2015 Joseph Kendall-Morwick

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



/**  A class representing a 8-tuple
 *


  @author Joseph Kendall-Morwick <jbmorwick@gmail.com>
  @version 2.0

  */
public class Tuple8<A1, A2, A3, A4, A5, A6, A7, A8> extends Tuple<Tuple8<A1, A2, A3, A4, A5, A6, A7, A8>> {
	private static final long serialVersionUID = 1L;
    public final A1 _1;
    public final A2 _2;
    public final A3 _3;
    public final A4 _4;
    public final A5 _5;
    public final A6 _6;
    public final A7 _7;
    public final A8 _8;

    public Tuple8(A1 _1, A2 _2, A3 _3, A4 _4, A5 _5, A6 _6, A7 _7, A8 _8) {
        super(8);
        this._1 = _1;
        this._2 = _2;
        this._3 = _3;
        this._4 = _4;
        this._5 = _5;
        this._6 = _6;
        this._7 = _7;
        this._8 = _8;
    }
    
    public A1 _1() { return _1; }
    public A2 _2() { return _2; }
    public A3 _3() { return _3; }
    public A4 _4() { return _4; }
    public A5 _5() { return _5; }
    public A6 _6() { return _6; }
    public A7 _7() { return _7; }
    public A8 _8() { return _8; }


    @Override
    @SuppressWarnings({ "unchecked" })
    public boolean equals(Object obj) {
        try {
            Tuple8<A1, A2, A3, A4, A5, A6, A7, A8> t = (Tuple8<A1, A2, A3, A4, A5, A6, A7, A8>)obj;
            if(_1 == null && t._1 != null) return false;
            if(_1 != null && t._1 == null) return false;
            if(_1 != null && t._1 != null && !_1.equals(t._1)) return false;
            if(_2 == null && t._2 != null) return false;
            if(_2 != null && t._2 == null) return false;
            if(_2 != null && t._2 != null && !_2.equals(t._2)) return false;
            if(_3 == null && t._3 != null) return false;
            if(_3 != null && t._3 == null) return false;
            if(_3 != null && t._3 != null && !_3.equals(t._3)) return false;
            if(_4 == null && t._4 != null) return false;
            if(_4 != null && t._4 == null) return false;
            if(_4 != null && t._4 != null && !_4.equals(t._4)) return false;
            if(_5 == null && t._5 != null) return false;
            if(_5 != null && t._5 == null) return false;
            if(_5 != null && t._5 != null && !_5.equals(t._5)) return false;
            if(_6 == null && t._6 != null) return false;
            if(_6 != null && t._6 == null) return false;
            if(_6 != null && t._6 != null && !_6.equals(t._6)) return false;
            if(_7 == null && t._7 != null) return false;
            if(_7 != null && t._7 == null) return false;
            if(_7 != null && t._7 != null && !_7.equals(t._7)) return false;
            if(_8 == null && t._8 != null) return false;
            if(_8 != null && t._8 == null) return false;
            if(_8 != null && t._8 != null && !_8.equals(t._8)) return false;          

            return true;
        }catch(ClassCastException e) { return false; }
    }

    @Override
    public int hashCode() {
        int hash = 7;
       hash = 17 * hash + (this._1 != null ? this._1.hashCode() : 0);
       hash = 17 * hash + (this._2 != null ? this._2.hashCode() : 0);
       hash = 17 * hash + (this._3 != null ? this._3.hashCode() : 0);
       hash = 17 * hash + (this._4 != null ? this._4.hashCode() : 0);
       hash = 17 * hash + (this._5 != null ? this._5.hashCode() : 0);
       hash = 17 * hash + (this._6 != null ? this._6.hashCode() : 0);
       hash = 17 * hash + (this._7 != null ? this._7.hashCode() : 0);
       hash = 17 * hash + (this._8 != null ? this._8.hashCode() : 0);   
        return hash;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	/** attempts to compare this tuple to another tuple using the common Comparable semantics.  
	 * @throws ClassCastException if any type within the tuple doesn't implement Comparable
	 */
	public int compareTo(Tuple8<A1, A2, A3, A4, A5, A6, A7, A8> t) {
		int r;
		r = ((Comparable)_1).compareTo(t._1);
		if(r != 0) return r;
		r = ((Comparable)_2).compareTo(t._2);
		if(r != 0) return r;
		r = ((Comparable)_3).compareTo(t._3);
		if(r != 0) return r;
		r = ((Comparable)_4).compareTo(t._4);
		if(r != 0) return r;
		r = ((Comparable)_5).compareTo(t._5);
		if(r != 0) return r;
		r = ((Comparable)_6).compareTo(t._6);
		if(r != 0) return r;
		r = ((Comparable)_7).compareTo(t._7);
		if(r != 0) return r;
		r = ((Comparable)_8).compareTo(t._8);
		if(r != 0) return r;
		return r;
	}

    @Override
    public String toString() { return "["+_1+","+_2+","+_3+","+_4+","+_5+","+_6+","+_7+","+_8+"]"; }
}
