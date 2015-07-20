<?php 
	$n = $argv[1];
	$type_params = implode(", ", array_map(function ($x) { return "A$x"; }, range(1, $n)));
	$params = implode(", ", array_map(function ($x) { return "A$x arg$x"; }, range(1, $n)));
	$args = implode(", ", array_map(function ($x) { return "arg$x"; }, range(1, $n)));
	$tuple_contents = implode(", ", array_map(function ($x) { return "args._$x"; }, range(1, $n)));
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

package net.sourcedestination.funcles.relation;

import java.util.function.BiPredicate;

import net.sourcedestination.funcles.function.Function<?=$n?>;
import net.sourcedestination.funcles.tuple.Tuple<?=$n?>;

/** This class provides a clean abstraction for implementing binary relations
 *
 * @author Joseph Kendall-Morwick <jmorwick@indiana.edu>
 * @version 2.0
 */
@FunctionalInterface
public abstract interface Relation2<<?=$type_params?>> extends Relation<Tuple<?=$n?><<?=$type_params?>>>, 
											   Function<?=$n?><<?=$type_params?>,Boolean> {
	
	public default Boolean apply(<?=$params?>) {
		return test(<?=$args?>);
	}

	public default boolean test(Tuple<?=$n?><<?=$type_params?>> args) {
		return test(<?=$tuple_contents?>);
	}

	public default Boolean apply(Tuple<?=$n?><<?=$type_params?>> args) {
		return test(<?=$tuple_contents?>);
	}

<?php if($n == 2) { ?>
	/** Converts this relation in to a java.util.function.BiPredicate.
	 * 
	 * This method is necessary due to a conflict with the negate method: 
	 * Relation2 cannot extend both java.util.Function.Predicate and 
	 * java.util.function.BiPredicate due to this conflict. 
	 * 
	 * @return this predicate represented as a BiPredicate
	 */
	public default BiPredicate<<?=$type_params?>> toBiPredicate() {
		return this::test;
	}
<?php } ?>
	public boolean test(<?=$params?>);
	
}
