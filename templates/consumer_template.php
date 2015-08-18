<?php 
	$n = $argv[1];
	$type_params = implode(", ", array_map(function ($x) { return "A$x"; }, range(1, $n)));
	$params = implode(", ", array_map(function ($x) { return "A$x arg$x"; }, range(1, $n)));
	$args = implode(", ", array_map(function ($x) { return "arg$x"; }, range(1, $n)));
	$tuple_contents = implode(", ", array_map(function ($x) { return "args._$x"; }, range(1, $n)));
?>/* Copyright 2011-2014 Joseph Kendall-Morwick

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

package net.sourcedestination.funcles.consumer;

import java.util.function.Consumer;
<?php if($n == 2) { ?>import java.util.function.BiConsumer;
<?php } ?>import java.util.function.Function;

import net.sourcedestination.funcles.Funcles;
import net.sourcedestination.funcles.tuple.Tuple<?=$n?>;

import static net.sourcedestination.funcles.tuple.Tuple.makeTuple;

/** 
 *
 * @author Joseph Kendall-Morwick <jmorwick@indiana.edu>
 * @version 2.0
 */
@FunctionalInterface
public abstract interface Consumer<?=$n?><<?=$type_params?>> extends Consumer<Tuple<?=$n?><<?=$type_params?>>><?php if($n == 2) { ?>,
														BiConsumer<A1,A2> <?php } ?> {
	
	public default void accept(Tuple<?=$n?><<?=$type_params?>> args) {
		accept(<?=$tuple_contents?>);
	}
	
	public void accept(<?=$params?>);
	
	public default Consumer<?=$n?><<?=$type_params?>> applyHigherOrderTo(Function< ? super Consumer<?=$n?><<?=$type_params?>>, 
				                                                ? extends Consumer<Tuple<?=$n?><<?=$type_params?>>>> hof) {
		return toConsumer<?=$n?>(hof.apply(this));
	}
	
	public static <<?=$type_params?>> Consumer<?=$n?><<?=$type_params?>> 
		toConsumer<?=$n?>(Consumer<Tuple<?=$n?><<?=$type_params?>>> f) {
		return (<?=$args?>) -> f.accept(makeTuple(<?=$args?>));
	}
	
	public static <<?=$type_params?>> Consumer<?=$n?><<?=$type_params?>>
		 applyHigherOrder(Function< ? super Consumer<?=$n?><<?=$type_params?>>, 
				                   ? extends Consumer<Tuple<?=$n?><<?=$type_params?>>>> hof,
				                Consumer<?=$n?><<?=$type_params?>> f) {
		return toConsumer<?=$n?>(hof.apply(f));
	}
<?php if($n == 2) { ?>	
	public default <V> Consumer2<A1, A2> andThen(Consumer2< ? super A1, ? super A2> after) {
		return (x, y) -> {
		    accept(x, y);
			after.accept(x, y);
		};
		
	}
<?php } ?>

}
