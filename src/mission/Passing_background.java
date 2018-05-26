package mission;

import java.awt.Image;
import setting.Single_State;
import setting.Single_screen_resolution;
final class Passing_background {
	
	/**
	 * Paper Plane 1.0 ------written by RecluseXU
	 */

//Image
	final Image back_ground_start;
    final Image back_ground_pass;
	final Image back_ground_end;
//Int
	final int width_start;
	final int width_pass;
	final int width_end;

//6参构造
	public Passing_background(Image strat,Image pass,Image end,int start_width,int pass_width,int end_width)
	{
		back_ground_start = strat;
		back_ground_pass = pass;
		back_ground_end = end;
		width_start = start_width;
		width_pass = pass_width;
		width_end = end_width;
	}
	
//判断是否锁定飞机
	public boolean Lock_or_not()
	{
		if(Single_State.getNow_X() >= Single_screen_resolution.getSr_width()/2 && Single_State.getNow_X()<=(Single_screen_resolution.getSr_width()/2+width_start+2*width_pass))
			return true;
		return false;
	}

//移动背景
	public void Next_X(int oC,int v)
	{
		if(315<oC&&oC<=360)
		{
			Single_State.setNow_X(Single_State.getNow_X()-0.4*v*Math.cos((oC-315)*Math.PI/360));
		}
		else if(270<oC)
		{
			Single_State.setNow_X(Single_State.getNow_X()-0.4*v*Math.sin((oC-275)*Math.PI/360));
		}
		else if(225<oC)
		{
			Single_State.setNow_X(Single_State.getNow_X()+0.4*v*Math.sin((oC-225)*Math.PI/360));
		}
		else if(180<oC)
		{
			Single_State.setNow_X(Single_State.getNow_X()+0.4*v*Math.cos((oC-180)*Math.PI/360));
		}	
		else if(135<oC)
		{
			Single_State.setNow_X(Single_State.getNow_X()+0.4*v*Math.cos((oC-135)*Math.PI/360));
		}
		else if(90<oC&&oC<=135)
		{
			Single_State.setNow_X(Single_State.getNow_X()+0.4*v*Math.sin((oC-90)*Math.PI/360));
		}
		else if(45<oC)
		{
			Single_State.setNow_X(Single_State.getNow_X()-0.4*v*Math.sin((oC-45)*Math.PI/360));
		}
		else if(0<oC)
		{
			Single_State.setNow_X(Single_State.getNow_X()-0.4*v*Math.cos(oC*Math.PI/360));
		}
		if(oC==0)
		{
			Single_State.setNow_X(Single_State.getNow_X()-0.4*v);
		}
	
	}	


}
