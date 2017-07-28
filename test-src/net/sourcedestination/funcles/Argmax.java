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

import net.sourcedestination.funcles.function.Function2;
import net.sourcedestination.funcles.tuple.Tuple;
import net.sourcedestination.funcles.tuple.Tuple2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static net.sourcedestination.funcles.tuple.Tuple.makeTuple;

/** example of using the funcles library to implement the argmax function
 */
public class Argmax {
    /** returns the argument from the collection which maximizes the function f
    *
    * @param inputs all arguments to be evaluated with this function
    * @return the argument from 'inputs' maximizing this function
    */
   public static <F,T extends Comparable<T>> F argmax(Function<F,T> f, 
   		Collection<F> inputs) {
   	return argmax(f, inputs.parallelStream());
   }
   
   /** returns the argument which maximizes this function
   *
   * @param inputs all arguments to be evaluated with this function
   * @return the argument from 'inputs' maximizing this function
   */
  @SafeVarargs
	public static <F, T extends Comparable<T>> F argmax(Function<F,T> f, 
  		F ... inputs) {
      return argmax(f, Arrays.stream(inputs));
   }
   

   /** returns the argument from the collection which maximizes the function f
    *
    * @param inputs all arguments to be evaluated with this function
    * @return the argument from 'inputs' maximizing this function
    */
   public static <F,T extends Comparable<T>> F argmax(Function<F,T> f, 
   		Stream<F> inputs) {
   	return inputs.map(x -> makeTuple(x, f.apply(x)))  // map to pairs of input/output for function
   			.max(comparing(Tuple2::_2))  // find the tuple with maximum output
            .get()._1();                 // retrieve the input for that maximum output
   }
   
   public static void main(String[] args) {
	   ArrayList<Integer> ls = new ArrayList<Integer>();
	   ls.add(1);
	   ls.add(2);
	   ls.add(3);
	   int max = argmax(x -> -x, 1, 2, 3);
	   Object m = argmax(x -> -x, 1, 2, 3);
	   System.out.println(max);
	   Function<Integer,Integer> f = x -> x;
	   System.out.println(argmax(f, 1, 2, 3));
	   System.out.println(argmax(f, ls.stream()));

	   Function2<Integer,Integer,Boolean> greater = (x, y) -> x > y;
	   ArrayList<Tuple2<Integer,Integer>> ls2 = new ArrayList<>();
	   ls2.add(Tuple.makeTuple(1, 2));
	   ls2.add(Tuple.makeTuple(5, 1));
	   ls2.add(Tuple.makeTuple(3, 4));
	   System.out.println(argmax(greater, ls2));
	   //System.out.println(argmax(x->x, ls.stream())); // error!
	   //System.out.println(argmax(x->x, 1, 2, 3));   // error!
   }

}
