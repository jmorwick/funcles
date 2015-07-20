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



/**  A class representing a 2-tuple
 *


  @author Joseph Kendall-Morwick <jmorwick@indiana.edu>
  @version 2.0

  */
public class Tuple2<A1, A2> extends Tuple {
    public final A1 _1;
    public final A2 _2;

    public Tuple2(A1 _1, A2 _2) {
        super(2);
        this._1 = _1;
        this._2 = _2;
    }
    
    public A1 _1() { return _1; }
    public A2 _2() { return _2; }


    @Override
    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    public boolean equals(Object obj) {
        try {
            Tuple2<A1, A2> t = (Tuple2<A1, A2>)obj;
            if(_1 == null && t._1 != null) return false;
            if(_1 != null && t._1 == null) return false;
            if(_1 != null && t._1 != null && !_1.equals(t._1)) return false;
            if(_2 == null && t._2 != null) return false;
            if(_2 != null && t._2 == null) return false;
            if(_2 != null && t._2 != null && !_2.equals(t._2)) return false;          

            return true;
        }catch(ClassCastException e) { return false; }
    }

    @Override
    public int hashCode() {
        int hash = 7;
       hash = 17 * hash + (this._1 != null ? this._1.hashCode() : 0);
       hash = 17 * hash + (this._2 != null ? this._2.hashCode() : 0);   
        return hash;
    }


    @Override
    public String toString() { return "["+_1+","+_2+"]"; }
}
