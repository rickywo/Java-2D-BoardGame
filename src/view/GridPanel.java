package view;

import controller.GameController;
import model.BoardCell;
import model.ImageManager;
import model.MainGame;
import resources.Consts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Human v Alien Team on 2016/4/12.
 */
class GridPanel extends JPanel {


    public BoardCell[][] board;

    public int[][] maskMatrix; // mask matrix for game board
    private boolean screenLock; // Lock screen for moving pieces
    public Point cursorXYPos;
    private PopupMenu editMenu;


    public GridPanel(MainGame modelManager) {

        board = modelManager.getBoard();
        setBackground(Color.GRAY);
        initMaskMtrix();
        cursorXYPos = new Point(0, 0);
        MxMouseListener ml = new MxMouseListener(this);
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
                if (board[i][j].getEntity() != null) {
                    Rectmech.draw(i, j, board[i][j].getCharImg(), g);
                    Rectmech.drawText(i, j, String.valueOf(board[i][j].getEntity().getMaxHP()), g );
                } else {
                    Rectmech.draw(i, j, null, g);
                }
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
        for (int i = 0; i < Consts.BSIZE; i++) {
            for (int j = 0; j < Consts.BSIZE; j++) {
                if (maskMatrix[i][j] < 0)
                    Rectmech.highlight(i, j, g2);
                else if(maskMatrix[i][j] > 0)
                    Rectmech.diminish(i, j, g2);
            }
        }

    }

    private void setScreenLock(boolean lock) {
        screenLock = lock;
    }

    public boolean isScreenLocked() {
        return screenLock;
    }

    private void movePiece(Point p) {
        setScreenLock(true);
        int steps = GameController.singleton().moveHandler(p);
        setMovableMatrix(p.x, p.y, steps);
    }

    public void moveTo(Point p) {
        setScreenLock(false);
        GameController.singleton().doMove(p);
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

    private void initMaskMtrix() {
        this.maskMatrix = new int[Consts.BSIZE][Consts.BSIZE];
        for (int i = 0; i < Consts.BSIZE; i++) {
            for (int j = 0; j < Consts.BSIZE; j++) {
                maskMatrix[i][j] = 0;
            }
        }
    }

    /***************************************************************************
     * To show a popup menu for selecting action
     *****************************************************************************/
    public void showPopupMenuDemo(int x, int y, Point point, String attackName) {
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
        add(editMenu);
        editMenu.show(this, x, y);
    }
} // end of DrawingPanel class
