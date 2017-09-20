<?php

?>
/********************************* up to here *********************************/

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


/** A class representing a 2-tuple where each element
 * of the tuple has the same type
 *


  @author Joseph Kendall-Morwick &lt;jbmorwick@gmail.com&gt;
  @version 2.0

  */
public class Triple<T> extends Tuple3<T,T,T> {

	public Triple(T a1, T a2, T a3) {
		super(a1, a2, a3);
	}

	/** creates a new immutable pair with the given arguments
	 */
	public static <T> Triple<T> makeTriple(T a1, T a2, T a3) {
		return new Triple<T>(a1, a2, a3);
	}
	

}
