package deadlocks;
// client app to run threads
public class DeadlockRunner {
	public static void main(String[] args) {

		System.out.println("--- Program started ---");
		Deadlock dl = new Deadlock();

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					dl.firstThread();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					dl.secondThread();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dl.finished();
		System.out.println("--- Program ended ---");
	}
}
