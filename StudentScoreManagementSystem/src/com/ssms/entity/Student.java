package com.ssms.entity;

import java.io.Serializable;

/**
 * ѧ����Ϣʵ����
 */
public class Student implements Serializable,Comparable<Student>{

    // ѧ��
    private String sno;
    // ����
    private String name;
    // �༶
    private String classes;
    // רҵ
    private String major;
    // ��ϵ��ʽ
    private String phone;

    /**
     * �޲ι��캯��
     */
    public Student() {
        super();
    }

    /**
     * ���ι��캯��
     */
    public Student(String sno,String name,String classes,String major,String phone) {
        super();
        this.sno=sno;
        this.name=name;
        this.classes=classes;
        this.major=major;
        this.phone=phone;
    }

    /**
     * ѧ�� Get������
     */
    public String getSno() {
        return this.sno;
    }

    /**
     * ѧ�� Set������
     */
    public void setSno(String sno) {
        this.sno=sno;
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
     * �༶ Get������
     */
    public String getClasses() {
        return this.classes;
    }

    /**
     * �༶ Set������
     */
    public void setClasses(String classes) {
        this.classes=classes;
    }

    /**
     * רҵ Get������
     */
    public String getMajor() {
        return this.major;
    }

    /**
     * רҵ Set������
     */
    public void setMajor(String major) {
        this.major=major;
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
	public int compareTo(Student arg0) {
		// TODO Auto-generated method stub
		return this.sno.compareTo(arg0.sno);
	}
}
