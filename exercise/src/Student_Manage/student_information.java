package Student_Manage;

public class student_information {
	private String stuID;    /*  ѧ��ѧ��  */
	private String name;     /* ѧ������ */
	private String sex;     /* ѧ���Ա�  */
    private String where;        /*  ѧ��Ժϵ  */
    private String callnumber;   /* ѧ���绰����   */
    private String item;   /* ѧ��������Ŀ */
    private String math;   /* ѧ�������ɼ� */

    
    /*�޲ι��캯��*/
    public student_information() {
    
    }
    

    
    /*�вι��캯�� */
    public student_information(String stuID,String name,String sex,String where,String callnumber,String item,String math) {
    	super();
    	this.stuID=stuID;
    	this.name=name;
    	this.sex=sex;
    	this.where=where;
    	this.callnumber=callnumber;
    	this.item=item;
    	this.math=math;
    }
    

    
    public String getStuID() {
    	return this.stuID;
    }
    public void setStuID(String stuID) {
    	this.stuID=stuID;
    }
    
    public String getName() {
    	return name;
    }
    public void setName(String name) {
    	this.name=name;
    }
    
    public String getSex() {
    	return this.where;
    }
    public void setSex(String sex) {
    	this.sex=sex;
    }
    
    public String getWhere() {
    	return this.where;
    }
    public void setWhere(String where) {
    	this.where=where;
    }
    
    public String getCallnumber() {
    	return this.callnumber;
    }
    public void setCallnummber(String callnumber) {
    	this.callnumber=callnumber;
    }
    
    public String getItem() {
    	return this.item;
    }
    public void setItem(String item) {
    	this.item=item;
    }
    
    public String getMath() {
    	return this.math;
    }
    public void setMath(String math) {
    	this.math=math;
    }
    
    //���ļ���¼������ݸ�ʽ    ѧ��","����","�Ա�","Ժϵ","�绰����","������Ŀ","�ɼ�"  
    public String fileString()
	{
		return stuID+" "+name+" "+sex+" "+where+"  "+callnumber+"  "+item+"  "+math;
	}

   
}
