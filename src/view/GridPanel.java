package view;

import javax.swing.*;

/**
 * Created by Human v Alien Team on 2016/4/12.
 */
class GridPanel extends JPanel {


    /*public BoardCell[][] board;
    public Point cursorXYPos;
    public int[][] maskMatrix; // mask matrix for game board


    private PopupMenu editMenu;
    private boolean screenLock; // Lock screen for moving pieces


    public GridPanel(MainGame modelManager) {

        board = modelManager.getBoard();
        setBackground(Color.GRAY);
        initMaskMatrix();
        cursorXYPos = new Point(0, 0);
        MxMouseListener ml = new MxMouseListener(this);
        addMouseListener(ml);
        addMouseMotionListener(ml);
    }
    *//***************************************************************************
     * Name: paintComponent(Graphics g) Parameters: (g) : the graphics context
     * to draw on.
     *****************************************************************************//*
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        super.paintComponent(g2);
        renderBackground(g2);
        renderGamePieces(g2);
        renderMaskMatrix(g2);

    }

    *//********************************************************************
     * renderGamePieces: draws game pieces on screen according to
     * the data stored in board[][]
     *********************************************************************//*

    private void renderGamePieces(Graphics2D g) {
        for (int i = 0; i < Consts.BSIZE; i++) {
            for (int j = 0; j < Consts.BSIZE; j++) {

                if (board[i][j].getEntity() != null) {
                    // If this cell has a entity in it
                    // To draw the image of a piece
                    Rectmech.draw(i, j, board[i][j].getCharImg(), g);
                    // To draw HP value of a piece
                    Rectmech.drawText(i, j, String.valueOf(board[i][j].getEntity().getMaxHP()), g );
                } else {
                    // If no entity in this cell
                    Rectmech.draw(i, j, null, g);
                }
            }
        }
    }

    *//********************************************************************
     * renderGamePieces: draws background image
     *********************************************************************//*

    private void renderBackground(Graphics2D g2) {
        Rectangle rect = new Rectangle(0, 0, Consts.SCR_WIDTH, Consts.SCR_HEIGHT);

        //
        TexturePaint texture = new TexturePaint(
                ImageManager.getBackGroundImage(),
                rect);

        g2.setPaint(texture);
        g2.fill(rect);
        g2.draw(rect);
    }

    *//********************************************************************
     * renderMaskMatrix: draws the shadow on cells according to data
     * stored in int MaskMatrix[][]
     * highlight a cell if value of the cell less than 0
     * diminish a cell if value of the cell greater than 0
     *********************************************************************//*

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

    *//********************************************************************
     * movePiece: Handle the request when menu item "Move" is clicked
     * Point p: the coordinator on the game board
     * It calls the function: moveHandler in GameController to get the number
     * can move of this piece as step, also lock the screen for de-reacting
     * click event of pieces
     *********************************************************************//*

    private void movePiece(Point p) {
        setScreenLock(true);
        int steps = GameController.singleton().moveHandler(p);
        setMovableMatrix(p.x, p.y, steps);
    }

    *//********************************************************************
     * moveTo: Handle the request when a cell in a movable area is clicked
     * Point p: the coordinator on the game board
     * It unlock the click event of pieces and call doMove function in
     * GameController, finally, it reset the MaskMatrix to remove shadow
     * on the game board
     *********************************************************************//*

    public void moveTo(Point p) {
        setScreenLock(false);
        GameController.singleton().doMove(p);
        resetMaskMatrix();
    }

    private int dist(int x, int y, int x1, int y1) {
        return Math.abs(x1-x) + Math.abs(y1-y);
    }

    *//********************************************************************
     * setMovableMatrix: It sets the cell value of non-movable area to 1
     * int step: number of step can move of a piece
     *********************************************************************//*

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

    *//********************************************************************
     * resetMaskMatrix: It resets the cell value to 0 in MaskMatrix
     *********************************************************************//*

    private void resetMaskMatrix() {
        for (int i = 0; i < Consts.BSIZE; i++) {
            for (int j = 0; j < Consts.BSIZE; j++) {
                maskMatrix[i][j] = 0;
            }
        }
        repaint();
    }

    *//********************************************************************
     * initMaskMatrix: It resets the cell value to 0 in MaskMatrix
     *********************************************************************//*

    private void initMaskMatrix() {
        this.maskMatrix = new int[Consts.BSIZE][Consts.BSIZE];
        for (int i = 0; i < Consts.BSIZE; i++) {
            for (int j = 0; j < Consts.BSIZE; j++) {
                maskMatrix[i][j] = 0;
            }
        }
    }

    *//***************************************************************************
     * To show a popup menu for selecting action
     *****************************************************************************//*
    public void showActionMenu(int x, int y, Point point, String attackName) {
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

        moveMenuItem.addActionListener(al);
        attackMenuItem.addActionListener(al);

        editMenu.add(moveMenuItem);
        editMenu.add(attackMenuItem);
        add(editMenu);
        editMenu.show(this, x, y);
    }*/
} // end of DrawingPanel class
