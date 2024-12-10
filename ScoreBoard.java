import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;


/**
 * @author Ferdinand Yeke
 *CS-310 Operating Systems
 *Bellarmine University
 *
 *
 *Program Source: https://projectgurukul.org/java-pong-game/#:~:text=Steps%20to%20Create%20a%20Pong%20Game%20in%20Java,Creating%20Paddles%204%20Creating%20Ball%205%20Creating%20Scoreboard
 */

/*
 * This class is a class that serves as the scoreboard for the ping
 * pong game.
 */

/*
 * The Scoring method builds and showcases the actual scoring of the
 * players of each respective side.
 */

/*
 * The draw method also draws a visual cue on each side as to what score
 * they have.
 */

public class ScoreBoard extends Rectangle{
	
	/*
	 * Makes up variables that make up the score board.
	 */
	 static int score_Box_Height;
	 static int score_Box_Width;
	 int Score_P1;
	 int Score_P2;
	
	/*
	 * @param score_Box_Height sets up the score box height, and @param score_Box_Width 
	 * sets up the width of the score box.
	 */
	public ScoreBoard(int score_Box_Height, int score_Box_Width)
	{
		ScoreBoard.score_Box_Height = score_Box_Height;
		ScoreBoard.score_Box_Width = score_Box_Width;
	}
	
	/*
	 * @param s sets the color of the score board, which is the text, sets up the font which is italic,
	 * and draws the line to easily distinguish the two scores.
	 */
	public void draw (Graphics s)
	{
		s.setColor(Color.black);
		s.setFont(new Font("Consolas",Font.ITALIC,60));
		
		s.drawLine(score_Box_Width/2,0, score_Box_Width/2, score_Box_Height);
		
	/*
	 * Next there should be statements regarding the mechanics of the game. If
	 * there is a player that reaches 11 points before the other, then the player with
	 * 11 points win.
	 */
	s.drawString(String.valueOf(Score_P1/11)+String.valueOf(Score_P1 % 11), 
			(score_Box_Width/2)-85,50);
	
	s.drawString(String.valueOf(Score_P2/11)+String.valueOf(Score_P2 % 11), 
			(score_Box_Width/2)+20,50);
	}

	/*
	 * In the main method or in some other class for this project, the mutex will be used for text based chat and also 
	 * for voting to play another game after a win. This seperate text box might also have another graphics box being
	 * drawn for text input.
	 */
	
}
