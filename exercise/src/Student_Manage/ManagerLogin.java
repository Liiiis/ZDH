package Student_Manage;

//��Ϣ������Ա��½��Ľ���
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
	
	//������� 
	JLabel lb1=new JLabel("��ǰ����Ϣ������Ա��¼����" );//JLabel ���������ʾ�ı���ͼ��
	JLabel lb2=new JLabel("��ʾ���޸ġ�ɾ������дѧ�š�Ժϵ");
	
    JTextField ѧ��,����,Ժϵ,�绰����,������Ŀ,�ɼ�;//����ѧ��������Ϣ���ı�
    JRadioButton ��,Ů;//������ѡ������Ա�ѡ��
    ButtonGroup group=null;//������ť��
    JButton ¼��,��ѯ,ɾ��,�޸�,��ʾ,����,����;//������Ӧ�Ĳ����İ�ť
    JPanel p1,p2,p3,p4,p5,p6,p7,pv,ph,pb,pan;//���ڲ��ֵ�ͨ��
   

    
    public ManagerLogin(){       //�������Ա��¼��������
        super("�˶���Ʒֹ���ϵͳ");
        ѧ��=new JTextField(15);//�����ı���Ϣ�ĵĶ���
        ����=new JTextField(15);
        �绰����=new JTextField(15);
        Ժϵ=new JTextField(15);
        ������Ŀ=new JTextField(15);
        �ɼ�=new JTextField(15);
        
        group=new ButtonGroup();
        ��=new JRadioButton("��");//��ʼ����ѡ��,
        Ů=new JRadioButton("Ů");
        group.add(��);//�Ѱ�ť��ӵ���ť��
        group.add(Ů);
        ¼��=new JButton("�����˶�Ա��Ϣ");//������ť����
        ��ѯ=new JButton("��ѯ�����������˶�Ա��Ϣ");
        ɾ��=new JButton("ɾ���˶�Ա��Ϣ");
        �޸�=new JButton("�޸��˶�Ա��Ϣ");
        ��ʾ=new JButton("��ʾȫ���˶�Ա��Ϣ");
        ����=new JButton("���ص�¼����");
        ����=new JButton("��ʾԺϵ�÷�����");
        
        
        
    
        pb=new JPanel();
        pb.add(lb1,JLabel.CENTER);
                
        p1=new JPanel();//����һ�����     
       
        p1.add(lb2,JLabel.CENTER);
        p1.add(new JLabel("ѧ��:",JLabel.CENTER));//JLabel.CENTER��ָJLabel�����ĵ㡣CENTER�����ĵ������
        p1.add(ѧ��);
        
        p2=new JPanel();
        p2.add(new JLabel("����:",JLabel.CENTER));
        p2.add(����);
        p3=new JPanel();
        p3.add(new JLabel("�Ա�:",JLabel.CENTER));
        p3.add(��);
        p3.add(Ů);
        p4=new JPanel();
        p4.add(new JLabel("Ժϵ:",JLabel.CENTER));
        p4.add(Ժϵ);
        p5=new JPanel();
        p5.add(new JLabel("�绰����:",JLabel.CENTER));
        p5.add(�绰����);    
        p6=new JPanel();
        p6.add(new JLabel("������Ŀ:",JLabel.CENTER));
        p6.add(������Ŀ);
        p7=new JPanel();
        p7.add(new JLabel("�ɼ�:",JLabel.CENTER));
        p7.add(�ɼ�);
       
        pv=new JPanel();//���
        pv.setLayout(new GridLayout(7,1));   //��pv������óɵ�����1�е����񲼾�
            
        pv.add(p1);//�����ŵ�������,add()��������
        pv.add(p2);
        pv.add(p3);
        pv.add(p4);
        pv.add(p5);
        pv.add(p6);
        pv.add(p7);
               
        ph=new JPanel();      
        ph.add(¼��);
        ph.add(��ѯ);
        ph.add(�޸�);
        ph.add(ɾ��);    
        ph.add(��ʾ);
        ph.add(����);
        
        
        
        pan=new JPanel();
        pan.add(����);
               
        Container con=getContentPane();//������������con,ȡ���������
        con.setLayout(new BorderLayout());//���ò���Ϊ�߿򲼾֣��߿򲼾ֶַ���������5����λ����ӿؼ���
        //��û��ָ����λ������ӵ��м䣬�������Ҷ�������չ
        con.add(pb, BorderLayout.NORTH);//Frame����pb���÷���add����,pb������Ϸ�     
        con.add(pv, BorderLayout.CENTER);//pv���м�
        con.add(ph, BorderLayout.SOUTH);//ph���ϱ�
        con.add(pan, BorderLayout.EAST);//pan�ڶ���
        setDefaultCloseOperation(EXIT_ON_CLOSE);//��һ��Ĭ�ϵĹرղ�����Ҳ�������JFrame���ڵĹرհ�ť�������ʱ���˳�����
        setBounds(450,200,900,500);//setBounds(x,y,width,height); x:���������X���ϵ���� y:���������Y���ϵ���� width:����ĳ��� height:����ĸ߶�
        setVisible(true);//Ŀ����ʹ�ؼ�������ʾ����,����ÿؼ��Ѿ�����ʾ����
        
        
        
        

          
        student_information stu=new student_information();
        pai_ming stuu=new pai_ming();
        ArrayList<student_information> arry=new ArrayList<student_information>();
        //��Ӽ���
        //���¼�밴ť  ѧ��","����","�Ա�","Ժϵ","�绰����","������Ŀ","�ɼ�"  
        ¼��.addActionListener(new ActionListener() {
    	   public void actionPerformed(ActionEvent e) {
    		   
    		   
//     		  ����
//     		  ����
//     		  ����
    		   String zuizhong=�ɼ�.getText();
     		  
     		  String syuan = Ժϵ.getText(); //��ȡ�ı���������
      		 if(fun.found(syuan)!=-1)
      		 {
      			String feng=null;
       		  double leijia=0;
      			 
      			 
      			 String tihuan=�ɼ�.getText().trim();
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
//����
//����
//����
    		   
    		   
    		   
    		   
    		 String sID = ѧ��.getText();    //��ȡ�ı���������
    		 if(fun.find(sID)!=-1)
    		 {
    			 JOptionPane.showMessageDialog(null, "��ѧ�Ŷ�Ӧ��ѧ���Ѿ����ڣ�����\n\n��������������޸��Ѿ�¼���ѧ��");   			 
    			  //����ı���
    			 ѧ��.setText("");
    			 ����.setText("");
    			 Ժϵ.setText("");
    			 �绰����.setText("");
    			 ������Ŀ.setText("");
    			 �ɼ�.setText("");
    			 return;
    		 }      		 
    		 String sname = ����.getText();  		 
    		 //���ж�ѧ�ź������Ƿ�Ϊ�գ����Ϊ�գ�ֱ���˳�
    		 if(sID.equals("")||sname.equals(""))
    		 {
    			 JOptionPane.showMessageDialog(null, "¼���ѧ��ѧ�Ż�����Ϊ�գ�����\n\n����������");
    			 return;
    		 }  	   		 	 
      		 String swhere = Ժϵ.getText();     		 
      		 if(swhere.equals(""))//ԺϵΪ�գ�û������
   			    swhere="--";  		 
      		 String scall = �绰����.getText();
      		 if(scall.equals(""))
      			  scall="--";
      		 String sitem = ������Ŀ.getText();
      		if(sitem.equals(""))
      		    sitem="--";
      		 String smath = �ɼ�.getText();
      		if(smath.equals(""))
      		   smath="--";

      		 String ssex=null;
             if(��.isSelected()){//ѡ����Ů
                ssex=��.getText();
             }
             else{
                 ssex=Ů.getText();
             }   
            
      		 stu.setStuID(sID);
      		 stu.setWhere(swhere);
      		 stu.setName(sname);
      		 stu.setSex(ssex);
      		 stu.setCallnummber(scall);
      		 stu.setItem(sitem);
      		 stu.setMath(smath);
  			 
      		 
//      		 ����
       		 stuu.setCollege(syuan);
       		 stuu.setTotal(zuizhong);
//       		 ����
	 
      		 
      		 //arry.clear();//	���ԭ�����б��е�����	
      		// Input input=new Input(stu);

      		 System.out.println("��Ϣ����Ա");
      		 System.out.println(stu.fileString());
//      		 ����
      		 System.out.println("��Ϣ����Ա");
      		 System.out.println(stuu.file1String());
//      		 ����
      		 
      		 
      		 fun.add(stu);
      		 fun.writefile();
//   		 ����
//      	 ����
   		 fun.adding(stuu);
   		 fun.xiefile();
   		 
//   		 ����
      		 
      		 JOptionPane.showMessageDialog(null, "¼��ɹ�������");
      		 //dispose();
      		 setVisible(false); 
      		 new ManagerLogin();
      		 
      		  //����ı���
      		 /*
			ѧ��.setText("");
			����.setText("");
			Ժϵ.setText("");
			�绰����.setText("");
		    ������Ŀ.setText("");
			�ɼ�.setText("");
			group.clearSelection();   //��հ�ť��ѡ״̬
			*/
    	   }   	   
       });
        
                   
       //��ʾȫ��ѧ����Ϣ
          ��ʾ.addActionListener(new ActionListener() {
     	   public void actionPerformed(ActionEvent e) {     		   
     		   //show_stuall all=new show_stuall();     
     		   new show_stuall();   		     		   
     	    }	     		   
          });
          
          
         //��ʾԺϵ�÷�����
          ����.addActionListener(new ActionListener(){
        	  public void actionPerformed(ActionEvent e) {     		   
        		   //show_stuall all=new show_stuall();     
        		   
        		  new paiming();   		     		   
        	    }	     		   
             });
          
          
                      
        //��ѯĳ��ѧ����Ϣ       
        ��ѯ.addActionListener(new ActionListener() {
        	   public void actionPerformed(ActionEvent e) {      		  
        		   String sID = ѧ��.getText();    //��ȡ�ı��������� 
        		   if(sID.equals(""))
        		     sID="--";       		   
          		  String sname = ����.getText();  		 
          		  if(sname.equals(""))
        			   sname="--";      		 	 
            	  String swhere = Ժϵ.getText();     		 
            	  if(swhere.equals(""))             //����Ϊ�գ�û������
            		  swhere="--";  		 
            	  String scall = �绰����.getText();
            	 if(scall.equals(""))
            			  scall="--";
            		 String sitem = ������Ŀ.getText();
            		if(sitem.equals(""))
            			sitem="--";
            		 String smath = �ɼ�.getText();
            		if(smath.equals(""))
            			smath="--";
            		 String ssex=null;
                   if(��.isSelected()){//ѡ����Ů
                      ssex=��.getText();
                   }
                   else {
                	    if(Ů.isSelected())
                         ssex=Ů.getText();
                       else {
						  ssex="--";
					   }
                   }          		            
        	 		 //��Ҫ��ѯ�ķ���������д���ļ�
        	 		FileWriter fw=null;
    				BufferedWriter out=null;//ѧ��","����","�Ա�","Ժϵ","�绰����","������Ŀ","�ɼ�" };
    				try {
    					 fw = new FileWriter("��ѯʱ��ʱ��ŵ��ļ�.txt");
    					 out = new BufferedWriter(fw);
    					 //����ѧ�ŵ��м��һ���ո�
    					 out.write(sID+"  ");
    					 out.write(sname+"  ");
    					 out.write(ssex+"  ");
    					 out.write(swhere+"  "); 					   					 
    					 out.write(scall+"  ");
    					 out.write(sitem+"  ");
    					 out.write(smath+"  "); //ÿ�θ���д�룬����ȫ������
    				   					 
    					out.close();
    					fw.close();
    						
    				} catch (IOException e1) {
    					e1.printStackTrace();
    					
    				}     				
    				//��ʼ��ѯ
    				new showones();  
    				
    				//����ı���
    				ѧ��.setText("");
    				����.setText("");
    				Ժϵ.setText("");
    				�绰����.setText("");
    			    ������Ŀ.setText("");
    				�ɼ�.setText("");
    				group.clearSelection();
    				
        	    }	     		   
             }); 
       
 
       
       //ɾ��ѧ����Ϣ,��ѧ��ɾ��
          ɾ��.addActionListener(new ActionListener() {
        	   public void actionPerformed(ActionEvent e) {
        		   String sID = ѧ��.getText(); //��ȡ�ı���������
//        		   String syuan=Ժϵ.getText();
//        		   String def=�ɼ�.getText().trim();
        		   int flag=fun.find(sID);
        		   if(flag==-1)
        		   {
        			   JOptionPane.showMessageDialog(null, "δ���ҵ���ѧ�ŵ�ѧ��������\n\n����������");       			    
        		   }
        		   else {
        			  double leijian=0;
        			  String zuihou=null;
					 fun.delete(sID); 
        		     fun.writefile();
        		     
//����        		     
// 
//          			 String yicunzhi=fun.ti(fun, syuan).trim();
//          			 double fen1=Double.parseDouble(def);
//          			 double fen2=Double.parseDouble(yicunzhi);
//          			 leijian=fen2-fen1;	     
//        		     zuihou=String.valueOf(leijian);
//        		     
////        		     ����
//        		     fun.gexing(stuu);;
//        		     fun.xiefile();
        		     JOptionPane.showMessageDialog(null, "ɾ���ɹ�������\n");
				 }
       		
        		   //����ı���
       			ѧ��.setText("");
       			����.setText("");
       			Ժϵ.setText("");
       			�绰����.setText("");
       		    ������Ŀ.setText("");
       			�ɼ�.setText("");
       			group.clearSelection();
        		   
        	    }	     		   
             });       
          
          //�޸�ѧ����Ϣ
          �޸�.addActionListener(new ActionListener() {
          	   public void actionPerformed(ActionEvent e) {
          		  String sID = ѧ��.getText();    //��ȡ�ı���������
          		 int flag=fun.find(sID);    //�����Ƿ����      		
         		 if (flag==-1)
         		 {
     			   JOptionPane.showMessageDialog(null, "δ���ҵ���ѧ�ŵ�ѧ��������\n\n����������");
      			   return;  
     		    }    
         		else
      			   JOptionPane.showMessageDialog(null, "��ϵͳ�д��ڸ�ѧ������\n\n,ȷ�Ϸ��غ���������Ҫ�޸ĺ������");
       			 		    		     		 
          		 String swhere = Ժϵ.getText();     		 
          		 if(swhere.equals(""))//����Ϊ�գ�û������
          			swhere="--";  		 
          		 String scall = �绰����.getText();
          		 if(scall.equals(""))
          			  scall="--";
          		 String sitem = ������Ŀ.getText();
          		if(sitem.equals(""))
          			sitem="--";
          		 String smath = �ɼ�.getText();
          		if(smath.equals(""))
          			smath="--";
          		 String ssex=null;
                 if(��.isSelected()){//ѡ����Ů
                    ssex=��.getText();
                 }
                 else{
                     ssex=Ů.getText();
                 }   
                 String sname = ����.getText();  		 
        		 //���ж�ѧ�ź������Ƿ�Ϊ�գ����Ϊ�գ�ֱ���˳�
        		 if(sname.equals(""))
        		 {
        			 JOptionPane.showMessageDialog(null, "¼���ѧ������Ϊ�գ�����\n\n����������");
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
         		 
          		 fun.update(stu);       //�޸�
          		 fun.writefile();
          		 JOptionPane.showMessageDialog(null, "�޸ĳɹ�������");
          		  //����ı���
     			ѧ��.setText("");
     			����.setText("");
     			Ժϵ.setText("");
     			�绰����.setText("");
     		    ������Ŀ.setText("");
     			�ɼ�.setText("");
     			group.clearSelection();	 		 			          		         		 
          	    }	     		             	   
               });
                        
    ����.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			setVisible(false);      //Ŀ����ʹ�ؼ���������ʾ����
			new Login();
		}
	});
     }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}


