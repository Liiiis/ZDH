package Student_Manage;

//信息管理人员登陆后的界面
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;/*ArrayList;
import java.util.Hashtable;
*/
public class ManagerLogin extends JFrame implements  ActionListener{
	
	Function fun=new Function();
	
	//定义组件 
	JLabel lb1=new JLabel("当前是信息管理人员登录界面" );//JLabel 对象可以显示文本、图像
	JLabel lb2=new JLabel("提示：修改、删除请填写学号、院系");
	
    JTextField 学号,姓名,院系,电话号码,比赛项目,成绩;//输入学生基本信息得文本
    JRadioButton 男,女;//声明单选项对象，性别选择
    ButtonGroup group=null;//声明按钮组
    JButton 录入,查询,删除,修改,显示,返回,排名;//声明相应的操作的按钮
    JPanel p1,p2,p3,p4,p5,p6,p7,pv,ph,pb,pan;//调节布局的通道
   

    
    public ManagerLogin(){       //负责管理员登录的主窗口
        super("运动会计分管理系统");
        学号=new JTextField(15);//创建文本信息的的对象
        姓名=new JTextField(15);
        电话号码=new JTextField(15);
        院系=new JTextField(15);
        比赛项目=new JTextField(15);
        成绩=new JTextField(15);
        
        group=new ButtonGroup();
        男=new JRadioButton("男");//初始化单选框,
        女=new JRadioButton("女");
        group.add(男);//把按钮添加到按钮组
        group.add(女);
        录入=new JButton("增加运动员信息");//创建按钮对象
        查询=new JButton("查询符合条件的运动员信息");
        删除=new JButton("删除运动员信息");
        修改=new JButton("修改运动员信息");
        显示=new JButton("显示全部运动员信息");
        返回=new JButton("返回登录界面");
        排名=new JButton("显示院系得分排名");
        
        
        
    
        pb=new JPanel();
        pb.add(lb1,JLabel.CENTER);
                
        p1=new JPanel();//创建一个面板     
       
        p1.add(lb2,JLabel.CENTER);
        p1.add(new JLabel("学号:",JLabel.CENTER));//JLabel.CENTER是指JLabel的中心点。CENTER是中心点的坐标
        p1.add(学号);
        
        p2=new JPanel();
        p2.add(new JLabel("姓名:",JLabel.CENTER));
        p2.add(姓名);
        p3=new JPanel();
        p3.add(new JLabel("性别:",JLabel.CENTER));
        p3.add(男);
        p3.add(女);
        p4=new JPanel();
        p4.add(new JLabel("院系:",JLabel.CENTER));
        p4.add(院系);
        p5=new JPanel();
        p5.add(new JLabel("电话号码:",JLabel.CENTER));
        p5.add(电话号码);    
        p6=new JPanel();
        p6.add(new JLabel("比赛项目:",JLabel.CENTER));
        p6.add(比赛项目);
        p7=new JPanel();
        p7.add(new JLabel("成绩:",JLabel.CENTER));
        p7.add(成绩);
       
        pv=new JPanel();//面板
        pv.setLayout(new GridLayout(7,1));   //把pv组件设置成第七行1列的网格布局
            
        pv.add(p1);//把面板放到容器中,add()代表容器
        pv.add(p2);
        pv.add(p3);
        pv.add(p4);
        pv.add(p5);
        pv.add(p6);
        pv.add(p7);
               
        ph=new JPanel();      
        ph.add(录入);
        ph.add(查询);
        ph.add(修改);
        ph.add(删除);    
        ph.add(显示);
        ph.add(返回);
        
        
        
        pan=new JPanel();
        pan.add(排名);
               
        Container con=getContentPane();//建立容器对象con,取得容器面板
        con.setLayout(new BorderLayout());//设置布局为边框布局，边框布局分东南西北中5个方位来添加控件。
        //若没有指定方位，将添加到中间，上下左右都可以扩展
        con.add(pb, BorderLayout.NORTH);//Frame对象pb调用方法add（）,pb放在最北上方     
        con.add(pv, BorderLayout.CENTER);//pv在中间
        con.add(ph, BorderLayout.SOUTH);//ph在南边
        con.add(pan, BorderLayout.EAST);//pan在东边
        setDefaultCloseOperation(EXIT_ON_CLOSE);//置一个默认的关闭操作，也就是你的JFrame窗口的关闭按钮，点击它时，退出程序
        setBounds(450,200,900,500);//setBounds(x,y,width,height); x:组件在容器X轴上的起点 y:组件在容器Y轴上的起点 width:组件的长度 height:组件的高度
        setVisible(true);//目的是使控件可以显示出来,如果该控件已经被显示出来
        
        
        
        

          
        student_information stu=new student_information();
        pai_ming stuu=new pai_ming();
        ArrayList<student_information> arry=new ArrayList<student_information>();
        //添加监听
        //点击录入按钮  学号","姓名","性别","院系","电话号码","比赛项目","成绩"  
        录入.addActionListener(new ActionListener() {
    	   public void actionPerformed(ActionEvent e) {
    		   
    		   
//     		  测试
//     		  测试
//     		  测试
    		   String zuizhong=成绩.getText();
     		  
     		  String syuan = 院系.getText(); //获取文本框中内容
      		 if(fun.found(syuan)!=-1)
      		 {
      			String feng=null;
       		  double leijia=0;
      			 
      			 
      			 String tihuan=成绩.getText().trim();
      			 String yicunzhi=fun.ti(fun, syuan).trim();
      			 double fen1=Double.parseDouble(tihuan);
      			 double fen2=Double.parseDouble(yicunzhi);
      			 if(fun.fa(syuan)!=-1) {
      				 yicunzhi=fun.huan(fun, syuan).trim();
      				 fen2=Double.parseDouble(yicunzhi);
      			 }
      			leijia=fen1+fen2;
      			 zuizhong=String.valueOf(leijia);
      			 fun.shachu(syuan);
      			fun.xiefile();
      			
      		 }
//测试
//测试
//测试
    		   
    		   
    		   
    		   
    		 String sID = 学号.getText();    //获取文本框中内容
    		 if(fun.find(sID)!=-1)
    		 {
    			 JOptionPane.showMessageDialog(null, "该学号对应的学生已经存在！！！\n\n请重新输入或者修改已经录入的学生");   			 
    			  //清空文本框
    			 学号.setText("");
    			 姓名.setText("");
    			 院系.setText("");
    			 电话号码.setText("");
    			 比赛项目.setText("");
    			 成绩.setText("");
    			 return;
    		 }      		 
    		 String sname = 姓名.getText();  		 
    		 //先判断学号和姓名是否为空，如果为空，直接退出
    		 if(sID.equals("")||sname.equals(""))
    		 {
    			 JOptionPane.showMessageDialog(null, "录入的学生学号或姓名为空！！！\n\n请重新输入");
    			 return;
    		 }  	   		 	 
      		 String swhere = 院系.getText();     		 
      		 if(swhere.equals(""))//院系为空，没有输入
   			    swhere="--";  		 
      		 String scall = 电话号码.getText();
      		 if(scall.equals(""))
      			  scall="--";
      		 String sitem = 比赛项目.getText();
      		if(sitem.equals(""))
      		    sitem="--";
      		 String smath = 成绩.getText();
      		if(smath.equals(""))
      		   smath="--";

      		 String ssex=null;
             if(男.isSelected()){//选择男女
                ssex=男.getText();
             }
             else{
                 ssex=女.getText();
             }   
            
      		 stu.setStuID(sID);
      		 stu.setWhere(swhere);
      		 stu.setName(sname);
      		 stu.setSex(ssex);
      		 stu.setCallnummber(scall);
      		 stu.setItem(sitem);
      		 stu.setMath(smath);
  			 
      		 
//      		 测试
       		 stuu.setCollege(syuan);
       		 stuu.setTotal(zuizhong);
//       		 测试
	 
      		 
      		 //arry.clear();//	清除原数组列表中的数据	
      		// Input input=new Input(stu);

      		 System.out.println("信息管理员");
      		 System.out.println(stu.fileString());
//      		 测试
      		 System.out.println("信息管理员");
      		 System.out.println(stuu.file1String());
//      		 测试
      		 
      		 
      		 fun.add(stu);
      		 fun.writefile();
//   		 测试
//      	 测试
   		 fun.adding(stuu);
   		 fun.xiefile();
   		 
//   		 测试
      		 
      		 JOptionPane.showMessageDialog(null, "录入成功！！！");
      		 //dispose();
      		 setVisible(false); 
      		 new ManagerLogin();
      		 
      		  //清空文本框
      		 /*
			学号.setText("");
			姓名.setText("");
			院系.setText("");
			电话号码.setText("");
		    比赛项目.setText("");
			成绩.setText("");
			group.clearSelection();   //清空按钮已选状态
			*/
    	   }   	   
       });
        
                   
       //显示全部学生信息
          显示.addActionListener(new ActionListener() {
     	   public void actionPerformed(ActionEvent e) {     		   
     		   //show_stuall all=new show_stuall();     
     		   new show_stuall();   		     		   
     	    }	     		   
          });
          
          
         //显示院系得分排名
          排名.addActionListener(new ActionListener(){
        	  public void actionPerformed(ActionEvent e) {     		   
        		   //show_stuall all=new show_stuall();     
        		   
        		  new paiming();   		     		   
        	    }	     		   
             });
          
          
                      
        //查询某个学生信息       
        查询.addActionListener(new ActionListener() {
        	   public void actionPerformed(ActionEvent e) {      		  
        		   String sID = 学号.getText();    //获取文本框中内容 
        		   if(sID.equals(""))
        		     sID="--";       		   
          		  String sname = 姓名.getText();  		 
          		  if(sname.equals(""))
        			   sname="--";      		 	 
            	  String swhere = 院系.getText();     		 
            	  if(swhere.equals(""))             //年龄为空，没有输入
            		  swhere="--";  		 
            	  String scall = 电话号码.getText();
            	 if(scall.equals(""))
            			  scall="--";
            		 String sitem = 比赛项目.getText();
            		if(sitem.equals(""))
            			sitem="--";
            		 String smath = 成绩.getText();
            		if(smath.equals(""))
            			smath="--";
            		 String ssex=null;
                   if(男.isSelected()){//选择男女
                      ssex=男.getText();
                   }
                   else {
                	    if(女.isSelected())
                         ssex=女.getText();
                       else {
						  ssex="--";
					   }
                   }          		            
        	 		 //将要查询的符合条件的写入文件
        	 		FileWriter fw=null;
    				BufferedWriter out=null;//学号","姓名","性别","院系","电话号码","比赛项目","成绩" };
    				try {
    					 fw = new FileWriter("查询时暂时存放的文件.txt");
    					 out = new BufferedWriter(fw);
    					 //姓名学号等中间隔一个空格
    					 out.write(sID+"  ");
    					 out.write(sname+"  ");
    					 out.write(ssex+"  ");
    					 out.write(swhere+"  "); 					   					 
    					 out.write(scall+"  ");
    					 out.write(sitem+"  ");
    					 out.write(smath+"  "); //每次覆盖写入，无需全部保存
    				   					 
    					out.close();
    					fw.close();
    						
    				} catch (IOException e1) {
    					e1.printStackTrace();
    					
    				}     				
    				//开始查询
    				new showones();  
    				
    				//清空文本框
    				学号.setText("");
    				姓名.setText("");
    				院系.setText("");
    				电话号码.setText("");
    			    比赛项目.setText("");
    				成绩.setText("");
    				group.clearSelection();
    				
        	    }	     		   
             }); 
       
 
       
       //删除学生信息,按学号删除
          删除.addActionListener(new ActionListener() {
        	   public void actionPerformed(ActionEvent e) {
        		   String sID = 学号.getText(); //获取文本框中内容
//        		   String syuan=院系.getText();
//        		   String def=成绩.getText().trim();
        		   int flag=fun.find(sID);
        		   if(flag==-1)
        		   {
        			   JOptionPane.showMessageDialog(null, "未查找到该学号的学生！！！\n\n请重新输入");       			    
        		   }
        		   else {
        			  double leijian=0;
        			  String zuihou=null;
					 fun.delete(sID); 
        		     fun.writefile();
        		     
//测试        		     
// 
//          			 String yicunzhi=fun.ti(fun, syuan).trim();
//          			 double fen1=Double.parseDouble(def);
//          			 double fen2=Double.parseDouble(yicunzhi);
//          			 leijian=fen2-fen1;	     
//        		     zuihou=String.valueOf(leijian);
//        		     
////        		     测试
//        		     fun.gexing(stuu);;
//        		     fun.xiefile();
        		     JOptionPane.showMessageDialog(null, "删除成功！！！\n");
				 }
       		
        		   //清空文本框
       			学号.setText("");
       			姓名.setText("");
       			院系.setText("");
       			电话号码.setText("");
       		    比赛项目.setText("");
       			成绩.setText("");
       			group.clearSelection();
        		   
        	    }	     		   
             });       
          
          //修改学生信息
          修改.addActionListener(new ActionListener() {
          	   public void actionPerformed(ActionEvent e) {
          		  String sID = 学号.getText();    //获取文本框中内容
          		 int flag=fun.find(sID);    //查找是否存在      		
         		 if (flag==-1)
         		 {
     			   JOptionPane.showMessageDialog(null, "未查找到该学号的学生！！！\n\n请重新输入");
      			   return;  
     		    }    
         		else
      			   JOptionPane.showMessageDialog(null, "该系统中存在该学生数据\n\n,确认返回后请输入需要修改后的数据");
       			 		    		     		 
          		 String swhere = 院系.getText();     		 
          		 if(swhere.equals(""))//年龄为空，没有输入
          			swhere="--";  		 
          		 String scall = 电话号码.getText();
          		 if(scall.equals(""))
          			  scall="--";
          		 String sitem = 比赛项目.getText();
          		if(sitem.equals(""))
          			sitem="--";
          		 String smath = 成绩.getText();
          		if(smath.equals(""))
          			smath="--";
          		 String ssex=null;
                 if(男.isSelected()){//选择男女
                    ssex=男.getText();
                 }
                 else{
                     ssex=女.getText();
                 }   
                 String sname = 姓名.getText();  		 
        		 //先判断学号和姓名是否为空，如果为空，直接退出
        		 if(sname.equals(""))
        		 {
        			 JOptionPane.showMessageDialog(null, "录入的学生姓名为空！！！\n\n请重新输入");
        			 return;
        		 }  	
        		// student_information stu=new student_information();
          		 stu.setStuID(sID);
          		 stu.setWhere(swhere);
          		 stu.setName(sname);
          		 stu.setSex(ssex);
          		 stu.setCallnummber(scall);
          		 stu.setItem(sitem);
          		 stu.setMath(smath);
         		 
          		 fun.update(stu);       //修改
          		 fun.writefile();
          		 JOptionPane.showMessageDialog(null, "修改成功！！！");
          		  //清空文本框
     			学号.setText("");
     			姓名.setText("");
     			院系.setText("");
     			电话号码.setText("");
     		    比赛项目.setText("");
     			成绩.setText("");
     			group.clearSelection();	 		 			          		         		 
          	    }	     		             	   
               });
                        
    返回.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			setVisible(false);      //目的是使控件不可以显示出来
			new Login();
		}
	});
     }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}


