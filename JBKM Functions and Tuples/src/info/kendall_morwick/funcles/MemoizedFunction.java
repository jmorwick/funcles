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

import com.google.common.base.Function;
import com.google.common.cache.LoadingCache;

public interface MemoizedFunction<F,T> extends Function<F,T> {
	
	/** returns the cache associated with this memoized function */
	public LoadingCache<F, T> getCache();

	/** returns the function that is being memoized */
	public Function<F,T> getOriginalFunction();
}
