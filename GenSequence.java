/**
 * 
 */

/**
 * @author ferdi
 *CS-310 Operating Systems
 *Bellarmine University
 */

/*
 * This class GenSequence is a class that is the building block
 * for the JavaMutex class, in which this class increments a value
 * once the getNextGenSequence method is used.
 */
public class GenSequence {
	
private int currentvalue= 0;

public int getNextGenSequence() {
	currentvalue+= 1;
	return currentvalue;
}

}
