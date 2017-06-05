<?php 
	$n = $argv[1];
	$type_params = implode(", ", array_map(function ($x) { return "A$x"; }, range(1, $n)));
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

package net.sourcedestination.funcles.function;

import java.util.function.Function;
<?php if($n == 2) { ?>import java.util.function.BiFunction;
<?php } ?>

import net.sourcedestination.funcles.tuple.*;

import static net.sourcedestination.funcles.tuple.Tuple.makeTuple;


/** 
 *
 * @author Joseph Kendall-Morwick <jmorwick@indiana.edu>
 * @version 2.0
 */
@FunctionalInterface
public interface Function<?=$n?><<?=$type_params?>, R> extends Function<Tuple<?=$n?><<?=$type_params?>>, R><?php if($n == 2) { ?>,
														BiFunction<A1,A2,R> <?php } ?> {
	default R apply(Tuple<?=$n?><<?=$type_params?>> args) {
		return apply(<?=implode(", ", array_map(function ($x) { return "args._$x"; }, range(1, $n)))?>);
	}

	R apply(<?=implode(", ", array_map(function ($x) { return "A$x arg$x"; }, range(1, $n)))?>);
	
<?php foreach(range(1, $n) as $i) { 
	$limited_type_params = '';
	$limited_args = '';
	$full_args = '';
	for($x=1; $x<=$n; $x++) {
		$full_args .= ($x > 1 ? ', ' : '') . ($x == $i ? "arg" : "a$x");
		if($x == $i) continue;
		$limited_type_params .= ($limited_args ? ', ' : '') ."A$x";
		$limited_args .= ($limited_args ? ', ' : '') . "a$x";
	}
	?>
	
	default Function<?=$n==2?'':($n-1)?><<?=$limited_type_params?>, R> bind<?=$i?>(A<?=$i?> arg) {
	    return (<?=$limited_args?>) -> apply(<?=$full_args?>);
	}
<?php } ?>
	
	static <<?=$type_params?>,R> Function<?=$n?><<?=$type_params?>,R>
		toFunction<?=$n?>(Function<Tuple<?=$n?><<?=$type_params?>>, R> f) {
		return (<?=implode(", ", array_map(function ($x) { return "arg$x"; }, range(1, $n)))?>) -> 
		  f.apply(makeTuple(<?=implode(", ", array_map(function ($x) { return "arg$x"; }, range(1, $n)))?>));
	}
	
	static <<?=$type_params?>, R> Function<?=$n?><<?=$type_params?>,R>
		 applyHigherOrder(Function< ? super Function<?=$n?><<?=$type_params?>,R>, 
				                   ? extends Function<Tuple<?=$n?><<?=$type_params?>>,R>> hof,
				          Function<?=$n?><<?=$type_params?>,R> f) {
		return toFunction<?=$n?>(hof.apply(f));
	}
<?php if($n == 2) { ?>	
	default <V> Function2<A1, A2, V> andThen(Function< ? super R, ? extends V> after) {
		return (x, y) -> after.apply(apply(x, y));
		
	}
<?php } ?>
			
}
