package frame;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import setting.ImageFactory;
import setting.Single_screen_resolution;

public final class Background_Panel extends JPanel{


	/**
	 * Paper Plane 1.0 ------written by RecluseXU
	 */
	private Image background;
	
	public Background_Panel(Image im)
	{
		background=im;
	}
	public Background_Panel(String im_location)
	{
		background=ImageFactory.creatImage(im_location);
	}
	protected void paintComponent(Graphics g)
	{
		g.drawImage(background,0,0,Single_screen_resolution.getSr_width(),Single_screen_resolution.getSr_height(),this);	
	}


}
