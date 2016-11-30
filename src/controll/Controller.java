package controll;

import entity.Ground;
import entity.MusicPlayer;
import entity.Shape;
import listener.GroundListener;
import listener.ShapeListener;
import until.Until;
import view.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener, ShapeListener, GroundListener {

	Shape shape;
	Ground ground;
	GamePanel gamePanel;
	MusicPlayer musicPlayer;

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		int dir = e.getKeyCode();

		switch (dir) {
			case KeyEvent.VK_UP:
			case KeyEvent.VK_W:
				if (isCanRotate() == true) {
					shape.rotate();
				}
				break;
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_A:
				if (isShapeMove('l') == true) {
					shape.moveLeft();
				}
				break;
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_S:
				shape.time = 20;
				break;
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_D:
				if (isShapeMove('r') == true) {
					shape.moveRight();
				}
				break;
		}
		gamePanel.repaint();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	//构造方法
	public Controller(Shape shape, GamePanel gamePanel, Ground ground, MusicPlayer musicPlayer) {
		this.shape = shape;
		this.gamePanel = gamePanel;
		this.ground = ground;
		this.musicPlayer = musicPlayer;
	}

	@Override
	public void moveDown() {
		// TODO Auto-generated method stub
		gamePanel.repaint();
	}

	public void removeLine(int time) {
		System.out.println("消了" + time + "行");
		switch (time) {
			case 1:
				Until.score += 100;
				break;
			case 2:
				Until.score += 400;
				break;
			case 3:
				Until.score += 800;
				break;
			case 4:
				Until.score += 1600;
				break;
			default:
				Until.score += 3200;
				break;
		}
	}

	public boolean isShapeMove(char dir) {
		int offset = -1;
		if (dir == 'r')
			offset = 1;
		for (int i = 0, j = 0; i <= 15; i++) {
			if (i == 4 || i == 8 || i == 12)
				j++;
			if (shape.shapes[shape.type][shape.rotate][i] == 1) {
				System.out.println((shape.x / 30 + j) + "," + (shape.y / 30 + (i % 4)));
				if (shape.x / 30 + j + offset < 0 || shape.x / 30 + j + offset > 9)
					return false;
				if (ground.gro[shape.x / 30 + j + offset][shape.y / 30 + (i % 4)] != 0) {
					return false;
				}
			}
		}

		return true;
	}

	public boolean isCanRotate() {
		for (int i = 0, j = 0; i <= 15; i++) {
			if (i == 4 || i == 8 || i == 12)
				j++;
			if (shape.shapes[shape.type][shape.getNextRotate(shape.type, shape.rotate)][i] == 1) {
				System.out.println((shape.x / 30 + j) + "," + (shape.y / 30 + (i % 4)));
				if (shape.x / 30 + j < 0 || shape.x / 30 + j > 9)
					return false;
				if (ground.gro[shape.x / 30 + j][shape.y / 30 + (i % 4)] != 0) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public boolean isShapeMoveDownable() {
		// TODO Auto-generated method stub
		//i为x
		for (int i = 15, j = 0; i >= 0; i--) {
			if (i == 4 || i == 8 || i == 12)
				j++;
			if (shape.shapes[shape.type][shape.rotate][i] == 1) {
				//判断是否越界
				if ((shape.y / 30 + i % 4) >= 16) {
					getShape();
					return false;
				}
			}

		}
		//判断是否与ground相碰
		for (int i = 0, j = 0; i <= 15; i++) {
			if (i == 4 || i == 8 || i == 12)
				j++;
			if (shape.shapes[shape.type][shape.rotate][i] == 1) {
				System.out.println((shape.x / 30 + j) + "," + (shape.y / 30 + (i % 4)));
				if (ground.gro[shape.x / 30 + j][shape.y / 30 + (i % 4) + 1] != 0) {
					getShape();
					return false;
				}
			}
		}
		return true;
	}

	private void getShape() {
		try {
			ground.getShape(shape);
		} catch (Exception e) {
			gameOver();
		}
	}

	//处理游戏失败
	private void gameOver() {
		System.out.println("Game Over");
		//停止音乐播放
		musicPlayer.stopMusic();
		System.exit(1);
	}

	//开始游戏
	public void startGame() {
		shape.startFall();
		musicPlayer.loop();
	}
}
