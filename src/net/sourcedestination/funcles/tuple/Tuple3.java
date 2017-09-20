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

import net.sourcedestination.funcles.consumer.Consumer3;



/**  A class representing a 3-tuple
 *


  @author Joseph Kendall-Morwick &lt;jbmorwick@gmail.com&gt;
  @version 2.0

  */
public class Tuple3<A1, A2, A3> extends Tuple<Tuple3<A1, A2, A3>> {
	private static final long serialVersionUID = 1L;
    public final A1 _1;
    public final A2 _2;
    public final A3 _3;

    public Tuple3(A1 _1, A2 _2, A3 _3) {
        super(3);
        this._1 = _1;
        this._2 = _2;
        this._3 = _3;
    }
    
    public A1 _1() { return _1; }
    public A2 _2() { return _2; }
    public A3 _3() { return _3; }

	/** a simple way to unpack a tuple with  arguments to an anonymous consumer
	 * @param c Consumer to accept the values in this tuple
	 */
	public void unpack(Consumer3<A1, A2, A3> c) {
	    c.accept(this);
	}

	/** copies this tuple and returns a new tuple with value #1 replaced by newValue
	 * @param newValue value to include at location 1	 * @return a new tuple with the new value at location 1	 */
	public Tuple3<A1, A2, A3> set1(A1 newValue) {
	    return makeTuple(newValue, _2, _3);
	}
	

	/** copies this tuple and returns a new tuple with value #2 replaced by newValue
	 * @param newValue value to include at location 2	 * @return a new tuple with the new value at location 2	 */
	public Tuple3<A1, A2, A3> set2(A2 newValue) {
	    return makeTuple(_1, newValue, _3);
	}
	

	/** copies this tuple and returns a new tuple with value #3 replaced by newValue
	 * @param newValue value to include at location 3	 * @return a new tuple with the new value at location 3	 */
	public Tuple3<A1, A2, A3> set3(A3 newValue) {
	    return makeTuple(_1, _2, newValue);
	}
	
	
    @Override
    @SuppressWarnings({ "unchecked" })
    public boolean equals(Object obj) {
        try {
            Tuple3<A1, A2, A3> t = (Tuple3<A1, A2, A3>)obj;
            if(_1 == null && t._1 != null) return false;
            if(_1 != null && t._1 == null) return false;
            if(_1 != null && t._1 != null && !_1.equals(t._1)) return false;
            if(_2 == null && t._2 != null) return false;
            if(_2 != null && t._2 == null) return false;
            if(_2 != null && t._2 != null && !_2.equals(t._2)) return false;
            if(_3 == null && t._3 != null) return false;
            if(_3 != null && t._3 == null) return false;
            if(_3 != null && t._3 != null && !_3.equals(t._3)) return false;          

            return true;
        }catch(ClassCastException e) { return false; }
    }

    @Override
    public int hashCode() {
        int hash = 7;
       hash = 17 * hash + (this._1 != null ? this._1.hashCode() : 0);
       hash = 17 * hash + (this._2 != null ? this._2.hashCode() : 0);
       hash = 17 * hash + (this._3 != null ? this._3.hashCode() : 0);   
        return hash;
    }

	/** attempts to compare this tuple to another tuple using the common Comparable semantics.
	 * @throws ClassCastException if any type within the tuple doesn't implement Comparable
	 * @param t tuple to compare this tuple to
	 * @return 0 if the same, other values indicate a difference
	 */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public int compareTo(Tuple3<A1, A2, A3> t) {
		int r;
		r = ((Comparable)_1).compareTo(t._1);
		if(r != 0) return r;
		r = ((Comparable)_2).compareTo(t._2);
		if(r != 0) return r;
		r = ((Comparable)_3).compareTo(t._3);
		if(r != 0) return r;
		return r;
	}

    @Override
    public String toString() { return "["+_1+","+_2+","+_3+"]"; }
}
