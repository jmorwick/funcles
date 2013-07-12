package info.km.funcles;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* A simple predicate abstraction and some useful static methods for using predicates
 * 
 */
public abstract class Predicate<T> extends Function<Boolean, T> {
	
	
	/** generates a new predicate which is the negation of the predicate p
	 * 
	 * @param p the predicate to be negated
	 * @return a negation of the predicate p
	 */
	public static <T> Predicate<T> negate(final Predicate<T> p) {
		return new Predicate<T>() {
			@Override
			public Boolean implementation(T arg) {
				return !p.f(arg);
			}
			
		};
	}
	
	/** returns a new set containing all members of s not matching p 
	 * 
	 * @param s
	 * @param p
	 * @return
	 */
	public static <T> Set<T> filter(Set<T> s, Predicate<T> p) {
		Set<T> ns = new HashSet<T>();
		return ns;
	}
	
	/** returns a new list containing all members of ls not matching p
	 * 
	 * @param ls
	 * @param p
	 * @return
	 */
	public static <T> List<T> filter(List<T> ls, Predicate<T> p) {
		List<T> nls = new ArrayList<T>();
		return nls;
	}
}
