package io.github.keelimeguy.TerraClone;

import java.awt.Graphics;
import java.awt.Point;

public class Character extends DoubleRectangle {
	public double fallingSpeed = (10 * Tile.tileSize) / (double) Component.fps;
	public double movementSpeed = (4 * Tile.tileSize) / (double) Component.fps;
	public double jumpingSpeed = fallingSpeed;

	public double jumpingHeight = (double) Component.fps / 3.0, jumpingCount = 1;
	public double animFrame = 1, animTime = (double) Component.fps / 5.0;
	public int anim = 0;

	public boolean isJumping = false;

	public Character(int width, int height) {
		setBounds(Component.pixel.width / 2 - width / 2 + (int) Component.sx, Component.pixel.height / 2 - height / 2 + (int) Component.sy, width, height);
	}

	public void tick() {
		if (!isJumping && !isCollidingWithBlock(new Point((int) x + 2, (int) (y + height)), new Point((int) (x + width - 2), (int) (y + height)))) {
			y += fallingSpeed;
			Component.sy += fallingSpeed;
		} else {
			if (Component.isJumping && !isJumping) {
				isJumping = true;
			}
		}
		if (Component.isMoving) {
			boolean canMove = false;

			if (Component.dir == movementSpeed) {
				canMove = !isCollidingWithBlock(new Point((int) (x + width), (int) y), new Point((int) (x + width), (int) (y + height - 2)));
			} else if (Component.dir == -movementSpeed) {
				canMove = !isCollidingWithBlock(new Point((int) x, (int) y), new Point((int) x, (int) (y + height - 2)));
			}

			if (animFrame >= animTime) {
				if (anim == 0)
					anim = 1;
				else
					anim = (anim) % 2 + 1;
				animFrame -= animTime;
			} else {
				animFrame++;
			}

			if (canMove) {
				x += Component.dir;
				Component.sx += Component.dir;
			}
		} else
			anim = 0;
		if (isJumping) {
			if (!isCollidingWithBlock(new Point((int) x + 2, (int) y), new Point((int) (x + width - 2), (int) y))) {
				if (jumpingCount >= jumpingHeight) {
					jumpingCount = 1;
					isJumping = false;
				} else {
					jumpingCount++;
					Component.sy -= jumpingSpeed;
					y -= jumpingSpeed;
				}
			} else {
				isJumping = false;
				jumpingCount = 1;
			}
		}
	}

	public boolean isCollidingWithBlock(Point pt1, Point pt2) {
		for (int x = (int) (this.x / Tile.tileSize); x < (int) (this.x / Tile.tileSize + 3); x++) {
			for (int y = (int) (this.y / Tile.tileSize); y < (int) (this.y / Tile.tileSize + 3); y++) {
				if (x >= 0 && y >= 0 && x < Component.level.block.length && y < Component.level.block[0].length) if (Component.level.block[x][y].id != Tile.air) if (Component.level.block[x][y].contains(pt1) || Component.level.block[x][y].contains(pt2)) return true;
			}
		}
		return false;
	}

	public void render(Graphics g) {
		//if (Component.dir == -movementSpeed)
			//g.drawImage(Tile.tilesetTerrain, (int) (x - Component.sx), (int) (y - Component.sy), (int) (x + width - Component.sx), (int) (y + height - Component.sy), Tile.character[0] * Tile.tileSize + Tile.tileSize * anim + (int) width, Tile.character[1] * Tile.tileSize, Tile.character[0] * Tile.tileSize + Tile.tileSize * anim, Tile.character[1] * Tile.tileSize + (int) height, null);
		//else
			g.drawImage(Tile.tilesetTerrain, (int) (x - Component.sx), (int) (y - Component.sy), (int) (x + width - Component.sx), (int) (y + height - Component.sy), Tile.character[0] * Tile.tileSize + Tile.tileSize * anim, Tile.character[1] * Tile.tileSize, Tile.character[0] * Tile.tileSize + (int) width + Tile.tileSize * anim, Tile.character[1] * Tile.tileSize + (int) height, null);

	}
}
