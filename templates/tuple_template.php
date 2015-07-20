<?php 
	$n = $argv[1];
	$type_params = implode(", ", array_map(function ($x) { return "A$x"; }, range(1, $n)));
?>/* Copyright 2011-2015 Joseph Kendall-Morwick

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



/**  A class representing a <?=$n?>-tuple
 *


  @author Joseph Kendall-Morwick <jmorwick@indiana.edu>
  @version 2.0

  */
public class Tuple<?=$n?><<?= $type_params ?>> extends Tuple {
<?= 
implode("\n", array_map(function ($x) { return "    public final A$x _$x;"; }, range(1, $n)))
?>


    public Tuple<?=$n?>(<?= implode(", ", array_map(function ($x) { return "A$x _$x"; }, range(1, $n))) ?>) {
        super(<?=$n?>);
<?= 
implode("\n", array_map(function ($x) { return "        this._$x = _$x;"; }, range(1, $n)))
?>

    }
    
<?= 
implode("\n", array_map(function ($x) { return "    public A$x _$x() { return _$x; }"; }, range(1, $n)))
?>



    @Override
    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    public boolean equals(Object obj) {
        try {
            Tuple<?=$n?><<?=$type_params?>> t = (Tuple<?=$n?><<?=$type_params?>>)obj;
<?= 
implode("\n", array_map(function ($x) { return 
"            if(_$x == null && t._$x != null) return false;
            if(_$x != null && t._$x == null) return false;
            if(_$x != null && t._$x != null && !_$x.equals(t._$x)) return false;"; }, range(1, $n)))
?>          

            return true;
        }catch(ClassCastException e) { return false; }
    }

    @Override
    public int hashCode() {
        int hash = 7;
<?= 
implode("\n", array_map(function ($x) { return 
"       hash = 17 * hash + (this._$x != null ? this._$x.hashCode() : 0);"; }, range(1, $n)))
?>   
        return hash;
    }


    @Override
    public String toString() { return "[<?=implode(",", array_map(function ($x) { return "\"+_$x+\""; }, range(1, $n)))?>]"; }
}
