package setting;

import java.util.Date;

public final class Time_Count {

	private static Date date_start;
	private static Date date_end;
	
	public Time_Count(){}
	
	public static void start_time_note()//��¼��ʼʱ��
	{
		Time_Count.date_start = new Date();
	}
	public static void end_time_note()//��¼����ʱ��
	{
		Time_Count.date_end = new Date();
	}
	public static String time_start_to_end()//����ʱ��� ������Ϊ��λ��
	{
		return Long.toString((Time_Count.date_end.getTime() - Time_Count.date_start.getTime())/1000);
	}
	public static long time_start_to_end_long()//����ʱ��� ������Ϊ��λ��
	{
		return ((Time_Count.date_end.getTime() - Time_Count.date_start.getTime()) /1000);
	}
}