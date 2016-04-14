package resources;

/**
 * Created by Human v Alien Team on 2016/4/6.
 */
public final class Consts  {

    private static final int BOARD_HIGHT = 612;
    private static final int BOARD_WIDTH = 612;
    public static final int EMPTY = 0;
    // Board Size of this game BSIZE x BSIZE
    public static final int BSIZE = 18;
    // Number of Teams
    public static final int NUM_TEAMS = 2;
    public static final int HUMAN_TEAM_NUM = 0;
    public static final int ALIEN_TEAM_NUM = 1;

    // Rectangle size RECTSIZE x RECTSIZE
    public static final int RECTSIZE = BOARD_WIDTH / BSIZE;
    public static final int BORDERS = 16;
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


    // Static Strings
    public static final String MOVE = "Move";
    public static final String ATTACK = "Attack";
    public static final String HUMAN = "Human";
    public static final String ALIEN = "Alien";

    // Game functional settings
    public final static int DIST = 4;
    public static final int NUM_PIECES_PER_TEAM = 4;
    public static final int NUM_WEAPONS = 20;
    public static final int INIT_STEPS = 3;

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
}

