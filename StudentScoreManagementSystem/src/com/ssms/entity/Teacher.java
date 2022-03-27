package com.ssms.entity;

import java.io.Serializable;

/**
 * ��ʦ��Ϣʵ����
 */
public class Teacher implements Serializable,Comparable<Teacher>{

    // ����
    private String tno;
    // ����
    private String name;
    // Ժϵ
    private String depart;
    // �οΰ༶
    private String classes;
    // ��ϵ��ʽ
    private String phone;

    /**
     * �޲ι��캯��
     */
    public Teacher() {
        super();
    }

    /**
     * ���ι��캯��
     */
    public Teacher(String tno,String name,String depart,String classes,String phone) {
        super();
        this.tno=tno;
        this.name=name;
        this.depart=depart;
        this.classes=classes;
        this.phone=phone;
    }

    /**
     * ���� Get������
     */
    public String getTno() {
        return this.tno;
    }

    /**
     * ���� Set������
     */
    public void setTno(String tno) {
        this.tno=tno;
    }

    /**
     * ���� Get������
     */
    public String getName() {
        return this.name;
    }

    /**
     * ���� Set������
     */
    public void setName(String name) {
        this.name=name;
    }

    /**
     * ���� Get������
     */
    public String getDepart() {
        return this.depart;
    }

    /**
     * ���� Set������
     */
    public void setDepart(String depart) {
        this.depart=depart;
    }

    /**
     * �οΰ༶ Get������
     */
    public String getClasses() {
        return this.classes;
    }

    /**
     * �οΰ༶ Set������
     */
    public void setClasses(String classes) {
        this.classes=classes;
    }

    /**
     * ��ϵ��ʽ Get������
     */
    public String getPhone() {
        return this.phone;
    }

    /**
     * ��ϵ��ʽ Set������
     */
    public void setPhone(String phone) {
        this.phone=phone;
    }

	@Override
	public int compareTo(Teacher arg0) {
		// TODO Auto-generated method stub
		return this.tno.compareTo(arg0.tno);
	}
}
