package setting;

import java.awt.Image;

import javax.swing.ImageIcon;


public final class ImageFactory {
	public static Image creatImage(String args)
	{
		ImageIcon imageIcon=new ImageIcon(args);
		return imageIcon.getImage();
	}
	public static ImageIcon creatImageIcon(String args)
	{
		ImageIcon imageIcon = new ImageIcon(args);
		return imageIcon;
	}

}
