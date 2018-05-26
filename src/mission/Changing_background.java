package mission;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

import setting.ImageFactory;
import setting.Single_State;
import setting.Single_screen_resolution;
final class Changing_background extends JPanel implements Runnable
{
    /**
	 * Paper Plane 1.0 ------written by RecluseXU
	 */
	private static final long serialVersionUID = -6103684600528378343L;
//Image
	private Image back_ground_start;
	private Image back_black;
	private Image back_ground_end;
//Int
	private int i = -400;
	private int width_start;
	private int width_end;
//Thread
	private Thread Changing_background_thread=new Thread(this);
//无参构造
	public Changing_background(Image start,int startwid,Image end,int endwid)
	{
		setBounds(0, 0,Single_screen_resolution.getSr_width(),Single_screen_resolution.getSr_height());
		back_black = ImageFactory.creatImage("src/Data/background/mission one/mission_one_start.png");
		back_ground_start = start;
		back_ground_end = end;
		width_start = startwid;
		width_end = endwid;
	}

//reset
	public void reset()
	{
		Single_State.setChanging_background_start(true);
		Single_State.setChanging_background_end(false);
		Single_State.setChanging_background_allend(false);	
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
			for(;i<=(Single_screen_resolution.getSr_width());i=i+16)
			{
				repaint();
				Thread.sleep(20);
			}
			Single_State.setChanging_background_start(false);
		while(true)
		{
			Thread.sleep(500);
			if(Single_State.isChanging_background_end())break;
		}
		i=width_end;
		for(;i>=-300;i=i-20)
		{
			Thread.sleep(20);
			repaint();
		}
		Single_State.setChanging_background_allend(true);
		Thread.sleep(3000);
			
		} catch (InterruptedException e) {e.printStackTrace();}
	}
	
//重画
	public void paint(Graphics g)
	{
		if(Single_State.isChanging_background_start())
		{
			super.paint(g);//将面板上的东西擦掉
			g.drawImage(back_ground_start,0,0,width_start,this.getHeight(),this);
			g.drawImage(back_black,i,0,width_start+500,this.getHeight(),this);
		}
		if(Single_State.isChanging_background_end())
		{
			super.paint(g);//将面板上的东西擦掉
			g.drawImage(back_ground_end,Single_screen_resolution.getSr_width()-width_end,0,width_end,this.getHeight(),this);
			g.drawImage(back_black,i,0,width_end+500,this.getHeight(),this);
		}
	}
}


