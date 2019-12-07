package com.wojto.wmcase.enums;

public enum Color {
	BLACK ("Czarny"), 
	RED ("Czerwony"),
	BLUE ("Niebieski"),
	GREY ("Szary"), 
	YELLOW ("Żółty"),
	ORANGE ("Pomarańczowy"),
	WHITE ("Biały"),
	TURQUISE ("Turkusowy"),
	OTHER ("Inny");
	
	public final String color;

	private Color(String color) {
		this.color = color;
	}

	public String getColor() {
		return color;
	}
	
	
}
