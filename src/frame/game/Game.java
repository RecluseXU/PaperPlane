package frame.game;

import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import dao.DB_Business;
import frame.Background_Panel;
import mission.MissionFactory;
import mission.Mission_one;
import music.Music;
import music.Music_MP3;
import setting.Single_State;

final class Game extends JPanel implements Runnable
{
	/**
	 * Paper Plane 1.0 ------written by RecluseXU
	 */
	private static final long serialVersionUID = -6817570179093401087L;

	//Music
		private final Music BGM = new Music_MP3();//背景音乐
	//Thread
		Thread game = new Thread(this);
	//JPanel
		private final JPanel all = new JPanel(new BorderLayout());
//构造
	public Game()
	{
		Single_State.setExit_game_frame(false);
		relation();
	}
//关系
	void relation()
	{
		setLayout(new BorderLayout());	
		all.setLayout(new BorderLayout());
		add(all,BorderLayout.CENTER);
	}
//开始线程
	public void start()
	{
		game.start();
	}
//RUN
	public void run() 
	{
		try {
		Single_State.setLevel(1);
		Single_State.setAll_point(0);
		
		List<String> missionNameList = new ArrayList<String>();
		missionNameList.add("one");
		missionNameList.add("two");
		missionNameList.add("three");
		missionNameList.add("four");
		missionNameList.add("five");
		missionNameList.add("six");
		
		for(String missionName : missionNameList)
		{
			Mission_one one = MissionFactory.creatMission(
					"src/Data/background/mission one/mission_"+missionName+"_start.jpg", 2000,
					"src/Data/background/mission one/mission_"+missionName+"_pass.jpg",3500,
					"src/Data/background/mission one/mission_"+missionName+"_end.jpg",2200);
			missionChange(one,"src/Data/BGM/mission_"+missionName+"_BGM.mp3");
			checkEnd(one);//检测第1关是否结束
		}
		
		
		} catch (InterruptedException e) 
		{e.printStackTrace();}
		
		if(Single_State.isNoInternet()==false)
			this.tryToUpdatePoint();
		
		
		//Background_Panel game_end_panel = new Background_Panel("src/Data/background/mission one/mission_complete.jpg");
		//game_end_panel.setLayout(new BorderLayout());
		//game_end_panel.setOpaque(false);
		//all.removeAll();
		//all.setOpaque(false);
		//all.add(game_end_panel,BorderLayout.CENTER);
		//all.repaint();
		
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		
		BGM.stop();
		Single_State.setExit_game_frame(true);
	}
	

//检查结束
	void checkEnd(Mission_one nowMission) throws InterruptedException
	{
		while(Single_State.isChanging_background_allend() == false)
		{
			Thread.sleep(3000);
		}
	}
//更改关卡
	void missionChange(Mission_one newone,String bgm_location)
	{
		//重置坐标
		Single_State.setNow_X(0);
		Single_State.setNow_y(100);
		//重置飞机
		Single_State.setPlane_lock(false);
		//Single_State.setPlane_degree(180);
		if(Single_State.isReturn_initial_interface()==false)
		{
			//重置关卡状态
			Single_State.setMission_one_running(true);
			Single_State.setMission_one_complete(false);
			//重置过场动画
			Single_State.setChanging_background_allend(false);
			Single_State.setChanging_background_end(false);
			Single_State.setChanging_background_start(true);
			//难度上升
			Single_State.setLevel(Single_State.getLevel()+1);
			//逆风调整
			setting.Single_State.setAgainst(Single_State.getLevel()/2);

			viewChange(newone);
			musicChange(bgm_location);
		}
	}
//更改界面
	void viewChange(Mission_one newMission)
	{
		all.removeAll();
		newMission.setLayout(new BorderLayout());
		all.add(newMission,BorderLayout.CENTER);
		repaint();
		newMission.start();
	}
//更改背景音乐
	void musicChange(String bgm_location)
	{
		BGM.stop();
		if(!bgm_location.equals("src/Data/BGM/mission_1_BGM.mp3"))
			BGM.stop();
		BGM.reset();
		BGM.Load_Music(bgm_location);
		BGM.play();
	}

//检查更新最高分
	void tryToUpdatePoint()
	{
		try {//更新最高分
			DB_Business db_business = new DB_Business("GetPoint");//查询就总分
			List<String> targe = new ArrayList<String>();
			targe.add(Single_State.getAccount());
			List<String> result = db_business.execute(targe);
			int oldpoint = Integer.parseInt(result.get(0));
			
			if(Single_State.getAll_point() > oldpoint)
			{
				db_business.business_select("UpdatePoint");
				List<String> target = new ArrayList<String>();
				target.add(String.valueOf(Single_State.getAll_point()));
				db_business.execute(target);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	
}


