import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * @author Ferdinand Yeke
 *CS-310 Operating Systems
 *Bellarmine University
 *
 *
 *Program Source: https://projectgurukul.org/java-pong-game/#:~:text=Steps%20to%20Create%20a%20Pong%20Game%20in%20Java,Creating%20Paddles%204%20Creating%20Ball%205%20Creating%20Scoreboard
 */
public class WelcomePage extends JFrame {

	private JTextField levelField;
	
	public WelcomePage()
	{
		setTitle("Pong Game Welcome Page");
		setSize(500, 250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		
		
		//Adding welcome page
		JLabel welcomeMessage = new JLabel ("Welcome to the Ping Pong Game!",
				SwingConstants.CENTER);
		welcomeMessage.setFont(new Font ("Serif",Font.BOLD, 16));
		panel.add(welcomeMessage, BorderLayout.NORTH);
		
		//Creates a central panel for imput and buttons.
		GridLayout gd = new GridLayout (2,2);
		JPanel centerPanel = new JPanel (gd);
		
		
		//Adds the label and field for level inputs.
		JLabel levelLabel = new JLabel ("Enter Level: ");
		centerPanel.add(levelLabel);
		
		levelField = new JTextField();
		centerPanel.add(levelField);
		
		JButton playNowButton = new JButton ("Play new game");
		playNowButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e)
			{
				startGame();
			}
		});
		centerPanel.add(playNowButton);
		
		
		//Adding a How To Play button
		JButton howToPlayButton = new JButton ("How to Play");
		howToPlayButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				//Shows the instructions for the game.
				showInstructions();
			}	
		});
		centerPanel.add(howToPlayButton);
		
		panel.add(centerPanel, BorderLayout.CENTER);
		
		//Adds the created panel to the Jframe created in welcome page
		add(panel);
		
		//Makes the JFrame visible.
		setVisible(true);
	}
	
	private void startGame()
	{
		String level = levelField.getText();
		
		new GameFrame (Integer.parseInt(level));
		
		//Finally, this closes the welcome page.
		dispose();
	}
	
	private void showInstructions()
	{
		//Shows the instructions on how to play
		JOptionPane.showMessageDialog(this, "Pong Game Instructions: \n"+
		"1. Use the Pg up and Pg down keys to move the paddle for the player-"
		+ "right.\n"+
		"2. Use the W and S keys to control the paddle for the player-left.\n"+
		"3. Prevent the ball from touching the ground.\n"+
		"4. The player gains a point when the opponent fails to bounce ball"
		+ "back with the paddle.\n"+ "5. There is also a chance that the ball can change color, use your directional"
		+ " keys to launch it at any direction you want!",
		"How To Play"
		, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
				{
					@Override
					public void run() 
					{
						new WelcomePage();
					}
				});
	}
}
