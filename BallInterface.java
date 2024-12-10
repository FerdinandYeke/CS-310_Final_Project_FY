
/**
 * @author Ferdinand Yeke
 *CS-310 Operating Systems
 *Bellarmine University
 */

/*
 * This class is the interface for the ball class. This interface
 * has methods such as Ball() (which sets up the ball object), Ball
 * Movm() (which sets up the ball movement, direction, and speed, &
 * BallRevertDirec() (which makes a method to where the ball movement
 * gets reversed with the BallMovm() method.
 */
public interface BallInterface {
	
	public void Ball(float x, float y, int width, int height, float
			speed);
	
	public void BallMovm();
	
	public void BallRevertDirec();

}//Interface ends here.
