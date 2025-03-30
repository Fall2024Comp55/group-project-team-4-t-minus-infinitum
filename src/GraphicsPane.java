import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import acm.graphics.*;

public class GraphicsPane {
	protected MainApplication mainScreen;
	protected ArrayList<GObject> contents;
	
	public GraphicsPane() {
		contents = new ArrayList<GObject>();
	}

	
	public void showContent() {
	}

	public void hideContent() {
	}

	public void mousePressed(MouseEvent e) {
		//Name the object
		//if(SwingUtilities.isteftMouseButton(e) )
		//else if (SwingUtilities.isRightMouseButton (e) )
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseDragged(MouseEvent e) {
	}

	public void mouseMoved(MouseEvent e) {
		//Plan A free movement and the spaceship is connected with the mouse.
		//plan C have it snap 
		System.out.println("mouse moved");
	}

	public void keyPressed(KeyEvent e) {
		//plan b use the keys for the movement of the user spaceship
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

}
