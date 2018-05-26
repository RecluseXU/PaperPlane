package frame.game;

import java.awt.*;
import javax.swing.JFrame;

import setting.Single_State;
import setting.Single_screen_resolution;

public final class Game_Flame extends JFrame 
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = -2725614630074242015L;
		//初始界面
		Game a1;
	//无参构造
		public Game_Flame()
		{   
			Single_State.setExit_game_frame(false);
			setUndecorated(true);//取消边框
			setResizable(false);//设置禁止改动大小
			setSize(Single_screen_resolution.getSr_width(),Single_screen_resolution.getSr_height());//设置Frame大小等于你电脑屏幕大小
			a1 = new Game();
			getContentPane().add(a1, BorderLayout.CENTER);
		}
		public void game_start()
		{
			setVisible(true);//设置可见	
			a1.start();	
		}
		public void game_end()
		{
			setVisible(false);
		}

}
