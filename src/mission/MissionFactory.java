package mission;

import java.awt.Image;

import setting.ImageFactory;

public final class MissionFactory {
	
	public static Mission_one creatMission(String start_location,int start_w,String pass_location,int pass_w,String end_location,int end_w)
	{
		Image start = ImageFactory.creatImage(start_location);
		Image pass = ImageFactory.creatImage(pass_location);
		Image end = ImageFactory.creatImage(end_location);
		Mission_one mission= new Mission_one(start,start_w,pass,pass_w,end,end_w);
		return mission;
	}

}
