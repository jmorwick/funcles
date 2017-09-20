<?php 
	$n = $argv[1];
	$type_params = implode(", ", array_map(function ($x) { return "A$x"; }, range(1, $n)));
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

import net.sourcedestination.funcles.consumer.Consumer<?=$n?>;



/**  A class representing a <?=$n?>-tuple
 *


  @author Joseph Kendall-Morwick &lt;jbmorwick@gmail.com&gt;
  @version 2.0

  */
public class Tuple<?=$n?><<?= $type_params ?>> extends Tuple<Tuple<?=$n?><<?= $type_params ?>>> {
	private static final long serialVersionUID = 1L;
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


	/** a simple way to unpack a tuple with <?=$i?> arguments to an anonymous consumer
	 * @param c Consumer to accept the values in this tuple
	 */
	public void unpack(Consumer<?=$n?><<?=$type_params?>> c) {
	    c.accept(this);
	}
<?php for($i=1; $i<=$n; $i++) { ?>

	/** copies this tuple and returns a new tuple with value #<?=$i?> replaced by newValue
	 * @param newValue value to include at location <?=$i?>
	 * @return a new tuple with the new value at location <?=$i?>
	 */
	public Tuple<?=$n?><<?=$type_params?>> set<?=$i?>(A<?=$i?> newValue) {
	    return makeTuple(<?php 
	    	for($j=1; $j<=$n; $j++) {
	    		if($j > 1) echo ', ';
	    		echo $j == $i ? 'newValue' : "_$j";
	    	}
	    ?>);
	}
	
<?php } ?>
	
    @Override
    @SuppressWarnings({ "unchecked" })
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

	/** attempts to compare this tuple to another tuple using the common Comparable semantics.
	 * @throws ClassCastException if any type within the tuple doesn't implement Comparable
	 * @param t tuple to compare this tuple to
	 * @return 0 if the same, other values indicate a difference
	 */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public int compareTo(Tuple<?=$n?><<?= $type_params ?>> t) {
		int r;
<?php foreach(range(1, $n) as $i) { ?>
		r = ((Comparable)_<?=$i?>).compareTo(t._<?=$i?>);
		if(r != 0) return r;
<?php } ?>
		return r;
	}

    @Override
    public String toString() { return "[<?=implode(",", array_map(function ($x) { return "\"+_$x+\""; }, range(1, $n)))?>]"; }
}
