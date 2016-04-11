package view;

import java.awt.*;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

import controller.GameController;
import model.*;
import resources.Consts;


public class MainPanel {

    private final BoardCell[][] board; // Current board matrix to store pieces
    private int[][] maskMatrix; // mask matrix for game board
    private boolean screenLock; // Lock screen for moving pieces
    private GameController game;
    private GridPanel panel;
    private JFrame frame;
    private PopupMenu editMenu;
    private Container content;
    private Point cursorXYPos;


    public MainPanel(BoardCell[][] board, GameController game) {
        this.board = board;
        this.game = game;
        this.cursorXYPos = new Point(0, 0);
        this.maskMatrix = new int[Consts.BSIZE][Consts.BSIZE];
        initMaskMtrix();
        createAndShowGUI();
    }

    private void initMaskMtrix() {
        for (int i = 0; i < Consts.BSIZE; i++) {
            for (int j = 0; j < Consts.BSIZE; j++) {
                maskMatrix[i][j] = 0;
            }
        }
    }

    private void setScreenLock(boolean lock) {
        screenLock = lock;
    }

    private boolean isScreenLocked() {
        return screenLock;
    }

    public static void showMessageBox() {
        Runnable updateAComponent = new Runnable() {
            public void run() { JOptionPane.showMessageDialog(null, "Team "+ GameController.getTeamOnMove() +"'s turn."); }
        };
        SwingUtilities.invokeLater(updateAComponent);

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
        frame.setSize(Consts.SCR_WIDTH, Consts.SCR_HEIGHT);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    class GridPanel extends JPanel {

        public GridPanel() {
            setBackground(Color.GRAY);

            MxMouseListener ml = new MxMouseListener();
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
            renderBackground(g2);
            renderGamePieces(g2);
            renderMaskMatrix(g2);

        }

        private void renderGamePieces(Graphics2D g) {
            for (int i = 0; i < Consts.BSIZE; i++) {
                for (int j = 0; j < Consts.BSIZE; j++) {
                    if (board[i][j].getEntity() != null)
                        Rectmech.draw(i, j, board[i][j].getCharImg(), g);
                    else
                        Rectmech.draw(i, j, null, g);
                }
            }
        }

        private void renderBackground(Graphics2D g2) {
            Rectangle rect = new Rectangle(0, 0, Consts.SCR_WIDTH, Consts.SCR_HEIGHT);
            // defensive design: Handle null reference
            TexturePaint texture = new TexturePaint(ImageManager.getImage("/resources/background/background.jpg"), rect);

            g2.setPaint(texture);
            g2.fill(rect);
            g2.draw(rect);
        }

        private void renderMaskMatrix(Graphics2D g2) {
            Rectangle rect = new Rectangle(0, 0, Consts.SCR_WIDTH, Consts.SCR_HEIGHT);
            for (int i = 0; i < Consts.BSIZE; i++) {
                for (int j = 0; j < Consts.BSIZE; j++) {
                    if (maskMatrix[i][j] < 0)
                        Rectmech.highlight(i, j, g2);
                    else if(maskMatrix[i][j] > 0)
                        Rectmech.diminish(i, j, g2);
                }
            }

        }

        private void movePiece(Point p) {
            setScreenLock(true);
            int steps = game.moveHandler(p);
            setMovableMatrix(p.x, p.y, steps);
        }

        private void moveTo(Point p) {
            setScreenLock(false);
            game.doMove(p);
            resetMaskMatrix();
        }

        private int dist(int x, int y, int x1, int y1) {
            return Math.abs(x1-x) + Math.abs(y1-y);
        }

        private void setMovableMatrix(int x, int y, int steps) {
            for (int i = 0; i < Consts.BSIZE; i++) {
                for (int j = 0; j < Consts.BSIZE; j++) {
                    if (dist(x, y, i, j) > steps ||
                            board[i][j].getEntity()!=null)
                        maskMatrix[i][j] = 1;
                }
            }
            maskMatrix[x][y] = 0;
            repaint();
        }

        private void resetMaskMatrix() {
            for (int i = 0; i < Consts.BSIZE; i++) {
                for (int j = 0; j < Consts.BSIZE; j++) {
                        maskMatrix[i][j] = 0;
                }
            }
            repaint();
        }

        class MxMouseListener extends MouseAdapter {    //inner class inside DrawingPanel
            public void mouseClicked(MouseEvent e) {


                int x = e.getX();
                int y = e.getY();
                Point p = new Point(Rectmech.pxtoRect(x, y));
                Entity t = board[p.x][p.y].getEntity();
                System.out.println("px:" + p.x + ", py:" + p.y);
                // Do nothing if mouse click the area out of bound
                if (p.x < 0 || p.y < 0 || p.x >= Consts.BSIZE || p.y >= Consts.BSIZE) return;

                if (t != null) {
                    // Do nothing if this piece is moved
                    if(t.isMoved()  || t.getTeam() != GameController.getTeamOnMove()) return;
                    // Show action menu of current selected pieces
                    showPopupMenuDemo(x + Consts.MENU_OFFSET_X
                            , y + Consts.MENU_OFFSET_Y
                            , p
                            , board[p.x][p.y].getEntity().getAttackName());
                } else {
                    // matrixValue == -1 means this cell is selectable
                    if(isScreenLocked() && maskMatrix[p.x][p.y] == -1) {
                        moveTo(p);
                    }
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
                if (p.x < 0 || p.y < 0 || p.x >= Consts.BSIZE || p.y >= Consts.BSIZE) return;
                if (isScreenLocked() && board[p.x][p.y].getEntity()!=null) return;
                if (maskMatrix[p.x][p.y] == 1) return;
                if (!p.equals(cursorXYPos)) {
                    maskMatrix[cursorXYPos.x][cursorXYPos.y] = 0;
                    cursorXYPos = p;
                }
                maskMatrix[p.x][p.y] = -1;
                repaint();
            }

        } //end of MxMouseListener class

        /***************************************************************************
         * To show a popup menu for selecting action
         *****************************************************************************/
        private void showPopupMenuDemo(int x, int y, Point point, String attackName) {
            editMenu = new PopupMenu();
            final Point p = point;
            ActionListener al = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(e.getActionCommand().compareTo(Consts.MOVE) == 0) {

                        movePiece(p);
                    }
                }
            };

            MenuItem moveMenuItem = new MenuItem(Consts.MOVE);
            moveMenuItem.setActionCommand(Consts.MOVE);

            MenuItem attackMenuItem = new MenuItem(attackName);
            attackMenuItem.setActionCommand(Consts.ATTACK);

            //MenuItemListener menuItemListener = new MenuItemListener();

            moveMenuItem.addActionListener(al);
            attackMenuItem.addActionListener(al);

            editMenu.add(moveMenuItem);
            editMenu.add(attackMenuItem);
            panel.add(editMenu);
            editMenu.show(panel, x, y);
        }
    } // end of DrawingPanel class

}
