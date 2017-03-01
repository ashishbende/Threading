// Every object in java has a intrinsic/monitor lock (mutex)
// Synchronized methods specifies to acquire the lock (behind the scene).

public class Synchronization {

	private int count = 0;

	private synchronized void increment() {
		count++;
	}

	public static void main(String[] args) {
		Synchronization syncDemo = new Synchronization();
		syncDemo.doWork();
	}

	public void doWork() {
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 1000; i++) {
					increment();
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 1000; i++) {
					increment();
				}
			}
		});

		t1.start();
		t2.start();
		try {
			t2.join();
			t1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Count value : " + count);

	}
}
