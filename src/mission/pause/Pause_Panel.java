package mission.pause;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import setting.ImageFactory;
import setting.Single_State;
import setting.Single_screen_resolution;

final class Pause_Panel extends JPanel implements MouseListener
{
	/**
	 * Writen by RecluseXU
	 */
	private static final long serialVersionUID = -4029236705391931500L;
	private Image background;
	private JLabel continue_game;
	private JLabel backto_initial_interface;
	public Pause_Panel()
	{
		setLayout(new GridLayout(1,2));
		
		background = setting.ImageFactory.creatImage("src/Data/background/mission one/pause/pause_background.png");
		setBounds(0,0,Single_screen_resolution.getSr_width(), Single_screen_resolution.getSr_height());
		setOpaque(false);
		
		continue_game = creat_Label("src/Data/background/mission one/pause/continue_button.png");
		continue_game.addMouseListener(this);
		backto_initial_interface = creat_Label("src/Data/background/mission one/pause/backto_initial_interface.png");
		backto_initial_interface.addMouseListener(this);
		
		add(continue_game);
		add(backto_initial_interface);

	}
	protected JLabel creat_Label(String arg)//创建一个带背景图片的JLabel对象
	{
		JLabel jl = new JLabel(setting.ImageFactory.creatImageIcon(arg));
		return jl;
	}
	protected void paintComponent(Graphics g)
	{
		g.drawImage(background,0,0,Single_screen_resolution.getSr_width(),Single_screen_resolution.getSr_height(),this);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		   if(e.getSource().equals(continue_game))
		   { 
			   Single_State.setReturn_game(true);
			   Single_State.setPause(false);
		   }
		   if(e.getSource().equals(backto_initial_interface))
		   {
			   Single_State.setReturn_initial_interface(true);
			   Single_State.setPause(false);
		   }
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		   if(e.getSource().equals(continue_game))
		   { 
			   ImageIcon new_icon = ImageFactory.creatImageIcon("src/Data/background/mission one/pause/continue_button_1.png");
			   continue_game.removeAll();
			   continue_game.setIcon(new_icon);
			   continue_game.repaint();
			   
			   background = ImageFactory.creatImage("src/Data/background/mission one/pause/pause_background_0.png");
			   this.repaint();
		   }
		   if(e.getSource().equals(backto_initial_interface))
		   {
			   ImageIcon new_icon = ImageFactory.creatImageIcon("src/Data/background/mission one/pause/backto_initial_interface_1.png");
			   backto_initial_interface.removeAll();
			   backto_initial_interface.setIcon(new_icon);
			   backto_initial_interface.repaint();
			   
			   background = ImageFactory.creatImage("src/Data/background/mission one/pause/pause_background_1.png");
			   this.repaint();
		   }
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		   if(e.getSource().equals(continue_game))
		   { 
			   ImageIcon new_icon = ImageFactory.creatImageIcon("src/Data/background/mission one/pause/continue_button.png");
			   continue_game.removeAll();
			   continue_game.setIcon(new_icon);
			   continue_game.repaint();
			 
		   }
		   if(e.getSource().equals(backto_initial_interface))
		   {
			   ImageIcon new_icon = ImageFactory.creatImageIcon("src/Data/background/mission one/pause/backto_initial_interface.png");
			   backto_initial_interface.removeAll();
			   backto_initial_interface.setIcon(new_icon);
			   backto_initial_interface.repaint();
		   }
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}



}