package view;

import entity.Ground;
import entity.Shape;
import until.Until;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

	private Ground ground;
	private Shape shape;
	public static Image img[] = new Image[8];


	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		drawOutline(g);
		shape.drawMe(g);
		ground.drawMe(g);
	}

	public GamePanel() {
		for (int i = 1; i <= 7; i++) {
			img[i] = new ImageIcon("resource/cube" + i + ".png").getImage();
		}
	}

	void drawOutline(Graphics g) {
		//10 17 30 30
		g.setColor(Color.black);
		g.fillRect(0,0, 671, 510);
		g.setColor(Color.orange);
		g.drawLine(300, 0, 300, 510);
		g.setColor(Color.black);
		g.setFont(new Font("Arial", Font.BOLD, 35));
		g.drawString("Score:" + Until.score, 400, 400);
	}

	public void display(Ground ground, Shape shape) {
		this.ground = ground;
		this.shape = shape;
	}
}
