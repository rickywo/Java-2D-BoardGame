package resources;

/**
 * Created by Human v Alien Team on 2016/4/6.
 */
public final class Consts  {

    public static final int BOARD_HIGHT = 612;
    public static final int BOARD_WIDTH = 612;
    public static final int EMPTY = 0;
    // Board Size of this game BSIZE x BSIZE
    public static int BSIZE = 16;
    // Number of Teams
    public static final int NUM_TEAMS = 2;
    public static final int HUMAN_TEAM_NUM = 0;
    public static final int ALIEN_TEAM_NUM = 1;

    // Rectangle size RECTSIZE x RECTSIZE
    public static final int RECTSIZE = BOARD_WIDTH / BSIZE;
    // Screen width
    public static final int SCR_WIDTH = 849;
    // Screen height
    public static final int SCR_HEIGHT = 669;
    // Popup Menu offset vertical
    public static final int MENU_OFFSET_X = 16;
    // Popup menu offset horizontal
    public static final int MENU_OFFSET_Y = 10;
    //
    public static final int MAP_X_OFFSET = 207;
    public static final int MAP_Y_OFFSET = 29;
    //
    public static final int DASHBOARD_X_OFFSET = 28;
    public static final int DASHBOARD_Y_OFFSET = 186;



    // Static Strings
    public static final String MOVE = "Move";
    public static final String ATTACK = "Attack";
    public static final String INVOKE = "Invoke";
    public static final String HUMAN = "Human";
    public static final String ALIEN = "Alien";
    public static final String[] TEAM_NAME = new String[] {HUMAN, ALIEN};

    // Game functional settings
    public static final int DIST = 4;
    public static int NUM_PIECES_PER_TEAM = 4;
    public static int NUM_WEAPONS = 80;
    public static final int INIT_STEPS = 1;

    // Game variables

    public static final int ATTACK_MODE = 0;
    public static final int INVOKE_MODE = 1;
    /**
     The caller references the constants using <tt>Consts.EMPTY_STRING</tt>,
     and so on. Thus, the caller should be prevented from constructing objects of
     this class, by declaring this private constructor.
     */
    private Consts(){
        //this prevents even the native class from
        //calling this ctor as well :
        throw new AssertionError();
    }

    public static int getBSIZE() {
        return BSIZE;
    }

    public static void setBSIZE(int size) {
        BSIZE = size;
    }

    public static int getNumPiecesPerTeam() {
        return NUM_PIECES_PER_TEAM;
    }

    public static void setNumPiecesPerTeam(int number) {
        NUM_PIECES_PER_TEAM = number;
    }

    public static int getNumWeapons() {
        return  NUM_WEAPONS;
    }

    public static void setNumWeapons(int number) {
        NUM_WEAPONS = number;
    }

    public static int getRectsize() {
        return BOARD_WIDTH / getBSIZE();
    }
}

