package model;


public abstract class Unit implements UnitInterface{

	private static final int INIT_VALUE = 10; // initial value of all attributes
	private static final String COMMANDOR = "Commandor";
	private String mName;
	private int mCamp; // Camp of this unit belongs to
	private int mProf; // Professional class
	private int mStr; // An attribute (strength) for causing damage
	private int mAgi; // An attribute (agility) for moving on the grid map
	private int mHp; // HP
	private int mDef; // Defence
	private Point mPos;
	private boolean mMoved;
	//private int weapon;
	
	public Unit() {
		mName = COMMANDOR;
		mCamp = 0;
		mProf = 1; // Class code of the King
		mStr = INIT_VALUE;
		mAgi = INIT_VALUE;
		mHp = INIT_VALUE;
		mDef = INIT_VALUE;
		mPos = new Point(0, 0);
		mMoved = false;
	}
	
	public Unit(String name,int camp, int prof, int str, int agi, int hp, int def, Point p) {
		mName = name;
		mCamp = camp;
		mProf = prof;
		mStr = str;
		mAgi = agi;
		mHp = hp;
		mDef = def;
		mPos = p;
		mMoved = false;
	}

	@Override
	public void resetTrun() {
		mMoved = false;
	}

	@Override
	public int calSteps(int steps) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void moveTo(Point p) {
		mMoved = true;
	}

	@Override
	public int attack() {
		return 0;
	}

	@Override
	public void beAttacked(int damage) {
		
	}

	public int getmCamp() {
		return mCamp;
	}

	public void setmCamp(int mCamp) {
		this.mCamp = mCamp;
	}

	public int getmProf() {
		return mProf;
	}

	public void setmProf(int mProf) {
		this.mProf = mProf;
	}

	public int getmStr() {
		return mStr;
	}

	public void setmStr(int mStr) {
		this.mStr = mStr;
	}

	public int getmAgi() {
		return mAgi;
	}

	public void setmAgi(int mAgi) {
		this.mAgi = mAgi;
	}

	public int getmHp() {
		return mHp;
	}

	public void setmHp(int mHp) {
		this.mHp = mHp;
	}

	public int getmDef() {
		return mDef;
	}

	public void setmDef(int mDef) {
		this.mDef = mDef;
	}

}
