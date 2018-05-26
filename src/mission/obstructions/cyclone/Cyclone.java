package mission.obstructions.cyclone;
import javax.swing.JPanel;

import music.Music;
import music.Music_MP3;
import setting.Single_State;
import setting.Single_screen_resolution;

import java.awt.Image;

public final class Cyclone extends JPanel {
	/**
	 * 
	 */

//Music
	private final Music sound;
//Image
	Image cyclone;
	Image cyclone1;
//int
	private int n;
	private final int num_C;
	int C_location[][];//风洞的坐标前为X后为Y
	
//加载图片
	void loadmagic()
	{
		this.cyclone = setting.ImageFactory.creatImage("src/Data/background/mission one/cyclone.png");
		this.cyclone1 = setting.ImageFactory.creatImage("src/Data/background/mission one/cyclone1.png");
	}

//构造
	public Cyclone(int mi_start_w,int mi_pass_w,int mi_end_w)
	{
		sound = new Music_MP3();
		loadmagic();
		this.num_C = Single_State.getLevel()*2+1;
		int bf_C_location[][]=new int[num_C][2];//风的坐标前为X后为Y
		this.C_location=bf_C_location;
		give_C_location(mi_start_w,mi_pass_w);//随机洞坐标
	}
//get
	public int getnum_C()
	{
		return this.num_C;
	}
	public int getn()
	{
		return this.n;
	}
	
	public int[][] getC_location() {
		return C_location;
	}
	
	public Image getCyclone() {
		return cyclone;
	}

	public Image getCyclone1() {
		return cyclone1;
	}

	//set
	public void setn(int newn)
	{
		this.n=newn;
	}
//随机洞坐标
	void give_C_location(int start_w,int pass_w)
	{
        for(n = this.num_C-1;n>=0;this.n--)
        {
        	this.C_location[n][0]= (int) Math.round(Math.random() * (2*pass_w) + Single_screen_resolution.getSr_width()); 
        }
        for(n = num_C-1;n>=0;n--)
        {
        	this.C_location[n][1]= (int) Math.round(Math.random() * (Single_screen_resolution.getSr_height()-100) + 50); 
        }
	}

//计算碰撞
	public void crash_or_not()
	{
		double r;
		for(n = num_C-1;n>=0;n--)
		{
			r = Math.sqrt(
					Math.pow(((C_location[n][1]+(100+18*Single_State.getLevel())/2)-(Single_State.getNow_y()+25)),2)
					+Math.pow(((C_location[n][0]+(100+18*Single_State.getLevel())/2)-(Single_State.getNow_X()+25)),2));
			if(r<=(100+13*Single_State.getLevel())/2+40/2)
			{
				Single_State.setCyclone_happen(true);
				//this.sound.stop();
				//this.sound.Load_Music("src/Data/BGM/effor/duang.mp3");
				//this.sound.play();
				break;
			}
			if(Single_screen_resolution.getSr_height()<=C_location[n][1]) 
				C_location[n][1]=0;
			if(0>C_location[n][1]) 
				C_location[n][1]=Single_screen_resolution.getSr_height();
			this.move_cyclone(n);
		}
	}
	void move_cyclone(int n)
	{
		int a1,a2,aa1,aa2,aaa,aaaa;
		a1=(int) (Math.random()*2+1);
		a2=(int) (Math.random()*2+1);
		aa1=(int) Math.pow(-4, a1);
		aa2=(int) Math.pow(-4, a2);
		aaa=(int) Math.random();
		aaaa=(int) Math.random();
		if(aaa==0){
			C_location[n][0]=(int) (aa1+ C_location[n][0]-5);
			}
		if(aaaa==0){
			C_location[n][1]=(int) (aa2+C_location[n][1]-5);
		}
	}


}
