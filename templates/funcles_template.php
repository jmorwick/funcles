<?php 
	$n = $argv[1];
?>
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
 * @author Joseph Kendall-Morwick &lt;jbmorwick@gmail.com&gt;
 * @version 2.0
 *
 */
public class Funcles {

<?php 
foreach(range(2,$n) as $i) {
	$type_params = implode(", ", array_map(function ($x) { return "A$x"; }, range(1, $i)));
	$params = implode(", ", array_map(function ($x) { return "A$x arg$x"; }, range(1, $i)));
	$args = implode(", ", array_map(function ($x) { return "arg$x"; }, range(1, $i)));
	$tuple_contents = implode(", ", array_map(function ($x) { return "args._$x"; }, range(1, $i)));
?>
	
	public static <<?=$type_params?>> void forEach(
			Stream<Tuple<?=$i?><<?=$type_params?>>> s, 
			Consumer<?=$i?><<?=$type_params?>> c) {
		s.forEach(t -> c.accept(t));
	}

<?php } ?>

	
   
}
