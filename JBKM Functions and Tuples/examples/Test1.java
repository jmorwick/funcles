import java.util.function.BiPredicate;

import info.kendall_morwick.function.Function2;
import info.kendall_morwick.relation.Relation2;

import com.google.common.base.Function;

import static info.kendall_morwick.funcles.Funcles.apply;


public class Test1 {
	public static void main(String[] args) {
		Function<Integer,Boolean> f = x -> x > 5;
		System.out.println(f.apply(4));
		System.out.println(f.apply(5));
		System.out.println(f.apply(6));
		
		System.out.println("----------------");
		
		Function2<Integer,Integer,Boolean> greater = (x,y) -> x > y;
		System.out.println(greater.apply(5, 6));
		System.out.println(greater.apply(5, 5));
		System.out.println(greater.apply(6, 5));
		
		System.out.println("----------------");

		System.out.println(apply(greater, 5, 6));
		System.out.println(apply(greater, 5, 5));
		System.out.println(apply(greater, 6, 5));
		
		System.out.println("----------------");
		Relation2<Integer> greater2 = greater::apply;

		System.out.println(apply(greater2, 5, 6));
		System.out.println(apply(greater2, 5, 5));
		System.out.println(apply(greater2, 6, 5));
		
		com.google.common.base.Function<Integer,Integer> inc = x -> x + 1;
		java.util.function.Function<Integer,Integer> inc2 = inc::apply;

		System.out.println("----------------");
		BiPredicate<Integer,Integer> greater3 = greater::apply;

		System.out.println(greater3.test(5, 6));
		System.out.println(greater3.test(5, 5));
		System.out.println(greater3.test(6, 5));
		
		
	}
}
