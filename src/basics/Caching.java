package basics;
import java.util.Scanner;

class Processor extends Thread {
	// prevent it from caching
	private volatile boolean running = true;

	public void run() {
		while (running) {
			System.out.println("Hello");
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}	

	public void shutDown() {
		running = false;
	}
}

public class Caching {
	public static void main(String[] args) {
		Processor newThread = new Processor();
		newThread.start();
		Scanner sc = new Scanner(System.in);
		System.out.println("Press any key to stop...");
		sc.nextLine();
		newThread.shutDown();
	}
}
