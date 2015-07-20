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

import static net.sourcedestination.funcles.tuple.Pair.makePair;
import static net.sourcedestination.funcles.Argmax.argmax;
import static org.junit.Assert.*;

import java.util.function.Function;

import net.sourcedestination.funcles.Funcles;
import net.sourcedestination.funcles.relation.Relation2;
import net.sourcedestination.funcles.relation.Relation3;
import net.sourcedestination.funcles.tuple.Pair;
import net.sourcedestination.funcles.tuple.Tuple2;
import net.sourcedestination.funcles.tuple.Tuple3;
import net.sourcedestination.funcles.tuple.Tuple4;
import net.sourcedestination.funcles.tuple.Tuple5;

import org.junit.Test;

public class FunclesTests {

	@Test
	public void testApplyFunctions() {
		int res2 = Funcles.apply(new Function<Tuple2<Integer,Integer>, Integer>() {
			@Override
			public Integer apply(Tuple2<Integer, Integer> args) {
				// TODO Auto-generated method stub
				return args._1() + args._2();
			}
		}, 1, 2);
		int res3 = Funcles.apply(new Function<
				Tuple3<Integer,Integer,Integer>, 
				Integer>() {
			@Override
			public Integer apply(Tuple3<Integer,Integer,Integer> args) {
				// TODO Auto-generated method stub
				return args._1() + args._2() + args._3();
			}
		}, 1, 2, 3);
		int res4 = Funcles.apply(new Function<
				Tuple4<Integer,Integer,Integer,Integer>, 
				Integer>() {
			@Override
			public Integer apply(Tuple4<Integer,Integer,Integer,Integer> args) {
				// TODO Auto-generated method stub
				return args._1() + args._2() + args._3() + args._4();
			}
		}, 1, 2, 3, 4);
		int res5 = Funcles.apply(new Function<
				Tuple5<Integer,Integer,Integer,Integer,Integer>, 
				Integer>() {
			@Override
			public Integer apply(Tuple5<Integer,Integer,Integer,Integer,Integer> args) {
				// TODO Auto-generated method stub
				return args._1() + args._2() + args._3() + args._4() + args._5();
			}
		}, 1, 2, 3, 4, 5);
		assertEquals(3, res2);
		assertEquals(6, res3);
		assertEquals(10, res4);
		assertEquals(15, res5);
	}

	@Test
	public void testApplyRelations() {
		assertTrue(Funcles.apply(new Relation2<Integer,Integer>() {
			@Override
			public boolean test(Integer _1, Integer _2) {
				if(_2 == 0) return false;
				return (_1/_2)*_2 == _1;
			}
			
		}, 8, 2));
		
		assertTrue(Funcles.apply(new Relation3<Integer,Integer,Integer>() {
			@Override
			public boolean test(Integer _1, Integer _2, Integer _3) {
				return _1 != _2 &&
						_1 != _3 &&
						_2 != _3;
			}
			
		}, 8, 2, 1));
	}


	@Test
	public void testArgmax() {
		Function<Pair<Integer>,Integer> f = new Function<Pair<Integer>,Integer>() {

			@Override
			public Integer apply(Pair<Integer> args) {
				return args._1() * 10 + args._2();
			}
			
		};
		Pair<Integer> maxarg = argmax(f, 
				makePair(2, 16),  
				makePair(3, 14),  
				makePair(4, 6),  
				makePair(1, 12)
		);
		assertEquals(makePair(4, 6), maxarg);
	}


}
