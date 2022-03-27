package com;

import java.util.Scanner;

public class math {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
        System.out.println("请输入数字：");
        String str = scanner.next();
        //定义转换后显示数组
        String[] alabo = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
        String[] zwhz = { "十", "百", "千", "万", "十", "百", "千", "亿", "十", "百", "千" };
        String digital = "";
        int n = str.length();
        for (int i = 0; i < n; i++) {
            int o = str.charAt(i) - '0';
            if (i != n - 1 && o != 0) {
                digital += alabo[o] + zwhz[n - 2 - i];
            } else {
                digital += alabo[o];
            }
        }
        System.out.println("转换结果为："+digital);

	}

}
