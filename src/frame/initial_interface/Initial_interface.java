package frame.initial_interface;


import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

import frame.Background_Panel;
import music.Music;
import music.Music_MP3;
import setting.ImageFactory;
import setting.Single_screen_resolution;
import util.ImageHelper;
public final class Initial_interface extends JPanel implements MouseListener{
    /**
	 * Paper Plane 1.0 ------written by RecluseXU
	 */
//Sound
	private final Music BGM = new Music_MP3();
//Panel
	private final Animation_FlyOutOfTheDorm b1 = new Animation_FlyOutOfTheDorm();
	private final JPanel true_all = new JPanel(new BorderLayout());
	private final JPanel all = new Background_Panel(ImageFactory.creatImage("src/Data/background/Initial_interface0.jpg"));
	private final JPanel talk_panel = new JPanel();
	private ExPanel exPanel;
//Label
	private final JLabel talk_label = new JLabel();
	private final JLabel start_label = new JLabel();
	private final JLabel help_label = new JLabel();
	private final JLabel exit_label = new JLabel();	
	private final JLabel opinion_label = new JLabel();
	private final JLabel ranking_label = new JLabel();
//util
	private final ImageHelper imHelper = new ImageHelper();
//ImageIcon
//无参构造
	public Initial_interface()
	{
		//调整初始界面的panel关系
		all.setLayout(null);
		true_all.add(all,BorderLayout.CENTER);
		//设置所有按钮
		set_all_Button();
		//设置所有panel为透明
		true_all.setOpaque(false);
	    all.setOpaque(false);
	
	}
//给每一个Panel加对应标签，并在标签加鼠标的Action_listener
	public void set_all_Button()              
	{//用默认布局时，注意顺序是先放的在上面，后放的在下面。
		int S_width = Single_screen_resolution.getSr_width();
		int S_height = Single_screen_resolution.getSr_height();
		
		//主菜单
		JPanel mainMenu = new JPanel();
		mainMenu.setLayout(new GridLayout(3,1));
		mainMenu.setOpaque(false);
		mainMenu.setBounds(4*S_width/5, S_height/4,S_width/4,S_height/3);	
		//主菜单 - 开始按钮设置
		start_label.setIcon(ImageFactory.creatImageIcon("src/Data/button/start.png"));
		start_label.addMouseListener(this);
		mainMenu.add(start_label);
		//主菜单 - 帮助按钮设置
		help_label.setIcon(ImageFactory.creatImageIcon("src/Data/button/help.png"));
		help_label.addMouseListener(this);
		mainMenu.add(help_label);
		//主菜单 - 退出按钮设置
		exit_label.setIcon(ImageFactory.creatImageIcon("src/Data/button/exit.png"));
		exit_label.addMouseListener(this);
		mainMenu.add(exit_label);
		all.add(mainMenu);//添加主菜单到页面
		
		//次菜单
		JPanel mainMenu2 = new JPanel();
		mainMenu2.setLayout(new GridLayout(2,3));
		mainMenu2.setOpaque(false);
		mainMenu2.setBounds(4*S_width/5, 3*S_height/5,S_width/4,S_height/6);	
		//意见按钮设置
		this.opinion_label.setIcon(imHelper.transform(ImageFactory.creatImageIcon("src/Data/button/opinion1.png"), S_height/12, S_height/12));
		this.opinion_label.addMouseListener(this);
		mainMenu2.add(this.opinion_label);
		//排行按钮设置
		this.ranking_label.setIcon(imHelper.transform(ImageFactory.creatImageIcon("src/Data/button/ranking1.png"), S_height/12, S_height/12));
		this.ranking_label.addMouseListener(this);
		mainMenu2.add(this.ranking_label);
		all.add(mainMenu2);//添加次菜单到页面
		
		//话语panel设置
		talk_panel.setOpaque(false);
		talk_panel.setLayout(null);
		talk_panel.setBounds(4*S_width/5, S_height/40,S_width/5,S_height/5);
		talk_panel.setVisible(false);
		//话语文字
		this.talk_label.setBounds(30,0,S_width/5,S_height/5);
		this.talk_label.setFont(new Font("宋体",Font.BOLD, 45));
		talk_panel.add(this.talk_label);
		//话语背景
		JLabel talkBg = new JLabel();
		talkBg.setBounds(0, 0,S_width/5,S_height/5);
		talkBg.setIcon(imHelper.transform(ImageFactory.creatImageIcon("src/Data/background/talk.png"), S_width/5, S_height/5));
		talk_panel.add(talkBg);
		all.add(talk_panel);
		
		//手拿纸背景设置
		JLabel righttop_hand = new JLabel();
		righttop_hand.setBounds(3*S_width/4+30, S_height/7,S_width/4,3*S_height/4);
		righttop_hand.setIcon(imHelper.transform(ImageFactory.creatImageIcon("src/Data/background/右上角.png"), S_width/4, 3*S_height/4));
		righttop_hand.addMouseListener(this);
		all.add(righttop_hand);
			
	}
//BGM
	public void BGMplay()
	{
		BGM.Load_Music("src/Data/BGM/Initial_interface_BGM.mp3");
		BGM.play();
	}
	public void BGMstop()
	{
		BGM.stop();
	}
//鼠标移入事件
	public void mouseEntered(MouseEvent e)
	{
		if(e.getSource().equals(start_label))
		{ 
			this.rePaintJLabelIcon(this.start_label, "src/Data/button/start2.png", -1, -1);
			this.talk_panel.setVisible(true);
			this.talk_label.setText("开始游戏");
			this.talk_panel.repaint();
		}
		if(e.getSource().equals(help_label))
		{
			this.rePaintJLabelIcon(this.help_label, "src/Data/button/help2.png", -1, -1);
			this.talk_panel.setVisible(true);
			this.talk_label.setText("　帮助　");
			this.talk_panel.repaint();
		}
		if(e.getSource().equals(exit_label))
		{
			this.rePaintJLabelIcon(this.exit_label, "src/Data/button/exit2.png", -1, -1);
			this.talk_panel.setVisible(true);
			this.talk_label.setText("退出游戏");
			this.talk_panel.repaint();
		}
		if(e.getSource().equals(this.opinion_label))
		{
			this.rePaintJLabelIcon(this.opinion_label, "src/Data/button/opinion2.png", Single_screen_resolution.getSr_height()/12, Single_screen_resolution.getSr_height()/12);
			this.talk_panel.setVisible(true);
			this.talk_label.setText("　意见　");
			this.talk_panel.repaint();
		}
		if(e.getSource().equals(this.ranking_label))
		{
			this.rePaintJLabelIcon(this.ranking_label, "src/Data/button/ranking2.png",  Single_screen_resolution.getSr_height()/12, Single_screen_resolution.getSr_height()/12);
			this.talk_panel.setVisible(true);
			this.talk_label.setText("　排行　");
			this.talk_panel.repaint();
		}
	}
//鼠标按下事件
	public void mousePressed(MouseEvent e){}
//鼠标释放事件
	public void mouseReleased(MouseEvent e){}
//鼠标单击事件
	public void mouseClicked(MouseEvent e)
	{
		if(e.getSource().equals(start_label))//按下开始按钮
		{
			this.true_all.removeAll();
			this.b1.setBounds(0,0,this.true_all.getWidth(),this.true_all.getHeight());
			this.true_all.add(this.b1);
			this.b1.start();
			BGM.stop();
		}
		else if(e.getSource().equals(exit_label))//按下退出
		{
			System.exit(0);
		}
		else if(e.getSource().equals(this.exPanel))//点击额外窗口。
		{
			resetExPanel();
		}
		if(this.exPanel == null)//额外窗口已经关闭时
		{
			if(e.getSource().equals(help_label))//按下帮助按钮
			{
				this.exPanel = new ExPanel("help");
				all.add(exPanel);
				exPanel.setVisible(true);
				this.exPanel.addMouseListener(this);
			}
			else if(e.getSource().equals(this.ranking_label))//按下排名按钮
			{
				this.exPanel = new ExPanel("ranking");
				all.add(exPanel);
				exPanel.setVisible(true);
				this.exPanel.addMouseListener(this);
			}
			else if(e.getSource().equals(this.opinion_label))//按下意见按钮
			{
				this.exPanel = new ExPanel("opinion");
				all.add(exPanel);
				exPanel.setVisible(true);
				this.exPanel.addMouseListener(this);
			}
		}
		else//额外窗口已经打开时
		{
			if(e.getSource().equals(help_label))//按下帮助按钮
			{
				resetExPanel();	
				this.exPanel = new ExPanel("help");
				all.add(exPanel);
				exPanel.setVisible(true);
				this.exPanel.addMouseListener(this);
			}
			else if(e.getSource().equals(this.ranking_label))//按下排名按钮
			{
				resetExPanel();
				this.exPanel = new ExPanel("ranking");
				all.add(exPanel);
				exPanel.setVisible(true);
				this.exPanel.addMouseListener(this);
			}
			else if(e.getSource().equals(this.opinion_label))//按下意见按钮
			{
				resetExPanel();
				this.exPanel = new ExPanel("opinion");
				all.add(exPanel);
				exPanel.setVisible(true);
				this.exPanel.addMouseListener(this);
			}
		}
	}
//鼠标移出事件
	public void mouseExited(MouseEvent e)
	{
		   if(e.getSource().equals(start_label))
		   { 
			   this.rePaintJLabelIcon(this.start_label, "src/Data/button/start.png", -1, -1);
			   this.talk_panel.setVisible(false);
			   this.talk_panel.repaint();
		   }
		   else if(e.getSource().equals(help_label))
		   {
			   this.rePaintJLabelIcon(this.help_label,"src/Data/button/help.png", -1, -1);
			   this.talk_panel.setVisible(false);
			   this.talk_panel.repaint();
		   }
		   else if(e.getSource().equals(exit_label))
		   {
			   this.rePaintJLabelIcon(exit_label,"src/Data/button/exit.png", -1, -1);
			   this.talk_panel.setVisible(false);
			   this.talk_panel.repaint();
		   }
		   else if(e.getSource().equals(this.opinion_label))
		   {
			   this.rePaintJLabelIcon(this.opinion_label, "src/Data/button/opinion1.png", Single_screen_resolution.getSr_height()/12, Single_screen_resolution.getSr_height()/12);
			   this.talk_panel.setVisible(false);
			   this.talk_panel.repaint();
		   }
		   else if(e.getSource().equals(this.ranking_label))
		   {
			   this.rePaintJLabelIcon(this.ranking_label, "src/Data/button/ranking1.png",  Single_screen_resolution.getSr_height()/12, Single_screen_resolution.getSr_height()/12);
			   this.talk_panel.setVisible(false);
			   this.talk_panel.repaint();
		   }
	}
	void rePaintJLabelIcon(JLabel label,String newiconLocate,int width,int height)//重置JLabel中的图片
	{
		label.removeAll();
		if(width!=-1 && height!=-1)
			label.setIcon(imHelper.transform(ImageFactory.creatImageIcon(newiconLocate), width, height));
		else
			label.setIcon(ImageFactory.creatImageIcon(newiconLocate));
		label.repaint();
	}
	void resetExPanel()//重置额外窗口
	{
		this.exPanel.removeMouseListener(this);
		all.remove(this.exPanel);
		all.repaint();
		this.exPanel = null;
	}
	public JPanel getTrue_all() {
		return true_all;
	}

}
