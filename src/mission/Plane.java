package mission;

import java.awt.Image;
import javax.swing.JPanel;

import setting.ImageFactory;
import setting.Single_State;
import setting.Single_screen_resolution;

final class Plane extends JPanel{
	/**
	 * Paper Plane 1.0 ------written by RecluseXU
	 */
//Image
	public Image plane[] = new Image [37];
//Int
	private double location_x = 0;//X坐标
	private int focus = 17;//状态
	public int oC = 180;//度数
	private int P = (int)(0.5* Math.pow(Single_State.getPlane_speed(),2) + Single_screen_resolution.getSr_height()- Single_State.getNow_y());//机械能
	double changed_y;
	int pD = -1;
//无参构造
	public Plane()
	{
		changed_y=Single_State.getNow_y();
	//加载图片
		loading_Plane_Image();
	}
//get
	int getfocus(){return focus;}
	double getlocation_x(){return location_x;}
	double getlocation_y(){return Single_State.getNow_y();}
//set
	void setfocus(int newf){this.focus = newf;}
	void setlocation_x(double newx){this.location_x = newx;}
	void setlocation_y(double newy){Single_State.setNow_y(newy);}
//加载飞机图片
	void loading_Plane_Image()
	{
		for(int n=0;n<=35;n++)
		{
			plane[n] = ImageFactory.creatImage("src/Data/background/plane/"+(-170+10*n)+"_plane.png");
		}
		plane[36] = ImageFactory.creatImage("src/Data/background/plane/0_plane.png");
	}

//计算无阻力下的速度
	void speedcount()
	{
		Single_State.setPlane_speed((int) Math.sqrt(2*(P+Single_State.getNow_y()-Single_screen_resolution.getSr_height())));
	}
//计算下一次飞机在哪里
	void Next()
	{
		if(315<oC&&oC<=360)
		{
			location_x=location_x-0.4*Single_State.getPlane_speed()*Math.cos((oC-315)*Math.PI/360);
			Single_State.setNow_y(Single_State.getNow_y()-0.5*Single_State.getPlane_speed()*Math.sin((oC-315)*Math.PI/360));
		}
		else if(270<oC)
		{
			location_x=location_x-0.4*Single_State.getPlane_speed()*Math.sin((oC-275)*Math.PI/360);
			Single_State.setNow_y(Single_State.getNow_y()-0.5*Single_State.getPlane_speed()*Math.cos((oC-275)*Math.PI/360));
		}
		else if(225<oC)
		{
			location_x=location_x+0.4*Single_State.getPlane_speed()*Math.sin((oC-225)*Math.PI/360);
			Single_State.setNow_y(Single_State.getNow_y()-0.5*Single_State.getPlane_speed()*Math.cos((oC-225)*Math.PI/360));
		}
		else if(180<oC)
		{
			location_x=location_x+0.4*Single_State.getPlane_speed()*Math.cos((oC-180)*Math.PI/360);
			Single_State.setNow_y(Single_State.getNow_y()-0.5*Single_State.getPlane_speed()*Math.sin((oC-180)*Math.PI/360));
		}
		else if(135<oC)
		{
			location_x=location_x+0.4*Single_State.getPlane_speed()*Math.cos((oC-135)*Math.PI/360);
			Single_State.setNow_y(Single_State.getNow_y()+0.5*Single_State.getPlane_speed()*Math.sin((oC-135)*Math.PI/360));
		}
		else if(90<oC)
		{
			location_x=location_x+0.4*Single_State.getPlane_speed()*Math.sin((oC-90)*Math.PI/360);
			Single_State.setNow_y(Single_State.getNow_y()+0.5*Single_State.getPlane_speed()*Math.cos((oC-90)*Math.PI/360));
		}
		else if(45<oC)
		{
			location_x=location_x-0.4*Single_State.getPlane_speed()*Math.sin((oC-45)*Math.PI/360);
			Single_State.setNow_y(Single_State.getNow_y()+0.5*Single_State.getPlane_speed()*Math.cos((oC-45)*Math.PI/360));
		}
		else if(0<oC)
		{
			location_x=location_x-0.4*Single_State.getPlane_speed()*Math.cos(oC*Math.PI/360);
			Single_State.setNow_y(Single_State.getNow_y()+0.5*Single_State.getPlane_speed()*Math.sin(oC*Math.PI/360));
		}
		else if(oC==0)
		{
			location_x=location_x-0.4*Single_State.getPlane_speed();
		}
	}
//是否锁定：定在屏幕一半那里- -
	void Location_lock()
	{
		if(Single_State.isPlane_lock())
			location_x = Single_screen_resolution.getSr_width()/2;
	}
//重力作用
	void g()
	{
		Single_State.setNow_y(Single_State.getNow_y()+0.1);
	}
//需要刷新的东西
	void Plane_update()
	{
		speedcount();
		Next();
		g();
		Location_lock();
	}

}
