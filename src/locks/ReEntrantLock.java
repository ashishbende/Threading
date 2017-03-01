package locks;
import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// instead of using synchronized block
// uses await & signal(Methods of Conditions class) instead of wait and notify

public class ReEntrantLock {

	private int count = 0;
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	private void increment() {
		for (int i = 0; i < 1000; i++) {
			count++;
		}
	}

	public void stage1() throws InterruptedException {
		System.out.println("Executing Stage 1 ...");
		lock.lock();
		condition.await();
		try {
			System.out.println("Stage1 : resumed");
			increment();
		} finally {
			lock.unlock();
		}

	}

	public void stage2() throws InterruptedException {
		Thread.sleep(200);
		lock.lock();
		System.out.println("Executing Stage 2 ...");
		System.out.println("Enter any key to continue : ");
		new Scanner(System.in).nextLine();
		System.out.println("Key received!");
		condition.signal();
		try {
			increment();
		} finally {
			lock.unlock();
		}
	}

	public void showResult() {
		System.out.println("Count is " + count);
	}

	public static void main(String[] args) {
		ReEntrantLock demo = new ReEntrantLock();
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				try {
					demo.stage1();
					System.out.println("Thread 1 : Done counting!");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {
			public void run() {
				try {
					demo.stage2();
					System.out.println("Thread 2 : Done counting!");

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
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
			e.printStackTrace();
		}

		demo.showResult();

	}

}
