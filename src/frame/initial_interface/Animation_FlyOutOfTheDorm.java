package frame.initial_interface;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

import setting.ImageFactory;
import setting.Single_State;
final class Animation_FlyOutOfTheDorm extends JPanel implements Runnable
{
    /**
	 * Paper Plane 1.0 ------written by RecluseXU
	 */
//Image
	Image back_ground = ImageFactory.creatImage("src/Data/background/Initial_interface0.jpg");
//Int
	int i=0;
//Thread
	Thread Changing_background_thread = new Thread(this);
//无参构造
	public Animation_FlyOutOfTheDorm()
	{
		Single_State.setAnimation_FlyOutOfTheDorm_running(true);
	}
//启动线程
	public void start()
	{
		Changing_background_thread.start();
	} 
//Run
	public void run()
	{
		try {	
			Thread.sleep(100);			
			for(;i<=37;i++)
			{	
				Thread.sleep(20);
				back_ground = ImageFactory.creatImage("src/Data/background/Initial_interface"+i+".jpg");
				repaint();
			}
			Single_State.setAnimation_FlyOutOfTheDorm_running(false);
		} catch (InterruptedException e) {e.printStackTrace();}
	}
	
	//重画
	public void paint(Graphics g)
	{
		super.paint(g);//将面板上的东西擦掉
		g.drawImage(back_ground,0,0,this.getWidth(),this.getHeight(),this);
	}
}

