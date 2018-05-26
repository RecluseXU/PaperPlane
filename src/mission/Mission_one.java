package mission;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import mission.obstructions.cyclone.Cyclone;
import setting.Single_State;
import setting.Single_screen_resolution;
import setting.Time_Count;
public final class Mission_one extends JPanel implements Runnable,KeyListener{
	/**
	 * Paper Plane 1.0 ------written by RecluseXU
	 */

//Thread
	Thread Thread_mission_one = new Thread(this);
//Image
	private Image iBuffer;
	private Graphics gBuffer;
//JPanel
	private final Changing_background black;//黑色过度
	private final Passing_background mission_background;//背景
	private final Plane Plane1;//飞机
	private final Cyclone C1;//滑稽风洞
	private final Point_Panel point_panel;
//构造
	public Mission_one(Image start,int start_w,Image pass,int pass_w,Image end,int end_w)
	{	
		Plane1 = new Plane();//初始化飞机,背景,黑框
		mission_background = new Passing_background(start,pass,end,start_w,pass_w,end_w);
		black = new Changing_background(start,start_w,end,end_w);
		C1 = new Cyclone(start_w,pass_w,end_w);
		
		point_panel = new Point_Panel();
		
		add(black,BorderLayout.CENTER);//加入黑框
		add(point_panel,BorderLayout.EAST);
		
		setBounds(0, 0,Single_screen_resolution.getSr_width(),Single_screen_resolution.getSr_height());//设置宽高
		setLayout(new BorderLayout());
		
		Single_State.setNow_X(Plane1.getlocation_x());//初始化背景X坐标
	}
//键盘事件设置
	void set_Key()
	{
		addKeyListener(this);//键盘监听器
		setFocusable(true);//设置可以作为键盘焦点
		this.requestFocusInWindow();//设置键盘焦点在这里
	}
//线程开始
	public void start()
	{
		Thread_mission_one.start();//游戏线程开始		
	}
//判断风洞情况
	void CyclonePD()
	{
		C1.crash_or_not();
		if(Single_State.isCyclone_happen())
		{
			Single_State.setCyclone_happen(false);
			Plane1.oC = Plane1.oC+180;
			Plane1.setlocation_x(Plane1.getlocation_x()-20);
			if(360 < Plane1.oC)
				Plane1.oC = Plane1.oC-360;
			if(Plane1.oC < 0)
				Plane1.oC = Plane1.oC+360;
			Plane1.setfocus(Plane1.oC/10);		
		}
	}
//逆风
	void againstwi()
	{
		Single_State.setNow_X(Single_State.getNow_X() - Single_State.getAgainst());
		Plane1.setlocation_x(Plane1.getlocation_x() - Single_State.getAgainst());
	}
//Run
	public void run()
	{
		try {
			black.start();
			set_Key();//键盘事件设置		
			while(Single_State.isChanging_background_start())
				Thread.sleep(500);
			Time_Count.start_time_note();//时间起始点
			while(true)
			{
				if(Single_State.isPause())//是否按下暂停
					while(Single_State.isPause())
						Thread.sleep(3000);
				CyclonePD();//风洞情况
				againstwi();//逆风
				Plane1.Plane_update();//飞机移动计算

				mission_background.Next_X(Plane1.oC,Single_State.getPlane_speed());//背景移动计算
				
				PD();//情况判断
				if(Single_State.isMission_one_complete() || Single_State.isReturn_initial_interface())//如果通关就退出来
					break;
				Thread.sleep(20);
				repaint();//重画
			}
			Time_Count.end_time_note();//结束时间记录
			if(Single_State.isReturn_initial_interface()==false)
			{
				int point =  (int)(10000-100*Time_Count.time_start_to_end_long());
				@SuppressWarnings("unused")
				int res=JOptionPane.showConfirmDialog(
						null, 
						"用时:"+Time_Count.time_start_to_end()+"秒\n分数:"+ Integer.toString(point)+"总分:"+Integer.toString(Single_State.getAll_point()), 
						"用时",
						JOptionPane.PLAIN_MESSAGE);
				Single_State.setAll_point(Single_State.getAll_point()+point);
			}
			Single_State.setChanging_background_end(true);
			Thread.sleep(2000);
		} catch (InterruptedException e) {e.printStackTrace();}
	}
	
//各种情况的判断
	void PD()
	{
		if(mission_background.Lock_or_not())//是否锁定飞机
			Single_State.setPlane_lock(true);
		else 
			Single_State.setPlane_lock(false);
		
		double plane_y = Plane1.getlocation_y();
		if(plane_y < -10)//如果飞到屏幕上
		{
			Plane1.setlocation_y(-10);
			Plane1.oC = Plane1.oC-2;
			Plane1.setfocus(Plane1.oC / 10);
		}
		if(plane_y > Single_screen_resolution.getSr_height()+14)//如果掉到屏幕下了
		{
			Single_State.setPlane_lock(false);
			Plane1.setlocation_y(0);
			Plane1.setlocation_x(200);
			Single_State.setNow_X(200);
		}
		
		double plane_x = Plane1.getlocation_x();
		if(plane_x < 0 && Single_State.getNow_X() < 0)//往后飞到地图边
		{
			Plane1.setlocation_x(0);
			Single_State.setNow_X(plane_x);
		}
		if(plane_x > Single_screen_resolution.getSr_width() + 60)//通关
		{
			Single_State.setMission_one_complete(true);
		}
	}
//重画
	public void paint(Graphics g)
	{

	//缓冲
		if(iBuffer == null)
		{
			iBuffer = createImage(this.getSize().width,this.getSize().height);
			gBuffer = iBuffer.getGraphics();
		}
	//加背景
		double nowX = Single_State.getNow_X();
		int S_width = Single_screen_resolution.getSr_width();
		if(nowX < S_width/2)
		{
			gBuffer.drawImage(mission_background.back_ground_start,0,0,mission_background.width_start,this.getHeight(),this);
		}
		else if(nowX>=S_width/2 && nowX<mission_background.width_start+mission_background.width_pass-S_width/2)
		{
			gBuffer.drawImage(mission_background.back_ground_start,(int) -(nowX-S_width/2),0,mission_background.width_start,this.getHeight(),this);
			gBuffer.drawImage(mission_background.back_ground_pass,(int) (-(nowX-S_width/2)+mission_background.width_start),0,mission_background.width_pass,this.getHeight(),this);
		}
		else if(nowX>=mission_background.width_start+mission_background.width_pass-S_width/2&&nowX<mission_background.width_start+2*mission_background.width_pass-S_width/2)
		{
			gBuffer.drawImage(mission_background.back_ground_pass,(int) (-(nowX-S_width/2)+mission_background.width_start),0,mission_background.width_pass,this.getHeight(),this);
			gBuffer.drawImage(mission_background.back_ground_pass,(int) (-(nowX-S_width/2)+mission_background.width_start+mission_background.width_pass),0,mission_background.width_pass,this.getHeight(),this);
		}

		else if(nowX>=mission_background.width_start+2*mission_background.width_pass-S_width/2&&nowX<=mission_background.width_end+mission_background.width_start+2*mission_background.width_pass-S_width/2)
		{
			gBuffer.drawImage(mission_background.back_ground_pass,(int) (-(nowX-S_width/2)+mission_background.width_start+mission_background.width_pass),0,mission_background.width_pass,this.getHeight(),this);
			gBuffer.drawImage(mission_background.back_ground_end,(int) (-(nowX-S_width/2)+mission_background.width_start+mission_background.width_pass*2),0,mission_background.width_end,this.getHeight(),this);
		}
		else if(nowX>=mission_background.width_end+mission_background.width_start+2*mission_background.width_pass-S_width/2)
		{
			gBuffer.drawImage(mission_background.back_ground_end,-(mission_background.width_end-S_width),0,mission_background.width_end,this.getHeight(),this);
		}
	//加飞机
		gBuffer.drawImage(Plane1.plane[Plane1.getfocus()],(int) Plane1.getlocation_x(),(int) Plane1.getlocation_y(),50,50,this);
	//加风洞
		int C_x;
		int C_width;
		for(C1.setn(C1.getnum_C()-1);C1.getn()>=0;C1.setn(C1.getn()-1))
		{
			C_x = (int) (C1.getC_location()[C1.getn()][0]-nowX+S_width/2)-30;
			C_width = 100+18*Single_State.getLevel();
			if(C_x > -C_width && C_x<S_width)
			{
				int C_y = C1.getC_location()[C1.getn()][1]-30;
				if(C1.getC_location()[C1.getn()][0] - nowX >0)
					gBuffer.drawImage(C1.getCyclone1(),C_x,C_y,C_width,C_width,this);
				else 
					gBuffer.drawImage(C1.getCyclone(),C_x,C_y,C_width,C_width,this);
			}
		}	
	//清理以前画的
		super.paint(g);
	//画所有
		g.drawImage(iBuffer, 0, 0,this);
	}
	//键按下事件
		public void keyPressed(KeyEvent e)
		{
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
			{
				Single_State.setReturn_game(false);
				Single_State.setReturn_initial_interface(false);
				Single_State.setPause(true);
			}
			switch(e.getKeyChar())
			{
		     	case 'w': 
		     		Plane1.oC = Plane1.oC+5;
		     		break;
			    case 's': 
			    	Plane1.oC = Plane1.oC-5;
			    	break;

			}
			if(360 <= Plane1.oC)
				Plane1.oC = Plane1.oC-360;
			if(Plane1.oC < 0)
				Plane1.oC = Plane1.oC+360;
			
			Plane1.setfocus(Plane1.oC / 10);
		}
	//放开键事件
		public void keyReleased(KeyEvent e) {}
	//键按一次事件
		public void keyTyped(KeyEvent e) {}

}
