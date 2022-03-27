package com.ssms.utils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 */
public class StringUtils {
	
	/**
	   * 验证手机号码
	   * 利用正则表达式
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
	  * 判断是否是数字
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
	  * 判断是否满足成绩范围0-100
	  */
	 public static boolean isScore(String str){  
		 return Float.parseFloat(str)>=0 && Float.parseFloat(str)<=100;
	 }
	 
	 /**	 
      * 判断是否是整数或者是小数
      * 利用正则表达式	 
	  */ 
	  public static boolean validateNumber(String str) { 	 
	    return str.matches("[0-9]+(\\.[0-9]+)?");   
	  }
	  
}
