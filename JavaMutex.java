import java.util.concurrent.Semaphore;

/**
 * @author Ferdinand Yeke
 *CS-310 Operating Systems
 *Bellarmine University
 */

/*
 * This class is a child of GenSequence class, where it is a class
 * that constantly increments its value and returns it. This class
 * also has a method that makes an Semaphore instance, names it a mutex,
 * and then it gets into try-catch blocks, where the getNextSequence
 * method tries to aquire a mutex and then returns the super getNextGenSequence.
 * If it fails to do so, an Interruption exception kicks in and prints out a 
 * faulty mutex acquire operation. Whether it succedes or not, it will release 
 * the mutex, and returns the getNextGenSequence().
 */

/*
 * Source: https://www.baeldung.com/java-mutex#synch
 */
public class JavaMutex extends GenSequence {
	
	private Semaphore mutex = new Semaphore(1);
	
	public int getNextSequence()
	{
		/*
		synchronized(mutex)
		{
			return super.getNextGenSequence();
		}
		*/
		try {
			mutex.acquire();
			return super.getNextGenSequence();
		}
		
		catch(InterruptedException e)
		{
			System.out.println("Faulty mutex recieve operation!");
		}
		finally
		{
			mutex.release();
		}
		return super.getNextGenSequence();
	}

}//Java Mutex class ends here.
