import javax.swing.SwingUtilities;
import controller.Hexgame;



public class HumanAlien {
	
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					Hexgame.singleton();
				}
		});
	}

}
