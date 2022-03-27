package Student_Manage;

public class student_information {
	private String stuID;    /*  学生学号  */
	private String name;     /* 学生姓名 */
	private String sex;     /* 学生性别  */
    private String where;        /*  学生院系  */
    private String callnumber;   /* 学生电话号码   */
    private String item;   /* 学生比赛项目 */
    private String math;   /* 学生比赛成绩 */

    
    /*无参构造函数*/
    public student_information() {
    
    }
    

    
    /*有参构造函数 */
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
    
    //从文件中录入的数据格式    学号","姓名","性别","院系","电话号码","比赛项目","成绩"  
    public String fileString()
	{
		return stuID+" "+name+" "+sex+" "+where+"  "+callnumber+"  "+item+"  "+math;
	}

   
}
