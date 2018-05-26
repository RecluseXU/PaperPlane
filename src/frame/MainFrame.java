package frame;
import java.awt.*;
import javax.swing.JFrame;

import frame.game.Game_Flame;
import frame.initial_interface.Initial_interface;
import setting.Single_State;
import setting.Single_screen_resolution;
public final class MainFrame extends JFrame implements Runnable
{
	/**
	 * Paper Plane 1.0 ------written by RecluseXU
	 */
	private static final long serialVersionUID = -187846701823283415L;
//初始界面
	private Initial_interface a1;
//游戏界面
	private Game_Flame a2;
//Thread
	private Thread frame_thread = new Thread(this);	

//无参构造
	public MainFrame()
	{   
		frame_thread.start();
		setUndecorated(true);//取消边框	
		setResizable(false);//设置禁止改动大小
		setSize(Single_screen_resolution.getSr_width(),Single_screen_resolution.getSr_height());//设置Frame大小等于你电脑屏幕大小	
	}
//加载初始界面
	void add_Initial_interface() 
	{
		Container ct = getContentPane();
		ct.removeAll();
		a1 = new Initial_interface();
		ct.add(a1.getTrue_all(),BorderLayout.CENTER);//将初始界面的all加到Frame中   
		setVisible(true);
		repaint();//刷新Frame
		a1.BGMplay();//播放BGM
	}
	void reset_game_frame()
	{
		Single_State.setReturn_initial_interface(false);
		if(a2 != null)
			a2.setVisible(false);
		a2 = new Game_Flame();
		a2.game_start();//游戏线程开始
	}
//RUN
	public void run()
	{
		try {
			while(Single_State.isExit_all()==false)
			{
				while(Single_State.isLogin()==false)
					Thread.sleep(3000);
				add_Initial_interface();
				while(true)
				{
					Thread.sleep(2000);
					if(Single_State.isAnimation_FlyOutOfTheDorm_running()==false)
					{
						a1.BGMstop();
						break;
					}//检验初始界面是否完毕
				}
				reset_game_frame();
				setVisible(false);//当前界面隐藏
				while(Single_State.isExit_game_frame()==false)
					Thread.sleep(3000);
				setVisible(true);
			}
			} catch (InterruptedException e) {e.printStackTrace();}	
			
	}
	
}