package com.ssms.utils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * �ַ���������
 */
public class StringUtils {
	
	/**
	   * ��֤�ֻ�����
	   * ����������ʽ
	  * @param mobiles
	  * @return
	 */
	 public static boolean isMobilePhone(String mobiles){
		  boolean flag = false;
		  try{
			   Pattern p = Pattern.compile("^1[3456789]\\d{9}$");
			   Matcher m = p.matcher(mobiles);
			   flag = m.matches();
		  }catch(Exception e){
			  flag = false;
		  }
		  return flag;
	 }
	
	 /**
	  * �ж��Ƿ�������
	  */
	 public static boolean isNumeric(String str){  
		 for (int i = 0; i < str.length(); i++){  		    
	        if (!Character.isDigit(str.charAt(i))){  
	            return false;  
	         }  
		  }  
		  return true;  
	 }
	 
	 /**
	  * �ж��Ƿ�����ɼ���Χ0-100
	  */
	 public static boolean isScore(String str){  
		 return Float.parseFloat(str)>=0 && Float.parseFloat(str)<=100;
	 }
	 
	 /**	 
      * �ж��Ƿ�������������С��
      * ����������ʽ	 
	  */ 
	  public static boolean validateNumber(String str) { 	 
	    return str.matches("[0-9]+(\\.[0-9]+)?");   
	  }
	  
}
