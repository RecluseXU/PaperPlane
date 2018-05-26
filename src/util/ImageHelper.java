package util;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ImageHelper {

	public Image transform(Image i,int width,int height)
	{
		return i.getScaledInstance(width, height, Image.SCALE_DEFAULT);
	}
	public ImageIcon transform(ImageIcon i,int width,int height)
	{
		i.setImage(i.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		return i;
	}
}
