package semaphore;

import java.util.concurrent.Semaphore;

// Singleton connection object
public class Connection {
	private static Connection connection = new Connection();
	private int numOfConnections = 0;
	// set number of permit & fairness : first come first serve
	private Semaphore sem = new Semaphore(10,true);
	
	public static Connection getConnection(){
		return connection;
	}
	
	public void connect(){
		try {
			sem.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			makeConnection();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			sem.release();
		}

	}
	public void makeConnection() throws InterruptedException{
		synchronized(this){
			System.out.println("Adding connection ...");
			numOfConnections++;
			System.out.println("Current connection : "+numOfConnections);
		}
		System.out.println("--- Connection Online ---");
		Thread.sleep(2000);
		System.out.println("--- Connection Offline ---");

		synchronized(this){
			System.out.println("Removing connection ...");
			numOfConnections--;
			System.out.println("Current connection : "+numOfConnections);
		}
		
		
	}
	
	
}
