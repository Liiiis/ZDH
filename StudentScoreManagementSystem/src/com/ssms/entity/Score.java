package com.ssms.entity;

import java.io.Serializable;

/**
 * 成绩信息实体类
 */
public class Score implements Serializable{

    // 学号
    private String sno;
    // 语文成绩
    private float score1;
    // 数学成绩
    private float score2;
    // 英语成绩
    private float score3;
    // 体育成绩
    private float score4;
    // 总成绩
    private float tscore;
    

    /**
     * 无参构造函数
     */
    public Score() {
        super();
    }

    /**
     * 带参构造函数
     */
    public Score(String sno) {
        super();
        this.sno=sno;
    }

    /**
     * 带参构造函数
     */
    public Score(String sno,float score1,float score2,float score3,float score4) {
        super();
        this.sno=sno;
        this.score1=score1;
        this.score2=score2;
        this.score3=score3;
        this.score4=score4;
        //自动计算总成绩
        this.tscore=score1+score2+score3+score4;
    }
    
    /**
     * 带参构造函数
     */
    public Score(String sno,float score1,float score2,float score3,float score4,float tscore ) {
        super();
        this.sno=sno;
        this.score1=score1;
        this.score2=score2;
        this.score3=score3;
        this.score4=score4;
        this.tscore=tscore;
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
     * 语文成绩 Get访问器
     */
    public float getScore1() {
        return this.score1;
    }

    /**
     * 语文成绩 Set访问器
     */
    public void setScore1(float score1) {
        this.score1=score1;
    }

    /**
     * 数学成绩 Get访问器
     */
    public float getScore2() {
        return this.score2;
    }

    /**
     * 数学成绩 Set访问器
     */
    public void setScore2(float score2) {
        this.score2=score2;
    }

    /**
     * 英语成绩 Get访问器
     */
    public float getScore3() {
        return this.score3;
    }

    /**
     * 英语成绩 Set访问器
     */
    public void setScore3(float score3) {
        this.score3=score3;
    }

    /**
     * 体育成绩 Get访问器
     */
    public float getScore4() {
        return this.score4;
    }

    /**
     * 体育成绩 Set访问器
     */
    public void setScore4(float score4) {
        this.score4=score4;
    }
    
    /**
     * 总成绩 Get访问器
     */
	public float getTscore() {
		return tscore;
	}

	/**
     * 总成绩 Set访问器
     */
	public void setTscore(float tscore) {
		this.tscore = tscore;
	}
}