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

package info.kendall_morwick.funcles;

import static org.junit.Assert.*;
import static info.kendall_morwick.funcles.Pair.makePair;
import static com.google.common.collect.Sets.newHashSet;
import info.kendall_morwick.funcles.BinaryRelation;
import info.kendall_morwick.funcles.Funcles;
import info.kendall_morwick.funcles.MemoizedFunction;
import info.kendall_morwick.funcles.Pair;
import info.kendall_morwick.funcles.T2;
import info.kendall_morwick.funcles.T3;
import info.kendall_morwick.funcles.T4;
import info.kendall_morwick.funcles.T5;
import info.kendall_morwick.funcles.TernaryRelation;
import info.kendall_morwick.funcles.Triple;

import java.util.Set;

import org.junit.Test;

import com.google.common.base.Function;

public class FunclesTests {

	@Test
	public void testApplyFunctions() {
		int res2 = Funcles.apply(new Function<T2<Integer,Integer>, Integer>() {
			@Override
			public Integer apply(T2<Integer, Integer> args) {
				// TODO Auto-generated method stub
				return args.a1() + args.a2();
			}
		}, 1, 2);
		int res3 = Funcles.apply(new Function<
				T3<Integer,Integer,Integer>, 
				Integer>() {
			@Override
			public Integer apply(T3<Integer,Integer,Integer> args) {
				// TODO Auto-generated method stub
				return args.a1() + args.a2() + args.a3();
			}
		}, 1, 2, 3);
		int res4 = Funcles.apply(new Function<
				T4<Integer,Integer,Integer,Integer>, 
				Integer>() {
			@Override
			public Integer apply(T4<Integer,Integer,Integer,Integer> args) {
				// TODO Auto-generated method stub
				return args.a1() + args.a2() + args.a3() + args.a4();
			}
		}, 1, 2, 3, 4);
		int res5 = Funcles.apply(new Function<
				T5<Integer,Integer,Integer,Integer,Integer>, 
				Integer>() {
			@Override
			public Integer apply(T5<Integer,Integer,Integer,Integer,Integer> args) {
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
		assertTrue(Funcles.apply(new BinaryRelation<Integer>() {
			@Override
			public boolean apply(Pair<Integer> args) {
				if(args.a2() == 0) return false;
				return (args.a1()/args.a2())*args.a2() == args.a1();
			}
			
		}, 8, 2));
		
		assertTrue(Funcles.apply(new TernaryRelation<Integer>() {
			@Override
			public boolean apply(Triple<Integer> args) {
				return args.a1() != args.a2() &&
						args.a1() != args.a3() &&
						args.a2() != args.a3();
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
	public void testPartition() {
		Set<Integer> numbers = newHashSet(1, 2, 3, 4, 5, 123, 41, 523, 1244);
		BinaryRelation r = new BinaryRelation<Integer>() {

			@Override
			public boolean apply(Pair<Integer> args) {
				return args.a1() % 3 == args.a2() % 3;
			}
			
		};
		Set<Set<Integer>> partitions = Funcles.partition(r, numbers);
		assertEquals(3, partitions.size());
		Set<Set<Integer>> correctResult = newHashSet(
				(Set<Integer>)newHashSet(1, 4, 523),	
				(Set<Integer>)newHashSet(2, 5, 41, 1244),	
				(Set<Integer>)newHashSet(3, 123)	
		);
		assertEquals(correctResult, partitions);
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
		
		MemoizedFunction<Integer, Integer> mf = Funcles.memoize(f);
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
