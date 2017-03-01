package semaphore;

import java.util.concurrent.Semaphore;

public class BasicDemo {
	public static void main(String[] args) throws InterruptedException {
		// number of permits
		Semaphore sem = new Semaphore(1);
		// reduces permit count
		System.out.println("[before] Available permits : " + sem.availablePermits());
		sem.acquire();
		System.out.println("[after] Available permits : " + sem.availablePermits());

		// restores permit acount
		sem.release();
		System.out.println("Available permits : " + sem.availablePermits());
	}
}
