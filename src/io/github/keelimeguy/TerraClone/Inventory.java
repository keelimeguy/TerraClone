package io.github.keelimeguy.TerraClone;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Inventory {
	public Cell[] invBar = new Cell[Tile.invLength];
	public static int selected = 0;

	public Inventory() {
		for (int i = 0; i < invBar.length; i++) {
			invBar[i] = new Cell(new Rectangle(Component.pixel.width / 2 - (Tile.invLength * (Tile.invCellSize + Tile.invCellSpace)) / 2 + i * (Tile.invCellSize + Tile.invCellSpace), Component.pixel.height - Tile.invCellSize - Tile.invBorderSpace, Tile.invCellSize, Tile.invCellSize), Tile.air);
		}
		invBar[0].id = Tile.earth;
		invBar[1].id = Tile.grass;
		invBar[2].id = Tile.sand;
	}

	public void render(Graphics g) {
		for (int i = 0; i < invBar.length; i++) {
			invBar[i].render(g, (i == selected));
		}
	}
}
