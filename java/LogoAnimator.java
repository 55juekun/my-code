package carrton;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**LogoAnimator.java
 * @author 55珏坤
 *LogoAnimator下午12:10:48
 *无法运行
 */
public class LogoAnimator extends JPanel implements ActionListener{
	protected ImageIcon images[];
	protected int totalImages=3,currentImage=0,animationDelay=50,
			width,height;
	protected String imageName="a";
	protected Timer animationtTimer;
	
	public  LogoAnimator() {
		initializeAnimation();
	}
	
	protected void initializeAnimation() {
		images=new ImageIcon[totalImages];
		for (int i = 0; i < images.length; i++) {
			images[i]=new ImageIcon(getClass().getResource("images/"+imageName+i+".gif"));
			width=images[0].getIconWidth();
			height=images[0].getIconHeight();
		}
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		images[currentImage].paintIcon(this, g, 0, 0);
		currentImage=(currentImage+1)%totalImages;
	}
	public void actionPerformed(ActionEvent actionEvent) {
		repaint();
	}
	public void startAnimation() {
		if (animationtTimer==null) {
			currentImage=0;
			animationtTimer=new Timer(animationDelay, this);
			animationtTimer.restart();
		}
		else if (!animationtTimer.isRunning()) {
			animationtTimer.restart();
		}
	}
	public void stopAnimation() {
		animationtTimer.stop();
	}
	public Dimension getPreferredSize() {
		return new Dimension(width,height);
	}
	
	public static void main(String[] args) {
		LogoAnimator animation=new LogoAnimator();
		JFrame window=new JFrame("Animator test");
		
		Container container=window.getContentPane();
		container.add(animation);
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		Insets insets=window.getInsets();
		
		window.setSize(animation.getPreferredSize().width+
				insets.left+insets.right, animation.getPreferredSize().height+
				insets.top+insets.bottom);
		window.setVisible(true);
		animation.startAnimation();
	}

}
