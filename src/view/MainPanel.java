package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.Hexgame;


public class MainPanel {
	
	final static Color COLOURBACK =  Color.WHITE;
	final static Color COLOURCELL =  Color.ORANGE;	 
	final static Color COLOURGRID =  Color.BLACK;	 
	final static Color COLOURONE = new Color(255,255,255,200);
	final static Color COLOURONETXT = Color.BLUE;
	final static Color COLOURTWO = new Color(0,0,0,200);
	final static Color COLOURTWOTXT = new Color(255,100,255);
	
	
	int[][] board; // Current board matrix to store pieces
	private GridPanel panel;
	private JFrame frame;
	private Container content;
	private Point cursorXYPos;
	

	public MainPanel(int[][] board) {
		this.board = board;
		this.cursorXYPos = new Point(0, 0);
		createAndShowGUI();
	}
	
	private void createAndShowGUI()
	{
		panel = new GridPanel();


		//JFrame.setDefaultLookAndFeelDecorated(true);
		frame = new JFrame("Hex Testing 4");
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		content = frame.getContentPane();
		content.add(panel);
		//this.add(panel);  -- cannot be done in a static context
		//for hexes in the FLAT orientation, the height of a 10x10 grid is 1.1764 * the width. (from h / (s+t))
		frame.setSize(Hexgame.SCRSIZE, Hexgame.SCRSIZE);
		frame.setResizable(false);
		frame.setLocationRelativeTo( null );
		frame.setVisible(true);
	}
	
	class GridPanel extends JPanel
	{		
		//mouse variables here
		//Point mPt = new Point(0,0);
		InputStream is;
		BufferedImage image;
		

		public GridPanel()
		{	
			setBackground(COLOURBACK);
			/*try {
				is = MainPanel.class.getResourceAsStream("/Tiles/Terrain/Mars/mars_02.png");
				image = ImageIO.read(is);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/

			MyMouseListener ml = new MyMouseListener();            
			addMouseListener(ml);
			addMouseMotionListener(ml);
		}

		public void paintComponent(Graphics g)
		{
			Graphics2D g2 = (Graphics2D)g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
			super.paintComponent(g2);
			//draw grid
			for (int i=0;i<Hexgame.BSIZE;i++) {
				for (int j=0;j<Hexgame.BSIZE;j++) {
					Rectmech.draw(i,j,g2);
				}
			}
			//fill in hexes
			for (int i=0;i<Hexgame.BSIZE;i++) {
				for (int j=0;j<Hexgame.BSIZE;j++) {					
					Rectmech.fill(i,j,board[i][j],g2);
				}
			}

		}

		class MyMouseListener extends MouseAdapter	{	//inner class inside DrawingPanel 
			public void mouseClicked(MouseEvent e) { 
				int x = e.getX(); 
				int y = e.getY(); 
				//mPt.x = x;
				//mPt.y = y;
				Point p = new Point( Rectmech.pxtoRect(e.getX(),e.getY()) );
				if (p.x < 0 || p.y < 0 || p.x >= Hexgame.BSIZE || p.y >= Hexgame.BSIZE) return;


				//What do you want to do when a hexagon is clicked?
				//board[p.x][p.y] = (int)'X';
				showPopupMenuDemo();
				repaint();
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
				int x = e.getX(); 
				int y = e.getY();
				Point p = new Point( Rectmech.pxtoRect(e.getX(),e.getY()) );
				
				if (p.x < 0 || p.y < 0 || p.x >= Hexgame.BSIZE || p.y >= Hexgame.BSIZE) return;
				if(!p.equals(cursorXYPos)) {
					board[cursorXYPos.x][cursorXYPos.y] = 0;
					cursorXYPos = p;
				}
				board[p.x][p.y] = -1;
				System.out.println("x:"+x+", y:"+y);
				repaint();
			}	
			
		} //end of MyMouseListener class 
		
		private void showPopupMenuDemo(){
		      final PopupMenu editMenu = new PopupMenu("Edit"); 

		      MenuItem moveMenuItem = new MenuItem("Move");
		      moveMenuItem.setActionCommand("Move");

		      MenuItem attackMenuItem = new MenuItem("Attack");
		      attackMenuItem.setActionCommand("Attack");

		      MenuItemListener menuItemListener = new MenuItemListener();

		      moveMenuItem.addActionListener(menuItemListener);
		      attackMenuItem.addActionListener(menuItemListener);

		      editMenu.add(moveMenuItem);
		      editMenu.add(attackMenuItem);
		      
		      panel.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					editMenu.show(panel, e.getX(), e.getY());
				}
			});
			panel.add(editMenu);
		   }
			
		   class MenuItemListener implements ActionListener {
		      public void actionPerformed(ActionEvent e) {            
		         /*statusLabel.setText(e.getActionCommand() 
		            + " MenuItem clicked.");*/
		      }    
		   }
	} // end of DrawingPanel class

}
