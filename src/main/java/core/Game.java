package core;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import graph.Render;
import graph.Screen;

public class Game extends Canvas implements Runnable {

	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final String TITLE = "MineKraft 0.1";

	private Thread thread;
	private boolean isRun = false;
	private Screen screen;
	private Render render;
	private BufferedImage img;
	private int[] pix;

	public Game() {
		this.screen = new Screen(WIDTH, HEIGHT);
		img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		pix = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();
	}

	private void start() {
		if (!isRun) {
			this.isRun = true;
			this.thread = new Thread(this);
			this.thread.start();
		}
	}

	private void stop() {
		if (isRun) {
			this.isRun = false;
			try {
				thread.join();
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
	}

	public void run() {
		while (isRun) {
			time();
			render();
		}
	}

	private void time() {
		// TODO Auto-generated method stub

	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		screen.render();

		for (int i = 0; i < WIDTH * HEIGHT; i++) {
			pix[i] = screen.pix[i];
		}

		Graphics g = bs.getDrawGraphics();
		g.drawImage(img, 0, 0, WIDTH, HEIGHT, null);
		g.dispose();
		bs.show();

	}

	public static void main(String[] args) {
		Game game = new Game();
		JFrame frame = new JFrame();
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setTitle(TITLE);
		frame.setLocationRelativeTo(null);
		game.start();
	}

}
