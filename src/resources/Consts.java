package resources;

/**
 * Created by Human v Alien Team on 2016/4/6.
 */
public final class Consts  {

    public final static int EMPTY = 0;
    // Board Size of this game BSIZE x BSIZE
    public final static int BSIZE = 18;
    // Number of Teams
    public final static int NUM_TEAMS = 2;
    public final static int HUMAN_TEAM_NUM = 0;
    public final static int ALIEN_TEAM_NUM = 1;

    // Rectangle size RECTSIZE x RECTSIZE
    public final static int RECTSIZE = 34;
    public final static int BORDERS = 16;
    // Screen width
    public final static int SCR_WIDTH = 900;
    // Screen height
    public final static int SCR_HEIGHT = 675;
    // Popup Menu offset vertical
    public final static int MENU_OFFSET_X = 16;
    // Popup menu offset horizontal
    public final static int MENU_OFFSET_Y = 10;
    //
    public final static int MAP_X_OFFSET = 236;
    public final static int MAP_Y_OFFSET = 32;


    // Static Strings
    public final static String MOVE = "Move";
    public final static String ATTACK = "Attack";
    public final static String HUMAN = "Human";
    public final static String ALIEN = "Alien";

    // Game functional settings
    public final static int INIT_STEPS = 3;

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

