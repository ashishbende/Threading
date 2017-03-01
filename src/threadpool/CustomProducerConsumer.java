package threadpool;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class CustomProducerConsumer {

	Queue<Integer> buffer = new LinkedList<Integer>();
	private Object lock = new Object();
	private static final int LIMIT = 10;
	private Random randomGen = new Random();
	private int value = 0;

	public void produce() throws InterruptedException {
			while (true) {
				synchronized (lock) {
				if (buffer.size() >= LIMIT) {
					lock.wait();
				}
				value = randomGen.nextInt(10);
				System.out.println("P:adding [" + value + "]" + "Queue size: [" + buffer.size() + "]");
				buffer.add(value);
				lock.notify();
			}
		}
	}

	public void consume() throws InterruptedException {
			while (true) {
			synchronized (lock) {
				if (buffer.size() <= 0) {
					lock.wait();
				}
				value = buffer.poll();
				System.out.println("C:reading [" + value + "]" + "Queue size: [" + buffer.size() + "]");
				lock.notify();
			}
			Thread.sleep(randomGen.nextInt(10)*100);
		}
		
	}

	public static void main(String[] args) {

		CustomProducerConsumer cPC = new CustomProducerConsumer();
		Thread p = new Thread(new Runnable() {
			public void run(){
				try {
					cPC.produce();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		Thread c = new Thread(new Runnable() {
			public void run(){
				try {
					cPC.consume();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		p.start();
		c.start();
		
		try {
			p.join();
			c.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
