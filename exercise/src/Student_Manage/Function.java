package Student_Manage;


import java.io.*;
import java.util.*;


//修改，删除，增加某个学生信息

class Function {
	
	//本程序学生数量不多，用数组列表ArrayList,  当学生数据多的时候，考虑用链表
    ArrayList<student_information> arry=new ArrayList<student_information>();
    
//    测试
//    测试
    ArrayList<pai_ming> list=new ArrayList<pai_ming>();
//    测试
//    测试
    

    
	public Function()           //读文件，将文件中的学生信息存放在数组列表
	{
		this.readfile();
		this.scanfile();
	}
	

	//根据学号查找学生信息，找到返回学号，找不到返回-1
	public int find(String str) 
	{		
	    for (int i = 0; i<arry.size(); i++)
	      if (arry.get(i).getStuID().equals(str))
						return i;//等于在数组列表的位置
			return -1;
	}
	
//	测试
//	测试
	public int found(String str) 
	{		
	    for (int i = 0; i<arry.size(); i++)
	      if (arry.get(i).getWhere().equals(str))
						return i;//等于在数组列表的位置
			return -1;
	}
	
	public int fa(String str) 
	{		
	    for (int i = 0; i<list.size(); i++)
	      if (list.get(i).getCollege().equals(str))
						return i;//等于在数组列表的位置
			return -1;
	}
	
//	测试
//	测试
	
	
	//修改学生信息
	public void update(student_information stu) {
		int flag=find(stu.getStuID());    //查找是否存在
		arry.set(flag, stu);		   //替换 flag 处的学生信息
	}
	
//	测试
//	public void gexing(pai_ming stuu) {
//		int flag=fa(stuu.getTotal());    //查找是否存在
//		list.set(flag, stuu);		   //替换 flag 处的学生信息
//	}	
	
	
	
//	测试
	// 读文件1，
	public boolean readfile() {					 
			String t=null;
			try{
				FileReader f1 = new FileReader("student.txt");
				BufferedReader br=new BufferedReader(f1);				
				arry.clear();    //	清除原数组列表中的数据				
				while ((t= br.readLine())!= null)
					{
					  String [] s=t.split("\\s+");				 
					  student_information st=new student_information(s[0],s[1],s[2],s[3],s[4],s[5],s[6]);//注意若该文件每行没有七个字符串，则会出错
					  arry.add(st);
					  System.out.println("读取文件：");
					  System.out.println(s[0]);
					}
				     f1.close();
				     br.close();				     
				     return true;
					} catch (IOException e) {
							// TODO 自动生成的 catch 块
					e.printStackTrace();
					return false;
				}	
		     }
	
//	测试
//	测试
//	测试
//	读文件2
	public boolean scanfile() {					 
		String t=null;
		try{
			FileReader f1 = new FileReader("C:\\Users\\qingshan\\defengpaiming.txt");
			BufferedReader br=new BufferedReader(f1);				
			list.clear();
			//	清除原数组列表中的数据				
			while ((t= br.readLine())!= null)
				{
				  String [] s=t.split("\\s+");				 
				  pai_ming st=new pai_ming(s[0],s[1]);//注意若该文件每行没有两个字符串，则会出错
				  list.add(st);
				  System.out.println("读取文件：");
				  System.out.println(s[0]);
				}
			     f1.close();
			     br.close();				     
			     return true;
				} catch (IOException e) {
						// TODO 自动生成的 catch 块
				e.printStackTrace();
				return false;
			}	
	     }	
	
//	测试
//	测试
//	测试
	
	
	
	
	//添加学生信息	
	public boolean add(student_information stu)
	     {		
		System.out.println();	
		System.out.println("要添加的学生");	
		System.out.println(stu.fileString());
		System.out.println();
		if (find(stu.getStuID())!=-1)
			return false;		
		arry.add(stu); 
		return true;			
	}
	
