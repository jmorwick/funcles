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
import java.util.List;

import com.google.common.base.Function;


/** This class is an interface to running instances of functions initiated
 * with the Funcles.parallelize method.  Results are returned via instances of this
 * class, and communication with the running instance can be propagated
 * through instances of this class (for example, 'kill').  This class is also 
 * the thread of execution for each instance of the running function.

  @author Joseph Kendall-Morwick <jmorwick@indiana.edu>
  @version 1.0.0
 */
public class ProcessingThread<F,T> extends Thread {
    private T result = null;
    private boolean pleaseDie = false;
    private long suggestedTime = -1;
    private final long startTime;
    private long stopTime = -1;
	private Function<F, T> f;
	private F input;

    public ProcessingThread(Function<F,T> f, F input) {
        startTime = System.currentTimeMillis();
        this.f = f;
        this.input = input;
        start();
    }
    
    public final void run() {
    	if(f instanceof JustInTimeAlgorithm) // supply monitor if it can be used
    		result = (((JustInTimeAlgorithm<F,T>)f).apply(input, this));
    	else result = (f.apply(input)); //otherwise just start the function up
        notifyAll();
        stopTime = System.currentTimeMillis();
    }
    
    /** milliseconds it took this function to execute 
     * if the function hasn't finished processing, the elapsed time is returned
     * @return
     */
    public long getRunTime() {
    	if(this.isAlive()) return System.currentTimeMillis() - startTime;
    	else return stopTime - startTime;
    }
    
    /** time in milliseconds when this thread was started */
    public long getStartTime() { return startTime; }
    
    /** time in milliseconds when this thread ended 
     * -1 is returned if it still running 
     */
    public long getStopTime() { return stopTime; }

    /** asks the function processing the result for this container to stop
     * processing.  
     */
    public void kill() {
        pleaseDie = true;
    }

    /** provides the function processing the result for this container with 
     * a suggested amount of processing time -- this is intended to be used 
     * by any-time algorithms.  
     * 
     * @param time
     */
    public void suggestProcessingTime(long time) {
        suggestedTime = time;
    }

    /** the amount of time remaining from the processing time suggestion.  If
     * no suggestion was made, -1 is returned.  
     * @return
     */
    public long getRemainingSuggestedTime() {
        if(suggestedTime > -1) {
            if(System.currentTimeMillis() - startTime < suggestedTime) {
                return suggestedTime - System.currentTimeMillis() + startTime;
            }
            return 0;
        }
        return -1;
    }

    /** returns true if thread currently  processing a function intended to
     * deliver a result via this container was asked to stop by an outside
     * source.  
     *
     * @return
     */
    public boolean askedToDie() { 
        return pleaseDie || getRemainingSuggestedTime() == 0;
    }


    /** the result of the function associated with this container
     * 
     * @return
     */
    public synchronized T getResult() { return result; }

}
