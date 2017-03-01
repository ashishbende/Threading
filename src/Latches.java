import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Follower implements Runnable{
	
	private CountDownLatch latch;
	private int id;
	Follower(CountDownLatch latch, int id){
		this.latch = latch;
		this.id = id;
	}
	public void run(){
		System.out.println("Started ["+id+"] ...");
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Thread ["+id+"] latch count : "+latch.getCount());
		latch.countDown();
	}
}
public class Latches {
	
	public static void main(String[] args) {		
		CountDownLatch latch = new CountDownLatch(3);
		ExecutorService executor = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 20; i++) {
			executor.submit(new Follower(latch,i));
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		
		executor.shutdown();
		try {
			executor.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Completed!");
	}
	
	
}
