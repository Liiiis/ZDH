package com;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeTest {

    public static void main(String[] args) {

        String s1 = "20151117190936";
   //     String s2="20090915-17:20:12";
        try {
            Date date1 = new SimpleDateFormat("yyyyMMddHHmmss").parse(s1);
            //Êä³ö¸ñÊ½
  //          System.out.println("=======½âÎö×Ö·û´®1======");
   //         System.out.println(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(date1));
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date1));
   //         System.out.println("=======½âÎö×Ö·û´®2======");
  //          Date date2 = new SimpleDateFormat("yyyyMMdd-HH:mm:ss").parse(s2);
  //          System.out.println(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(date2));
  //          System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date2));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}

