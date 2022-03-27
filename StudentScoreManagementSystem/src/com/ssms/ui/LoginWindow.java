package com.ssms.ui;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * 	�û���¼����
 *
 */
public class LoginWindow extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FlowPane pane = new FlowPane();
        pane.setPadding(new Insets(20, 20, 20, 20));
        //���ÿؼ�֮��Ĵ�ֱ�������
        pane.setHgap(15);
        //���ÿؼ�֮���ˮƽ�������
        pane.setVgap(15);
        //����ؼ�
        Label lbTitle = new Label("�� �û���¼ ��");
        lbTitle.setFont(Font.font("����", FontWeight.NORMAL, 25));
        lbTitle.setPrefWidth(450);
        lbTitle.setAlignment(Pos.CENTER);
        //
        Label label1 = new Label("  �û���:");
        label1.setPrefWidth(160);
        label1.setAlignment(Pos.CENTER_RIGHT);
        label1.setFont(Font.font(20));
        //
        TextField tfUser = new TextField();
        tfUser.setPrefWidth(200);
        //
        Label label2 = new Label("��   ��:");
        label2.setPrefWidth(160);
        label2.setAlignment(Pos.CENTER_RIGHT);
        label2.setFont(Font.font(20));
        //
        PasswordField tfPwd = new PasswordField();
        tfPwd.setPrefWidth(200);
        //
        Button btnLogin = new Button("ȷ�ϵ�¼");
        btnLogin.setOnAction(event -> {
        	//�������
        	if (tfUser.getText().trim().isEmpty() || tfPwd.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "���Ȳ�ȫ���ĵ�¼��Ϣ!", "��ʾ", JOptionPane.ERROR_MESSAGE);
                return;
            }
        	//����û����������Ƿ���ȷ
        	if(tfUser.getText().trim().equals("hxh") && tfPwd.getText().trim().equals("123456")) {
        		//��¼�ɹ�
        		//���ش���
                primaryStage.hide();
                //�������˵�����
                new MainWindow().start(new Stage());
        	}else {
                //��¼ʧ��
            	JOptionPane.showMessageDialog(null, "��½ʧ��,�����û����������Ƿ���ȷ!", "��ʾ", JOptionPane.ERROR_MESSAGE);
            }
        });
        Label tmp = new Label();
        tmp.setPrefWidth(160);
        //���ؼ����뵽������
        pane.getChildren().addAll(lbTitle, label1, tfUser, label2, tfPwd,tmp, btnLogin);
        Scene scene = new Scene(pane, 500, 200);
        primaryStage.setScene(scene);
        //���ô������
        primaryStage.setTitle("ѧ���ɼ�����ϵͳ ");
        //���ý�ֹ�ı䴰���С
        primaryStage.setResizable(false);
        primaryStage.show();
    }

}
