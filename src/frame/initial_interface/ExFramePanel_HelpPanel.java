package frame.initial_interface;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import setting.ImageFactory;
import setting.Single_screen_resolution;
import util.ImageHelper;

class ExFramePanel_HelpPanel extends JPanel {

	private final JLabel lable = new JLabel();//添加一个显示帮助的JLable
	ExFramePanel_HelpPanel() {
		ImageHelper imhelper = new ImageHelper();
		lable.setIcon(imhelper.transform(ImageFactory.creatImageIcon("src/Data/background/help.jpg"),Single_screen_resolution.getSr_width()/2,Single_screen_resolution.getSr_height()/2));
		this.add(lable, BorderLayout.CENTER);
	}
	public JLabel getLable() {
		return lable;
	}

}
