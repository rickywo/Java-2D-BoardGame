package model;


public class Entity implements EntityInterface{

	private static final int INIT_VALUE = 10; // initial value of all attributes
	private static final String COMMANDOR = "Commandor";
	private String mName;
	private int mCamp; // Camp of this unit belongs to
	private Profession mProf; // Professional class
	private int mStr; // An attribute (strength) for causing damage
	private int mAgi; // An attribute (agility) for moving on the grid map
	private int currentHP; // HP
	private int maxHP; //max HP
	private int mDef; // Defence
	private Point mPos;
	private boolean mMoved;
	//private int weapon;
	
	public Entity() {
		mName = COMMANDOR;
		mCamp = 0;
		mProf = null; // Class code of the King
		mStr = INIT_VALUE;
		mAgi = INIT_VALUE;
		maxHP = INIT_VALUE;
		currentHP = INIT_VALUE;
		mDef = INIT_VALUE;
		mPos = new Point(0, 0);
		mMoved = false;
	}
	
	public Entity(String name,int camp, Profession prof, int str, int agi, 
			int hp, int cHp, int def, Point p) {
		mName = name;
		mCamp = camp;
		mProf = prof;
		mStr = str;
		mAgi = agi;
		maxHP = hp;
		currentHP = cHp;
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

	public Profession getmProf() {
		return mProf;
	}

	public void setmProf(Profession mProf) {
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

	public int getMaxHP() {
		return maxHP;
	}

	public void setMaxHP(int mHp) {
		this.maxHP = mHp;
	}
	
	public int currentHP() {
		return currentHP;
	}

	public void setCurrentHP(int mHp) {
		this.currentHP = mHp;
	}

	public int getmDef() {
		return mDef;
	}

	public void setmDef(int mDef) {
		this.mDef = mDef;
	}
	

}
