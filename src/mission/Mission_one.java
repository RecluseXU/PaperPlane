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
	private final Changing_background black;//��ɫ����
	private final Passing_background mission_background;//����
	private final Plane Plane1;//�ɻ�
	private final Cyclone C1;//�����綴
	private final Point_Panel point_panel;
//����
	public Mission_one(Image start,int start_w,Image pass,int pass_w,Image end,int end_w)
	{	
		Plane1 = new Plane();//��ʼ���ɻ�,����,�ڿ�
		mission_background = new Passing_background(start,pass,end,start_w,pass_w,end_w);
		black = new Changing_background(start,start_w,end,end_w);
		C1 = new Cyclone(start_w,pass_w,end_w);
		
		point_panel = new Point_Panel();
		
		add(black,BorderLayout.CENTER);//����ڿ�
		add(point_panel,BorderLayout.EAST);
		
		setBounds(0, 0,Single_screen_resolution.getSr_width(),Single_screen_resolution.getSr_height());//���ÿ��
		setLayout(new BorderLayout());
		
		Single_State.setNow_X(Plane1.getlocation_x());//��ʼ������X����
	}
//�����¼�����
	void set_Key()
	{
		addKeyListener(this);//���̼�����
		setFocusable(true);//���ÿ�����Ϊ���̽���
		this.requestFocusInWindow();//���ü��̽���������
	}
//�߳̿�ʼ
	public void start()
	{
		Thread_mission_one.start();//��Ϸ�߳̿�ʼ		
	}
//�жϷ綴���
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
//���
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
			set_Key();//�����¼�����		
			while(Single_State.isChanging_background_start())
				Thread.sleep(500);
			Time_Count.start_time_note();//ʱ����ʼ��
			while(true)
			{
				if(Single_State.isPause())//�Ƿ�����ͣ
					while(Single_State.isPause())
						Thread.sleep(3000);
				CyclonePD();//�綴���
				againstwi();//���
				Plane1.Plane_update();//�ɻ��ƶ�����

				mission_background.Next_X(Plane1.oC,Single_State.getPlane_speed());//�����ƶ�����
				
				PD();//����ж�
				if(Single_State.isMission_one_complete() || Single_State.isReturn_initial_interface())//���ͨ�ؾ��˳���
					break;
				Thread.sleep(20);
				repaint();//�ػ�
			}
			Time_Count.end_time_note();//����ʱ���¼
			if(Single_State.isReturn_initial_interface()==false)
			{
				int point =  (int)(10000-100*Time_Count.time_start_to_end_long());
				@SuppressWarnings("unused")
				int res=JOptionPane.showConfirmDialog(
						null, 
						"��ʱ:"+Time_Count.time_start_to_end()+"��\n����:"+ Integer.toString(point)+"�ܷ�:"+Integer.toString(Single_State.getAll_point()), 
						"��ʱ",
						JOptionPane.PLAIN_MESSAGE);
				Single_State.setAll_point(Single_State.getAll_point()+point);
			}
			Single_State.setChanging_background_end(true);
			Thread.sleep(2000);
		} catch (InterruptedException e) {e.printStackTrace();}
	}
	
//����������ж�
	void PD()
	{
		if(mission_background.Lock_or_not())//�Ƿ������ɻ�
			Single_State.setPlane_lock(true);
		else 
			Single_State.setPlane_lock(false);
		
		double plane_y = Plane1.getlocation_y();
		if(plane_y < -10)//����ɵ���Ļ��
		{
			Plane1.setlocation_y(-10);
			Plane1.oC = Plane1.oC-2;
			Plane1.setfocus(Plane1.oC / 10);
		}
		if(plane_y > Single_screen_resolution.getSr_height()+14)//���������Ļ����
		{
			Single_State.setPlane_lock(false);
			Plane1.setlocation_y(0);
			Plane1.setlocation_x(200);
			Single_State.setNow_X(200);
		}
		
		double plane_x = Plane1.getlocation_x();
		if(plane_x < 0 && Single_State.getNow_X() < 0)//����ɵ���ͼ��
		{
			Plane1.setlocation_x(0);
			Single_State.setNow_X(plane_x);
		}
		if(plane_x > Single_screen_resolution.getSr_width() + 60)//ͨ��
		{
			Single_State.setMission_one_complete(true);
		}
	}
//�ػ�
	public void paint(Graphics g)
	{

	//����
		if(iBuffer == null)
		{
			iBuffer = createImage(this.getSize().width,this.getSize().height);
			gBuffer = iBuffer.getGraphics();
		}
	//�ӱ���
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
	//�ӷɻ�
		gBuffer.drawImage(Plane1.plane[Plane1.getfocus()],(int) Plane1.getlocation_x(),(int) Plane1.getlocation_y(),50,50,this);
	//�ӷ綴
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
	//������ǰ����
		super.paint(g);
	//������
		g.drawImage(iBuffer, 0, 0,this);
	}
	//�������¼�
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
	//�ſ����¼�
		public void keyReleased(KeyEvent e) {}
	//����һ���¼�
		public void keyTyped(KeyEvent e) {}

}
