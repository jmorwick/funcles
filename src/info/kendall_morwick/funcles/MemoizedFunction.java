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

import java.util.concurrent.ExecutionException;
import java.util.function.Function;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;


/** an interface with two additional functions to assist in managing 
 * an instance of a memoized function
 *
 * @author Joseph Kendall-Morwick <jmorwick@indiana.edu>
 * @version 2.1
 * @param <F> return type
 * @param <T> parameter type
 */
public interface MemoizedFunction<F,T> extends Function<F,T> {
	
	/** returns the cache associated with this memoized function */
	public LoadingCache<F, T> getCache();

	/** returns the function that is being memoized */
	public Function<F,T> getOriginalFunction();
	
	

	/** modifies a function to cache its results
	 * 
	 * @param f function to be memoized
	 * @return memoized function f
	 */
	public static <F,T> MemoizedFunction<F,T> memoize(final Function<F,T> f) {
		return memoize(f, CacheBuilder.newBuilder());
	}
	
	
	/** modifies a function to cache its results
	 * 
	 * @param f function to be memoized
	 * @param builder a cache builder which creates a cache for mapping inputs to outputs
	 * @return memozied function f
	 */
	public static <F,T> MemoizedFunction<F,T> memoize(final Function<F,T> f, 
				final CacheBuilder<? super F, ? super T> builder) {
		final LoadingCache<F,T> cache = builder.build(CacheLoader.from(f::apply));
		return new MemoizedFunction<F,T>() {
			
			public T apply(final F input) {
				try {
					return cache.get(input);
				} catch (ExecutionException e) {
					//Functions can't throw checked exceptions, so this never happens
					return null;
				}
			}

			@Override
			public LoadingCache<F, T> getCache() {
				return cache;
			}

			@Override
			public Function<F, T> getOriginalFunction() {
				return f;
			}
		};
	}

}
