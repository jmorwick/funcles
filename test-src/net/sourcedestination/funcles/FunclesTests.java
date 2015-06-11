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
import static org.junit.Assert.*;
import static com.google.common.collect.Sets.newHashSet;

import java.util.Set;
import java.util.function.Function;

import net.sourcedestination.funcles.Funcles;
import net.sourcedestination.funcles.MemoizedFunction;
import net.sourcedestination.funcles.relation.Relation2;
import net.sourcedestination.funcles.relation.Relation3;
import net.sourcedestination.funcles.tuple.Pair;
import net.sourcedestination.funcles.tuple.Triple;
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
				return args.a1() + args.a2();
			}
		}, 1, 2);
		int res3 = Funcles.apply(new Function<
				Tuple3<Integer,Integer,Integer>, 
				Integer>() {
			@Override
			public Integer apply(Tuple3<Integer,Integer,Integer> args) {
				// TODO Auto-generated method stub
				return args.a1() + args.a2() + args.a3();
			}
		}, 1, 2, 3);
		int res4 = Funcles.apply(new Function<
				Tuple4<Integer,Integer,Integer,Integer>, 
				Integer>() {
			@Override
			public Integer apply(Tuple4<Integer,Integer,Integer,Integer> args) {
				// TODO Auto-generated method stub
				return args.a1() + args.a2() + args.a3() + args.a4();
			}
		}, 1, 2, 3, 4);
		int res5 = Funcles.apply(new Function<
				Tuple5<Integer,Integer,Integer,Integer,Integer>, 
				Integer>() {
			@Override
			public Integer apply(Tuple5<Integer,Integer,Integer,Integer,Integer> args) {
				// TODO Auto-generated method stub
				return args.a1() + args.a2() + args.a3() + args.a4() + args.a5();
			}
		}, 1, 2, 3, 4, 5);
		assertEquals(3, res2);
		assertEquals(6, res3);
		assertEquals(10, res4);
		assertEquals(15, res5);
	}

	@Test
	public void testApplyRelations() {
		assertTrue(Funcles.apply(new Relation2<Integer>() {
			@Override
			public boolean test(Integer a1, Integer a2) {
				if(a2 == 0) return false;
				return (a1/a2)*a2 == a1;
			}
			
		}, 8, 2));
		
		assertTrue(Funcles.apply(new Relation3<Integer>() {
			@Override
			public boolean test(Integer a1, Integer a2, Integer a3) {
				return a1 != a2 &&
						a1 != a3 &&
						a2 != a3;
			}
			
		}, 8, 2, 1));
	}


	@Test
	public void testArgmax() {
		Function<Pair<Integer>,Integer> f = new Function<Pair<Integer>,Integer>() {

			@Override
			public Integer apply(Pair<Integer> args) {
				return args.a1() * 10 + args.a2();
			}
			
		};
		Pair<Integer> maxarg = Funcles.argmax(f, 
				makePair(2, 16),  
				makePair(3, 14),  
				makePair(4, 6),  
				makePair(1, 12)
		);
		assertEquals(makePair(4, 6), maxarg);
	}



	@Test
	public void testMemoize() {
		Function<Integer,Integer> f = new Function<Integer,Integer>() {

			@Override
			public Integer apply(Integer x) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return x;
			}
			
		};
		
		MemoizedFunction<Integer, Integer> mf = MemoizedFunction.memoize(f);
		long time = System.currentTimeMillis();
		assertEquals((Integer)5, mf.apply(5));
		assertTrue(System.currentTimeMillis() >= time + 100);
		time = System.currentTimeMillis();
		assertEquals(mf.apply(5), (Integer)5);
		assertTrue(System.currentTimeMillis() < time + 100);
		time = System.currentTimeMillis();
		assertEquals(mf.apply(10), (Integer)10);
		assertTrue(System.currentTimeMillis() >= time + 100);
		time = System.currentTimeMillis();
		assertEquals(mf.apply(10), (Integer)10);
		assertTrue(System.currentTimeMillis() < time + 100);
	}

	
}
