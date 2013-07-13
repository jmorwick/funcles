package info.km.funcles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import com.google.common.base.Predicate;

public class FunctionTimer {    
	private Map<Thread, Stack<Long>> startTimes = 
			new HashMap<Thread,Stack<Long>>();
	private long lastTime = 0;
	private long maxRunTime = 0;
	private long minRunTime = Long.MAX_VALUE;
	private long totalTime = 0;
	private long runs = 0;
	private List<Long> allTimes = new ArrayList<Long>();
	private boolean recordAllTimes;
	
	public FunctionTimer(boolean recordAllTimes) {
		this.recordAllTimes = recordAllTimes;
	}
	
	public FunctionTimer() {
		this.recordAllTimes = false;
	}

	/** returns the average time it last took to execute implementation, or 0 if
	 * implementation has never been called or timing has not been initiated.
	 *
	 * @return
	 */
	public long getAverageTime() { return runs == 0 ? 0 : totalTime / runs; }

	/** returns the time it last took to execute implementation, or 0 if
	 * implementation has never been called or timing has not been initiated.
	 *
	 * @return
	 */
	public long getLastTime() { return lastTime; }


	/** returns the maximum time it last took to execute implementation, or 0 if
	 * the function has never been called or timing has not been initiated.
	 *
	 * @return
	 */
	public long getMaxTime() { return maxRunTime; }


	/** returns the minimum time it last took to execute implementation, 
	 * or Long.MAX_VALUE if the function has never been called or timing 
	 * has not been initiated.
	 *
	 * @return
	 */
	public long getMinTime() { return maxRunTime; }



	/** returns a list of all run times 
	 *
	 * @return
	 */
	public List<Long> getAllTimes() { return allTimes; }


	/** record the time it takes to execute a function
	 * (including recursive calls) */
	public void start() {
		if(!startTimes.containsKey(Thread.currentThread()))
			startTimes.put(Thread.currentThread(), new Stack<Long>());
		startTimes.get(Thread.currentThread()).push(System.currentTimeMillis());
	}

	/** designate that a call to the function has been completed;
	 *  record the time elapsed
	 */
	public void stop() {
		long elapsedTime = System.currentTimeMillis() - 
				startTimes.get(Thread.currentThread()).pop();
		totalTime += elapsedTime;
		runs++;
		lastTime = elapsedTime;
		if(elapsedTime > maxRunTime)
			maxRunTime = elapsedTime;
		if(elapsedTime < minRunTime)
			minRunTime = elapsedTime;
		if(recordAllTimes)
			allTimes.add(elapsedTime);
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
