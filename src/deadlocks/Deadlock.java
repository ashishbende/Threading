package deadlocks;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Deadlock {

	private Account checkingAcc = new Account();
	private Account savingAcc = new Account();
	private Random random = new Random();

	private Lock lockSavingAcc = new ReentrantLock();
	private Lock lockCheckingAcc = new ReentrantLock();

	public Deadlock() {
		savingAcc.withdraw(900);
	}
	
	private void acquireLocks(Lock savingAcc, Lock checkingAcc ) throws InterruptedException{
		while(true){
			
			boolean sLockAcquired = false;
			boolean cLockAcquired = false;
			
			try{
				sLockAcquired = savingAcc.tryLock();
				cLockAcquired = checkingAcc.tryLock();
			}finally{
				if(sLockAcquired && cLockAcquired){
					return;
				}
				if(sLockAcquired){
					savingAcc.unlock();
				}
				if(cLockAcquired){
					checkingAcc.unlock();
				}
			}
			// try to get the locks
			
			// lock not acquired
			Thread.sleep(1);
		}
	}

	public void firstThread() throws InterruptedException {
		/* dissimilar lock orders can cause deadlocks
		lockSavingAcc.lock();
		lockCheckingAcc.lock();*/
		
		acquireLocks(lockSavingAcc, lockCheckingAcc);
		try {
			Account.transfer(savingAcc, checkingAcc, random.nextInt(20));
		} finally {
			lockSavingAcc.unlock();
			lockCheckingAcc.unlock();
		}
	}

	public void secondThread() throws InterruptedException {
		// deadlock
		acquireLocks(lockSavingAcc, lockCheckingAcc);
		try {
			Account.transfer(checkingAcc, savingAcc, random.nextInt(20));
		} finally {
			lockSavingAcc.unlock();
			lockCheckingAcc.unlock();
		}
	}

	public void finished() {
		System.out.println("Checking Account balance: " + checkingAcc.getBalance());
		System.out.println("Saving Account balance: " + savingAcc.getBalance());
		System.out.println("Total balance : " + (checkingAcc.getBalance() + savingAcc.getBalance()));
	}

}
