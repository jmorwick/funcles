<?php 
	$n = $argv[1];
?>
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

package net.sourcedestination.funcles;


import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

import net.sourcedestination.funcles.consumer.*;
import net.sourcedestination.funcles.relation.*;
import net.sourcedestination.funcles.tuple.*;

import static net.sourcedestination.funcles.tuple.Pair.makePair;
import static net.sourcedestination.funcles.tuple.Triple.makeTriple;
import static net.sourcedestination.funcles.tuple.Tuple.makeTuple;


/** A utility class which provides static methods for shrinking common
 * function and consumer operations. Similar methods for tuples and 
 * relations are defined in their respective interfaces. Since the 
 * Java 1.8 Function and Consumer interfaces are used with this library,
 * this utility class was necessary to add. 
 * 
 * @author Joseph Kendall-Morwick <jbmorwick@gmail.com>
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
	/** a simple way to call accept for a consumer with <?=$i?> arguments 
	 */
	public static <<?=$type_params?>> void accept(Consumer<Tuple<?=$i?><<?=$type_params?>>> c, 
		<?=$params?>) {
		c.accept(makeTuple(<?=$args?>));
	}
	
	/** a simple way to call apply for a function with <?=$i?> arguments 
	 */
	public static <<?=$type_params?>, R> R apply(Function<Tuple<?=$i?><<?=$type_params?>>,R> c, 
		<?=$params?>) {
		return c.apply(makeTuple(<?=$args?>));
	}
	
	public static <<?=$type_params?>> void forEach(
			Stream<Tuple<?=$i?><<?=$type_params?>>> s, 
			Consumer<?=$i?><<?=$type_params?>> c) {
		s.forEach(t -> c.accept(t));
	}
	
	/** a simple way to call apply for a relation with <?=$i?> arguments
	 */
	public static <<?=$type_params?>> boolean apply(Relation<?=$i?><<?=$type_params?>> r,  
	    <?=$params?>) {
		return r.apply(makeTuple(<?=$args?>));
	}

<?php } ?>

	
   
}