	//写注意每行必须有七个元素，否则读取文件并判断是否存在时会出错，没有输入的用---代替
	public boolean writefile() {
				FileWriter fw=null;
				BufferedWriter out=null;
				try {
					 fw = new FileWriter("student.txt");    
					 out = new BufferedWriter(fw);
					 //姓名学号等中间隔一个空格
						for(int i=0;i<arry.size();i++){
							String s=arry.get(i).fileString();
							System.out.println("数组列表数据：");
							System.out.println(arry.get(i).fileString());
					    	out.write(s);
					    	out.newLine();
					    }
						out.close();
						fw.close();
						return true;
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
		   }
	
//	测试
//	测试
//	写注意每行必须有两个元素，否则读取文件并判断是否存在时会出错
	public boolean xiefile() {
		FileWriter jia=null;
		BufferedWriter you=null;
		try {
			 jia = new FileWriter("C:\\Users\\qingshan\\defengpaiming.txt");   
			 you = new BufferedWriter(jia);
			 //姓名学号等中间隔一个空格
				for(int i=0;i<list.size();i++){
					String s=list.get(i).file1String();
					System.out.println("数组列表数据：");
					System.out.println(list.get(i).file1String());
			    	you.write(s);
			    	you.newLine();
			    }
				you.close();
				jia.close();
				return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
   }
	
//	测试
//	测试
	public boolean adding(pai_ming stuu)
    {		
	System.out.println();	
	System.out.println("要添加的学院");	
	System.out.println(stuu.file1String());
	System.out.println();
	list.add(stuu); 
	return true;			
}

	
	
	
	
//	测试
//	测试
//	返回arry中特殊的值
	public String ti(Function t1,String t2) {
		String t3=null;
		if(t1.found(t2)!=-1) {
			int ai=t1.found(t2);
			t3=arry.get(ai).getMath();
		}
		
		return t3;
		
	}
	
	
//	测试
//	返回list中特殊的值
	public String huan(Function t1,String t2) {
		String t3=null;
		if(t1.fa(t2)!=-1) {
			int ai=t1.fa(t2);
			t3=list.get(ai).getTotal();
		}
		
		return t3;
		
	}
	
//	测试
//	测试
	
	
	//删除学生信息,并且文件中的内容也要修改
	public boolean delete(String s)	//添加学生记信息
	{  
		int pos=find(s);
		if (pos==-1)
			return false;
		
		arry.remove(pos);    
		return true;
	}
	
	
//	测试
//	测试
//	删除院系信息
	
	public boolean shachu(String s)	
	{  
		int pos=fa(s);
		list.remove(pos);
		return true;
	}
		
//	测试
//	测试
//  测试
	
//	public boolean shanchu1(String s)	
//	{  
//		int pos=fa(s);
//		list.remove(pos);
//		if(fa(s)!=-1) {
//			int po=fa(s);
//			while(list.get(pos).equals(s)) {
//				list.remove(po);
//				po++;
//			}
//		}
//
//		return true;
//	}
	
//	public boolean shanchu2(String s,String c) {
//		
//		
//		
//		return true;
//	}
//	
//	public String jian(Function t1,String t2) {
//		String t3=null;
//		if(t1.found(t2)!=-1) {
//			int ai=t1.found(t2);
//			t3=arry.get(ai).getMath();
//		}
//		
//		return t3;
//		
//	}
		
/*
	//删除学生信息，并且重写文件内容					
	 public boolean delete(String s) {	
		        File file =new File("student.txt");
		        try {
		            if(!file.exists()) {
		                file.createNewFile();
		            }
		            FileWriter fileWriter =new FileWriter(file);
		            fileWriter.write("");
		            fileWriter.flush();
		            fileWriter.close();		            
	    			int flag=find(s);		    		
		    		System.out.print(flag);
		    		arry.remove(flag);  		    		
		    		writefile();
		    		return true;	            
		        } catch (IOException e) {
		            e.printStackTrace();
		            return false;
		        }				
		    }
		    */		
}			
	
