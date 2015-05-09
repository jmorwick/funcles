package info.kendall_morwick.relation;

import java.util.function.Predicate;

@FunctionalInterface
public interface Relation<T> extends Predicate<T> {
	
}
