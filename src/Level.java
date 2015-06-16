package io.github.keelimeguy.TerraClone;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;

public class Level {
	public int worldW = 100, worldH = 50;
	public Block[][] block = new Block[worldW][worldH];

	public Level() {
		for (int x = 0; x < block.length; x++) {
			for (int y = 0; y < block[0].length; y++) {
				block[x][y] = new Block(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.air);
			}
		}
		generateLevel();
	}

	public void generateLevel() {
		// Making mountains
		for (int y = 0; y < block[0].length; y++) {
			for (int x = 0; x < block.length; x++) {
				if (y > worldH / 4) {

					try {
						if (new Random().nextInt(100) > 20) if (block[x - 1][y - 1].id == Tile.earth) block[x][y].id = Tile.earth;

						if (new Random().nextInt(100) > 30) if (block[x + 1][y - 1].id == Tile.earth) block[x][y].id = Tile.earth;

						if (block[x][y - 1].id == Tile.earth) block[x][y].id = Tile.earth;

					} catch (Exception e) {
					}

					if (new Random().nextInt(100) < 2) block[x][y].id = Tile.earth;
				}
				if (x == 0 || y == 0 || x == block.length - 1 || y == block[0].length - 1) block[x][y].id = Tile.obsidian;
			}
		}

		// Placing grass
		for (int y = 1; y < block[0].length; y++) {
			for (int x = 0; x < block.length; x++) {
				if (block[x][y].id == Tile.earth && block[x][y - 1].id == Tile.air) block[x][y].id = Tile.grass;
			}
		}
	}

	public void building(double sx, double sy, int width, int height) {
		if (Component.isMouseLeft)
			for (int x = (int) (sx / Tile.tileSize); x < (int) (sx / Tile.tileSize) + width; x++) {
				for (int y = (int) (sy / Tile.tileSize); y < (int) (sy / Tile.tileSize) + height; y++) {
					if (x >= 0 && y >= 0 && x < worldW && y < worldH) {
						if (block[x][y].id != Tile.obsidian && block[x][y].contains(new Point((int) (Component.mse.x / 2.0 + sx), (int) (Component.mse.y / 2.0 + sy)))) {
							block[x][y].id = Tile.air;
							break;
						}
					}
				}
			}
		else if (Component.isMouseRight) for (int x = (int) (sx / Tile.tileSize); x < (int) (sx / Tile.tileSize) + width; x++) {
			for (int y = (int) (sy / Tile.tileSize); y < (int) (sy / Tile.tileSize) + height; y++) {
				if (x >= 0 && y >= 0 && x < worldW && y < worldH) {
					if (block[x][y].id != Tile.obsidian && block[x][y].contains(new Point((int) (Component.mse.x / 2.0 + sx), (int) (Component.mse.y / 2.0 + sy)))) {
						if (Component.inventory.invBar[Inventory.selected].id != Tile.air) {
							block[x][y].id = Component.inventory.invBar[Inventory.selected].id;
							if (block[x][y + 1].id == Tile.grass) block[x][y + 1].id = Tile.earth;
						}
						break;
					}
				}
			}
		}
	}

	public void tick(double sx, double sy, int width, int height) {
		building(sx, sy, width, height);
	}

	public void render(Graphics g, double sx, double sy, int width, int height) {
		for (int x = (int) (sx / Tile.tileSize); x < (int) (sx / Tile.tileSize) + width + 2; x++) {
			for (int y = (int) (sy / Tile.tileSize); y < (int) (sy / Tile.tileSize) + height + 2; y++) {
				if (x >= 0 && y >= 0 && x < worldW && y < worldH) block[x][y].render(g);
			}
		}
	}
}
