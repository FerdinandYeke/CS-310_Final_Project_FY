import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JPanel;

/**
 * 
 */

/**
 * @author Ferdinand Yeke
 *CS-310 Operating Systems
 *Bellarmine University
 *
 *
 *Program Source: https://projectgurukul.org/java-pong-game/#:~:text=Steps%20to%20Create%20a%20Pong%20Game%20in%20Java,Creating%20Paddles%204%20Creating%20Ball%205%20Creating%20Scoreboard
 */

//import java.awt.*;

/*
 * Welcome to the GamePanel Class. This class builds the "main world"
 * of ping-pong. First off there is the actual method of GamePanel, which
 * serves as the main building block of this class. The draw method()
 * makes a "line" between players (for now, there will be two players, but
 * more players is planned). 
 */

/*
 * (NOTE: The Ping-Pong game will also have a MULTI-threaded server,
 * where the client-server brings in multiple ping pong players into
 * the game.)
 */
public class GamePanel extends JPanel implements Runnable {
	
private static final int WIDTH= 1000;
private static final int HEIGHT= 555;
private static final Dimension dimen= new Dimension(WIDTH, HEIGHT);
private static final int BALL_DIAMETER =20;
private static final int P_WIDTH = 25;
private static final int P_HEIGHT = 100;
private Thread gameThread;
private Image image;
private Graphics graphics;
private Random random;
private PaddleClass p1;
private PaddleClass p2;
private BallClass ball;
private ScoreBoard scoreboard;
private int speed;

/*
 * GamePanel constructor starts here.
 */
public GamePanel(int speed)
{
	this.speed=speed;
	newPaddles();
	newBall(speed);
	scoreboard = new ScoreBoard (WIDTH, HEIGHT);
	this.setFocusable(true);
	this.addKeyListener(new AL());
	this.setPreferredSize(dimen);
	
	gameThread = new Thread (this);
	gameThread.start();
}//GamePanel constructor ends here.

/*
 * @param Graphics g draws all the elements of the game, including the
 * paddes, ping pong ball, and the scoreboard.
 */
public void draw(Graphics g)
{
	p1.draw(g);
	p2.draw(g);
	ball.draw(g);
	scoreboard.draw(g);
	
	//To smooth animation.
	Toolkit.getDefaultToolkit().sync();
}//Draw method ends here.


/*
 * The newBall() method creates a new ball from the ball class. More detail
 * of the ball class like the ball having specific behaviors such 
 * as the ball reversing direction once getting hit will be written in the
 * Ball class.
 */
public void newBall(int speed)
{
	random = new Random();
	ball = new BallClass((WIDTH/2)-(BALL_DIAMETER/2),random.nextInt(HEIGHT-
			BALL_DIAMETER),BALL_DIAMETER,BALL_DIAMETER,speed);
}//newBall method ends here.

/*
 * This is a method that makes new paddles from the Paddle class.
 */
public void newPaddles()
{	
	/*
	 * p1_Y is the Y dimension calculations for the first paddle.
	 */
	int p1_Y = (HEIGHT/2)-(P_HEIGHT/2);
	p1 = new PaddleClass(0,p1_Y,P_WIDTH,P_HEIGHT,1);
	
	/*
	 * p2_Y is the Y dimension calculations for the second paddle.
	 */
	int p2_Y = (HEIGHT/2)-(P_HEIGHT/2);
	p2 = new PaddleClass(WIDTH-P_WIDTH, p2_Y, P_WIDTH,P_HEIGHT,2);
}//newPaddles end here.

/*
 * This is a method that makes the paddles of each player move. 
 */
public void move()
{
	p1.move();
	p2.move();
	ball.BallMovm();
}//move method ends here.

/*
 * paint method starts here.
 */
public void paint (Graphics g)
{
	image = createImage(getWidth(), getHeight());
	graphics = image.getGraphics();
	draw(graphics);
	g.drawImage(image,0,0,this);
}//paint method ends here.


/*
 * This is a method that checks the collision between the ping-pong
 * ball and the players.
 */
public void checkCollision()
{
	if(ball.y <= 0)
	{
		ball.setYDirection(-ball.speed_y);
	}
	
	if(ball.y >= HEIGHT-BALL_DIAMETER)
	{
		ball.setYDirection(-ball.speed_y);
		
		/*
		 * This tries to make a random change of the ball reversing direction
		 */
		//ball.setXDirection(ball.speed+1);
		
	}
	
	/*
	 * if(ball.y <= HEIGHT+BALL_DIAMETER)
	{
		ball.setYDirection(ball.speed_y);
		
		This tries to make a random change of the ball reversing direction
		ball.setXDirection(ball.speed-1);
		ball.setXDirection(ball.speed-1);
	}
	
	 */
	
	/*
	 * If the ball hits the paddle of player 1, it bounces back from
	 * p1.
	 */
	if (ball.intersects(p1))
	{
		/*
		 * Calls the ball revert direction method once the ball hits p2.
		 */
		ballRevertDirection();
		ball.speed_x = Math.abs(ball.speed_x);
		
		ball.setXDirection(ball.speed_x);
		ball.setYDirection(ball.speed_y);
		
		//Increments the ball speed each time it gets hit by player 2.
		this.ball.speed_x++;
		//ball.BallMovm();
		
		/*
		 * Once the ball hits p1, if its x direction is less than
		 * 0 OR greater than 0, AND if its y direction is less than
		 * 0 OR greater than 0, then the X direction should be set 
		 * to the current direction plus 5, and the Y direction should 
		 * be set to the current direction plus 5, making the ball
		 * faster each time it hits p1.
		 */
		/*
		 * 
		 * EDIT: this could be used for the ball reversing method, 
		 * where there is a random chance the ball can go back to the
		 * player.
		if(ball.getXDirection()<=0 || ball.getXDirection() >= 0
			&& ball.getYDirection() <= 0 || ball.getYDirection() >= 0)
		{
			ball.setXDirection(ball.getXDirection()+5);
			ball.setYDirection(ball.getYDirection()+5);
		}
		*/
				
	}
	
	/*
	 * If the ball hits the paddle of player 2, it bounces back from
	 * p2.
	 */
	if (ball.intersects(p2))
	{
		/*
		 * Calls the ball revert direction method once the ball hits p2.
		 */
		ballRevertDirection();
		ball.speed_x = Math.abs(ball.speed_x);
		
		ball.setXDirection(-ball.speed_x);
		ball.setYDirection(ball.speed_y);
		
		//Increments the ball speed each time it gets hit by player 2.
		this.ball.speed_x++;
		//ball.BallMovm();
		
		//ball.ballSpeed(1);
	}
	

		
	if(p1.y <= 0)
	{
		p1.y=0;
	}
		
	
	if(p1.y >= (HEIGHT-P_HEIGHT))
	{
		p1.y = HEIGHT - P_HEIGHT;
	}
		
	if(p2.y <= 0)
	{
		p2.y=0;
	}

	if(p2.y >= (HEIGHT-P_HEIGHT))
	{
		p2.y = HEIGHT - P_HEIGHT;
	}
	
	if(ball.x <= 0)
	{
		scoreboard.Score_P2++;
		newPaddles();
		newBall (speed);
	}
	
	if(ball.x >= WIDTH-BALL_DIAMETER)
	{
		scoreboard.Score_P1++;
		newPaddles();
		newBall(speed);
	}
	
	/*
	 * Based on the logic presented in here and the ball class, the
	 * ball has movements dictated here and its class, but in the 
	 * ball class, there is a chance where its x and y movements will
	 * be set to 0, in which the ball does not move, so there are if
	 * statemets below that refresh the game if the ball speed is
	 * 0 on y and or x.
	 */
	if(ball.speed_y ==0 && ball.speed_x == 0)
	{
		newBall(speed);
	}
	
	if (ball.speed_x == 0)
	{
		newBall(speed);
	}
	
	if (ball.speed_y == 0)
	{
		newBall(speed);
	}
}//checkCollision method ends here.

/*
 * ballRevertDirection checks a random condition to see if it is matched to 1 while
 * the ball also touches either p1 or p2. If either of them gets the ball while
 * met their condition, they can launch the ball to any opposing direction.
 */
public void ballRevertDirection() 
{
	Random rand = new Random();
	JavaMutex jM = new JavaMutex();
	rand.nextInt(0,3);
	if(rand.nextInt()==2)
	{
		rand.nextInt(0,3);
		
		/*
		 * This nested if block is for player 1 to have a chance of controlling the 
		 * ball while it changes color.
		 */
		if(rand.nextInt()==1 && ball.intersects(p1)==true)
		{
			/*
			 * Changes the ball color and holds the ball in place for player.
			 */
			ball.setColor();
			ball.setXDirection(0);
			ball.setYDirection(0);
			
			/*
			 * Next, listens to the player 1's key.
			 */
			jM.getNextGenSequence();
			
			KeyEvent e = null;
			p1.KeyActionPressed(e);
			/*
			 * If player 1 hit keys W and D here, then the ball would shoot diagonally up.
			 */
			if(e.getKeyCode()==KeyEvent.VK_W && e.getKeyCode()==KeyEvent.VK_D)
			{
				ball.setYDirection(ball.getYDirection()-1);
				ball.setXDirection(+ball.getXDirection());
				move();
				Graphics g = null;
				
				//Sets the ball back to normal.
				ball.draw(g);
			}
			
			/*
			 * If player 1 hit keys W and S here, then the ball would shoot diagonally down.
			 */
			if(e.getKeyCode()==KeyEvent.VK_S)
			{
				ball.setYDirection(-ball.getYDirection());
				ball.setXDirection(-ball.getXDirection());
				move();
				Graphics g = null;
				
				//Sets the ball back to normal.
				ball.draw(g);
			}
			p1.KeyActionReleased(e);
			
		}//Nested P1 If block ends here.
		
		
		
		/*
		 * This nested if block is for player 2 to have a chance of controlling the 
		 * ball while it changes color.
		 */
		if(rand.nextInt()==2 && ball.intersects(p2)==true)
		{
			/*
			 * Changes the ball color and holds the ball in place for player.
			 */
			ball.setColor();
			ball.setXDirection(0);
			ball.setYDirection(0);
			
			/*
			 * Next, listens to the player 1's key.
			 */
			jM.getNextGenSequence();
			
			KeyEvent e = null;
			p2.KeyActionPressed(e);
			/*
			 * If player 1 hit keys W and D here, then the ball would shoot diagonally up.
			 */
			if(e.getKeyCode()==KeyEvent.VK_UP && e.getKeyCode()==KeyEvent.VK_RIGHT)
			{
				ball.setYDirection(ball.getYDirection()-1);
				ball.setXDirection(+ball.getXDirection());
				move();
				Graphics g = null;
				
				//Sets the ball back to normal.
				ball.draw(g);
			}
			
			/*
			 * If player 1 hit keys W and S here, then the ball would shoot diagonally down.
			 */
			if(e.getKeyCode()==KeyEvent.VK_LEFT && e.getKeyCode()==KeyEvent.VK_DOWN)
			{
				ball.setYDirection(-ball.getYDirection());
				ball.setXDirection(-ball.getXDirection());
				move();
				Graphics g = null;
				
				//Sets the ball back to normal.
				ball.draw(g);
			}
			p2.KeyActionReleased(e);
			
			
		}//Nested P2 if block ends here.
		
	}//Main If block ends here
}//Ball revert direction method ends here.


/*
 * This is a method that makes the Game panel run, while also making
 * it run indefinitely until both the players quit.
 */
@Override
public void run()
{
	 long lastTime = System.nanoTime();
     double amountOfTicks =60.0;
     double ns = 1000000000 / amountOfTicks;
     double delta = 0;
     
     /*
      * While loop starts here, so that the game can continuously run
      * by player's input until checkCollision score conditions has been
      * met.
      */
     while(true) {
         long now = System.nanoTime();
         delta += (now -lastTime)/ns;
         lastTime = now;
         if(delta >=1) {
             move();
             checkCollision();
             repaint();
             delta--;
         }
     }//While loop end here.
}//Run method ends here.

//A inner class of AL for GamePanel.
public class AL extends KeyAdapter
{
	/*
	 * @param KeyEvent e registers a key event that has
	 * been pressed by both players.
	 */
	public void keyPressed (KeyEvent e)
		{
			p1.KeyActionPressed(e);
			p2.KeyActionPressed(e);
		}
	
	/*
	 * @param KeyEvent e registers a key event where
	 * players released their keys after they pressed it.
	 */
	
	public void keyReleased (KeyEvent e)
		{
			p1.KeyActionReleased(e);
			p2.KeyActionReleased(e);
		}
	
	}//AL Class ends here.


/*
 * In one of the classes, the client, clientHandler, and server
 * will be implemented in a method for a textbox so the players
 * can text each other and/or vote.
 */

}//Class ends here
