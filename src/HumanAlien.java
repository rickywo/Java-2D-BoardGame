/*
 * Copyright (C) 2016 Ricky Wu.
 */
import javax.swing.SwingUtilities;
import controller.GameController;



// TODO: Auto-generated Javadoc
/**
 * The Class HumanAlien.
 */
public class HumanAlien {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					GameController.singleton();
				}
		});
	}

}
