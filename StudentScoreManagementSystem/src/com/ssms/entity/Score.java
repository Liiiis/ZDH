package com.ssms.entity;

import java.io.Serializable;

/**
 * �ɼ���Ϣʵ����
 */
public class Score implements Serializable{

    // ѧ��
    private String sno;
    // ���ĳɼ�
    private float score1;
    // ��ѧ�ɼ�
    private float score2;
    // Ӣ��ɼ�
    private float score3;
    // �����ɼ�
    private float score4;
    // �ܳɼ�
    private float tscore;
    

    /**
     * �޲ι��캯��
     */
    public Score() {
        super();
    }

    /**
     * ���ι��캯��
     */
    public Score(String sno) {
        super();
        this.sno=sno;
    }

    /**
     * ���ι��캯��
     */
    public Score(String sno,float score1,float score2,float score3,float score4) {
        super();
        this.sno=sno;
        this.score1=score1;
        this.score2=score2;
        this.score3=score3;
        this.score4=score4;
        //�Զ������ܳɼ�
        this.tscore=score1+score2+score3+score4;
    }
    
    /**
     * ���ι��캯��
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
     * ���ĳɼ� Get������
     */
    public float getScore1() {
        return this.score1;
    }

    /**
     * ���ĳɼ� Set������
     */
    public void setScore1(float score1) {
        this.score1=score1;
    }

    /**
     * ��ѧ�ɼ� Get������
     */
    public float getScore2() {
        return this.score2;
    }

    /**
     * ��ѧ�ɼ� Set������
     */
    public void setScore2(float score2) {
        this.score2=score2;
    }

    /**
     * Ӣ��ɼ� Get������
     */
    public float getScore3() {
        return this.score3;
    }

    /**
     * Ӣ��ɼ� Set������
     */
    public void setScore3(float score3) {
        this.score3=score3;
    }

    /**
     * �����ɼ� Get������
     */
    public float getScore4() {
        return this.score4;
    }

    /**
     * �����ɼ� Set������
     */
    public void setScore4(float score4) {
        this.score4=score4;
    }
    
    /**
     * �ܳɼ� Get������
     */
	public float getTscore() {
		return tscore;
	}

	/**
     * �ܳɼ� Set������
     */
	public void setTscore(float tscore) {
		this.tscore = tscore;
	}
}