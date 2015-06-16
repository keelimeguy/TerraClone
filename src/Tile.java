package io.github.keelimeguy.TerraClone;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Tile {
	public static int tileSize = 20;
	public static int invLength = 8;
	public static int invCellSize = 25;
	public static int invCellSpace = 4;
	public static int invBorderSpace = 4;
	public static int invItemBorder = 3;

	public static int[] air = { -1, -1 };
	public static int[] earth = { 0, 0 };
	public static int[] grass = { 1, 0 };
	public static int[] sand = { 2, 0 };
	public static int[] obsidian = { 3, 0 };
	public static int[] character = { 0, 18 };

	public static BufferedImage tilesetTerrain;
	public static BufferedImage tileCell, tileCellSelect;

	public Tile() {
		try {
			Tile.tilesetTerrain = ImageIO.read(new File("res/tileset_terrain.png"));
			Tile.tileCell = ImageIO.read(new File("res/cell.png"));
			Tile.tileCellSelect = ImageIO.read(new File("res/cell_select.png"));
		} catch (Exception e) {
		}
	}
}
