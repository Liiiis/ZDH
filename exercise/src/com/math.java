package com;

import java.util.Scanner;

public class math {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
        System.out.println("���������֣�");
        String str = scanner.next();
        //����ת������ʾ����
        String[] alabo = { "��", "һ", "��", "��", "��", "��", "��", "��", "��", "��" };
        String[] zwhz = { "ʮ", "��", "ǧ", "��", "ʮ", "��", "ǧ", "��", "ʮ", "��", "ǧ" };
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
        System.out.println("ת�����Ϊ��"+digital);

	}

}
