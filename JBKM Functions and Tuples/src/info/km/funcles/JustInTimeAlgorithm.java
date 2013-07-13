package info.km.funcles;

import com.google.common.base.Function;

/** Implementing this interface designates that this function is able to 
 * use information stored in a ProcessingThread which provides running 
 * time, expected running time, and other signals to the algorithm.
 * 
 * @author jmorwick
 *
 * @param <F>
 * @param <T>
 */
public interface JustInTimeAlgorithm<F,T> extends Function<F,T> {
	
	/** start the function and provide a reference to the ProcessingThread it is running on
	 * 
	 * @param input
	 * @param pc
	 * @return
	 */
	public T apply(F input, ProcessingThread<F,T> pc);
}
