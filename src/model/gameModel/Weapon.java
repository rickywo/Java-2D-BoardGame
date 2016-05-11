package model.gameModel;


public class Weapon {

	public enum Weapons {
		CANNON,
		GUN,
		MAGICALHANDS,
		SHIELD,
		COMBATKIT,
		FLAG;

	}

	private static final String[] weapons = {
			"Cannon",
			"Gun",
			"Magical Hands",
			"Shield",
			"Combat Kit",
			"Flag"
	};
	
	//stores 1 human job, 1 alien job
	private Weapons weaponName;
	private String name;
	private int xPos;
	private int yPos;
	
	public Weapon(int weaponNumber) {
		this.weaponName = Weapons.values()[weaponNumber];
		name = weapons[weaponNumber];
	}

	public Weapons getType() {
		return weaponName;
	}
	
	public String getName(){
		return name;
	}
	
	public void setPos(int x, int y){
		this.xPos = x;
		this.yPos = y;
	}
	
	public int getXPos(){
		return xPos;
	}
	
	public int getYPos(){
		return yPos;
	}
	
	public void printWeaponInfo(){
		System.out.println("Name: " + name);
		System.out.println("Coords: " + xPos + ", " + yPos);
	}

}
