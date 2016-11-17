package entity;

import java.awt.Graphics;
import java.util.Random;

import listener.ShapeListener;
import view.GamePanel;

public class Shape {

    private ShapeDiver shapeDiver = new ShapeDiver();
    public int rotate = 0;
    public int type = 2;
    public int time = 300;
    Random r = new Random();

    public static int shapes[][][] = new int[][][]{
    /* 第一种 */{ /** ***** */
            {1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}},

	/* 第二种 */
            { /** ********* */
                    {1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},

                    {0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0},

                    {1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},

                    {1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0}},
    /* 第三种 */
            { /** ******* */
                    {1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},

                    {1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},

                    {0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},

                    {1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0}},
    /* 第四种 */
            { /** ******** */
                    {1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},

                    {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,}},
	/* 第五种 */
            { /** ******** */
                    {0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},

                    {1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0}},
	/* 第六种 */
            { /** *********** */
                    {0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},

                    {0, 1, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0},

                    {0, 0, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0},

                    {0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0}},

	/* 第七种 */
            { /** ********** */
                    {1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},

                    {1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0}},

    };

    ShapeListener listener;
    //shape的坐标
    public int x = 30 * 4, y = 0;

    public void moveDown() {
        y += 30;
        System.out.println("shape move down");

    }

    public void moveRight() {
        x += 30;
        System.out.println("shape move right");
    }

    public void moveLeft() {
        x -= 30;
        System.out.println("shape move Left");
    }

    public void newShape() {
        rotate = 0;
        time = 300;
        x = 30 * 4;
        y = -30;
        type = Math.abs(r.nextInt() % 7);
    }

    private int rotateSwitch(int type) {
        int b = 0;
        switch (type) {
            case 0:b = 0;break;
            case 1:b = 3;break;
            case 2:b = 3;break;
            case 3:b = 1;break;
            case 4:b = 1;break;
            case 5:b = 3;break;
            case 6:b = 1;break;
        }
        return b;
    }

    //旋转
    public void rotate() {
        int a = 0, b;

        b = rotateSwitch(type);
        if (rotate <= b && rotate >= a) {
            rotate++;
            if ((rotate) > b)
                rotate = a;
        }
    }

    public int getNextRotate(int type, int rotate) {
        int a = 0, b = 0;

        b = rotateSwitch(type);
        if (rotate <= b && rotate >= a) {
            rotate++;
            if ((rotate) > b)
                rotate = a;
        }
        return rotate;
    }

    //启动自动下落线程
    public void startFall() {
        shapeDiver.start();
    }

    public void drawMe(Graphics g) {
        for (int i = 0, j = 0; i <= 15; i++) {
            if (i == 4 || i == 8 || i == 12)
                j++;
            if (shapes[type][rotate][i] == 1) {
                g.drawImage(GamePanel.img[type + 1], x + j * 30, y + (i % 4) * 30, 30, 30, null);
            }
        }
    }

    public void addListener(ShapeListener l) {
        if (this.listener == null)
            this.listener = l;
    }


    //内部类，驱动方块自动下落
    private class ShapeDiver extends Thread {
        public void run() {
            while (true) {
                //判断是否可以下落
                if (listener.isShapeMoveDownable() == true) {
                    moveDown();
                    if (listener != null) {
                        listener.moveDown();
                    } else {
						System.out.println("请注册监听器！");
						System.exit(-1);
					}
                    try {
                        Thread.sleep(time);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } else {
                    newShape();
                }
            }
        }
    }
}
