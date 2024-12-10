import java.awt.*;
import javax.swing.*;
/**
 * @author Ferdinand Yeke
 *CS-310 Operating Systems
 *Bellarmine University
 *
 *
 *Program Source: https://projectgurukul.org/java-pong-game/#:~:text=Steps%20to%20Create%20a%20Pong%20Game%20in%20Java,Creating%20Paddles%204%20Creating%20Ball%205%20Creating%20Scoreboard
 */

/*
 * This Class creates a game frame that also tries to build the world of the ping pong game like setting the 
 * background color, it title and more below.
 */
public class GameFrame extends JFrame {
	
	
	GamePanel panel;
	GameFrame(int speed)
	{
		panel = new GamePanel(speed);
		this.add(panel);
		this.setTitle("Pong Game");
		this.setResizable(false);
		this.setBackground(Color.white);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}//GameFrame method ends here.

}//Class ends here.
