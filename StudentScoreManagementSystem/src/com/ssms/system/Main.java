package com.ssms.system;

import com.ssms.ui.LoginWindow;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * 	程序启动类
 *
 */
public class Main extends Application {

	public static void main(String[] args) {
		// 启动程序
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//进入登录界面
		new LoginWindow().start(primaryStage);
	}
}
