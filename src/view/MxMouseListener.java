package view;

import controller.GameController;
import model.gameModel.*;
import resources.Consts;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Human v Alien Team on 2016/4/12.
 */
class MxMouseListener extends MouseAdapter {    //inner class inside DrawingPanel

    private GridPanelRunnable panel;
    private GameController gameController;
    public MxMouseListener(GridPanelRunnable panel, GameController controller) {
        this.panel = panel;
        this.gameController = controller;
    }


    /***************************************************************************
     * To handle the click event of game board
     * It calls showActionMenu function in GridPanelRunnable when screen is not locked
     * for moving a piece.
     * It calls moveTo() function in GridPanelRunnable when Move item is clicked.
     *****************************************************************************/
    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        model.gameModel.Point p = new model.gameModel.Point(Rectmech.pxtoRect(x, y));
        // System.out.println("x:" + p.x + "y:"+p.y);
        Entity t = gameController.getBoardCell(p.x, p.y).getEntity();

        // Do nothing if mouse click the area out of bound
        if (p.x < 0 || p.y < 0 || p.x >= Consts.getBSIZE() || p.y >= Consts.getBSIZE()) return;

        if (panel.isScreenLocked() && panel.maskMatrix[p.x][p.y] != 1) {
            // TODO: To call moveTo(Point p) in GridPanelRunnable if no entity in clicked cell

            if (t != null) {
                // Do nothing if this piece is moved
                switch (panel.getAttackMode()) {
                    case Consts.INVOKE_MODE:
                        panel.invoke(p);
                        break;
                    case Consts.ATTACK_MODE:
                    default:
                        panel.attack(p);
                        break;
                }

                return;
            } else {
                // matrixValue == -1 means this cell is selectable
                panel.moveTo(p);
                return;
            }
        } else if(!panel.isScreenLocked() && t != null) {
                // Do nothing if this piece is moved
            if (t.isMoved() || t.getTeam() != gameController.getTeamOnMove()) return;
            // Show action menu of current selected pieces
            panel.showActionMenu(x + Consts.MENU_OFFSET_X
                    , y + Consts.MENU_OFFSET_Y
                    , p
                    , t.getAttackName());

            return;
        }
        //panel.repaint();
    }
    /***************************************************************************
     * To highlight a cell when mouse is hovering over it
     *****************************************************************************/
    @Override
    public void mouseMoved(MouseEvent e) {
        Point p;
        p = new Point(Rectmech.pxtoRect(e.getX(), e.getY()));
        Entity entity;
        try {

            entity = gameController.getBoardCell(p.x, p.y).getEntity();
        } catch (ArrayIndexOutOfBoundsException exception) {
            return;
        }
        // Do nothing if cursor move over the area out of boundary
        if (p.x < 0 || p.y < 0 || p.x >= Consts.getBSIZE() || p.y >= Consts.getBSIZE()) return;
        // Do nothing if cursor move over a cell has a n entity in it
        if (panel.isScreenLocked() && entity != null) return;
        // Do nothing if cursor move over non-selectable area
        if (panel.maskMatrix[p.x][p.y] == 1) return;
        // highlight the cell when cursor is hovering over it.
        if (!p.equals(panel.cursorXYPos)) {
            // Set the color of a cell, which has been hovered over, to normal
            panel.maskMatrix[panel.cursorXYPos.x][panel.cursorXYPos.y] = 0;
            // Keep the coordinator in cursorXYPos
            panel.cursorXYPos = new model.gameModel.Point(p);
            if(entity != null) DashBoard.parseCharInfo(entity.toString());
        }
        panel.maskMatrix[p.x][p.y] = -1;
    }

    public static void update() {
    }

} //end of MxMouseListener class


