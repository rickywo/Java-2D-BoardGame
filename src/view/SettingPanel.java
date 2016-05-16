package view;

import resources.Consts;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class SettingPanel extends JPanel{

    private static final String GAME_SETTINGS = "GAME SETTINGS";
    private static final String BOARD_SIZE = "N x N Board Size";
    private static final String NUMBER_PIECES = "Number of pieces per team";
    private static final String NUMBER_WEAPONS = "Number of weapons on map";
    private static final String START_GAME = "START GAME";
    /**
     * Create the panel.
     */

    // Controller
    private MainPanel mainPanel;
    private JPanel north = new JPanel();
    private JPanel center = new JPanel();
    private JPanel south = new JPanel();
    private JLabel hint_label = new JLabel(GAME_SETTINGS);
    // Member ID component
    private JLabel size_label = new JLabel(BOARD_SIZE);
    private JTextField size_textfield = new JTextField();
    // Member name component
    private JLabel pieceNum_label = new JLabel(NUMBER_PIECES);
    private JTextField pieceNum_textfield = new JTextField();
    // Member contact number component
    private JLabel weaponNum_label = new JLabel(NUMBER_WEAPONS);
    private JTextField weaponNum_textfield = new JTextField();
    // Vocation select radio group
    // Register button component
    private JButton start_button = new JButton(START_GAME);
    private ActionListener mListener = new ActionListener (){

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO register new member into library system
            int bsize = Integer.valueOf(size_textfield.getText());
            int num_pieces = Integer.valueOf(pieceNum_textfield.getText());
            int num_weapons = Integer.valueOf(weaponNum_textfield.getText());
            mainPanel.startGame(bsize, num_pieces, num_weapons);

        }

    };

    public SettingPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        setLayout(new BorderLayout(10, 0));
        setBorder(new EmptyBorder(10, 10, 10, 10));
        initContent();
    }

    private void initContent() {

        initNorthView();
        initCenterView();
        initSouthView();
        add(north, BorderLayout.PAGE_START);
        add(center, BorderLayout.CENTER);
        add(south, BorderLayout.PAGE_END);
    }

    private void initNorthView() {
        north.add(hint_label);
    }

    private void initCenterView() {
        center.setLayout(new GridLayout(0, 2, 10, 0));
        // ID components
        center.add(size_label);
        center.add(size_textfield);
        size_textfield.setText(String.valueOf(Consts.getBSIZE()));
        // Name components
        center.add(pieceNum_label);
        center.add(pieceNum_textfield);
        pieceNum_textfield.setText(String.valueOf(Consts.getNumPiecesPerTeam()));
        // Contact components
        center.add(weaponNum_label);
        center.add(weaponNum_textfield);
        weaponNum_textfield.setText(String.valueOf(Consts.getNumWeapons()));

    }

    private void initSouthView() {
        south.add(start_button);
        start_button.setEnabled(true);
        start_button.addActionListener(mListener);
    }

    public void showDialog(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }
}