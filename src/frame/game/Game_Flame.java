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
		//��ʼ����
		Game a1;
	//�޲ι���
		public Game_Flame()
		{   
			Single_State.setExit_game_frame(false);
			setUndecorated(true);//ȡ���߿�
			setResizable(false);//���ý�ֹ�Ķ���С
			setSize(Single_screen_resolution.getSr_width(),Single_screen_resolution.getSr_height());//����Frame��С�����������Ļ��С
			a1 = new Game();
			getContentPane().add(a1, BorderLayout.CENTER);
		}
		public void game_start()
		{
			setVisible(true);//���ÿɼ�	
			a1.start();	
		}
		public void game_end()
		{
			setVisible(false);
		}

}
