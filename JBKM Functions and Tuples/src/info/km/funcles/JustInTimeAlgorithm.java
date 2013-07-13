/* Copyright 2011-2013 Joseph Kendall-Morwick

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

package info.km.funcles;

import com.google.common.base.Function;

/** Implementing this interface designates that this function is able to 
 * use information stored in a ProcessingThread which provides running 
 * time, expected running time, and other signals to the algorithm.
 * 
 * @author jmorwick
 * @version 1.0.0
 *
 * @param <F>
 * @param <T>
 */
public interface JustInTimeAlgorithm<F,T> extends Function<F,T> {
	
	/** start the function and provide a reference to the ProcessingThread it is running on
	 * 
	 * @param input
	 * @param pc
	 * @return
	 */
	public T apply(F input, ProcessingThread<F,T> pc);
}