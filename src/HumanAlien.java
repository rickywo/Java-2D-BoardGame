import javax.swing.SwingUtilities;
import controller.GameController;



public class HumanAlien {
	
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					GameController.singleton();
				}
		});
	}

}
