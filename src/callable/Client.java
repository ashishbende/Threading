package callable;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Future;


// allows classes to return value from threads
public class Client {
	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		//	Future<?> future = executor.submit(new Callable<Void>(){

		Future<Integer> future = executor.submit(new Callable<Integer>(){
			public Integer call() throws IOException{
					Random random = new Random();
					int duration = random.nextInt(3000);
					if(duration>500){
						throw new IOException("Timout error: Closing connection");
					}
					System.out.println("Starting thread..");
					try {
						Thread.sleep(duration);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("Ending thread..");
				return duration;
			}
		});
		executor.shutdown();
		try {
			executor.awaitTermination(1000, TimeUnit.SECONDS);
			// returns a value from thread
			System.out.println("Value from thread  : "+future.get());
		} catch (InterruptedException | ExecutionException e) {
			IOException io = (IOException)e.getCause();
			System.out.println(io.getMessage());
		}
		

	}
	
	
}
