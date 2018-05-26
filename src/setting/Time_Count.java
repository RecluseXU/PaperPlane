package setting;

import java.util.Date;

public final class Time_Count {

	private static Date date_start;
	private static Date date_end;
	
	public Time_Count(){}
	
	public static void start_time_note()//记录开始时间
	{
		Time_Count.date_start = new Date();
	}
	public static void end_time_note()//记录结束时间
	{
		Time_Count.date_end = new Date();
	}
	public static String time_start_to_end()//计算时间差 （以秒为单位）
	{
		return Long.toString((Time_Count.date_end.getTime() - Time_Count.date_start.getTime())/1000);
	}
	public static long time_start_to_end_long()//计算时间差 （以秒为单位）
	{
		return ((Time_Count.date_end.getTime() - Time_Count.date_start.getTime()) /1000);
	}
}