package Student_Manage;

public class pai_ming {
    private String college;
    private String total;
    
    
//    无参构造参数
    public pai_ming() {
        
    }
    
    
//    有参构造参数
    public pai_ming(String college,String total) {
    	super();
    	this.college=college;
    	this.total=total;

    }
    
    
    public String getCollege() {
    	return this.college;
    }
    public void setCollege(String college) {
    	this.college=college;
    }
    
    public String getTotal() {
    	return this.total;
    }
    public void setTotal(String total) {
    	this.total=total;
    }

    public String file1String()
	{
		return college+"  "+total;
	}

}
