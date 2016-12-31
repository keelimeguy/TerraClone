package io.github.keelimeguy.TerraClone;

import java.applet.Applet;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.JFrame;

public class Component extends Applet implements Runnable {
	private static final long serialVersionUID = 1L;

	public static int pixelSize = 2;

	public static double sx = 0.0, sy = 0.0;
	public static double dir = 0;

	public static Dimension size = new Dimension(700, 560);
	public static Dimension pixel = new Dimension(size.width / pixelSize, size.height / pixelSize);

	public static Point mse = new Point(0, 0);

	public static String title = "TerraClone: A 2D Minecraft Clone";

	public static boolean running = false;
	public static boolean isMoving = false;
	public static boolean isJumping = false;
	public static boolean isMouseLeft = false;
	public static boolean isMouseRight = false;

	public static int fps = 60;
	private static JFrame frame = null;

	private Image screen;

	public static Level level;
	public static Character character;
	public static Inventory inventory;
	public static Sky sky;

	public Component() {
		setPreferredSize(size);
		addKeyListener(new Keyboard());
		addMouseListener(new Keyboard());
		addMouseMotionListener(new Keyboard());
		addMouseWheelListener(new Keyboard());
	}

	public void start() {
		// Defining objects, etc.
		new Tile(); // Loading images
		level = new Level();
		character = new Character(Tile.tileSize, Tile.tileSize * 2);
		inventory = new Inventory();
		sky = new Sky();

		requestFocus();

		// Starting game loop
		running = true;
		new Thread(this).start();
	}

	public void stop() {
		running = false;
	}

	public void tick() {
		sky.tick();
		level.tick(sx, sy, pixel.width / Tile.tileSize, pixel.height / Tile.tileSize);
		character.tick();
	}

	public void render() {
		Graphics g = screen.getGraphics();

		sky.render(g);

		level.render(g, sx, sy, pixel.width / Tile.tileSize, pixel.height / Tile.tileSize);
		character.render(g);
		inventory.render(g);

		g = getGraphics();
		g.drawImage(screen, 0, 0, size.width, size.height, 0, 0, pixel.width, pixel.height, null);
		g.dispose();
	}

	public void run() {
		/*long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / fps;
		double delta = 0;
		int updates = 0, frames = 0;
		*/

		screen = createVolatileImage(pixel.width, pixel.height);

		while (running) {
			tick();
			render();
			try {
				Thread.sleep(1000 / fps);
			} catch (Exception e) {
			}
			/*
						long now = System.nanoTime();
						delta += (now - lastTime) / ns;
						lastTime = now;
			
						// Update 60 times a second
						while (delta >= 1) {
							tick();
							//timera++;
							updates++;
			
							delta--;
						}
			
						render();
						frames++;
			
						// Keep track of and display the game's ups and fps every second
						if (System.currentTimeMillis() - timer >= 1000) {
							timer += 1000;
							if (frame != null) frame.setTitle(title + " | ups: " + updates + " | fps: " + frames);
							updates = 0;
							frames = 0;
						}*/
		}
	}

	public static void main(String[] args) {
		Component component = new Component();

		frame = new JFrame();
		frame.add(component);
		frame.pack();
		frame.setTitle(title);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		component.start();
	}

}
