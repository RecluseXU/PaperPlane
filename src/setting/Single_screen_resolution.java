package setting;

import java.awt.Toolkit;

public final class Single_screen_resolution {
	private static Single_screen_resolution instance = null;
	private static final int sr_width = Toolkit.getDefaultToolkit().getScreenSize().width;
	private static final int sr_height = Toolkit.getDefaultToolkit().getScreenSize().height;
	
	
	private Single_screen_resolution(){}
    public static Single_screen_resolution getInstance(){
        if(instance==null){
               instance=new Single_screen_resolution();
        }
        return instance;
    }
	public static int getSr_width() {
		return sr_width;
	}
	public static int getSr_height() {
		return sr_height;
	}

}
