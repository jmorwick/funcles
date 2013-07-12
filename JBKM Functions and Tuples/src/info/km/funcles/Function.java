/* Copyright 2011-2013 Joseph Kendall-Morwick

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

package info.km.funcles;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/* An abstraction for first-class functions which includes several useful methods

  @author Joseph Kendall-Morwick <jmorwick@indiana.edu>
  @version 0.1
 */
public abstract class Function<R, T> {

    //-------------------  Basic info ----------------------------------------
    private String name;

    /** returns the name for this function.  If none was set, the name
     * of the method which called this function is used.
     *
     * @return
     */
    public String getName() {
        if(name != null) return name;

        //get name of calling method if no name is set
        StackTraceElement[] st = new Exception().getStackTrace();
        if(st[2].getMethodName().equals("c"))
            name = st[3].getClassName()+"."+st[3].getMethodName();
        else name = st[2].getClassName()+"."+st[2].getMethodName();

        return name;
    }

    /** sets a name for this function
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }


    //-------------------  Basic implementation ------------------------------
    private R lastResult = null;

    /** this method is the implementation of the function.  The function
     * should be called using the method 'f'.
     */
    public abstract R implementation(T args);

    /** executes this function with no arguments */
    public final R f() { return f(null); }

    /** executes this function */
    public final R f(T args) {
        if(cache != null) {
            if(cache.contains(args)) {
                return cache.get(args);
            }
            params.push(args);
        }
        if(timeFunction) startTimes.push(System.currentTimeMillis());
        R ret =  implementation(args);
        if(startTimes.size()>0) {
            long runTime = System.currentTimeMillis() - startTimes.pop();
            if(runTime > maxRunTime) maxRunTime = runTime;
            totalTime += runTime;
            lastTime = runTime;
            runs++;
        }
        if(params.size()>0) {
            cache.set(params.pop(), ret);
        }
        lastResult = ret;
        return ret;
    }


    /** returns the last result computed by this function by the 'f' method.
     *
     * @return
     */
    public final R getLastResult() { return lastResult; }


    /** returns the argument which maximizes this function
     *
     * @param inputs all arguments to be evaluated with this function
     * @return the argument from 'inputs' maximizing this function
     */
    public T argmax(T ... inputs) {
        List<T> ls = new ArrayList<T>();
        for(T i : inputs) ls.add(i);
        return argmaxC(ls);
    }

    /** returns the argument which maximizes this function
     *
     * @param inputs all arguments to be evaluated with this function
     * @return the argument from 'inputs' maximizing this function
     */
    private T argmaxC(Collection<T> inputs) {
        Comparable max = null;
        T maxarg = null;
        try { Function<Comparable,T> nf = (Function<Comparable,T>)this;
            for(T input : inputs) {
                Comparable cur = nf.f(input);
                if(max == null || cur.compareTo(max) > 0) {
                    max = cur;
                    maxarg = input;
                }
            }
            return maxarg;
        } catch(ClassCastException e) {
            throw new RuntimeException("Argmax called on a funnction without comparable output");
        }

    }



    
    //------------------------------  Timing --------------------------------
    private Stack<Long> startTimes = new Stack<Long>();
    private boolean timeFunction = true;
    private long lastTime = 0;
    private long maxRunTime = 0;
    private long totalTime = 0;
    private long runs = 0;

    /** returns the time it last took to execute implementation, or 0 if
     * implementation has never been called or timing has not been initiated.
     *
     * @return
     */
    public long getLastTime() { return lastTime; }

    /** returns the maximum time it last took to execute implementation, or 0 if
     * implementation has never been called or timing has not been initiated.
     *
     * @return
     */
    public long getMaxTime() { return maxRunTime; }


    /** returns the average time it last took to execute implementation, or 0 if
     * implementation has never been called or timing has not been initiated.
     *
     * @return
     */
    public long getAverageTime() { return runs == 0 ? 0 : totalTime / runs; }

    
    /** remove cached results and do not cache future results
     * 
     */
    public void stopCaching() {
        cache = null;
    }

