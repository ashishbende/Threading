package threadpool;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumer {
	// Threadsafe queue of size 10
	private static BlockingQueue<Integer> buffer = new ArrayBlockingQueue<Integer>(10);

	public static void produce() throws InterruptedException {
		Random randGen = new Random();
		while (true) {
			int taskNumber = randGen.nextInt(100);
			System.out.println("P : adding [" + taskNumber + "]" + "Queue size: " + buffer.size());
			buffer.put(taskNumber);
		}
	}

	public static void consume() throws InterruptedException {
		Random randGen = new Random();
		while (true) {
			Thread.sleep(100);
			if (randGen.nextInt(2) == 0) {
				Integer taskNumber = buffer.take();
				System.out.println("C: processing [" + taskNumber + "]" + "Queue size: " + buffer.size());
			}
		}

	}

	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				try {
					produce();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {
			public void run() {
				try {
					consume();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
