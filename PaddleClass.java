import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
/**
 * @author Ferdinand Yeke
 *
 */

/*
 * This class builds the Paddle object for the Ping-Pong game for the players.
 * Players can move the paddle up and down. While extra players in the middle
 * may be able to move the paddle up and down as well, but this may not be in the 
 * final version. 
 */
@SuppressWarnings("serial")
public class PaddleClass extends Rectangle implements PaddleInterface {

	int id;
	int y_Velocity;
	int speed= 10;
	int P_Width;
	int P_Height;

	public PaddleClass(int x, int y, int P_Width, int P_Height, int id)
	{
		super(x, y, P_Width, P_Height);
		this.id = id;
	}
	
	/*
	 * Makes a method to chance Paddle's info.
	 */
	public void Paddle(int x, int y, int P_Width, int P_Height, int id) {
		// TODO Auto-generated method stub
	this.x = x;
	this.y = y;
	this.P_Width = P_Width;
	this.P_Height = P_Height;
	this.id = id;
	}
	
	
	
	/*
	@Override
	public void Paddle(int x, int y, int P_Width, int P_Height, int id) {
		// TODO Auto-generated method stub
		super.setRect(x, y, P_Width, P_Height);
		this.id = id;
	}
	*/
	@Override
	/*
	 * This reads the key action of the pad players, where it maps the keys to
	 * the Y direction movements. If the pad player presses a key that is for going
	 * up, the y direction is sent to the negative direction. If the pad player
	 * presses a key that is for going down, the y direction is sent to the positive
	 * direction.
	 */
	public void KeyActionPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		//Switch case starts here.
		switch(id)
		{
		//Case 1 starts here
		case (1):
		{
			if(e.getKeyCode()==KeyEvent.VK_W)
				{
					setYDirection(-speed);
					move();
				}
			if(e.getKeyCode()==KeyEvent.VK_S)
				{
					setYDirection(speed);
					move();
				}
			}break;
		//Case 1 ends here.
			
		//Case 2 starts here.	
		case (2):
		{
			if(e.getKeyCode()==KeyEvent.VK_UP)
				{
					setYDirection(-speed);
					move();
				}
			if(e.getKeyCode()==KeyEvent.VK_DOWN)
				{
					setYDirection(speed);
					move();
				}
			}break;
		//Case 2 ends here.
		
		}//Switch case ends here.
		
	}//KeyActionPressed method ends here.

	@Override
	/*
	 * This determines the behavior of the pad the players get to control once they
	 * release the key. In this, once you release the key, you expect the movement
	 * of the pad to completely stop, in which this method is doing.
	 */
	
	/*
	 * Also, Player 1 may need to use an external keyboard.
	 */
	public void KeyActionReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
		//Switch case starts here.
		switch(id)
		{
		//Case 1 starts here.
		case (1):
		{
				if(e.getKeyCode()==KeyEvent.VK_W)
				{
					setYDirection(0);
				}
				if(e.getKeyCode()==KeyEvent.VK_S)
				{
					setYDirection(0);
				}break;
		}//Case 1 ends here
		
		//Case 2 starts here.
		case (2):
		{
				if(e.getKeyCode()==KeyEvent.VK_UP)
				{
					setYDirection(0);
				}
				if(e.getKeyCode()==KeyEvent.VK_DOWN)
				{
					setYDirection(0);
				}break;
			}//Case 2 ends here.
		}//Switch case ends here.
	}//Key action released method ends here.
	
	/*
	 * @param y_Direction sets the y direction for the ball
	 */
	
	public void setYDirection(int y_Direction)
	{
		y_Velocity= y_Direction;
	}//setYDirection end here.
	
	/*
	 * A move method, where the paddle move based on velocity.
	 */
	public void move()
	{
		y+= y_Velocity;
	}
	/*
	 * @param p draws the graphics of the paddle and sets the color, while making up the rest of the rectangle body.
	 */
	public void draw (Graphics P)
	{
		if(id==1)
		{
			P.setColor(Color.blue);
		}
		
		else
		{
			P.setColor(Color.red);
		}
		P.fillRect(x, y, width, height);
	}//draw method ends here.

/*
 * There will be a method here where you can text one player to another in different instances to start another game.
 */

	
}//PaddleClass ends here.
