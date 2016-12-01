package view;

import entity.Ground;
import entity.Shape;
import util.Util;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class GamePanel extends JPanel {

	private Ground ground;
	private Shape shape;
	public static final Image img[] = new Image[8];

	static {
		for (int i = 1; i <= 7; i++) {
			img[i] = new ImageIcon("resource/cube" + i + ".png").getImage();
		}
	}


	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		drawOutline(g);
		shape.drawMe(g);
		ground.drawMe(g);
	}

	public GamePanel() {
	}

	void drawOutline(Graphics g) {
		//10 17 30 30
		g.setColor(Color.black);
		g.fillRect(0, 0, 671, 510);
		g.setColor(Color.gray);
		g.drawLine(300, 0, 300, 510);
		g.drawLine(300, 250, 671, 250);
		g.setFont(new Font("Consolas", Font.BOLD, 28));
		g.drawString("score", 340, 100);
		g.drawString("" + String.format("%04d", Util.score), 344, 150);
		g.drawString("next", 345, 320);
	}

	public void display(Ground ground, Shape shape) {
		this.ground = ground;
		this.shape = shape;
	}
}
