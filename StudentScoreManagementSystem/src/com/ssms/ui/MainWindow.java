package com.ssms.ui;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;

import com.ssms.dao.ScoreDao;
import com.ssms.dao.StudentDao;
import com.ssms.dao.TeacherDao;
import com.ssms.entity.Score;
import com.ssms.entity.Student;
import com.ssms.entity.Teacher;
import com.ssms.utils.StringUtils;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainWindow extends Application {

	private StudentDao studentDao = new StudentDao();
	private TeacherDao teacherDao = new TeacherDao();
	private ScoreDao scoreDao = new ScoreDao();

	// 学生信息操作相关
	private TextField tfSnoName = new TextField();
	private final TableView<Student> studentTable = new TableView<Student>();
	private ObservableList<Student> studentObservableList = FXCollections.observableArrayList();

	// 教师信息操作相关
	private TextField tfTno = new TextField();
	private final TableView<Teacher> teacherTable = new TableView<Teacher>();
	private ObservableList<Teacher> teacherObservableList = FXCollections.observableArrayList();

	// 成绩信息操作相关
	private TextField tfScoSno = new TextField();
	private final TableView<Score> scoreTable = new TableView<Score>();
	private ObservableList<Score> scoreObservableList = FXCollections.observableArrayList();

	// 成绩排序操作相关
	private ChoiceBox cbSortObj;
	private ChoiceBox cbSortType;
	private final TableView<Score> scoreSortTable = new TableView<Score>();
	private ObservableList<Score> scoreSortObservableList = FXCollections.observableArrayList();

	// 成绩分析操作相关
	private ChoiceBox cbScoreType;
	private final TableView<Student> scoreAnalysisTable = new TableView<Student>();
	private ObservableList<Student> scoreAnalysisObservableList = FXCollections.observableArrayList();

	// 成绩统计操作相关
	private ChoiceBox cbStatisScoreType;
	private ChoiceBox cbStatisObj;
	private ChoiceBox cbStatisType;
	private final TableView<Score> scoreStatisTable = new TableView<Score>();
	private ObservableList<Score> scoreStatisObservableList = FXCollections.observableArrayList();

	@Override
	public void start(Stage primaryStage) {
		// 添加选项卡面板
		TabPane tabPane = new TabPane();
		// 学生信息操作
		Tab tabStudent = new Tab("学生信息操作");
		tabStudent.setClosable(false);
		tabStudent.setContent(this.studentTab());
		// 教师信息操作
		Tab tabTeacher = new Tab("教师信息操作");
		tabTeacher.setClosable(false);
		tabTeacher.setContent(this.teacherTab());
		// 成绩信息操作
		Tab tabScore = new Tab("成绩信息操作");
		tabScore.setClosable(false);
		tabScore.setContent(this.scoreTab());
		// 成绩排序操作
		Tab tabScoreSort = new Tab("成绩排序操作");
		tabScoreSort.setClosable(false);
		tabScoreSort.setContent(this.scoreSortTab());
		// 成绩分析操作
		Tab tabScoreAnalysis = new Tab("成绩分析操作");
		tabScoreAnalysis.setClosable(false);
		tabScoreAnalysis.setContent(this.scoreAnalysisTab());
		// 成绩分析操作
		Tab tabScoreStatis = new Tab("成绩分析操作");
		tabScoreStatis.setClosable(false);
		tabScoreStatis.setContent(this.scoreStatisTab());
		// 将选项卡加入选项卡面板中
		tabPane.getTabs().add(tabStudent);
		tabPane.getTabs().add(tabTeacher);
		tabPane.getTabs().add(tabScore);
		tabPane.getTabs().add(tabScoreSort);
		tabPane.getTabs().add(tabScoreAnalysis);
		tabPane.getTabs().add(tabScoreStatis);
		// 添加布局
		VBox vBox = new VBox(tabPane);
		Scene scene = new Scene(vBox, 700, 600);
		primaryStage.setScene(scene);
		// 设置窗体标题
		primaryStage.setTitle("学生成绩管理系统");
		// 设置禁止改变窗体大小
		primaryStage.setResizable(false);
		// 屏幕居中显示
		primaryStage.centerOnScreen();
		primaryStage.show();
	}

	/**
	 * 学生信息操作面板
	 * 
	 * @return
	 */
	private GridPane studentTab() {
		GridPane pane = new GridPane();
		// 按钮
		Button btnQuery = new Button("查询");
		btnQuery.setPrefWidth(60);
		Button btnAdd = new Button("添加");
		btnAdd.setPrefWidth(60);
		Button btnModify = new Button("修改");
		btnModify.setPrefWidth(60);
		Button btnDelete = new Button("删除");
		btnDelete.setPrefWidth(60);
		Button btnRefresh = new Button("刷新");
		btnRefresh.setPrefWidth(60);
		HBox hBox = new HBox(5);
		Label lb = new Label("学号/姓名:");
		lb.setPrefWidth(70);
		hBox.getChildren().addAll(lb, tfSnoName, btnQuery, btnAdd, btnModify, btnDelete, btnRefresh);
		tfSnoName.setPrefColumnCount(15);
		VBox vBox = new VBox(10);
		vBox.getChildren().addAll(hBox);
		// 事件监听
		btnQuery.setOnAction(e -> queryStudent());
		btnAdd.setOnAction(e -> addStudent());
		btnModify.setOnAction(e -> modifyStudent());
		btnDelete.setOnAction(e -> deleteStudent());
		btnRefresh.setOnAction(e -> refreshStudent());
		// 添加table数据列
		TableColumn snoCol = new TableColumn("学号");
		snoCol.setCellValueFactory(new PropertyValueFactory<Student, String>("sno"));
		TableColumn nameCol = new TableColumn("姓名");
		nameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
		TableColumn classesCol = new TableColumn("班级");
		classesCol.setCellValueFactory(new PropertyValueFactory<Student, String>("classes"));
		TableColumn majorCol = new TableColumn("专业");
		majorCol.setCellValueFactory(new PropertyValueFactory<Student, String>("major"));
		TableColumn phoneCol = new TableColumn("联系方式");
		phoneCol.setCellValueFactory(new PropertyValueFactory<Student, String>("phone"));
		studentTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		studentTable.getColumns().addAll(snoCol, nameCol, classesCol, majorCol, phoneCol);
		studentTable.setItems(studentObservableList);
		vBox.getChildren().addAll(studentTable);
		vBox.setPadding(new Insets(15, 15, 15, 15));
		pane.getChildren().add(vBox);
		// 刷新学生信息
		refreshStudent();
		return pane;
	}

	/**
	 * 刷新学生信息
	 */
	private void refreshStudent() {
		// 存放学生信息
		List<Student> stuList = studentDao.doGetStudentList();
		// 清空表格
		studentObservableList.clear();
		// 将学生信息添加到表格中
		studentObservableList.addAll(stuList);
	}

	/**
	 * 查询学生信息
	 */
	private void queryStudent() {
		// 检查输入
		if (tfSnoName.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "请先补全查询信息!", "提示", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// 实例化学生对象
		Student student = new Student(tfSnoName.getText().trim(), tfSnoName.getText().trim(), null, null, null);
		// 开始查询
		List<Student> list = studentDao.doQueryStudentBySnoOrName(student);
		// 清空表格
		studentObservableList.clear();
		// 将学生信息添加到表格中
		studentObservableList.addAll(list);
	}

	/**
	 * 添加学生信息
	 */
	private void addStudent() {
		Stage stage = new Stage();
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(15, 15, 15, 15));
		pane.setHgap(5);
		pane.setVgap(5);
		// 添加相关控件
		pane.add(new Label("学号:"), 0, 0);
		TextField tfSno = new TextField();
		tfSno.setPrefColumnCount(20);
		pane.add(tfSno, 1, 0);
		//
		pane.add(new Label("姓名:"), 0, 1);
		TextField tfName = new TextField();
		tfName.setPrefColumnCount(20);
		pane.add(tfName, 1, 1);
		//
		pane.add(new Label("班级:"), 0, 2);
		TextField tfClasses = new TextField();
		tfClasses.setPrefColumnCount(20);
		pane.add(tfClasses, 1, 2);
		//
		pane.add(new Label("专业:"), 0, 3);
		TextField tfMajor = new TextField();
		tfMajor.setPrefColumnCount(20);
		pane.add(tfMajor, 1, 3);
		//
		pane.add(new Label("联系方式:"), 0, 4);
		TextField tfPhone = new TextField();
		tfPhone.setPrefColumnCount(20);
		pane.add(tfPhone, 1, 4);
		//
		Button btnAdd = new Button("确认添加");
		pane.add(btnAdd, 1, 5);
		GridPane.setHalignment(btnAdd, HPos.RIGHT);
		//
		Scene scene = new Scene(pane);
		stage.setTitle("添加学生信息");
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
		// 添加按钮点击事件
		btnAdd.setOnAction(e -> {
			// 检查输入
			if (tfSno.getText().trim().isEmpty() || tfName.getText().trim().isEmpty()
					|| tfClasses.getText().trim().isEmpty() || tfMajor.getText().trim().isEmpty()
					|| tfPhone.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "请先补全学生信息!", "提示", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (!StringUtils.isMobilePhone(tfPhone.getText().trim())) {
				JOptionPane.showMessageDialog(null, "请先输入正确的联系方式!", "提示", JOptionPane.ERROR_MESSAGE);
				return;
			}
			// 实例化学生对象
			Student student = new Student(tfSno.getText().trim(), tfName.getText().trim(), tfClasses.getText().trim(),
					tfMajor.getText().trim(), tfPhone.getText().trim());
			// 开始添加学生信息
			if (studentDao.doAddStudent(student)) {
				// 添加学生信息成功
				JOptionPane.showMessageDialog(null, "添加学生信息成功!", "提示", JOptionPane.INFORMATION_MESSAGE);
				// 刷新学生信息
				refreshStudent();
				// 关闭界面
				stage.close();
			} else {
				// 添加学生信息失败
				JOptionPane.showMessageDialog(null, "添加学生信息失败,可能是该学生信息已存在!", "提示", JOptionPane.ERROR_MESSAGE);
			}
		});
	}

	/**
	 * 修改学生学生
	 */
	private void modifyStudent() {
		// 判断当前是否选择学生信息
		if (studentTable.getSelectionModel().getSelectedItem() == null) {
			JOptionPane.showMessageDialog(null, "请先选择需要修改的学生信息!", "提示", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// 获取选择的学生信息
		Student stuinfo = studentTable.getSelectionModel().getSelectedItem();
		// 开始准备状态
		Stage stage = new Stage();
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(15, 15, 15, 15));
		pane.setHgap(5);
		pane.setVgap(5);
		// 添加相关控件
		pane.add(new Label("学号:"), 0, 0);
		TextField tfSno = new TextField();
		tfSno.setPrefColumnCount(20);
		tfSno.setEditable(false);
		tfSno.setText(stuinfo.getSno());
		pane.add(tfSno, 1, 0);
		//
		pane.add(new Label("姓名:"), 0, 1);
		TextField tfName = new TextField();
		tfName.setPrefColumnCount(20);
		tfName.setText(stuinfo.getName());
		pane.add(tfName, 1, 1);
		//
		pane.add(new Label("班级:"), 0, 2);
		TextField tfClasses = new TextField();
		tfClasses.setPrefColumnCount(20);
		tfClasses.setText(stuinfo.getClasses());
		pane.add(tfClasses, 1, 2);
		//
		pane.add(new Label("专业:"), 0, 3);
		TextField tfMajor = new TextField();
		tfMajor.setPrefColumnCount(20);
		tfMajor.setText(stuinfo.getMajor());
		pane.add(tfMajor, 1, 3);
		//
		pane.add(new Label("联系方式:"), 0, 4);
		TextField tfPhone = new TextField();
		tfPhone.setPrefColumnCount(20);
		tfPhone.setText(stuinfo.getPhone());
		pane.add(tfPhone, 1, 4);
		//
		Button btnModify = new Button("确认修改");
		pane.add(btnModify, 1, 5);
		GridPane.setHalignment(btnModify, HPos.RIGHT);
		//
		Scene scene = new Scene(pane);
		stage.setTitle("修改学生信息");
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
		// 修改按钮点击事件
		btnModify.setOnAction(e -> {
			// 检查输入
			if (tfSno.getText().trim().isEmpty() || tfName.getText().trim().isEmpty()
					|| tfClasses.getText().trim().isEmpty() || tfMajor.getText().trim().isEmpty()
					|| tfPhone.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "请先补全学生信息!", "提示", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (!StringUtils.isMobilePhone(tfPhone.getText().trim())) {
				JOptionPane.showMessageDialog(null, "请先输入正确的联系方式!", "提示", JOptionPane.ERROR_MESSAGE);
				return;
			}
			// 实例化学生对象
			Student student = new Student(tfSno.getText().trim(), tfName.getText().trim(), tfClasses.getText().trim(),
					tfMajor.getText().trim(), tfPhone.getText().trim());
			// 开始修改学生信息
			if (studentDao.doModifyStudent(student)) {
				// 修改学生信息成功
				JOptionPane.showMessageDialog(null, "修改学生信息成功!", "提示", JOptionPane.INFORMATION_MESSAGE);
				// 刷新学生信息
				refreshStudent();
				// 关闭界面
				stage.close();
			} else {
				// 修改学生信息失败
				JOptionPane.showMessageDialog(null, "修改学生信息失败,可能是该学生信息不存在!", "提示", JOptionPane.ERROR_MESSAGE);
			}
		});
	}

	/**
	 * 删除学生信息
	 */
	private void deleteStudent() {
		// 判断当前是否选择学生信息
		if (studentTable.getSelectionModel().getSelectedItem() == null) {
			JOptionPane.showMessageDialog(null, "请先选择需要删除的学生信息!", "提示", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// 获取选择的学生信息
		Student student = studentTable.getSelectionModel().getSelectedItem();
		// 询问是否确认删除
		int res = JOptionPane.showConfirmDialog(null, String.format("您确定删除学号[%s]的学生信息？", student.getSno()), "操作提示",
				JOptionPane.YES_NO_OPTION);
		if (res == JOptionPane.YES_OPTION) {
			// 开始删除学生信息
			if (studentDao.doDeleteStudent(student)) {
				// 删除学生信息成功
				JOptionPane.showMessageDialog(null, "删除学生信息成功!", "提示", JOptionPane.INFORMATION_MESSAGE);
				// 刷新学生信息
				refreshStudent();
			} else {
				// 删除学生信息失败
				JOptionPane.showMessageDialog(null, "删除学生信息失败!", "提示", JOptionPane.ERROR_MESSAGE);
			}

		}
	}

	/**
	 * 教师信息操作面板
	 * 
	 * @return
	 */
	private GridPane teacherTab() {
		GridPane pane = new GridPane();
		// 按钮
		Button btnQuery = new Button("查询");
		btnQuery.setPrefWidth(60);
		Button btnAdd = new Button("添加");
		btnAdd.setPrefWidth(60);
		Button btnModify = new Button("修改");
		btnModify.setPrefWidth(60);
		Button btnDelete = new Button("删除");
		btnDelete.setPrefWidth(60);
		Button btnRefresh = new Button("刷新");
		btnRefresh.setPrefWidth(60);
		HBox hBox = new HBox(5);
		Label lb = new Label("工号/姓名:");
		lb.setPrefWidth(70);
		hBox.getChildren().addAll(lb, tfTno, btnQuery, btnAdd, btnModify, btnDelete, btnRefresh);
		tfTno.setPrefColumnCount(15);
		VBox vBox = new VBox(10);
		vBox.getChildren().addAll(hBox);
		// 事件监听
		btnQuery.setOnAction(e -> queryTeacher());
		btnAdd.setOnAction(e -> addTeacher());
		btnModify.setOnAction(e -> modifyTeacher());
		btnDelete.setOnAction(e -> deleteTeacher());
		btnRefresh.setOnAction(e -> refreshTeacher());
		// 添加table数据列
		TableColumn tnoCol = new TableColumn("工号");
		tnoCol.setCellValueFactory(new PropertyValueFactory<Teacher, String>("tno"));
		TableColumn nameCol = new TableColumn("姓名");
		nameCol.setCellValueFactory(new PropertyValueFactory<Teacher, String>("name"));
		TableColumn departCol = new TableColumn("院系");
		departCol.setCellValueFactory(new PropertyValueFactory<Teacher, String>("depart"));
		TableColumn classesCol = new TableColumn("任课班级");
		classesCol.setCellValueFactory(new PropertyValueFactory<Teacher, String>("classes"));
		TableColumn phoneCol = new TableColumn("联系方式");
		phoneCol.setCellValueFactory(new PropertyValueFactory<Teacher, String>("phone"));
		teacherTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		teacherTable.getColumns().addAll(tnoCol, nameCol, departCol, classesCol, phoneCol);
		teacherTable.setItems(teacherObservableList);
		vBox.getChildren().addAll(teacherTable);
		vBox.setPadding(new Insets(15, 15, 15, 15));
		pane.getChildren().add(vBox);
		// 刷新教师信息
		refreshTeacher();
		return pane;
	}

	/**
	 * 刷新教师信息
	 */
	private void refreshTeacher() {
		// 存放教师信息
		List<Teacher> stuList = teacherDao.doGetTeacherList();
		// 清空表格
		teacherObservableList.clear();
		// 将教师信息添加到表格中
		teacherObservableList.addAll(stuList);
	}

	/**
	 * 查询教师信息
	 */
	private void queryTeacher() {
		// 检查输入
		if (tfTno.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "请先补全查询信息!", "提示", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// 实例化教师对象
		Teacher teacher = new Teacher(tfTno.getText().trim(), tfTno.getText().trim(), null, null, null);
		// 开始查询
		List<Teacher> list = teacherDao.doQueryTeacherByTnoOrName(teacher);
		// 清空表格
		teacherObservableList.clear();
		// 将教师信息添加到表格中
		teacherObservableList.addAll(list);
	}

	/**
	 * 添加教师信息
	 */
	private void addTeacher() {
		Stage stage = new Stage();
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(15, 15, 15, 15));
		pane.setHgap(5);
		pane.setVgap(5);
		// 添加相关控件
		pane.add(new Label("工号:"), 0, 0);
		TextField tfTno = new TextField();
		tfTno.setPrefColumnCount(20);
		pane.add(tfTno, 1, 0);
		//
		pane.add(new Label("姓名:"), 0, 1);
		TextField tfName = new TextField();
		tfName.setPrefColumnCount(20);
		pane.add(tfName, 1, 1);
		//
		pane.add(new Label("院系:"), 0, 2);
		TextField tfDepart = new TextField();
		tfDepart.setPrefColumnCount(20);
		pane.add(tfDepart, 1, 2);
		//
		pane.add(new Label("任课班级:"), 0, 3);
		TextField tfClasses = new TextField();
		tfClasses.setPrefColumnCount(20);
		pane.add(tfClasses, 1, 3);
		//
		pane.add(new Label("联系方式:"), 0, 4);
		TextField tfPhone = new TextField();
		tfPhone.setPrefColumnCount(20);
		pane.add(tfPhone, 1, 4);
		//
		Button btnAdd = new Button("确认添加");
		pane.add(btnAdd, 1, 5);
		GridPane.setHalignment(btnAdd, HPos.RIGHT);
		//
		Scene scene = new Scene(pane);
		stage.setTitle("添加教师信息");
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
		// 添加按钮点击事件
		btnAdd.setOnAction(e -> {
			// 检查输入
			if (tfTno.getText().trim().isEmpty() || tfName.getText().trim().isEmpty()
					|| tfDepart.getText().trim().isEmpty() || tfClasses.getText().trim().isEmpty()
					|| tfPhone.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "请先补全教师信息!", "提示", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (!StringUtils.isMobilePhone(tfPhone.getText().trim())) {
				JOptionPane.showMessageDialog(null, "请先输入正确的联系方式!", "提示", JOptionPane.ERROR_MESSAGE);
				return;
			}
			// 实例化教师对象
			Teacher teacher = new Teacher(tfTno.getText().trim(), tfName.getText().trim(), tfDepart.getText().trim(),
					tfClasses.getText().trim(), tfPhone.getText().trim());
			// 开始添加教师信息
			if (teacherDao.doAddTeacher(teacher)) {
				// 添加教师信息成功
				JOptionPane.showMessageDialog(null, "添加教师信息成功!", "提示", JOptionPane.INFORMATION_MESSAGE);
				// 刷新教师信息
				refreshTeacher();
				// 关闭界面
				stage.close();
			} else {
				// 添加教师信息失败
				JOptionPane.showMessageDialog(null, "添加教师信息失败,可能是该教师信息已存在!", "提示", JOptionPane.ERROR_MESSAGE);
			}
		});
	}

	/**
	 * 修改教师教师
	 */
	private void modifyTeacher() {
		// 判断当前是否选择教师信息
		if (teacherTable.getSelectionModel().getSelectedItem() == null) {
			JOptionPane.showMessageDialog(null, "请先选择需要修改的教师信息!", "提示", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// 获取选择的教师信息
		Teacher stuinfo = teacherTable.getSelectionModel().getSelectedItem();
		// 开始准备状态
		Stage stage = new Stage();
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(15, 15, 15, 15));
		pane.setHgap(5);
		pane.setVgap(5);
		// 添加相关控件
		pane.add(new Label("工号:"), 0, 0);
		TextField tfTno = new TextField();
		tfTno.setPrefColumnCount(20);
		tfTno.setEditable(false);
		tfTno.setText(stuinfo.getTno());
		pane.add(tfTno, 1, 0);
		//
		pane.add(new Label("姓名:"), 0, 1);
		TextField tfName = new TextField();
		tfName.setPrefColumnCount(20);
		tfName.setText(stuinfo.getName());
		pane.add(tfName, 1, 1);
		//
		pane.add(new Label("院系:"), 0, 2);
		TextField tfDepart = new TextField();
		tfDepart.setPrefColumnCount(20);
		tfDepart.setText(stuinfo.getDepart());
		pane.add(tfDepart, 1, 2);
		//
		pane.add(new Label("任课班级:"), 0, 3);
		TextField tfClasses = new TextField();
		tfClasses.setPrefColumnCount(20);
		tfClasses.setText(stuinfo.getClasses());
		pane.add(tfClasses, 1, 3);
		//
		pane.add(new Label("联系方式:"), 0, 4);
		TextField tfPhone = new TextField();
		tfPhone.setPrefColumnCount(20);
		tfPhone.setText(stuinfo.getPhone());
		pane.add(tfPhone, 1, 4);
		//
		Button btnModify = new Button("确认修改");
		pane.add(btnModify, 1, 5);
		GridPane.setHalignment(btnModify, HPos.RIGHT);
		//
		Scene scene = new Scene(pane);
		stage.setTitle("修改教师信息");
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
		// 修改按钮点击事件
		btnModify.setOnAction(e -> {
			// 检查输入
			if (tfTno.getText().trim().isEmpty() || tfName.getText().trim().isEmpty()
					|| tfDepart.getText().trim().isEmpty() || tfClasses.getText().trim().isEmpty()
					|| tfPhone.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "请先补全教师信息!", "提示", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (!StringUtils.isMobilePhone(tfPhone.getText().trim())) {
				JOptionPane.showMessageDialog(null, "请先输入正确的联系方式!", "提示", JOptionPane.ERROR_MESSAGE);
				return;
			}
			// 实例化教师对象
			Teacher teacher = new Teacher(tfTno.getText().trim(), tfName.getText().trim(), tfDepart.getText().trim(),
					tfClasses.getText().trim(), tfPhone.getText().trim());
			// 开始修改教师信息
			if (teacherDao.doModifyTeacher(teacher)) {
				// 修改教师信息成功
				JOptionPane.showMessageDialog(null, "修改教师信息成功!", "提示", JOptionPane.INFORMATION_MESSAGE);
				// 刷新教师信息
				refreshTeacher();
				// 关闭界面
				stage.close();
			} else {
				// 修改教师信息失败
				JOptionPane.showMessageDialog(null, "修改教师信息失败,可能是该教师信息不存在!", "提示", JOptionPane.ERROR_MESSAGE);
			}
		});
	}

	/**
	 * 删除教师信息
	 */
	private void deleteTeacher() {
		// 判断当前是否选择教师信息
		if (teacherTable.getSelectionModel().getSelectedItem() == null) {
			JOptionPane.showMessageDialog(null, "请先选择需要删除的教师信息!", "提示", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// 获取选择的教师信息
		Teacher teacher = teacherTable.getSelectionModel().getSelectedItem();
		// 询问是否确认删除
		int res = JOptionPane.showConfirmDialog(null, String.format("您确定删除工号[%s]的教师信息？", teacher.getTno()), "操作提示",
				JOptionPane.YES_NO_OPTION);
		if (res == JOptionPane.YES_OPTION) {
			// 开始删除教师信息
			if (teacherDao.doDeleteTeacher(teacher)) {
				// 删除教师信息成功
				JOptionPane.showMessageDialog(null, "删除教师信息成功!", "提示", JOptionPane.INFORMATION_MESSAGE);
				// 刷新教师信息
				refreshTeacher();
			} else {
				// 删除教师信息失败
				JOptionPane.showMessageDialog(null, "删除教师信息失败!", "提示", JOptionPane.ERROR_MESSAGE);
			}

		}
	}

	/**
	 * 成绩信息操作面板
	 * 
	 * @return
	 */
	private GridPane scoreTab() {
		GridPane pane = new GridPane();
		// 按钮
		Button btnQuery = new Button("查询");
		btnQuery.setPrefWidth(60);
		Button btnAdd = new Button("添加");
		btnAdd.setPrefWidth(60);
		Button btnModify = new Button("修改");
		btnModify.setPrefWidth(60);
		Button btnDelete = new Button("删除");
		btnDelete.setPrefWidth(60);
		Button btnRefresh = new Button("刷新");
		btnRefresh.setPrefWidth(60);
		HBox hBox = new HBox(5);
		hBox.getChildren().addAll(new Label("学号:"), tfScoSno, btnQuery, btnAdd, btnModify, btnDelete, btnRefresh);
		tfScoSno.setPrefColumnCount(15);
		VBox vBox = new VBox(10);
		vBox.getChildren().addAll(hBox);
		// 事件监听
		btnQuery.setOnAction(e -> queryScore());
		btnAdd.setOnAction(e -> addScore());
		btnModify.setOnAction(e -> modifyScore());
		btnDelete.setOnAction(e -> deleteScore());
		btnRefresh.setOnAction(e -> refreshScore());
		// 添加table数据列
		TableColumn snoCol = new TableColumn("学号");
		snoCol.setCellValueFactory(new PropertyValueFactory<Score, String>("sno"));
		TableColumn score1Col = new TableColumn("语文成绩");
		score1Col.setCellValueFactory(new PropertyValueFactory<Score, String>("score1"));
		TableColumn score2Col = new TableColumn("数学成绩");
		score2Col.setCellValueFactory(new PropertyValueFactory<Score, String>("score2"));
		TableColumn score3Col = new TableColumn("英语成绩");
		score3Col.setCellValueFactory(new PropertyValueFactory<Score, String>("score3"));
		TableColumn score4Col = new TableColumn("体育成绩");
		score4Col.setCellValueFactory(new PropertyValueFactory<Score, String>("score4"));
		TableColumn score5Col = new TableColumn("总成绩");
		score5Col.setCellValueFactory(new PropertyValueFactory<Score, String>("tscore"));
		
		scoreTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		scoreTable.getColumns().addAll(snoCol, score1Col, score2Col, score3Col, score4Col, score5Col);
		scoreTable.setItems(scoreObservableList);
		vBox.getChildren().addAll(scoreTable);
		vBox.setPadding(new Insets(15, 15, 15, 15));
		pane.getChildren().add(vBox);
		// 刷新成绩信息
		refreshScore();
		return pane;
	}

	/**
	 * 刷新成绩信息
	 */
	private void refreshScore() {
		// 存放成绩信息
		List<Score> stuList = scoreDao.doGetScoreList();
		// 清空表格
		scoreObservableList.clear();
		// 将成绩信息添加到表格中
		scoreObservableList.addAll(stuList);
	}

	/**
	 * 查询成绩信息
	 */
	private void queryScore() {
		// 检查输入
		if (tfScoSno.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "请先补全查询信息!", "提示", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// 实例化成绩对象
		Score score = new Score(tfScoSno.getText().trim(), -1, -1, -1, -1);
		// 开始查询
		Score scoinfo = scoreDao.doQueryScoreBySno(score);
		// 清空表格
		scoreObservableList.clear();
		// 将成绩信息添加到表格中
		scoreObservableList.add(scoinfo);
	}

	/**
	 * 添加成绩信息
	 */
	private void addScore() {
		Stage stage = new Stage();
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(15, 15, 15, 15));
		pane.setHgap(5);
		pane.setVgap(5);
		// 添加相关控件
		pane.add(new Label("学号:"), 0, 0);
		TextField tfScoSno = new TextField();
		tfScoSno.setPrefColumnCount(20);
		pane.add(tfScoSno, 1, 0);
		//
		pane.add(new Label("语文成绩:"), 0, 1);
		TextField tfScore1 = new TextField();
		tfScore1.setPrefColumnCount(20);
		pane.add(tfScore1, 1, 1);
		//
		pane.add(new Label("数学成绩:"), 0, 2);
		TextField tfScore2 = new TextField();
		tfScore2.setPrefColumnCount(20);
		pane.add(tfScore2, 1, 2);
		//
		pane.add(new Label("英语成绩:"), 0, 3);
		TextField tfScore3 = new TextField();
		tfScore3.setPrefColumnCount(20);
		pane.add(tfScore3, 1, 3);
		//
		pane.add(new Label("体育成绩:"), 0, 4);
		TextField tfScore4 = new TextField();
		tfScore4.setPrefColumnCount(20);
		pane.add(tfScore4, 1, 4);
		
		//
		Button btnAdd = new Button("确认添加");
		pane.add(btnAdd, 1, 5);
		GridPane.setHalignment(btnAdd, HPos.RIGHT);
		//
		Scene scene = new Scene(pane);
		stage.setTitle("添加成绩信息");
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
		// 添加按钮点击事件
		btnAdd.setOnAction(e -> {
			// 检查输入
			if (tfScoSno.getText().trim().isEmpty() || tfScore1.getText().trim().isEmpty()
					|| tfScore2.getText().trim().isEmpty() || tfScore3.getText().trim().isEmpty()
					|| tfScore4.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "请先补全成绩信息!", "提示", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (!StringUtils.validateNumber(tfScore1.getText().trim())
					|| !StringUtils.isScore(tfScore1.getText().trim())) {
				JOptionPane.showMessageDialog(null, "请先输入正确的语文成绩!", "提示", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (!StringUtils.validateNumber(tfScore2.getText().trim())
					|| !StringUtils.isScore(tfScore2.getText().trim())) {
				JOptionPane.showMessageDialog(null, "请先输入正确的数学成绩!", "提示", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (!StringUtils.validateNumber(tfScore3.getText().trim())
					|| !StringUtils.isScore(tfScore3.getText().trim())) {
				JOptionPane.showMessageDialog(null, "请先输入正确的英语成绩!", "提示", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (!StringUtils.validateNumber(tfScore4.getText().trim())
					|| !StringUtils.isScore(tfScore4.getText().trim())) {
				JOptionPane.showMessageDialog(null, "请先输入正确的体育成绩!", "提示", JOptionPane.ERROR_MESSAGE);
				return;
			}
			// 实例化成绩对象
			Score score = new Score(tfScoSno.getText().trim(), Float.parseFloat(tfScore1.getText().trim()),
					Float.parseFloat(tfScore2.getText().trim()), Float.parseFloat(tfScore3.getText().trim()),
					Float.parseFloat(tfScore4.getText().trim()));
			// 获取学生信息
			Student student = studentDao.doQueryStudentBySno(new Student(score.getSno(), null, null, null, null));
			// 判断学生信息是否正确
			if (student == null) {
				// 学生信息不正确
				JOptionPane.showMessageDialog(null, "请先输入正确的学生学号!", "提示", JOptionPane.ERROR_MESSAGE);
				return;
			}
			// 开始添加成绩信息
			if (scoreDao.doAddScore(score)) {
				// 添加成绩信息成功
				JOptionPane.showMessageDialog(null, "添加成绩信息成功!", "提示", JOptionPane.INFORMATION_MESSAGE);
				// 刷新成绩信息
				refreshScore();
				// 关闭界面
				stage.close();
			} else {
				// 添加成绩信息失败
				JOptionPane.showMessageDialog(null, "添加成绩信息失败,可能是该成绩信息已存在!", "提示", JOptionPane.ERROR_MESSAGE);
			}
		});
	}

	/**
	 * 修改成绩成绩
	 */
	private void modifyScore() {
		// 判断当前是否选择成绩信息
		if (scoreTable.getSelectionModel().getSelectedItem() == null) {
			JOptionPane.showMessageDialog(null, "请先选择需要修改的成绩信息!", "提示", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// 获取选择的成绩信息
		Score stuinfo = scoreTable.getSelectionModel().getSelectedItem();
		// 开始准备状态
		Stage stage = new Stage();
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(15, 15, 15, 15));
		pane.setHgap(5);
		pane.setVgap(5);
		// 添加相关控件
		pane.add(new Label("学号:"), 0, 0);
		TextField tfScoSno = new TextField();
		tfScoSno.setPrefColumnCount(20);
		tfScoSno.setEditable(false);
		tfScoSno.setText(stuinfo.getSno());
		pane.add(tfScoSno, 1, 0);
		//
		pane.add(new Label("语文成绩:"), 0, 1);
		TextField tfScore1 = new TextField();
		tfScore1.setPrefColumnCount(20);
		tfScore1.setText(Float.toString(stuinfo.getScore1()));
		pane.add(tfScore1, 1, 1);
		//
		pane.add(new Label("数学成绩:"), 0, 2);
		TextField tfScore2 = new TextField();
		tfScore2.setPrefColumnCount(20);
		tfScore2.setText(Float.toString(stuinfo.getScore2()));
		pane.add(tfScore2, 1, 2);
		//
		pane.add(new Label("英语成绩:"), 0, 3);
		TextField tfScore3 = new TextField();
		tfScore3.setPrefColumnCount(20);
		tfScore3.setText(Float.toString(stuinfo.getScore3()));
		pane.add(tfScore3, 1, 3);
		//
		pane.add(new Label("体育成绩:"), 0, 4);
		TextField tfScore4 = new TextField();
		tfScore4.setPrefColumnCount(20);
		tfScore4.setText(Float.toString(stuinfo.getScore4()));
		pane.add(tfScore4, 1, 4);
		//
		Button btnModify = new Button("确认修改");
		pane.add(btnModify, 1, 5);
		GridPane.setHalignment(btnModify, HPos.RIGHT);
		//
		Scene scene = new Scene(pane);
		stage.setTitle("修改成绩信息");
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
		// 修改按钮点击事件
		btnModify.setOnAction(e -> {
			// 检查输入
			if (tfScoSno.getText().trim().isEmpty() || tfScore1.getText().trim().isEmpty()
					|| tfScore2.getText().trim().isEmpty() || tfScore3.getText().trim().isEmpty()
					|| tfScore4.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "请先补全成绩信息!", "提示", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (!StringUtils.validateNumber(tfScore1.getText().trim())
					|| !StringUtils.isScore(tfScore1.getText().trim())) {
				JOptionPane.showMessageDialog(null, "请先输入正确的语文成绩!", "提示", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (!StringUtils.validateNumber(tfScore2.getText().trim())
					|| !StringUtils.isScore(tfScore2.getText().trim())) {
				JOptionPane.showMessageDialog(null, "请先输入正确的数学成绩!", "提示", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (!StringUtils.validateNumber(tfScore3.getText().trim())
					|| !StringUtils.isScore(tfScore3.getText().trim())) {
				JOptionPane.showMessageDialog(null, "请先输入正确的英语成绩!", "提示", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (!StringUtils.validateNumber(tfScore4.getText().trim())
					|| !StringUtils.isScore(tfScore4.getText().trim())) {
				JOptionPane.showMessageDialog(null, "请先输入正确的体育成绩!", "提示", JOptionPane.ERROR_MESSAGE);
				return;
			}
			// 实例化成绩对象
			Score score = new Score(tfScoSno.getText().trim(), Float.parseFloat(tfScore1.getText().trim()),
					Float.parseFloat(tfScore2.getText().trim()), Float.parseFloat(tfScore3.getText().trim()),
					Float.parseFloat(tfScore4.getText().trim()));
			// 开始修改成绩信息
			if (scoreDao.doModifyScore(score)) {
				// 修改成绩信息成功
				JOptionPane.showMessageDialog(null, "修改成绩信息成功!", "提示", JOptionPane.INFORMATION_MESSAGE);
				// 刷新成绩信息
				refreshScore();
				// 关闭界面
				stage.close();
			} else {
				// 修改成绩信息失败
				JOptionPane.showMessageDialog(null, "修改成绩信息失败,可能是该成绩信息不存在!", "提示", JOptionPane.ERROR_MESSAGE);
			}
		});
	}

	/**
	 * 删除成绩信息
	 */
	private void deleteScore() {
		// 判断当前是否选择成绩信息
		if (scoreTable.getSelectionModel().getSelectedItem() == null) {
			JOptionPane.showMessageDialog(null, "请先选择需要删除的成绩信息!", "提示", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// 获取选择的成绩信息
		Score score = scoreTable.getSelectionModel().getSelectedItem();
		// 询问是否确认删除
		int res = JOptionPane.showConfirmDialog(null, String.format("您确定删除学号[%s]的成绩信息？", score.getSno()), "操作提示",
				JOptionPane.YES_NO_OPTION);
		if (res == JOptionPane.YES_OPTION) {
			// 开始删除成绩信息
			if (scoreDao.doDeleteScore(score)) {
				// 删除成绩信息成功
				JOptionPane.showMessageDialog(null, "删除成绩信息成功!", "提示", JOptionPane.INFORMATION_MESSAGE);
				// 刷新成绩信息
				refreshScore();
			} else {
				// 删除成绩信息失败
				JOptionPane.showMessageDialog(null, "删除成绩信息失败!", "提示", JOptionPane.ERROR_MESSAGE);
			}

		}
	}

	/**
	 * 成绩排序操作面板
	 * 
	 * @return
	 */
	private GridPane scoreSortTab() {
		GridPane pane = new GridPane();
		// 排序对象下拉框
		cbSortObj = new ChoiceBox<String>(FXCollections.observableArrayList("按[学号]排序", "按[语文成绩]排序", "按[数学成绩]排序",
				"按[英语成绩]排序", "按[体育成绩]排序", "按[总成绩]排序"));
		cbSortObj.setPrefWidth(500);
		// 排序方式下拉框
		cbSortType = new ChoiceBox<String>(FXCollections.observableArrayList("进行[升序]排序", "进行[降序]排序"));
		cbSortType.setPrefWidth(500);
		// 布局下拉框
		HBox tBox = new HBox(2);
		tBox.getChildren().addAll(new Label("排序对象:"), cbSortObj);
		HBox hBox = new HBox(2);
		hBox.getChildren().addAll(new Label("排序方式:"), cbSortType);
		VBox vBox = new VBox(10);
		vBox.getChildren().addAll(tBox, hBox);
		// 事件监听
		cbSortObj.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				// 排序成绩信息
				scoreSort();
			}
		});
		cbSortType.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				// 排序成绩信息
				scoreSort();
			}
		});
		// 添加table数据列
		TableColumn snoCol = new TableColumn("学号");
		snoCol.setCellValueFactory(new PropertyValueFactory<Score, String>("sno"));
		TableColumn score1Col = new TableColumn("语文成绩");
		score1Col.setCellValueFactory(new PropertyValueFactory<Score, String>("score1"));
		TableColumn score2Col = new TableColumn("数学成绩");
		score2Col.setCellValueFactory(new PropertyValueFactory<Score, String>("score2"));
		TableColumn score3Col = new TableColumn("英语成绩");
		score3Col.setCellValueFactory(new PropertyValueFactory<Score, String>("score3"));
		TableColumn score4Col = new TableColumn("体育成绩");
		score4Col.setCellValueFactory(new PropertyValueFactory<Score, String>("score4"));
		TableColumn score5Col = new TableColumn("总成绩");
		score5Col.setCellValueFactory(new PropertyValueFactory<Score, String>("tscore"));
		scoreSortTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		scoreSortTable.getColumns().addAll(snoCol, score1Col, score2Col, score3Col, score4Col, score5Col);
		scoreSortTable.setItems(scoreSortObservableList);
		vBox.getChildren().addAll(scoreSortTable);
		vBox.setPadding(new Insets(15, 15, 15, 15));
		pane.getChildren().add(vBox);
		return pane;
	}

	/**
	 * 排序成绩信息
	 */
	private void scoreSort() {
		// 获取选择的排序对象
		int objIndex = cbSortObj.getSelectionModel().getSelectedIndex();
		// 获取选择的排序类型
		int typeIndex = cbSortType.getSelectionModel().getSelectedIndex();
		// 判断是否可以进行排序
		if (objIndex != -1 && typeIndex != -1) {
			// 开始排序
			// 获取所有成绩信息
			List<Score> list = scoreDao.doGetScoreList();
			// 使用冒泡排序法进行排序
			for (int i = 0; i < list.size() - 1; i++) {
				for (int j = 0; j < list.size() - 1 - i; j++) {
					// 判断是否达到交换条件
					if ((objIndex == 0 && typeIndex == 0
							&& list.get(j).getSno().compareTo(list.get(j + 1).getSno()) > 0)
							|| (objIndex == 0 && typeIndex == 1
									&& list.get(j).getSno().compareTo(list.get(j + 1).getSno()) < 0)
							|| (objIndex == 1 && typeIndex == 0
									&& list.get(j).getScore1() > list.get(j + 1).getScore1())
							|| (objIndex == 1 && typeIndex == 1
									&& list.get(j).getScore1() < list.get(j + 1).getScore1())
							|| (objIndex == 2 && typeIndex == 0
									&& list.get(j).getScore2() > list.get(j + 1).getScore2())
							|| (objIndex == 2 && typeIndex == 1
									&& list.get(j).getScore2() < list.get(j + 1).getScore2())
							|| (objIndex == 3 && typeIndex == 0
									&& list.get(j).getScore3() > list.get(j + 1).getScore3())
							|| (objIndex == 3 && typeIndex == 1
									&& list.get(j).getScore3() < list.get(j + 1).getScore3())
							|| (objIndex == 4 && typeIndex == 0
									&& list.get(j).getScore4() > list.get(j + 1).getScore4())
							|| (objIndex == 4 && typeIndex == 1
									&& list.get(j).getScore4() < list.get(j + 1).getScore4())
							|| (objIndex == 5 && typeIndex == 0
									&& list.get(j).getTscore() > list.get(j + 1).getTscore())
							|| (objIndex == 5 && typeIndex == 1
									&& list.get(j).getTscore() < list.get(j + 1).getTscore())) {
						// 交换两个成绩信息
						Score stmp = list.get(j);
						list.set(j, list.get(j + 1));
						list.set(j + 1, stmp);
					}
				}
			}
			// 排序完成
			// 清空表格
			scoreSortObservableList.clear();
			// 将成绩信息添加到表格中
			scoreSortObservableList.addAll(list);
		}
	}

	/**
	 * 成绩分析操作面板
	 * 
	 * @return
	 */
	private GridPane scoreAnalysisTab() {
		GridPane pane = new GridPane();
		// 查询课程下拉框
		cbScoreType = new ChoiceBox<String>(FXCollections.observableArrayList("语文课程", "数学课程", "英语课程", "体育课程"));
		cbScoreType.setPrefWidth(500);
		// 布局下拉框
		HBox tBox = new HBox(2);
		tBox.getChildren().addAll(new Label("查询课程:"), cbScoreType);
		VBox vBox = new VBox(10);
		vBox.getChildren().addAll(tBox);
		// 事件监听
		cbScoreType.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				// 获取选择的课程
				int indexType = cbScoreType.getSelectionModel().getSelectedIndex();
				// 判断是否有选择课程
				if (indexType == -1) {
					return;
				}
				// 查询当前所有成绩信息
				List<Score> list = scoreDao.doGetScoreList();
				// 判断成绩信息数量
				if (list.isEmpty()) {
					return;
				}
				// 开始分析最高分与最低分的成绩信息
				Score max = list.get(0);
				Score min = list.get(0);
				for (int i = 1; i < list.size(); i++) {
					// 判断最高分
					if ((indexType == 0 && max.getScore1() < list.get(i).getScore1())
							|| (indexType == 1 && max.getScore2() < list.get(i).getScore2())
							|| (indexType == 2 && max.getScore3() < list.get(i).getScore3())
							|| (indexType == 3 && max.getScore4() < list.get(i).getScore4())) {
						max = list.get(i);
					}
					// 判断最低分
					if ((indexType == 0 && min.getScore1() > list.get(i).getScore1())
							|| (indexType == 1 && min.getScore2() > list.get(i).getScore2())
							|| (indexType == 2 && min.getScore3() > list.get(i).getScore3())
							|| (indexType == 3 && min.getScore4() > list.get(i).getScore4())) {
						min = list.get(i);
					}
				}
				// 找出最高分最低分的学生信息
				Student stu_max = studentDao.doQueryStudentBySno(new Student(max.getSno(), null, null, null, null));
				Student stu_min = studentDao.doQueryStudentBySno(new Student(min.getSno(), null, null, null, null));
				// 设置最高分与最低分信息
				if (indexType == 0) {
					stu_max.setMajor(Float.toString(max.getScore1()));
					stu_min.setMajor(Float.toString(min.getScore1()));
				}
				if (indexType == 1) {
					stu_max.setMajor(Float.toString(max.getScore2()));
					stu_min.setMajor(Float.toString(min.getScore2()));
				}
				if (indexType == 2) {
					stu_max.setMajor(Float.toString(max.getScore3()));
					stu_min.setMajor(Float.toString(min.getScore3()));
				}
				if (indexType == 3) {
					stu_max.setMajor(Float.toString(max.getScore4()));
					stu_min.setMajor(Float.toString(min.getScore4()));
				}
				stu_max.setPhone("最高分");
				stu_min.setPhone("最低分");
				// 将最高分与最低分的学生信息显示到表格中
				scoreAnalysisObservableList.clear();
				// 将学生信息添加到表格中
				scoreAnalysisObservableList.addAll(stu_max, stu_min);
			}
		});
		// 添加table数据列
		TableColumn snoCol = new TableColumn("学号");
		snoCol.setCellValueFactory(new PropertyValueFactory<Student, String>("sno"));
		TableColumn nameCol = new TableColumn("姓名");
		nameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
		TableColumn classesCol = new TableColumn("班级");
		classesCol.setCellValueFactory(new PropertyValueFactory<Student, String>("classes"));
		TableColumn majorCol = new TableColumn("成绩");
		majorCol.setCellValueFactory(new PropertyValueFactory<Student, String>("major"));
		TableColumn phoneCol = new TableColumn("备注");
		phoneCol.setCellValueFactory(new PropertyValueFactory<Student, String>("phone"));
		scoreAnalysisTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		scoreAnalysisTable.getColumns().addAll(snoCol, nameCol, classesCol, majorCol, phoneCol);
		scoreAnalysisTable.setItems(scoreAnalysisObservableList);
		vBox.getChildren().addAll(scoreAnalysisTable);
		vBox.setPadding(new Insets(15, 15, 15, 15));
		pane.getChildren().add(vBox);
		return pane;
	}

	/**
	 * 成绩统计操作面板
	 * 
	 * @return
	 */
	private GridPane scoreStatisTab() {
		GridPane pane = new GridPane();
		// 统计对象下拉框
		cbStatisScoreType=new ChoiceBox<String>(
				FXCollections.observableArrayList("语文课程", "数学课程", "英语课程", "体育课程"));
		cbStatisScoreType.setPrefWidth(500);
		// 排序对象下拉框
		cbStatisObj = new ChoiceBox<String>(
				FXCollections.observableArrayList("按[优秀率]排序", "按[较好率]排序", "按[良好率]排序", "按[合格率]排序", "按[不合格率]排序"));
		cbStatisObj.setPrefWidth(500);
		// 排序方式下拉框
		cbStatisType = new ChoiceBox<String>(FXCollections.observableArrayList("进行[升序]排序", "进行[降序]排序"));
		cbStatisType.setPrefWidth(500);
		// 布局下拉框
		HBox fBox = new HBox(2);
		fBox.getChildren().addAll(new Label("统计对象:"), cbStatisScoreType);
		HBox tBox = new HBox(2);
		tBox.getChildren().addAll(new Label("排序对象:"), cbStatisObj);
		HBox hBox = new HBox(2);
		hBox.getChildren().addAll(new Label("排序方式:"), cbStatisType);
		VBox vBox = new VBox(10);
		vBox.getChildren().addAll(fBox,tBox, hBox);
		// 事件监听
		cbStatisScoreType.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				// 排序统计信息
				statisSort();
			}
		});
		cbStatisObj.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				// 排序统计信息
				statisSort();
			}
		});
		cbStatisType.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				// 排序统计信息
				statisSort();
			}
		});
		// 添加table数据列
		TableColumn snoCol = new TableColumn("班级");
		snoCol.setCellValueFactory(new PropertyValueFactory<Score, String>("sno"));
		TableColumn score1Col = new TableColumn("优秀率");
		score1Col.setCellValueFactory(new PropertyValueFactory<Score, String>("score1"));
		TableColumn score2Col = new TableColumn("较好率");
		score2Col.setCellValueFactory(new PropertyValueFactory<Score, String>("score2"));
		TableColumn score3Col = new TableColumn("良好率");
		score3Col.setCellValueFactory(new PropertyValueFactory<Score, String>("score3"));
		TableColumn score4Col = new TableColumn("合格率");
		score4Col.setCellValueFactory(new PropertyValueFactory<Score, String>("score4"));
		TableColumn score5Col = new TableColumn("不合格率");
		score5Col.setCellValueFactory(new PropertyValueFactory<Score, String>("tscore"));
		scoreStatisTable.getColumns().addAll(snoCol, score1Col, score2Col, score3Col, score4Col, score5Col);
		scoreStatisTable.setItems(scoreStatisObservableList);
		vBox.getChildren().addAll(scoreStatisTable);
		vBox.setPadding(new Insets(15, 15, 15, 15));
		pane.getChildren().add(vBox);
		return pane;
	}

	/**
	 * 排序统计信息
	 */
	private void statisSort() {
		// 获取选择的统计对象
		int stypeIndex = cbStatisScoreType.getSelectionModel().getSelectedIndex();
		// 获取选择的排序对象
		int objIndex = cbStatisObj.getSelectionModel().getSelectedIndex();
		// 获取选择的排序类型
		int typeIndex = cbStatisType.getSelectionModel().getSelectedIndex();
		// 判断是否可以进行排序
		if (stypeIndex!=-1 && objIndex != -1 && typeIndex != -1) {
			// 开始排序
			List<Score> slist=new ArrayList<Score>();
			// 获取所有成绩信息
			List<Score> list = scoreDao.doGetScoreList();
			// 统计所有班级
			Set st_classes = new HashSet();
			for(int i=0;i<list.size();i++) {
				//查询指定学号的学生信息
				Student student=studentDao.doQueryStudentBySno(new Student(list.get(i).getSno(),null,null,null,null));
				st_classes.add(student.getClasses());
			}
			// 按照班级统计优秀率、较好率、良好率、合格率、不合格率
			Iterator it = st_classes.iterator();
	        while (it.hasNext()) {
	        	String classes=it.next().toString();
	        	Score score=new Score(classes,0.0f,0.0f,0.0f,0.0f);
	        	int ccount=0;
	        	//统计当前班级的指定课程的各个分数段人数
	        	for(int i=0;i<list.size();i++) {
	        		//查询指定学号的学生信息
					Student student=studentDao.doQueryStudentBySno(new Student(list.get(i).getSno(),null,null,null,null));
					if(student.getClasses().equals(classes)) {
						//累加班级人数
						ccount++;
						//统计分数段人数
						if((stypeIndex==0 && list.get(i).getScore1()>=90) || (stypeIndex==1 && list.get(i).getScore2()>=90) || (stypeIndex==2 && list.get(i).getScore3()>=90) || (stypeIndex==3 && list.get(i).getScore4()>=90)) {
							score.setScore1(score.getScore1()+1);
						}
						if((stypeIndex==0 && list.get(i).getScore1()>=80 && list.get(i).getScore1()<90) || (stypeIndex==1 && list.get(i).getScore2()>=80 && list.get(i).getScore2()<90) || (stypeIndex==2 && list.get(i).getScore3()>=80 && list.get(i).getScore3()<90) || (stypeIndex==3 && list.get(i).getScore4()>=80 && list.get(i).getScore4()<90)) {
							score.setScore2(score.getScore2()+1);
						}
						if((stypeIndex==0 && list.get(i).getScore1()>=70 && list.get(i).getScore1()<80) || (stypeIndex==1 && list.get(i).getScore2()>=70 && list.get(i).getScore2()<80) || (stypeIndex==2 && list.get(i).getScore3()>=70 && list.get(i).getScore3()<80) || (stypeIndex==3 && list.get(i).getScore4()>=70 && list.get(i).getScore4()<80)) {
							score.setScore3(score.getScore3()+1);
						}
						if((stypeIndex==0 && list.get(i).getScore1()>=60 && list.get(i).getScore1()<70) || (stypeIndex==1 && list.get(i).getScore2()>=60 && list.get(i).getScore2()<70) || (stypeIndex==2 && list.get(i).getScore3()>=60 && list.get(i).getScore3()<70) || (stypeIndex==3 && list.get(i).getScore4()>=60 && list.get(i).getScore4()<70)) {
							score.setScore4(score.getScore4()+1);
						}
						if((stypeIndex==0 && list.get(i).getScore1()<60) || (stypeIndex==1 && list.get(i).getScore2()<60) || (stypeIndex==2 && list.get(i).getScore3()<60) || (stypeIndex==3 && list.get(i).getScore4()<60)) {
							score.setTscore(score.getTscore()+1);
						}
					}
				}
	        	//计算优秀率、较好率、良好率、合格率、不合格率
	        	score.setScore1(score.getScore1()/ccount);
	        	score.setScore2(score.getScore2()/ccount);
	        	score.setScore3(score.getScore3()/ccount);
	        	score.setScore4(score.getScore4()/ccount);
	        	score.setTscore(score.getTscore()/ccount);
	        	slist.add(score);
	        }
			// 使用冒泡排序法进行排序
			for (int i = 0; i < slist.size() - 1; i++) {
				for (int j = 0; j < slist.size() - 1 - i; j++) {
					// 判断是否达到交换条件
					if ((objIndex == 0 && typeIndex == 0
									&& slist.get(j).getScore1() > slist.get(j + 1).getScore1())
							|| (objIndex == 0 && typeIndex == 1
									&& slist.get(j).getScore1() < slist.get(j + 1).getScore1())
							|| (objIndex == 1 && typeIndex == 0
									&& slist.get(j).getScore2() > slist.get(j + 1).getScore2())
							|| (objIndex == 1 && typeIndex == 1
									&& slist.get(j).getScore2() < slist.get(j + 1).getScore2())
							|| (objIndex == 2 && typeIndex == 0
									&& slist.get(j).getScore3() > slist.get(j + 1).getScore3())
							|| (objIndex == 2 && typeIndex == 1
									&& slist.get(j).getScore3() < slist.get(j + 1).getScore3())
							|| (objIndex == 3 && typeIndex == 0
									&& slist.get(j).getScore4() > slist.get(j + 1).getScore4())
							|| (objIndex == 3 && typeIndex == 1
									&& slist.get(j).getScore4() < slist.get(j + 1).getScore4())
							|| (objIndex == 4 && typeIndex == 0
									&& slist.get(j).getTscore() > slist.get(j + 1).getTscore())
							|| (objIndex == 4 && typeIndex == 1
									&& slist.get(j).getTscore() < slist.get(j + 1).getTscore())) {
						// 交换两个成绩信息
						Score stmp = slist.get(j);
						slist.set(j, slist.get(j + 1));
						slist.set(j + 1, stmp);
					}
				}
			}
			// 排序完成
			// 清空表格
			scoreStatisObservableList.clear();
			// 将成绩信息添加到表格中
			scoreStatisObservableList.addAll(slist);
		}
	}
}
