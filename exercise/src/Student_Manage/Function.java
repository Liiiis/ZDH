package Student_Manage;


import java.io.*;
import java.util.*;


//�޸ģ�ɾ��������ĳ��ѧ����Ϣ

class Function {
	
	//������ѧ���������࣬�������б�ArrayList,  ��ѧ�����ݶ��ʱ�򣬿���������
    ArrayList<student_information> arry=new ArrayList<student_information>();
    
//    ����
//    ����
    ArrayList<pai_ming> list=new ArrayList<pai_ming>();
//    ����
//    ����
    

    
	public Function()           //���ļ������ļ��е�ѧ����Ϣ����������б�
	{
		this.readfile();
		this.scanfile();
	}
	

	//����ѧ�Ų���ѧ����Ϣ���ҵ�����ѧ�ţ��Ҳ�������-1
	public int find(String str) 
	{		
	    for (int i = 0; i<arry.size(); i++)
	      if (arry.get(i).getStuID().equals(str))
						return i;//�����������б��λ��
			return -1;
	}
	
//	����
//	����
	public int found(String str) 
	{		
	    for (int i = 0; i<arry.size(); i++)
	      if (arry.get(i).getWhere().equals(str))
						return i;//�����������б��λ��
			return -1;
	}
	
	public int fa(String str) 
	{		
	    for (int i = 0; i<list.size(); i++)
	      if (list.get(i).getCollege().equals(str))
						return i;//�����������б��λ��
			return -1;
	}
	
//	����
//	����
	
	
	//�޸�ѧ����Ϣ
	public void update(student_information stu) {
		int flag=find(stu.getStuID());    //�����Ƿ����
		arry.set(flag, stu);		   //�滻 flag ����ѧ����Ϣ
	}
	
//	����
//	public void gexing(pai_ming stuu) {
//		int flag=fa(stuu.getTotal());    //�����Ƿ����
//		list.set(flag, stuu);		   //�滻 flag ����ѧ����Ϣ
//	}	
	
	
	
//	����
	// ���ļ�1��
	public boolean readfile() {					 
			String t=null;
			try{
				FileReader f1 = new FileReader("student.txt");
				BufferedReader br=new BufferedReader(f1);				
				arry.clear();    //	���ԭ�����б��е�����				
				while ((t= br.readLine())!= null)
					{
					  String [] s=t.split("\\s+");				 
					  student_information st=new student_information(s[0],s[1],s[2],s[3],s[4],s[5],s[6]);//ע�������ļ�ÿ��û���߸��ַ�����������
					  arry.add(st);
					  System.out.println("��ȡ�ļ���");
					  System.out.println(s[0]);
					}
				     f1.close();
				     br.close();				     
				     return true;
					} catch (IOException e) {
							// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
					return false;
				}	
		     }
	
//	����
//	����
//	����
//	���ļ�2
	public boolean scanfile() {					 
		String t=null;
		try{
			FileReader f1 = new FileReader("C:\\Users\\qingshan\\defengpaiming.txt");
			BufferedReader br=new BufferedReader(f1);				
			list.clear();
			//	���ԭ�����б��е�����				
			while ((t= br.readLine())!= null)
				{
				  String [] s=t.split("\\s+");				 
				  pai_ming st=new pai_ming(s[0],s[1]);//ע�������ļ�ÿ��û�������ַ�����������
				  list.add(st);
				  System.out.println("��ȡ�ļ���");
				  System.out.println(s[0]);
				}
			     f1.close();
			     br.close();				     
			     return true;
				} catch (IOException e) {
						// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
				return false;
			}	
	     }	
	
//	����
//	����
//	����
	
	
	
	
	//���ѧ����Ϣ	
	public boolean add(student_information stu)
	     {		
		System.out.println();	
		System.out.println("Ҫ��ӵ�ѧ��");	
		System.out.println(stu.fileString());
		System.out.println();
		if (find(stu.getStuID())!=-1)
			return false;		
		arry.add(stu); 
		return true;			
	}
	
	//дע��ÿ�б������߸�Ԫ�أ������ȡ�ļ����ж��Ƿ����ʱ�����û���������---����
	public boolean writefile() {
				FileWriter fw=null;
				BufferedWriter out=null;
				try {
					 fw = new FileWriter("student.txt");    
					 out = new BufferedWriter(fw);
					 //����ѧ�ŵ��м��һ���ո�
						for(int i=0;i<arry.size();i++){
							String s=arry.get(i).fileString();
							System.out.println("�����б����ݣ�");
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
	
//	����
//	����
//	дע��ÿ�б���������Ԫ�أ������ȡ�ļ����ж��Ƿ����ʱ�����
	public boolean xiefile() {
		FileWriter jia=null;
		BufferedWriter you=null;
		try {
			 jia = new FileWriter("C:\\Users\\qingshan\\defengpaiming.txt");   
			 you = new BufferedWriter(jia);
			 //����ѧ�ŵ��м��һ���ո�
				for(int i=0;i<list.size();i++){
					String s=list.get(i).file1String();
					System.out.println("�����б����ݣ�");
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
	
//	����
//	����
	public boolean adding(pai_ming stuu)
    {		
	System.out.println();	
	System.out.println("Ҫ��ӵ�ѧԺ");	
	System.out.println(stuu.file1String());
	System.out.println();
	list.add(stuu); 
	return true;			
}

	
	
	
	
//	����
//	����
//	����arry�������ֵ
	public String ti(Function t1,String t2) {
		String t3=null;
		if(t1.found(t2)!=-1) {
			int ai=t1.found(t2);
			t3=arry.get(ai).getMath();
		}
		
		return t3;
		
	}
	
	
//	����
//	����list�������ֵ
	public String huan(Function t1,String t2) {
		String t3=null;
		if(t1.fa(t2)!=-1) {
			int ai=t1.fa(t2);
			t3=list.get(ai).getTotal();
		}
		
		return t3;
		
	}
	
//	����
//	����
	
	
	//ɾ��ѧ����Ϣ,�����ļ��е�����ҲҪ�޸�
	public boolean delete(String s)	//���ѧ������Ϣ
	{  
		int pos=find(s);
		if (pos==-1)
			return false;
		
		arry.remove(pos);    
		return true;
	}
	
	
//	����
//	����
//	ɾ��Ժϵ��Ϣ
	
	public boolean shachu(String s)	
	{  
		int pos=fa(s);
		list.remove(pos);
		return true;
	}
		
//	����
//	����
//  ����
	
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
	//ɾ��ѧ����Ϣ��������д�ļ�����					
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
	
