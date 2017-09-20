<?php 
	$max = $argv[1];
?>/* Copyright 2011-2017 Joseph Kendall-Morwick

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

  @author Joseph Kendall-Morwick &lt;jbmorwick@gmail.com&gt;
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

    
    
<?php foreach(range(2, $max) as $i) { 
	    $type_params = implode(", ", array_map(function ($x) { return "A$x"; }, range(1, $i)));
	    $parameters = implode(", ", array_map(function ($x) { return "A$x _$x"; }, range(1, $i)));
	    $arguments = implode(", ", array_map(function ($x) { return "_$x"; }, range(1, $i)));
?>
    public static <<?=$type_params?>> Tuple<?=$i?><<?=$type_params?>> makeTuple(<?=$parameters?>) {
        return new Tuple<?=$i?><>(<?=$arguments?>);
    }
    
<?php } ?>
}
