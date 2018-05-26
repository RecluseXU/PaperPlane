package main;

import frame.MainFrame;
import login.Login_Frame;
import mission.pause.Pause_Frame;
import setting.Single_State;
import setting.Single_screen_resolution;

public final class Main
{
	public static void main(String arg[])
	{
		
		Single_screen_resolution.getInstance();
		Single_State.getInstance();
		
		Login_Frame lo = new Login_Frame();
		//Single_State.setLogin(true);
		
		MainFrame a1 = new MainFrame();
		
		Pause_Frame pf = new Pause_Frame();
	}
}