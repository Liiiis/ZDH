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
 * 	用户登录界面
 *
 */
public class LoginWindow extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FlowPane pane = new FlowPane();
        pane.setPadding(new Insets(20, 20, 20, 20));
        //设置控件之间的垂直间隔距离
        pane.setHgap(15);
        //设置控件之间的水平间隔距离
        pane.setVgap(15);
        //加入控件
        Label lbTitle = new Label("— 用户登录 —");
        lbTitle.setFont(Font.font("黑体", FontWeight.NORMAL, 25));
        lbTitle.setPrefWidth(450);
        lbTitle.setAlignment(Pos.CENTER);
        //
        Label label1 = new Label("  用户名:");
        label1.setPrefWidth(160);
        label1.setAlignment(Pos.CENTER_RIGHT);
        label1.setFont(Font.font(20));
        //
        TextField tfUser = new TextField();
        tfUser.setPrefWidth(200);
        //
        Label label2 = new Label("密   码:");
        label2.setPrefWidth(160);
        label2.setAlignment(Pos.CENTER_RIGHT);
        label2.setFont(Font.font(20));
        //
        PasswordField tfPwd = new PasswordField();
        tfPwd.setPrefWidth(200);
        //
        Button btnLogin = new Button("确认登录");
        btnLogin.setOnAction(event -> {
        	//检查输入
        	if (tfUser.getText().trim().isEmpty() || tfPwd.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "请先补全您的登录信息!", "提示", JOptionPane.ERROR_MESSAGE);
                return;
            }
        	//检查用户名或密码是否正确
        	if(tfUser.getText().trim().equals("hxh") && tfPwd.getText().trim().equals("123456")) {
        		//登录成功
        		//隐藏窗体
                primaryStage.hide();
                //进入主菜单窗体
                new MainWindow().start(new Stage());
        	}else {
                //登录失败
            	JOptionPane.showMessageDialog(null, "登陆失败,请检查用户名或密码是否正确!", "提示", JOptionPane.ERROR_MESSAGE);
            }
        });
        Label tmp = new Label();
        tmp.setPrefWidth(160);
        //将控件加入到窗体中
        pane.getChildren().addAll(lbTitle, label1, tfUser, label2, tfPwd,tmp, btnLogin);
        Scene scene = new Scene(pane, 500, 200);
        primaryStage.setScene(scene);
        //设置窗体标题
        primaryStage.setTitle("学生成绩管理系统 ");
        //设置禁止改变窗体大小
        primaryStage.setResizable(false);
        primaryStage.show();
    }

}
