package view;

import entity.Ground;
import entity.Shape;
import until.Until;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    Ground ground;
    Shape shape;
    public static Image img[] = new Image[8];
    public Image bg = new ImageIcon("resource/bgb.png").getImage();

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
        g.drawImage(bg, 0, 0, null);
        g.setFont(new Font("Arial", Font.BOLD, 35));
        g.drawString("Score:" + Until.score, 400, 400);
    }

    public void display(Ground ground, Shape shape) {
        this.ground = ground;
        this.shape = shape;
    }
}
