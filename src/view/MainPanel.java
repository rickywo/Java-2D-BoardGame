package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.GameController;
import model.ImageManager;


public class MainPanel {

    public static final int MENU_OFFSET_X = 16;
    public static final int MENU_OFFSET_Y = 10;
    int[][] board; // Current board matrix to store pieces
    private GameController game;
    private GridPanel panel;
    private JFrame frame;
    private PopupMenu editMenu;
    private Container content;
    private Point cursorXYPos;


    public MainPanel(int[][] board, GameController game) {
        this.board = board;
        this.game = game;
        this.cursorXYPos = new Point(0, 0);
        createAndShowGUI();
    }

    /***************************************************************************
     * Name: createAndShowGUI(): init a Jframe to contain this game
     *****************************************************************************/

    private void createAndShowGUI() {
        panel = new GridPanel();
        frame = new JFrame("Human vs Alien");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        content = frame.getContentPane();
        content.add(panel);
        frame.setSize(GameController.SCRSIZE, GameController.SCRSIZE);
        frame.setSize(900, 675);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    class GridPanel extends JPanel {
        InputStream is;
        BufferedImage image;


        public GridPanel() {
            setBackground(Color.GRAY);

            MyMouseListener ml = new MyMouseListener();
            addMouseListener(ml);
            addMouseMotionListener(ml);
        }
        /***************************************************************************
         * Name: paintComponent(Graphics g) Parameters: (g) : the graphics context
         * to draw on.
         *****************************************************************************/
        public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            super.paintComponent(g2);
            // draw the tiles and characters on the map
            paintBackground(g2);
            for (int i = 0; i < GameController.BSIZE; i++) {
                for (int j = 0; j < GameController.BSIZE; j++) {
                    // Paint the tiles
                     Rectmech.draw(i, j, game.gameBoard[i][j].getTileImg(), g2);
                    // Paint the characters
                    if (game.gameBoard[i][j].getEntity() != null)
                        Rectmech.draw(i, j, game.gameBoard[i][j].getCharImg(), g2);
                }
            }
            // Repaint the grid according to if the cell is selected
            for (int i = 0; i < GameController.BSIZE; i++) {
                for (int j = 0; j < GameController.BSIZE; j++) {
                    if (game.gameBoard[i][j].getEntity() != null)
                        Rectmech.fill(i, j, board[i][j], g2);
                }
            }

        }

        private void paintBackground(Graphics2D g2) {
            Rectangle rect = new Rectangle(0, 0, 900, 675);
            // defensive design: Handle null reference
            TexturePaint texture = new TexturePaint(ImageManager.getImage("/resources/background/background.jpg"), rect);

            g2.setPaint(texture);
            g2.fill(rect);
            g2.draw(rect);
        }

        class MyMouseListener extends MouseAdapter {    //inner class inside DrawingPanel
            public void mouseClicked(MouseEvent e) {


                int x = e.getX();
                int y = e.getY();
                Point p = new Point(Rectmech.pxtoRect(x, y));
                System.out.println("px:" + p.x + ", py:" + p.y);
                // Do nothing if mouse click the area out of bound
                if (p.x < 0 || p.y < 0 || p.x >= GameController.BSIZE || p.y >= GameController.BSIZE) return;


                if (game.gameBoard[p.x][p.y].getEntity() != null) {
                    // Show action menu of current selected pieces
                    showPopupMenuDemo(x + MENU_OFFSET_X
                            , y + MENU_OFFSET_Y
                            , game.gameBoard[p.x][p.y].getEntity().getAttackName());
                } else {
                    return;
                }
                repaint();
            }
            /***************************************************************************
             * To show the cell when mouse hovering it
             *****************************************************************************/
            @Override
            public void mouseMoved(MouseEvent e) {

                int x = e.getX();
                int y = e.getY();
                Point p = new Point(Rectmech.pxtoRect(e.getX(), e.getY()));
                System.out.println("x:" + x + ", y:" + y);

                if (p.x < 0 || p.y < 0 || p.x >= GameController.BSIZE || p.y >= GameController.BSIZE) return;
                if (!p.equals(cursorXYPos)) {
                    board[cursorXYPos.x][cursorXYPos.y] = 0;
                    cursorXYPos = p;
                }
                board[p.x][p.y] = -1;

                repaint();
            }

        } //end of MyMouseListener class

        /***************************************************************************
         * To show a popup menu for selecting action
         *****************************************************************************/
        private void showPopupMenuDemo(int x, int y, String attackName) {
            editMenu = new PopupMenu("Edit");
            System.out.println("MouseClicked");

            MenuItem moveMenuItem = new MenuItem("Move");
            moveMenuItem.setActionCommand("Move");

            MenuItem attackMenuItem = new MenuItem(attackName);
            attackMenuItem.setActionCommand("Attack");

            MenuItemListener menuItemListener = new MenuItemListener();

            moveMenuItem.addActionListener(menuItemListener);
            attackMenuItem.addActionListener(menuItemListener);

            editMenu.add(moveMenuItem);
            editMenu.add(attackMenuItem);
            panel.add(editMenu);
            editMenu.show(panel, x, y);
        }

        class MenuItemListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                 /*statusLabel.setText(e.getActionCommand()
		            + " MenuItem clicked.");*/
            }
        }
    } // end of DrawingPanel class

}
