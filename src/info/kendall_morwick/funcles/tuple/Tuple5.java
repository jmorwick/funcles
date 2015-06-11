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



/** A class representing a 5-element tuple
  @author Joseph Kendall-Morwick <jmorwick@indiana.edu>
  @version 2.1

  */
public class Tuple5<A1, A2, A3, A4, A5> extends Tuple {
    private final A1 a1;
    private final A2 a2;
    private final A3 a3;
    private final A4 a4;
    private final A5 a5;

    public Tuple5(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, boolean mutable) {
        super(mutable,5);
        this.a1 = a1;
        this.a2 = a2;
        this.a3 = a3;
        this.a4 = a4;
        this.a5 = a5;
    }

    public A1 a1() { return a1; }
    public A2 a2() { return a2; }
    public A3 a3() { return a3; }
    public A4 a4() { return a4; }
    public A5 a5() { return a5; }

    public void setA1(A1 a1) { 
    	throw new RuntimeException("attempted modification of immutable tuple");
    }
    public void setA2(A2 a2) { 
    	throw new RuntimeException("attempted modification of immutable tuple");
    }
    public void setA3(A3 a3) { 
    	throw new RuntimeException("attempted modification of immutable tuple");
    }
    public void setA4(A4 a4) { 
    	throw new RuntimeException("attempted modification of immutable tuple");
    }
    public void setA5(A5 a5) { 
    	throw new RuntimeException("attempted modification of immutable tuple");
    }


    @Override
    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    public boolean equals(Object obj) {
        try {
            Tuple5<A1,A2,A3,A4,A5> t = (Tuple5<A1,A2,A3,A4,A5>)obj;
            if(a1() == null && t.a1() != null) return false;
            else if(a1() != null && t.a1() == null) return false;
            else if(a1() != null && t.a1() != null && !a1().equals(t.a1())) return false;
            if(a2() == null && t.a2() != null) return false;
            else if(a2() != null && t.a2() == null) return false;
            else if(a2() != null && t.a2() != null && !a2().equals(t.a2())) return false;
            if(a3() == null && t.a3() != null) return false;
            else if(a3() != null && t.a3() == null) return false;
            else if(a3() != null && t.a3() != null && !a3().equals(t.a3())) return false;
            if(a4() == null && t.a4() != null) return false;
            else if(a4() != null && t.a4() == null) return false;
            else if(a4() != null && t.a4() != null && !a4().equals(t.a4())) return false;
            if(a5() == null && t.a5() != null) return false;
            else if(a5() != null && t.a5() == null) return false;
            else if(a5() != null && t.a5() != null && !a5().equals(t.a5())) return false;
            return true;
        }catch(ClassCastException e) { return false; }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + (this.a1() != null ? this.a1().hashCode() : 0);
        hash = 17 * hash + (this.a2() != null ? this.a2().hashCode() : 0);
        hash = 17 * hash + (this.a3() != null ? this.a3().hashCode() : 0);
        hash = 17 * hash + (this.a4() != null ? this.a4().hashCode() : 0);
        hash = 17 * hash + (this.a5() != null ? this.a5().hashCode() : 0);
        return hash;
    }


}
