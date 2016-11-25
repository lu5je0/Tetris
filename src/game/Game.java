package game;


import controll.Controller;
import entity.Ground;
import entity.MusicPlayer;
import entity.Shape;
import view.GamePanel;

import javax.swing.*;

public class Game {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//创建对象
		Shape shape = new Shape();
		Ground ground = new Ground();
		GamePanel gamePanel = new GamePanel();
		Controller controller = new Controller(shape, gamePanel, ground, new MusicPlayer("resource/gameBG.wav"));

		//显示方块
		gamePanel.display(ground, shape);

		//创建窗体
		JFrame frame = new JFrame("俄罗斯方块");
		frame.setSize(466, 548);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		//添加面板
		frame.add(gamePanel);

		//添加按键监视器
		frame.addKeyListener(controller);
		//添加shape监听器
		shape.addListener(controller);
		ground.addListener(controller);

		//开始下落
		controller.startGame();
	}
}
