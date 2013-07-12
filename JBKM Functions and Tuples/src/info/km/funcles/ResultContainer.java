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


/** This class is an interface to running instances of functions initiated
 * wtih the Function.r method.  Results are returned via instances of this
 * class, and communication with the running instance can be propagated
 * through instances of this class (for example, 'kill').

  @author Joseph Kendall-Morwick <jmorwick@indiana.edu>
  @version 0.1
 */
public class ResultContainer<R> {
    private R result = null;
    private boolean complete = false;
    private boolean pleaseDie = false;
    private long suggestedTime = -1;
    private final long startTime;
    private List<Object> pleaseNotifyUs = new ArrayList<Object>();

    public ResultContainer() {
        startTime = System.currentTimeMillis();
    }

    /** whether or not the function processing the result for this container
     * is still running.
     * @return
     */
    public synchronized boolean done() {
        return complete;
    }

    /** whether or not a result has been provided
     * 
     * @return
     */
    public synchronized boolean resultPresent() {
        return result != null;
    }

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
    public synchronized R getResult() { return result; }

/** this function should only be called by the background thread associated with
 * this object.  This should be called when the function is done processing.
 * Threads waiting on this object will be notified.
 * 
 * @param result
 */
    public synchronized void setResult(R result) {
        complete = true;
        this.result = result;
        notifyAll();
        for(Object o : pleaseNotifyUs) {
            synchronized(o) { o.notifyAll(); }
        }
    }

    /** Notify object 'o' when there is a result present
     * 
     * @param o
     */
    public void notifyWhenDone(Object o) {
        pleaseNotifyUs.add(o);
    }
}
