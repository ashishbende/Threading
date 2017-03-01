package semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// usually used to control access to any resource
public class Client {
	public static void main(String[] args) throws InterruptedException {

		ExecutorService executor = Executors.newCachedThreadPool();

		for (int i = 0; i < 200; i++) {
			executor.submit(new Runnable() {

				public void run() {
					Connection.getConnection().connect();

				}
			});
		}

		executor.shutdown();
		executor.awaitTermination(10000, TimeUnit.SECONDS);

	}
}
