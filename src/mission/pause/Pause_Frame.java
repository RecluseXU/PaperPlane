package mission.pause;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import setting.Single_State;
import setting.Single_screen_resolution;

public final class Pause_Frame extends JFrame implements Runnable{
	/**
	 * 
	 */

	Thread t = new Thread(this);
	public Pause_Frame()
	{
		setUndecorated(true);//取消边框
		setResizable(false);//设置禁止改动大小
		setOpacity(0.7f);
		setSize(Single_screen_resolution.getSr_width(),Single_screen_resolution.getSr_height());//设置Frame大小等于你电脑屏幕大小
		
		Pause_Panel a1 = new Pause_Panel();
		getContentPane().add(a1, BorderLayout.CENTER);
		setVisible(false);//设置可见
		t.start();
	}

	public void run() 
	{
		while(true)
		{
			if(Single_State.isPause())
				setVisible(true);
			else
				setVisible(false);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}

