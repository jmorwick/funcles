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

import static net.sourcedestination.funcles.tuple.Tuple.*;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Set;

import net.sourcedestination.funcles.tuple.Tuple;
import net.sourcedestination.funcles.tuple.Tuple2;
import net.sourcedestination.funcles.tuple.Tuple3;
import net.sourcedestination.funcles.tuple.Tuple4;
import net.sourcedestination.funcles.tuple.Tuple5;

import org.junit.Test;

public class TupleTest {

	
	@Test
	public void testMakeTuple() {
		Tuple2<String, String> t2 = Tuple.makeTuple("hi", "there");
		assertNotNull(t2);
		assertEquals(2, t2.size());
		assertEquals("hi", t2._1());
		assertEquals("there", t2._2());

		Tuple3<Integer, Integer, Integer> t3 = 
				Tuple.makeTuple(1, 2, 3);
		assertNotNull(t3);
		assertEquals(3, t3.size());
		assertEquals((Integer)1, t3._1());
		assertEquals((Integer)2, t3._2());
		assertEquals((Integer)3, t3._3());

		Tuple4<Integer, Integer, Integer, Integer> t4 = 
				Tuple.makeTuple(1, 2, 3, 4);
		assertNotNull(t4);
		assertEquals(4, t4.size());
		assertEquals((Integer)1, t4._1());
		assertEquals((Integer)2, t4._2());
		assertEquals((Integer)3, t4._3());
		assertEquals((Integer)4, t4._4());

		Tuple5<Integer, Integer, Integer, Integer, Integer> t5 = 
				Tuple.makeTuple(1, 2, 3, 4, 5);
		assertNotNull(t5);
		assertEquals(5, t5.size());
		assertEquals((Integer)1, t5._1());
		assertEquals((Integer)2, t5._2());
		assertEquals((Integer)3, t5._3());
		assertEquals((Integer)4, t5._4());
		assertEquals((Integer)5, t5._5());
	}



	@Test
	public void testComparable3() throws IOException, ClassNotFoundException {
		Tuple3<Integer,String,Double> t1 = makeTuple(5, "blah", 1.2);
		Tuple3<Integer,String,Double> t2 = makeTuple(6, "blah", 1.2);
		Tuple3<Integer,String,Double> t3 = makeTuple(5, "apple", 1.2);
		Tuple3<Integer,String,Double> t4 = makeTuple(5, "blah", 1.1);
		Tuple3<Integer,String,Double> t5 = makeTuple(5, "blah", 1.2);

		assertEquals(0, t1.compareTo(t1));
		assertEquals(-1, t1.compareTo(t2));
		assertEquals(1, t2.compareTo(t1));
		assertEquals(1, t1.compareTo(t3));
		assertEquals(1, t2.compareTo(t3));
		assertEquals(-1, t3.compareTo(t4));
		assertEquals(1, t1.compareTo(t4));
		assertEquals(0, t1.compareTo(t5));
	}
	

	@Test(expected=ClassCastException.class)
	public void testComparable3bad() throws IOException, ClassNotFoundException {
		class NotComparable {}
		Tuple3<Integer,String,NotComparable> t1 = makeTuple(5, "blah", new NotComparable());
		Tuple3<Integer,String,NotComparable> t2 = makeTuple(5, "blah", new NotComparable());

		assertEquals(0, t1.compareTo(t2));
	}
	
	@Test
	public void testSerialize3() throws IOException, ClassNotFoundException {
		Tuple3<Integer,String,Double> t1 = makeTuple(5, "blah", 1.2);
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		ObjectOutputStream oout = new ObjectOutputStream(bout);
		oout.writeObject(t1);
		oout.close();
		ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
		ObjectInputStream oin = new ObjectInputStream(bin);
		Tuple3<Integer,String,Double> t2 = (Tuple3<Integer,String,Double>)oin.readObject();
		assertEquals(t1, t2);
	}

	@Test(expected=java.io.NotSerializableException.class)
	public void testSerialize3bad() throws IOException, ClassNotFoundException {
		class CantSerialize {}
		Tuple3<Integer,String,CantSerialize> t1 = makeTuple(5, "blah", new CantSerialize());
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		ObjectOutputStream oout = new ObjectOutputStream(bout);
		oout.writeObject(t1);
		oout.close();
		ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
		ObjectInputStream oin = new ObjectInputStream(bin);
		Tuple3<Integer,String,CantSerialize> t2 = 
				(Tuple3<Integer,String,CantSerialize>)oin.readObject();
		assertEquals(t1, t2);
	}
	
	@Test
	public void testHashCode() {
		Set<Tuple> tuples = new HashSet<>();
		for(int i=0; i<(int)(Math.random()*1000); i++) 
			tuples.add(makeTuple(1, 2, 3));
		for(int i=0; i<(int)(Math.random()*1000); i++) 
			tuples.add(makeTuple(1, 23, 4, 3));
		for(int i=0; i<(int)(Math.random()*1000); i++) 
			tuples.add(makeTuple(1, 1));
		for(int i=0; i<(int)(Math.random()*1000); i++) 
			tuples.add(makeTuple("hi", "there"));
		for(int i=0; i<(int)(Math.random()*1000); i++) 
			tuples.add(makeTuple(1, "2"));
		assertEquals(5, tuples.size());
		
		Set<Integer> codes = new HashSet<>();
		for(Tuple t : tuples)
			codes.add(t.hashCode());
		
		assertTrue(codes.size() > 1); //it's possible some codes will overlap
		
	}

}
