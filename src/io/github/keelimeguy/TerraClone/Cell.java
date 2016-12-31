package io.github.keelimeguy.TerraClone;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Cell extends Rectangle {
	private static final long serialVersionUID = 1L;

	public int id[] = Tile.air;

	public Cell(Rectangle size, int[] id) {
		setBounds(size);
		this.id = id;
	}

	public void render(Graphics g, boolean isSelected) {
		if (!isSelected)
			g.drawImage(Tile.tileCell, x, y, width, height, null);
		else
			g.drawImage(Tile.tileCellSelect, x - 1, y - 1, width + 2, height + 2, null);

		if (id != Tile.air) g.drawImage(Tile.tilesetTerrain, x + Tile.invItemBorder, y + Tile.invItemBorder, x - Tile.invItemBorder + width, y - Tile.invItemBorder + height, id[0] * Tile.tileSize, id[1] * Tile.tileSize, id[0] * Tile.tileSize + Tile.tileSize, id[1] * Tile.tileSize + Tile.tileSize, null);
	}

}
