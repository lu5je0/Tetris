package entity;

import listener.GroundListener;
import view.GamePanel;

import java.awt.*;

public class Ground {

	public int[][] gro = new int[10][17];
	GroundListener listener;

	public void drawMe(Graphics g) {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 17; j++) {
				if (gro[i][j] != 0) {
					g.drawImage(GamePanel.img[gro[i][j]], i * 30, j * 30, 30, 30, null);
				}
			}
		}
	}

	//消除行
	public void remove() {
		int score = 0;
		for (int j = 0; j < 17; j++) {
			for (int i = 0, a = 0; i < 10; i++) {
				if (gro[i][j] != 0)
					a++;
				//如果满了
				if (a == 10) {
					score++;
					new AePlayWave("resource/delete.wav").start();
					for (int y = j; y > 0; y--) {
						for (int x = 0; x < 10; x++) {
							gro[x][y] = gro[x][y - 1];
						}
					}
				}
			}
		}
		if (score != 0) {
			listener.removeLine(score);
		}
	}

	//将方块添加到groud中
	public void getShape(Shape shape) throws Exception {
		for (int i = 0, j = 0; i <= 15; i++) {
			if (i == 4 || i == 8 || i == 12)
				j++;
			if (shape.shapes[shape.type][shape.rotate][i] == 1) {
				gro[j + shape.x / 30][i % 4 + shape.y / 30] = shape.type + 1;
			}
		}
		remove();
	}

	public void addListener(GroundListener l) {
		if (this.listener == null)
			this.listener = l;
	}

}
