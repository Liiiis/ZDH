package com;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Addtime {
	public static void main(String[] args) {
		
		String date1 = "2009-03-23 08:00:00";//ָ��ʱ��
		String date2= addDate(date1,1);//��1Сʱ����
		System.out.println("after:"+date2);

	}
	public static String addDate(String day, int x)
	{
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//24Сʱ��
	//SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//12Сʱ��
	Date date = null;
	try
	{
	date = format.parse(day);
	}
	catch (Exception ex)
	{
	ex.printStackTrace();
	}
	if (date == null) return "";
	Calendar cal = Calendar.getInstance();
	cal.setTime(date);
	cal.add(Calendar.HOUR_OF_DAY, x);//24Сʱ��
	//cal.add(Calendar.HOUR, x);12Сʱ��
	date = cal.getTime();
	System.out.println("front:" + date);
	cal = null;
	return format.format(date);
	}


}
