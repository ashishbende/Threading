package net.cnollege.threading.java;

/**
 * Created by mugen on 1/1/2017.
 */

class CounterRunnable implements Runnable{

    @Override
    public void run(){
        for (int i = 0; i <10 ; i++) {
            System.out.println(" Thread : "+i);
            try{
                Thread.sleep(200);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }   
    }
}
public class StartingThread {
    public static void main(String[] args) {
        Thread t1 = new Thread(new CounterRunnable());
        t1.start();
    }
}
