package view;

import controller.GameController;
import model.gameModel.*;
import model.gameModel.Point;
import model.graphicModel.Art;
import model.graphicModel.Bitmap;
import model.graphicModel.GameScreen;
import model.graphicModel.ImageManager;
import resources.Consts;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

/**
 * Created by Human v Alien Team on 2016/4/12.
 */
class GridPanelRunnable extends Canvas implements  Runnable {


    public BoardCell[][] board;
    public model.gameModel.Point cursorXYPos;
    public int[][] maskMatrix; // mask matrix for game board

    private boolean running;
    private PopupMenu editMenu;
    private boolean screenLock; // Lock screen for moving pieces
    MxMouseListener ml;
    private GameScreen screen;


    public GridPanelRunnable(MainGame modelManager) {

        board = modelManager.getBoard();
        setBackground(Color.GRAY);
        initMaskMatrix();
        cursorXYPos = new Point(0, 0);
        ml = new MxMouseListener(this);
        addMouseListener(ml);
        addMouseMotionListener(ml);
        screen = new GameScreen(Consts.SCR_WIDTH, Consts.SCR_HEIGHT);

    }

    @Override
    public void run() {
        running = true;
        requestFocus();
        double target = 60.0;
        double nsPerTick = 1000000000.0 / target;
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        double unprocessed = 0.0;
        int fps = 0;
        int tps = 0;
        boolean canRender = false;

        while (running) {
            long now = System.nanoTime();
            unprocessed += (now - lastTime) / nsPerTick;
            lastTime = now;

            if (unprocessed >= 1.0) {
                //MxMouseListener.update();
                unprocessed--;
                tps++;
                canRender = true;
            } else canRender = false;

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (canRender) {
                render();
                fps++;
            }

            if (System.currentTimeMillis() - 1000 > timer) {
                timer += 1000;
                System.out.printf("FPS: %d | TPS: %d\n", fps, tps);
                fps = 0;
                tps = 0;
            }
        }
    }

    private void render() {
        // TODO: if game state == PLAYING, render current game state
        // if game state == STORY, render game story
        // if game state == MENU, render menu

        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;
        // Render Game Background
        screen.render(Art.background, 0, 0);
        ////////////////////////////////////////////////
        DashBoard.render(screen);
        renderGamePieces();
        Verbose.render(screen);

        g.drawImage(screen.image, 0,0, Consts.SCR_WIDTH,Consts.SCR_HEIGHT,null);
        paint(g2d);

        ////////////////////////////////////////////////
        g.dispose();
        bs.show();
    }
    public void start() {
        running = true;
        new Thread(this).start();
        MainPanel.showMessageBox("Game Start", 5000);
    }
    public void stop() {
        if (!running) return;
        running = false;
    }
    /***************************************************************************
     * Name: paintComponent(Graphics g) Parameters: (g) : the graphics context
     * to draw on.
     *****************************************************************************/
    public void paint(Graphics2D g2) {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        renderMaskMatrix(g2);

    }

    /********************************************************************
     * renderGamePieces: draws game pieces on screen according to
     * the data stored in board[][]
     *********************************************************************/

    private void renderGamePieces() {
        for (int i = 0; i < Consts.BSIZE; i++) {
            for (int j = 0; j < Consts.BSIZE; j++) {

                if (board[i][j].getEntity() != null) {
                    // If this cell has a entity in it
                    // To draw the image of a piece
                    BufferedImage charImge = board[i][j].getCharImg();
                    BufferedImage image = ImageManager.resizeImage(charImge, 0.9 * ((double) Consts.RECTSIZE / (double) charImge.getWidth()));
                    int w = image.getWidth();
                    int h = image.getHeight();
                    Bitmap result = new Bitmap(w, h);
                    image.getRGB(0, 0, w, h, result.pixels, 0, w);

                    screen.render(result, i*Consts.RECTSIZE + Consts.MAP_X_OFFSET, j*Consts.RECTSIZE+Consts.MAP_Y_OFFSET+2);
                }
            }
        }
    }

    /********************************************************************
     * renderMaskMatrix: draws the shadow on cells according to data
     * stored in int MaskMatrix[][]
     * highlight a cell if value of the cell less than 0
     * diminish a cell if value of the cell greater than 0
     *********************************************************************/

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

    /********************************************************************
     * movePiece: Handle the request when menu item "Move" is clicked
     * Point p: the coordinator on the game board
     * It calls the function: moveHandler in GameController to get the number
     * can move of this piece as step, also lock the screen for de-reacting
     * click event of pieces
     *********************************************************************/

    private void movePiece(Point p) {
        setScreenLock(true);
        int steps = GameController.singleton().moveHandler(p);
        setSelectableMatrix(p.x, p.y, steps, false);
    }

    private void beforeAttack(Point p) {
        setScreenLock(true);
        int range = GameController.singleton().attackHandler(p);
        setSelectableMatrix(p.x, p.y, range, true);
    }

    /********************************************************************
     * moveTo: Handle the request when a cell in a movable area is clicked
     * Point p: the coordinator on the game board
     * It unlock the click event of pieces and call doMove function in
     * GameController, finally, it reset the MaskMatrix to remove shadow
     * on the game board
     *********************************************************************/

    public void moveTo(Point p) {
        setScreenLock(false);
        GameController.singleton().doMove(p);
        resetMaskMatrix();
    }

    public void attack(Point p) {
        setScreenLock(false);
        GameController.singleton().doAttack(p);
        resetMaskMatrix();
    }

    private int dist(int x, int y, int x1, int y1) {
        return Math.abs(x1-x) + Math.abs(y1-y);
    }

    /********************************************************************
     * setMovableMatrix: It sets the cell value of non-movable area to 1
     * int step: number of step can move of a piece
     *********************************************************************/

    private void setSelectableMatrix(int x, int y, int range, boolean isEntitySelectable) {
        for (int i = 0; i < Consts.BSIZE; i++) {
            for (int j = 0; j < Consts.BSIZE; j++) {
                if (dist(x, y, i, j) > range) {
                    maskMatrix[i][j] = 1;
                } else {

                }
                if (isEntitySelectable) {
                    if(board[i][j].getEntity() == null) {
                        maskMatrix[i][j] = 1;
                        System.out.println("Attack range: " +range);
                    }
                } else {
                    if(board[i][j].getEntity() != null) {
                        maskMatrix[i][j] = 1;
                    }

                }

            }
        }
        maskMatrix[x][y] = 0;
        //repaint();
    }

    /********************************************************************
     * resetMaskMatrix: It resets the cell value to 0 in MaskMatrix
     *********************************************************************/

    private void resetMaskMatrix() {
        for (int i = 0; i < Consts.BSIZE; i++) {
            for (int j = 0; j < Consts.BSIZE; j++) {
                maskMatrix[i][j] = 0;
            }
        }
        //repaint();
    }

    /********************************************************************
     * initMaskMatrix: It resets the cell value to 0 in MaskMatrix
     *********************************************************************/

    private void initMaskMatrix() {
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
    public void showActionMenu(int x, int y, Point point, String attackName) {
        editMenu = new PopupMenu();
        final Point p = point;
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand().compareTo(Consts.MOVE) == 0) {
                    movePiece(p);
                }
                if(e.getActionCommand().compareTo(Consts.ATTACK) == 0) {
                    beforeAttack(p);
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
    }
} // end of DrawingPanel class
