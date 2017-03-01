package basics;

public class VolatileKeyword {
	public static void main(String[] args) {
		MyThread myThread = new MyThread();
		Thread t1 = new Thread(myThread);
		t1.start();
		try {
			Thread.sleep(800);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		myThread.setTerminated(true);
		try {
			t1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("That's it.. Done!");
	}
}

class MyThread implements Runnable {

	private volatile boolean isTerminated;

	@Override
	public void run() {
		int age = 0;
		while (!isTerminated) {
			System.out.println("I'm alive and .." + (age++) + " cycle old");
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void setTerminated(boolean terminated) {
		this.isTerminated = terminated;
	}

}
