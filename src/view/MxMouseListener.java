package view;

import controller.GameController;
import model.gameModel.Entity;
import resources.Consts;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Human v Alien Team on 2016/4/12.
 */
class MxMouseListener extends MouseAdapter {    //inner class inside DrawingPanel

    private GridPanelRunnable panel;
    public MxMouseListener(GridPanelRunnable panel) {
        this.panel = panel;
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
        Point p = new Point(Rectmech.pxtoRect(x, y));
        // System.out.println("x:" + p.x + "y:"+p.y);
        Entity t = panel.board[p.x][p.y].getEntity();

        // Do nothing if mouse click the area out of bound
        if (p.x < 0 || p.y < 0 || p.x >= Consts.BSIZE || p.y >= Consts.BSIZE) return;

        if (t != null) {
            // Do nothing if this piece is moved
            if(t.isMoved()  || t.getTeam() != GameController.getTeamOnMove()) return;
            // Show action menu of current selected pieces
            panel.showActionMenu(x + Consts.MENU_OFFSET_X
                    , y + Consts.MENU_OFFSET_Y
                    , p
                    , panel.board[p.x][p.y].getEntity().getAttackName());
        } else {
            // matrixValue == -1 means this cell is selectable
            if(panel.isScreenLocked() && panel.maskMatrix[p.x][p.y] == -1) {
                panel.moveTo(p);
            }
            return;
        }
        //panel.repaint();
    }
    /***************************************************************************
     * To highlight a cell when mouse is hovering over it
     *****************************************************************************/
    @Override
    public void mouseMoved(MouseEvent e) {
        Point p = new Point(Rectmech.pxtoRect(e.getX(), e.getY()));
        Entity entity;
        try {
            entity = panel.board[p.x][p.y].getEntity();
        } catch (ArrayIndexOutOfBoundsException exception) {
            return;
        }
        // Do nothing if cursor move over the area out of boundary
        if (p.x < 0 || p.y < 0 || p.x >= Consts.BSIZE || p.y >= Consts.BSIZE) return;
        // Do nothing if cursor move over a cell has a n entity in it
        if (panel.isScreenLocked() && entity != null) return;
        // Do nothing if cursor move over non-selectable area
        if (panel.maskMatrix[p.x][p.y] == 1) return;
        // highlight the cell when cursor is hovering over it.
        if (!p.equals(panel.cursorXYPos)) {
            // Set the color of a cell, which has been hovered over, to normal
            panel.maskMatrix[panel.cursorXYPos.x][panel.cursorXYPos.y] = 0;
            // Keep the coordinator in cursorXYPos
            panel.cursorXYPos = p;
            if(entity != null) DashBoard.parseCharInfo(entity.toString());
        }
        panel.maskMatrix[p.x][p.y] = -1;
        //panel.repaint();
    }

    public static void update() {
    }

} //end of MxMouseListener class


