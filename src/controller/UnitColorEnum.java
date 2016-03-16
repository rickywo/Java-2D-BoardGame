package controller;

import java.awt.Color;

public enum UnitColorEnum {
	CHEF(Color.RED);
	
	
	private Color color;
	private UnitColorEnum(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return this.color;
	}
	
}