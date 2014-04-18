package info.km.funcles;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.google.common.base.Function;

public class FunctionTimingTest {
	
	public static int ERROR = 5; //maximum expected error in nanoseconds

	@Test
	public void testTimerOnlyLast() throws InterruptedException {
		//fail("Not yet implemented");
	}
	
	
	@Test
	public void testTimerBasic() throws InterruptedException {
		FunctionTimer t = new FunctionTimer(true);
		t.start();
		Thread.sleep(50);
		t.stop();
		long t1 = t.getLastTime();
		assertTrue(t1 >= 50 && t1 <= 50 + ERROR);
		assertEquals(t1, t.getMaxTime());
		assertEquals(t1, t.getMinTime());
		assertEquals(t1, t.getAverageTime());
		List<Long> times = new ArrayList<Long>();
		times.add(t1);
		assertEquals(times, t.getAllTimes());

		t.start();
		Thread.sleep(100);
		t.stop();
		long t2 = t.getLastTime();
		assertTrue(t2 >= 100 && t2 <= 100 + ERROR);
		assertEquals(t2, t.getMaxTime());
		assertEquals(t1, t.getMinTime());
		assertEquals((t1+t2)/2, t.getAverageTime());
		times.add(t2);
		assertEquals(times, t.getAllTimes());
	}
	
	@Test
	public void testTimerRecursive() throws InterruptedException {
		FunctionTimer t = new FunctionTimer(true);
		t.start();
		Thread.sleep(50);
		t.start();
		Thread.sleep(50);
		t.stop();
		long t1 = t.getLastTime();
		assertTrue(t1 >= 50 && t1 <= 50 + ERROR);
		assertEquals(t1, t.getMaxTime());
		assertEquals(t1, t.getMinTime());
		assertEquals(t1, t.getAverageTime());
		List<Long> times = new ArrayList<Long>();
		times.add(t1);
		assertEquals(times, t.getAllTimes());
		t.stop();
		long t2 = t.getLastTime();
		assertTrue(t2 >= 100 && t2 <= 100 + ERROR);
		assertEquals(t2, t.getMaxTime());
		assertEquals(t1, t.getMinTime());
		assertEquals((t1+t2)/2, t.getAverageTime());
		times.add(t2);
		assertEquals(times, t.getAllTimes());
	}

	@Test
	public void testTimerMultipleThreads() {
		final FunctionTimer t = new FunctionTimer(true);
		final Thread thread1 = new Thread() {

			@Override
			public void run() {
				t.start();
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				t.stop();
			}
			
		};
		Thread thread2 = new Thread() {

			@Override
			public void run() {
				t.start();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				t.stop();
			}
			
		};
		thread1.start();
		thread2.start();
		while(thread1.isAlive() || thread2.isAlive()) {
			//wait?
		}

		long t1 = t.getLastTime();
		assertTrue(t1 >= 100 && t1 <= 100 + ERROR);
		assertEquals(t1, t.getMaxTime());
		List<Long> times = t.getAllTimes();
		assertEquals(times.size(), 2);
		assertTrue(times.contains(t1));
		times.remove((Long)t1);
		long t2 = times.get(0);
		assertTrue(t2 >= 50 && t2 <= 50 + ERROR);
		assertEquals(t2, t.getMinTime());
		assertEquals((t1+t2)/2, t.getAverageTime());
	}

	@Test
	public void testTimerMultipleThreadsRecursive() {
		final FunctionTimer t = new FunctionTimer(true);
		final Thread thread1 = new Thread() {

			@Override
			public void run() {
				callRecursive(2, 100, t);
			}
			
		};
		Thread thread2 = new Thread() {

			@Override
			public void run() {
				callRecursive(4, 2000, t);
			}
			
		};
		thread1.start();
		thread2.start();
		while(thread1.isAlive() || thread2.isAlive()) {
			//wait?
		}
		long t1 = t.getLastTime();
		long tavg = t.getAverageTime();
		List<Long> times = t.getAllTimes();
		long ttot = 0;
		for(long time : times) ttot += time;
		assertEquals(tavg, ttot/6);
		assertTrue(t1 >= 3750 && t1 <= 3750 + ERROR);
		assertTrue(tavg >= 1116 && tavg <= 1117 + ERROR);
		assertEquals(times.size(), 6);
		
	}
	
	public void callRecursive(int depth, long time, FunctionTimer t) {
		if(depth == 0) return;
		t.start();
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		callRecursive(depth-1, time/2, t);
		t.stop();
	}

	@Test
	public void testTimedFunction() {
		FunctionTimer t = new FunctionTimer(true);
		Function<Object, Object> f = 
				Funcles.timerize(new Function<Object, Object>() {

			@Override
			public Object apply(Object arg0) {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
			
		}, t);
		
		f.apply(null);
		long t1 = t.getLastTime();
		assertTrue(t1 >= 50 && t1 <= 50 + ERROR);
		assertEquals(t1, t.getMaxTime());
		assertEquals(t1, t.getMinTime());
		assertEquals(t1, t.getAverageTime());
		List<Long> times = new ArrayList<Long>();
		times.add(t1);
		assertEquals(times, t.getAllTimes());
		f.apply(null);
		long t2 = t.getLastTime();
		assertTrue(t2 >= 50 && t2 <= 50 + ERROR);
		assertEquals((t1+t2)/2, t.getAverageTime());
		times.add(t2);
		assertEquals(times, t.getAllTimes());
		
	}

}
