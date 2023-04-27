package graph;

public class Render {

	public final int width;
	public final int height;
	public final int[] pix;

	public Render(int width, int height) {
		this.width = width;
		this.height = height;
		this.pix = new int[width * height];
	}

	public void draw(Render render, int xOffs, int yOffs) {
		for (int y = 0; y < render.height; y++) {
			int pointY = y + yOffs;
			for (int x = 0; x < render.width; x++) {
				int pointX = x + yOffs;
				pix[pointX + pointY * width] = render.pix[x + y * render.width];
			}
		}

	}

}
