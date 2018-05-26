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
//��ʼ����
	private Initial_interface a1;
//��Ϸ����
	private Game_Flame a2;
//Thread
	private Thread frame_thread = new Thread(this);	

//�޲ι���
	public MainFrame()
	{   
		frame_thread.start();
		setUndecorated(true);//ȡ���߿�	
		setResizable(false);//���ý�ֹ�Ķ���С
		setSize(Single_screen_resolution.getSr_width(),Single_screen_resolution.getSr_height());//����Frame��С�����������Ļ��С	
	}
//���س�ʼ����
	void add_Initial_interface() 
	{
		Container ct = getContentPane();
		ct.removeAll();
		a1 = new Initial_interface();
		ct.add(a1.getTrue_all(),BorderLayout.CENTER);//����ʼ�����all�ӵ�Frame��   
		setVisible(true);
		repaint();//ˢ��Frame
		a1.BGMplay();//����BGM
	}
	void reset_game_frame()
	{
		Single_State.setReturn_initial_interface(false);
		if(a2 != null)
			a2.setVisible(false);
		a2 = new Game_Flame();
		a2.game_start();//��Ϸ�߳̿�ʼ
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
					}//�����ʼ�����Ƿ����
				}
				reset_game_frame();
				setVisible(false);//��ǰ��������
				while(Single_State.isExit_game_frame()==false)
					Thread.sleep(3000);
				setVisible(true);
			}
			} catch (InterruptedException e) {e.printStackTrace();}	
			
	}
	
}