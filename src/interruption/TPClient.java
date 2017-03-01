package interruption;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

// Thread Pool example.
// Two ways to interrupt Threadpool
// 1. future.cancel(Boolean); it sets the interrupt flag for threads
// executor.shutdown();

public class TPClient {
	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		System.out.println("Starting threadpool");
			Future<?> future = executor.submit(new Callable<Void>() {
				public Void call() {
					Random random = new Random();
					for (int i = 0; i < 1E8; i++) {
						if (Thread.currentThread().isInterrupted()) {
							System.out.println("Interruped! Resource too expesive.");
							break;
						}
						Math.sin(random.nextDouble());
					}
					return null;
				}
			});
		

		executor.shutdown();
		
		try {
			Thread.sleep(800);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		// or use executor.shutdownNow();
		future.cancel(true);
		// both method won't kill threads but set the interrupt flag.
		try {
			executor.awaitTermination(1000, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Ended!");
	}
}
