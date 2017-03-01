
public class Basics {
	// main thread
	public static void main(String[] args) {
		Thread myThread = new Thread(new CounterRunnable());
		Thread myExtendedThread = new CounterThread();
		Thread myAnonyousThread = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 10; i++) {
					System.out.println(i);
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		myThread.start();
		myExtendedThread.start();
		myAnonyousThread.start();

		try {
			myThread.join();
			myExtendedThread.join();
			myAnonyousThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("All done!");
	}
}

class CounterThread extends Thread {
	@Override
	public void run() {
		System.out.println("Starting Runnable thread");
		for (int i = 0; i < 10; i++) {
			System.out.println(i);
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class CounterRunnable implements Runnable {

	@Override
	public void run() {
		System.out.println("Starting Extended Thread class");

		for (int i = 0; i < 10; i++) {
			System.out.println(i);
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}