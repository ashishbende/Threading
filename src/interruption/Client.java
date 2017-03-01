package interruption;

import java.util.Random;

// Single Thread example
// interrupt method sets a flag in the thread.
// test interrupted : Thread.currentThread().isInterrupted()
public class Client {

	public static void main(String[] args) {
		System.out.println("Starting ...");
		Thread expensiveOp = new Thread(new Runnable() {
			public void run() {
				Random random = new Random();
				for (int i = 0; i < 1E8; i++) {
					if (Thread.currentThread().isInterrupted()) {
						System.out.println("Interruped! Resource too expesive.");
						break;
					}
					Math.sin(random.nextDouble());
				}
			}
		});

		expensiveOp.start();
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		expensiveOp.interrupt();

		try {
			expensiveOp.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Ended ...");
	}
}
