package io.github.keelimeguy.TerraClone;

import java.awt.Color;
import java.awt.Graphics;

public class Sky {
	public int night = 1, day = 0;
	public int time = night;

	public int r1 = 70, g1 = 120, b1 = 230;
	public int r2 = 15, g2 = 15, b2 = 80;
	public int r = r1, g = g1, b = b1;
	public double dayFrame = 1, dayTime = 60.0 * (double) Component.fps;
	public double changeFrame = 1, changeTime = (double) Component.fps / 15;

	public Sky() {
		if (time == day) {
			r = r2;
			g = g2;
			b = b2;
		} else if (time == night) {
			r = r1;
			g = g1;
			b = b1;
		}
	}

	public void tick() {
		if (dayFrame >= dayTime) {
			if (time == day) {
				time = night;
			} else if (time == night) time = day;
			dayFrame = 1;
		} else {
			dayFrame++;
		}
		if (changeFrame >= changeTime) {
			if (time == day) {
				if (r > r2) r--;
				if (g > g2) g--;
				if (b > b2) b--;
			} else if (time == night) {
				if (r < r1) r++;
				if (g < g1) g++;
				if (b < b1) b++;
			}
			changeFrame = 1;
		} else {
			changeFrame++;
		}
	}

	public void render(Graphics gr) {
		gr.setColor(new Color(r, g, b));
		gr.fillRect(0, 0, Component.pixel.width, Component.pixel.height);
	}
}
