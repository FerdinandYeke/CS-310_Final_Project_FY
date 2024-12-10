import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 * @author Ferdinand Yeke
 *CS-310 Operating Systems
 *Bellarmine University
 *
 *
 *Program Source: https://projectgurukul.org/java-pong-game/#:~:text=Steps%20to%20Create%20a%20Pong%20Game%20in%20Java,Creating%20Paddles%204%20Creating%20Ball%205%20Creating%20Scoreboard
 */

/*
 * This class creates the ball, while also giving it its characteristics like color, speed, and dimension.
 */
@SuppressWarnings("serial")
public class BallClass extends Rectangle implements BallInterface{
	Random random = new Random();
	//Start speed is the initial speed of the ball
	 float Start_Speed;
	//speed_y is the speed of the ball of the y direction
	float speed_y;
	//speed_x is the speed of the ball of the x direction
	float speed_x;
	 float speed;
	@Override
	/*
	 * This method makes the ball object with a set width, height, speed, and x
	 * y coordinates using the super set rectangle method.
	 */
	public void Ball(float x, float y, int width, int height, float speed) {
		this.x = (int) x;
		this.y = (int) y;
		this.width = width;
		this.height = height;
		this.speed = speed;
	}
	
	/*
	 * This constructor sets the ping pong ball's characteristics.
	 */
	public BallClass(float x, float y, int width, int height, float speed)
	{
		super.setRect(x, y, width, height);
		// TODO Auto-generated method stub
		
		Start_Speed= speed;
		int randXdirection = random.nextInt(2);
		if(randXdirection == 0)
			{
			 randXdirection--;
			 setXDirection(randXdirection*Start_Speed);
			}
		
		int randYdirection = random.nextInt(2);
		if(randYdirection == 0)
			{
			 randYdirection--;
			 setYDirection(randXdirection*Start_Speed);
			}
	}//Preferred BallClass constructor ends here.


	/*
	 * This method has the ball's vertical speed being set to some randX number.
	 */
	/*
	 * @param randX is the set speed of speed_x.
	 */
	public void setXDirection(float randX)
	{
		speed_x = randX;
	}//setXDirection ends here.
	
	/*
	 * This method has the ball's horizontal speed being set to some randY number.
	 */
	/*
	 * @param randY is the set speed of speed_y.
	 */
	public void setYDirection(float randY)
	{
		speed_y= randY;
	}//setYDirection method ends here.
	
	/*
	 * @return speed_y returns the y direction of the ball.
	 */
	public float getYDirection()
	{
		return speed_y;
	}//getYDirection method ends here.
	
	/*
	 * @return speed_x returns the x direction of the ball.
	 */
	public float getXDirection()
	{
		return speed_x;
	}//getXDirection method ends here.

	@Override
	/*
	 * This method sets the ball movement, where x is equal to itself plus the
	 * randomized speed of x, and y is equal to itself plus the randomized speed
	 * of y.
	 */
	public void BallMovm() {
		// TODO Auto-generated method stub
		x += speed_x;
		y += speed_y;
	}//BallMovm method ends here.
	
	/*
	 * @param B makes up the ping pong ball, where it sets the color to black, and makes up the rest of the ball, its height and width.
	 */
	public void draw(Graphics B)
	{
		B.setColor(Color.black);
		B.fillOval(x, y, height, width);
		
	}//draw method ends here.
	
	/*
	 * setColor method changes color of the ball.
	 */
	public void setColor()
	{
		Graphics B = null;
		B.setColor(Color.GREEN);
		B.fillOval(x, y, width, height);
	}//setColor method ends here.
	
	/*
	 * @param speed sets the speed of the ball.
	 */
	public void ballSpeed(float speed)
	{
		speed = speed_x++; speed_y++;
	}//ballSpeed method ends here.

	@Override
	public void BallRevertDirec() {
		// TODO Auto-generated method stub
		
	}

}//BallClass ends here.
