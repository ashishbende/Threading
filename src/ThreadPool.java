import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Slaves implements Runnable {

	private int id;

	public Slaves(int id) {
		this.id = id;
	}

	@Override
	public void run() {
		System.out.println("Starting worker - " + id);
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Stopping worker - " + id);
	}

}

public class ThreadPool {
	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(4);
		for (int i = 0; i < 10; i++) {
			executor.submit(new Slaves(i));
		}
		
		executor.shutdown();
		try {
			executor.awaitTermination(100, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("All done! Threads stopped");
	}
}
