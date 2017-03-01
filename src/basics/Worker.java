package basics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Worker {

	private Random random = new Random();
	private List<Integer> list1 = new ArrayList<Integer>();
	private List<Integer> list2 = new ArrayList<Integer>();

	private Object lockKey1 = new Object();
	private Object lockKey2 = new Object();

	private void stage1() {
		synchronized (lockKey1) {
			list1.add(random.nextInt());
		}
	}

	private void stage2() {
		synchronized (lockKey2) {
			list2.add(random.nextInt());
		}
	}
	
	public void work(){
		for (int i = 0; i < 100000; i++) {			
			stage1();
			stage2();
		}
	}

	
	public void start() {
		System.out.println("Started ...");
		
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				work();
			}
		});

		Thread t2 = new Thread(new Runnable() {
			public void run() {
				work();
			}
		});

		long start = System.currentTimeMillis();
		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		
		System.out.println("List 1 size: "+list1.size()+"\nList 2 size: "+list2.size());
		System.out.println("Time took :"+ (end-start));
	}
}
