package view;

import controller.GameController;
import model.Entity;
import resources.Consts;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Human v Alien Team on 2016/4/12.
 */
class MxMouseListener extends MouseAdapter {    //inner class inside DrawingPanel

    private GridPanel panel;
    public MxMouseListener(GridPanel panel) {
        this.panel = panel;
    }

    public void mouseClicked(MouseEvent e) {


        int x = e.getX();
        int y = e.getY();
        Point p = new Point(Rectmech.pxtoRect(x, y));
        Entity t = panel.board[p.x][p.y].getEntity();
        System.out.println("px:" + p.x + ", py:" + p.y);
        // Do nothing if mouse click the area out of bound
        if (p.x < 0 || p.y < 0 || p.x >= Consts.BSIZE || p.y >= Consts.BSIZE) return;

        if (t != null) {
            // Do nothing if this piece is moved
            if(t.isMoved()  || t.getTeam() != GameController.getTeamOnMove()) return;
            // Show action menu of current selected pieces
            panel.showPopupMenuDemo(x + Consts.MENU_OFFSET_X
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
        panel.repaint();
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
        if (panel.isScreenLocked() && panel.board[p.x][p.y].getEntity()!=null) return;
        if (panel.maskMatrix[p.x][p.y] == 1) return;
        if (!p.equals(panel.cursorXYPos)) {
            panel.maskMatrix[panel.cursorXYPos.x][panel.cursorXYPos.y] = 0;
            panel.cursorXYPos = p;
        }
        panel.maskMatrix[p.x][p.y] = -1;
        panel.repaint();
    }

} //end of MxMouseListener class


