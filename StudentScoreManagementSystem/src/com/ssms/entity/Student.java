package com.ssms.entity;

import java.io.Serializable;

/**
 * 学生信息实体类
 */
public class Student implements Serializable,Comparable<Student>{

    // 学号
    private String sno;
    // 姓名
    private String name;
    // 班级
    private String classes;
    // 专业
    private String major;
    // 联系方式
    private String phone;

    /**
     * 无参构造函数
     */
    public Student() {
        super();
    }

    /**
     * 带参构造函数
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
     * 学号 Get访问器
     */
    public String getSno() {
        return this.sno;
    }

    /**
     * 学号 Set访问器
     */
    public void setSno(String sno) {
        this.sno=sno;
    }

    /**
     * 姓名 Get访问器
     */
    public String getName() {
        return this.name;
    }

    /**
     * 姓名 Set访问器
     */
    public void setName(String name) {
        this.name=name;
    }

    /**
     * 班级 Get访问器
     */
    public String getClasses() {
        return this.classes;
    }

    /**
     * 班级 Set访问器
     */
    public void setClasses(String classes) {
        this.classes=classes;
    }

    /**
     * 专业 Get访问器
     */
    public String getMajor() {
        return this.major;
    }

    /**
     * 专业 Set访问器
     */
    public void setMajor(String major) {
        this.major=major;
    }

    /**
     * 联系方式 Get访问器
     */
    public String getPhone() {
        return this.phone;
    }

    /**
     * 联系方式 Set访问器
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
