package com.ssms.entity;

import java.io.Serializable;

/**
 * 教师信息实体类
 */
public class Teacher implements Serializable,Comparable<Teacher>{

    // 工号
    private String tno;
    // 姓名
    private String name;
    // 院系
    private String depart;
    // 任课班级
    private String classes;
    // 联系方式
    private String phone;

    /**
     * 无参构造函数
     */
    public Teacher() {
        super();
    }

    /**
     * 带参构造函数
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
     * 工号 Get访问器
     */
    public String getTno() {
        return this.tno;
    }

    /**
     * 工号 Set访问器
     */
    public void setTno(String tno) {
        this.tno=tno;
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
     * 部门 Get访问器
     */
    public String getDepart() {
        return this.depart;
    }

    /**
     * 部门 Set访问器
     */
    public void setDepart(String depart) {
        this.depart=depart;
    }

    /**
     * 任课班级 Get访问器
     */
    public String getClasses() {
        return this.classes;
    }

    /**
     * 任课班级 Set访问器
     */
    public void setClasses(String classes) {
        this.classes=classes;
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
	public int compareTo(Teacher arg0) {
		// TODO Auto-generated method stub
		return this.tno.compareTo(arg0.tno);
	}
}
