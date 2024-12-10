import java.awt.event.KeyEvent;
/**
 * @author Ferdinand Yeke
 *
 */

/*
 * This is the Interface for the Paddle Class. Methods that it has here
 * are the paddle method, which creates a paddle with the Rectangle class
 * (which will be used in the actual Paddle Class. The KeyAction() method 
 * is a method that reads key inputs for the Paddle to move with.
 */
public interface PaddleInterface {

public  void Paddle(int x, int y, int P_Width, int P_Height, int id);

public void KeyActionPressed(KeyEvent e);

public void KeyActionReleased(KeyEvent e);

int padSpeed= 0;
}
