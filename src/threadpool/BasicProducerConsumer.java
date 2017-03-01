package threadpool;
import java.util.Scanner;

public class BasicProducerConsumer {

	public void produce() throws InterruptedException {
		synchronized (this) {
			System.out.println("P: Producing ...");
			wait();
			System.out.println("P: Resumed");
		}
	}

	public void consume() throws InterruptedException {
		Scanner sc = new Scanner(System.in);
		Thread.sleep(2000);
		synchronized (this) {
			System.out.println("C: Press any key...");
			sc.nextLine();
			System.out.println("Key detected!");
			notify();
		}
	}

	public static void main(String[] args) {
		BasicProducerConsumer pcObj = new BasicProducerConsumer();
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				try {
					pcObj.produce();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {
			public void run() {
				try {
					pcObj.consume();
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