    /** record the time it takes to execute implementation
     * (including recursive calls) */
    public void startTiming() {
        timeFunction = true;
    }

    /** no longer collect timing statistics
     * 
     */
    public void stopTiming() {
        timeFunction = false;
        startTimes.clear();
    }




    //------------------------------   Caching ---------------------------------
    private Stack<T> params = new Stack<T>();
    private Cache<T,R> cache;


/**
 * cache previous results with the same set of input values, and don't
 * invoke implementation.  By default, the cache is a least-recently-used
 * cache.

 * @param cacheSize maximum number of results to store.
 */
    public void startCaching(int cacheSize) {
        cache = new Cache<T,R>(cacheSize);
    }

    /** cache previous results with the same set of input values, and don't
     * invoke implementation.  Cache size is unlimited.
     */
    public void startCaching() {
        cache = new Cache<T,R>(0);
    }

    /** remove a tuple of arguments from the cache
     *
     * @param args the tuple of arguments to be removed from the cache
     */
    private void unCache(T args) {
        if(cache != null) {
            cache.remove(args);
        }
    }

    /** remove all stored results from the cache
     * 
     */
    public void clearCache() {
        if(cache != null) cache.clear();
    }


    //--------------------- Multithreading --------------------------------------
    private Map<ResultContainer,Thread> runningInstances = new HashMap<ResultContainer, Thread>();


    /** executes the function on a seperate thread */
    public ResultContainer<R> r(final T p1,
            Object ... pleaseNotifyUs) {
        final ResultContainer<R> r = new ResultContainer<R>();
        for(Object o : pleaseNotifyUs)
            r.notifyWhenDone(o);
        final Function self = this;
        Thread t = new Thread() {
            @Override
            public void run() {
                    r.setResult(f(p1));
            }
        };
        t.start();
        synchronized(this) {runningInstances.put(r, t);}
        return r;
    }


    /** This method can be called within a function's body to retrieve the
     * result container it will be using.  This function returns null unless
     * the 'r' function was called, which starts a seperate thread and creates
     * a result container.
     *
     * The result container can be used by the function to determine if the
     * function has been asked to stop processing.  This is to avoid using
     * the deprecated 'stop' methods of the thread class.  If the programmer
     * wishes to be able to kill threads for functions, it must periodically
     * check the result container provided by this method to see if a kill
     * signal has been sent, or if it has exausted the time allocated to it.
     *
     * If this method is called from anything but a running instance of a
     * function, null will be returned.
     * 
     * @return The result container associated with the running instance of this function
     */
    protected synchronized ResultContainer getMyResultContainer() {
    	for(Map.Entry<ResultContainer, Thread> e : runningInstances.entrySet()) {
    		if(e.getValue().equals(Thread.currentThread()))
    			return e.getKey();
    	}
        return null;
    }

    /** returns a set of all currently running threads for this function 
     * (initiated with the 'r' method).
     * 
     * @return
     */
    public synchronized Set<Thread> getRunningThreads() {
        for(ResultContainer r : //copy to avoid concurrent modification errors
        	copySet(runningInstances.keySet())) {
            if(!runningInstances.get(r).isAlive()) 
            	runningInstances.remove(r);
        }
        return copySet(runningInstances.values());
    }

    /** returns the currently running thread for the associated result container,
     * if any is currently running.
     * @param r
     * @return
     */
    public synchronized Thread getThread(ResultContainer r) {
        for(ResultContainer r2 : copySet(runningInstances.keySet()))
            if(!runningInstances.get(r2).isAlive()) runningInstances.remove(r2);
        return runningInstances.get(r);
    }
/** provides a set of result containers for all currently running instances of
 * this function (initiated with the 'r' method).
 *
 * @return
 */
    public synchronized Set<ResultContainer> getResultContainers() {
        for(ResultContainer r : copySet(runningInstances.keySet()))
            if(!runningInstances.get(r).isAlive()) runningInstances.remove(r);
        return copySet(runningInstances.keySet());
    }
    
    
    /** copy a hash set */
    private static <V> Set<V> copySet(Collection<V> s) {
    	return new HashSet<V>(s);
    }
}
