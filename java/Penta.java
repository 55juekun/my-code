package aa.aaaa;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

import javax.swing.JFrame;
class Penta extends JFrame{
    public Penta(){
        super("画个2D图形");
        getContentPane().setBackground(Color.yellow);
        setSize(600,600);
        setVisible(true);
    }
    public void paint(Graphics g) {
        super.paint(g);
        int xPoints[]={(int)(Math.random()*200),
                (int)(Math.random()*300),
                (int)(Math.random()*300),
                (int)(Math.random()*300),
                (int)(Math.random()*300),
                (int)(Math.random()*300),
                (int)(Math.random()*300),
                (int)(Math.random()*300),
                (int)(Math.random()*300),
                (int)(Math.random()*300),};//这儿的数值最好都是随机的确定的数值
        int yPoints[]={(int)(Math.random()*300),
                (int)(Math.random()*300),
                (int)(Math.random()*300),
                (int)(Math.random()*300),
                (int)(Math.random()*300),
                (int)(Math.random()*300),
                (int)(Math.random()*300),
                (int)(Math.random()*300),
                (int)(Math.random()*300),
                (int)(Math.random()*300),};//此处代码因为全取随机值，故出现万花筒的样子
        Graphics2D graphics2d=(Graphics2D)g;
        GeneralPath star =new GeneralPath();
        star.moveTo(xPoints[0], yPoints[0]);
        for(int count=1;count<xPoints.length;count++)
            star.lineTo(xPoints[count], yPoints[count]);
        star.closePath();
        graphics2d.translate(300, 300);
        for(int count=1;count<=2;count++){//画多少个五角星
            graphics2d.rotate(Math.PI/15.0);//每个五角星旋转的角度,360度是2π
            graphics2d.setColor(new Color(
                    (int)(Math.random()*256),
                    (int)(Math.random()*256),
                    (int)(Math.random()*256) ) );
            graphics2d.fill(star);
        }
    }
    public static void main(String args[]) {
        Penta a = new Penta();
        a.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }
}
