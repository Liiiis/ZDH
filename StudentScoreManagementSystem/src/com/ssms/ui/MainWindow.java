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

	// ѧ����Ϣ�������
	private TextField tfSnoName = new TextField();
	private final TableView<Student> studentTable = new TableView<Student>();
	private ObservableList<Student> studentObservableList = FXCollections.observableArrayList();

	// ��ʦ��Ϣ�������
	private TextField tfTno = new TextField();
	private final TableView<Teacher> teacherTable = new TableView<Teacher>();
	private ObservableList<Teacher> teacherObservableList = FXCollections.observableArrayList();

	// �ɼ���Ϣ�������
	private TextField tfScoSno = new TextField();
	private final TableView<Score> scoreTable = new TableView<Score>();
	private ObservableList<Score> scoreObservableList = FXCollections.observableArrayList();

	// �ɼ�����������
	private ChoiceBox cbSortObj;
	private ChoiceBox cbSortType;
	private final TableView<Score> scoreSortTable = new TableView<Score>();
	private ObservableList<Score> scoreSortObservableList = FXCollections.observableArrayList();

	// �ɼ������������
	private ChoiceBox cbScoreType;
	private final TableView<Student> scoreAnalysisTable = new TableView<Student>();
	private ObservableList<Student> scoreAnalysisObservableList = FXCollections.observableArrayList();

	// �ɼ�ͳ�Ʋ������
	private ChoiceBox cbStatisScoreType;
	private ChoiceBox cbStatisObj;
	private ChoiceBox cbStatisType;
	private final TableView<Score> scoreStatisTable = new TableView<Score>();
	private ObservableList<Score> scoreStatisObservableList = FXCollections.observableArrayList();

	@Override
	public void start(Stage primaryStage) {
		// ���ѡ����
		TabPane tabPane = new TabPane();
		// ѧ����Ϣ����
		Tab tabStudent = new Tab("ѧ����Ϣ����");
		tabStudent.setClosable(false);
		tabStudent.setContent(this.studentTab());
		// ��ʦ��Ϣ����
		Tab tabTeacher = new Tab("��ʦ��Ϣ����");
		tabTeacher.setClosable(false);
		tabTeacher.setContent(this.teacherTab());
		// �ɼ���Ϣ����
		Tab tabScore = new Tab("�ɼ���Ϣ����");
		tabScore.setClosable(false);
		tabScore.setContent(this.scoreTab());
		// �ɼ��������
		Tab tabScoreSort = new Tab("�ɼ��������");
		tabScoreSort.setClosable(false);
		tabScoreSort.setContent(this.scoreSortTab());
		// �ɼ���������
		Tab tabScoreAnalysis = new Tab("�ɼ���������");
		tabScoreAnalysis.setClosable(false);
		tabScoreAnalysis.setContent(this.scoreAnalysisTab());
		// �ɼ���������
		Tab tabScoreStatis = new Tab("�ɼ���������");
		tabScoreStatis.setClosable(false);
		tabScoreStatis.setContent(this.scoreStatisTab());
		// ��ѡ�����ѡ������
		tabPane.getTabs().add(tabStudent);
		tabPane.getTabs().add(tabTeacher);
		tabPane.getTabs().add(tabScore);
		tabPane.getTabs().add(tabScoreSort);
		tabPane.getTabs().add(tabScoreAnalysis);
		tabPane.getTabs().add(tabScoreStatis);
		// ��Ӳ���
		VBox vBox = new VBox(tabPane);
		Scene scene = new Scene(vBox, 700, 600);
		primaryStage.setScene(scene);
		// ���ô������
		primaryStage.setTitle("ѧ���ɼ�����ϵͳ");
		// ���ý�ֹ�ı䴰���С
		primaryStage.setResizable(false);
		// ��Ļ������ʾ
		primaryStage.centerOnScreen();
		primaryStage.show();
	}

	/**
	 * ѧ����Ϣ�������
	 * 
	 * @return
	 */
	private GridPane studentTab() {
		GridPane pane = new GridPane();
		// ��ť
		Button btnQuery = new Button("��ѯ");
		btnQuery.setPrefWidth(60);
		Button btnAdd = new Button("���");
		btnAdd.setPrefWidth(60);
		Button btnModify = new Button("�޸�");
		btnModify.setPrefWidth(60);
		Button btnDelete = new Button("ɾ��");
		btnDelete.setPrefWidth(60);
		Button btnRefresh = new Button("ˢ��");
		btnRefresh.setPrefWidth(60);
		HBox hBox = new HBox(5);
		Label lb = new Label("ѧ��/����:");
		lb.setPrefWidth(70);
		hBox.getChildren().addAll(lb, tfSnoName, btnQuery, btnAdd, btnModify, btnDelete, btnRefresh);
		tfSnoName.setPrefColumnCount(15);
		VBox vBox = new VBox(10);
		vBox.getChildren().addAll(hBox);
		// �¼�����
		btnQuery.setOnAction(e -> queryStudent());
		btnAdd.setOnAction(e -> addStudent());
		btnModify.setOnAction(e -> modifyStudent());
		btnDelete.setOnAction(e -> deleteStudent());
		btnRefresh.setOnAction(e -> refreshStudent());
		// ���table������
		TableColumn snoCol = new TableColumn("ѧ��");
		snoCol.setCellValueFactory(new PropertyValueFactory<Student, String>("sno"));
		TableColumn nameCol = new TableColumn("����");
		nameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
		TableColumn classesCol = new TableColumn("�༶");
		classesCol.setCellValueFactory(new PropertyValueFactory<Student, String>("classes"));
		TableColumn majorCol = new TableColumn("רҵ");
		majorCol.setCellValueFactory(new PropertyValueFactory<Student, String>("major"));
		TableColumn phoneCol = new TableColumn("��ϵ��ʽ");
		phoneCol.setCellValueFactory(new PropertyValueFactory<Student, String>("phone"));
		studentTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		studentTable.getColumns().addAll(snoCol, nameCol, classesCol, majorCol, phoneCol);
		studentTable.setItems(studentObservableList);
		vBox.getChildren().addAll(studentTable);
		vBox.setPadding(new Insets(15, 15, 15, 15));
		pane.getChildren().add(vBox);
		// ˢ��ѧ����Ϣ
		refreshStudent();
		return pane;
	}

	/**
	 * ˢ��ѧ����Ϣ
	 */
	private void refreshStudent() {
		// ���ѧ����Ϣ
		List<Student> stuList = studentDao.doGetStudentList();
		// ��ձ��
		studentObservableList.clear();
		// ��ѧ����Ϣ��ӵ������
		studentObservableList.addAll(stuList);
	}

	/**
	 * ��ѯѧ����Ϣ
	 */
	private void queryStudent() {
		// �������
		if (tfSnoName.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "���Ȳ�ȫ��ѯ��Ϣ!", "��ʾ", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// ʵ����ѧ������
		Student student = new Student(tfSnoName.getText().trim(), tfSnoName.getText().trim(), null, null, null);
		// ��ʼ��ѯ
		List<Student> list = studentDao.doQueryStudentBySnoOrName(student);
		// ��ձ��
		studentObservableList.clear();
		// ��ѧ����Ϣ��ӵ������
		studentObservableList.addAll(list);
	}

	/**
	 * ���ѧ����Ϣ
	 */
	private void addStudent() {
		Stage stage = new Stage();
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(15, 15, 15, 15));
		pane.setHgap(5);
		pane.setVgap(5);
		// �����ؿؼ�
		pane.add(new Label("ѧ��:"), 0, 0);
		TextField tfSno = new TextField();
		tfSno.setPrefColumnCount(20);
		pane.add(tfSno, 1, 0);
		//
		pane.add(new Label("����:"), 0, 1);
		TextField tfName = new TextField();
		tfName.setPrefColumnCount(20);
		pane.add(tfName, 1, 1);
		//
		pane.add(new Label("�༶:"), 0, 2);
		TextField tfClasses = new TextField();
		tfClasses.setPrefColumnCount(20);
		pane.add(tfClasses, 1, 2);
		//
		pane.add(new Label("רҵ:"), 0, 3);
		TextField tfMajor = new TextField();
		tfMajor.setPrefColumnCount(20);
		pane.add(tfMajor, 1, 3);
		//
		pane.add(new Label("��ϵ��ʽ:"), 0, 4);
		TextField tfPhone = new TextField();
		tfPhone.setPrefColumnCount(20);
		pane.add(tfPhone, 1, 4);
		//
		Button btnAdd = new Button("ȷ�����");
		pane.add(btnAdd, 1, 5);
		GridPane.setHalignment(btnAdd, HPos.RIGHT);
		//
		Scene scene = new Scene(pane);
		stage.setTitle("���ѧ����Ϣ");
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
		// ��Ӱ�ť����¼�
		btnAdd.setOnAction(e -> {
			// �������
			if (tfSno.getText().trim().isEmpty() || tfName.getText().trim().isEmpty()
					|| tfClasses.getText().trim().isEmpty() || tfMajor.getText().trim().isEmpty()
					|| tfPhone.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "���Ȳ�ȫѧ����Ϣ!", "��ʾ", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (!StringUtils.isMobilePhone(tfPhone.getText().trim())) {
				JOptionPane.showMessageDialog(null, "����������ȷ����ϵ��ʽ!", "��ʾ", JOptionPane.ERROR_MESSAGE);
				return;
			}
			// ʵ����ѧ������
			Student student = new Student(tfSno.getText().trim(), tfName.getText().trim(), tfClasses.getText().trim(),
					tfMajor.getText().trim(), tfPhone.getText().trim());
			// ��ʼ���ѧ����Ϣ
			if (studentDao.doAddStudent(student)) {
				// ���ѧ����Ϣ�ɹ�
				JOptionPane.showMessageDialog(null, "���ѧ����Ϣ�ɹ�!", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
				// ˢ��ѧ����Ϣ
				refreshStudent();
				// �رս���
				stage.close();
			} else {
				// ���ѧ����Ϣʧ��
				JOptionPane.showMessageDialog(null, "���ѧ����Ϣʧ��,�����Ǹ�ѧ����Ϣ�Ѵ���!", "��ʾ", JOptionPane.ERROR_MESSAGE);
			}
		});
	}

	/**
	 * �޸�ѧ��ѧ��
	 */
	private void modifyStudent() {
		// �жϵ�ǰ�Ƿ�ѡ��ѧ����Ϣ
		if (studentTable.getSelectionModel().getSelectedItem() == null) {
			JOptionPane.showMessageDialog(null, "����ѡ����Ҫ�޸ĵ�ѧ����Ϣ!", "��ʾ", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// ��ȡѡ���ѧ����Ϣ
		Student stuinfo = studentTable.getSelectionModel().getSelectedItem();
		// ��ʼ׼��״̬
		Stage stage = new Stage();
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(15, 15, 15, 15));
		pane.setHgap(5);
		pane.setVgap(5);
		// �����ؿؼ�
		pane.add(new Label("ѧ��:"), 0, 0);
		TextField tfSno = new TextField();
		tfSno.setPrefColumnCount(20);
		tfSno.setEditable(false);
		tfSno.setText(stuinfo.getSno());
		pane.add(tfSno, 1, 0);
		//
		pane.add(new Label("����:"), 0, 1);
		TextField tfName = new TextField();
		tfName.setPrefColumnCount(20);
		tfName.setText(stuinfo.getName());
		pane.add(tfName, 1, 1);
		//
		pane.add(new Label("�༶:"), 0, 2);
		TextField tfClasses = new TextField();
		tfClasses.setPrefColumnCount(20);
		tfClasses.setText(stuinfo.getClasses());
		pane.add(tfClasses, 1, 2);
		//
		pane.add(new Label("רҵ:"), 0, 3);
		TextField tfMajor = new TextField();
		tfMajor.setPrefColumnCount(20);
		tfMajor.setText(stuinfo.getMajor());
		pane.add(tfMajor, 1, 3);
		//
		pane.add(new Label("��ϵ��ʽ:"), 0, 4);
		TextField tfPhone = new TextField();
		tfPhone.setPrefColumnCount(20);
		tfPhone.setText(stuinfo.getPhone());
		pane.add(tfPhone, 1, 4);
		//
		Button btnModify = new Button("ȷ���޸�");
		pane.add(btnModify, 1, 5);
		GridPane.setHalignment(btnModify, HPos.RIGHT);
		//
		Scene scene = new Scene(pane);
		stage.setTitle("�޸�ѧ����Ϣ");
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
		// �޸İ�ť����¼�
		btnModify.setOnAction(e -> {
			// �������
			if (tfSno.getText().trim().isEmpty() || tfName.getText().trim().isEmpty()
					|| tfClasses.getText().trim().isEmpty() || tfMajor.getText().trim().isEmpty()
					|| tfPhone.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "���Ȳ�ȫѧ����Ϣ!", "��ʾ", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (!StringUtils.isMobilePhone(tfPhone.getText().trim())) {
				JOptionPane.showMessageDialog(null, "����������ȷ����ϵ��ʽ!", "��ʾ", JOptionPane.ERROR_MESSAGE);
				return;
			}
			// ʵ����ѧ������
			Student student = new Student(tfSno.getText().trim(), tfName.getText().trim(), tfClasses.getText().trim(),
					tfMajor.getText().trim(), tfPhone.getText().trim());
			// ��ʼ�޸�ѧ����Ϣ
			if (studentDao.doModifyStudent(student)) {
				// �޸�ѧ����Ϣ�ɹ�
				JOptionPane.showMessageDialog(null, "�޸�ѧ����Ϣ�ɹ�!", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
				// ˢ��ѧ����Ϣ
				refreshStudent();
				// �رս���
				stage.close();
			} else {
				// �޸�ѧ����Ϣʧ��
				JOptionPane.showMessageDialog(null, "�޸�ѧ����Ϣʧ��,�����Ǹ�ѧ����Ϣ������!", "��ʾ", JOptionPane.ERROR_MESSAGE);
			}
		});
	}

	/**
	 * ɾ��ѧ����Ϣ
	 */
	private void deleteStudent() {
		// �жϵ�ǰ�Ƿ�ѡ��ѧ����Ϣ
		if (studentTable.getSelectionModel().getSelectedItem() == null) {
			JOptionPane.showMessageDialog(null, "����ѡ����Ҫɾ����ѧ����Ϣ!", "��ʾ", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// ��ȡѡ���ѧ����Ϣ
		Student student = studentTable.getSelectionModel().getSelectedItem();
		// ѯ���Ƿ�ȷ��ɾ��
		int res = JOptionPane.showConfirmDialog(null, String.format("��ȷ��ɾ��ѧ��[%s]��ѧ����Ϣ��", student.getSno()), "������ʾ",
				JOptionPane.YES_NO_OPTION);
		if (res == JOptionPane.YES_OPTION) {
			// ��ʼɾ��ѧ����Ϣ
			if (studentDao.doDeleteStudent(student)) {
				// ɾ��ѧ����Ϣ�ɹ�
				JOptionPane.showMessageDialog(null, "ɾ��ѧ����Ϣ�ɹ�!", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
				// ˢ��ѧ����Ϣ
				refreshStudent();
			} else {
				// ɾ��ѧ����Ϣʧ��
				JOptionPane.showMessageDialog(null, "ɾ��ѧ����Ϣʧ��!", "��ʾ", JOptionPane.ERROR_MESSAGE);
			}

		}
	}

	/**
	 * ��ʦ��Ϣ�������
	 * 
	 * @return
	 */
	private GridPane teacherTab() {
		GridPane pane = new GridPane();
		// ��ť
		Button btnQuery = new Button("��ѯ");
		btnQuery.setPrefWidth(60);
		Button btnAdd = new Button("���");
		btnAdd.setPrefWidth(60);
		Button btnModify = new Button("�޸�");
		btnModify.setPrefWidth(60);
		Button btnDelete = new Button("ɾ��");
		btnDelete.setPrefWidth(60);
		Button btnRefresh = new Button("ˢ��");
		btnRefresh.setPrefWidth(60);
		HBox hBox = new HBox(5);
		Label lb = new Label("����/����:");
		lb.setPrefWidth(70);
		hBox.getChildren().addAll(lb, tfTno, btnQuery, btnAdd, btnModify, btnDelete, btnRefresh);
		tfTno.setPrefColumnCount(15);
		VBox vBox = new VBox(10);
		vBox.getChildren().addAll(hBox);
		// �¼�����
		btnQuery.setOnAction(e -> queryTeacher());
		btnAdd.setOnAction(e -> addTeacher());
		btnModify.setOnAction(e -> modifyTeacher());
		btnDelete.setOnAction(e -> deleteTeacher());
		btnRefresh.setOnAction(e -> refreshTeacher());
		// ���table������
		TableColumn tnoCol = new TableColumn("����");
		tnoCol.setCellValueFactory(new PropertyValueFactory<Teacher, String>("tno"));
		TableColumn nameCol = new TableColumn("����");
		nameCol.setCellValueFactory(new PropertyValueFactory<Teacher, String>("name"));
		TableColumn departCol = new TableColumn("Ժϵ");
		departCol.setCellValueFactory(new PropertyValueFactory<Teacher, String>("depart"));
		TableColumn classesCol = new TableColumn("�οΰ༶");
		classesCol.setCellValueFactory(new PropertyValueFactory<Teacher, String>("classes"));
		TableColumn phoneCol = new TableColumn("��ϵ��ʽ");
		phoneCol.setCellValueFactory(new PropertyValueFactory<Teacher, String>("phone"));
		teacherTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		teacherTable.getColumns().addAll(tnoCol, nameCol, departCol, classesCol, phoneCol);
		teacherTable.setItems(teacherObservableList);
		vBox.getChildren().addAll(teacherTable);
		vBox.setPadding(new Insets(15, 15, 15, 15));
		pane.getChildren().add(vBox);
		// ˢ�½�ʦ��Ϣ
		refreshTeacher();
		return pane;
	}

	/**
	 * ˢ�½�ʦ��Ϣ
	 */
	private void refreshTeacher() {
		// ��Ž�ʦ��Ϣ
		List<Teacher> stuList = teacherDao.doGetTeacherList();
		// ��ձ��
		teacherObservableList.clear();
		// ����ʦ��Ϣ��ӵ������
		teacherObservableList.addAll(stuList);
	}

	/**
	 * ��ѯ��ʦ��Ϣ
	 */
	private void queryTeacher() {
		// �������
		if (tfTno.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "���Ȳ�ȫ��ѯ��Ϣ!", "��ʾ", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// ʵ������ʦ����
		Teacher teacher = new Teacher(tfTno.getText().trim(), tfTno.getText().trim(), null, null, null);
		// ��ʼ��ѯ
		List<Teacher> list = teacherDao.doQueryTeacherByTnoOrName(teacher);
		// ��ձ��
		teacherObservableList.clear();
		// ����ʦ��Ϣ��ӵ������
		teacherObservableList.addAll(list);
	}

	/**
	 * ��ӽ�ʦ��Ϣ
	 */
	private void addTeacher() {
		Stage stage = new Stage();
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(15, 15, 15, 15));
		pane.setHgap(5);
		pane.setVgap(5);
		// �����ؿؼ�
		pane.add(new Label("����:"), 0, 0);
		TextField tfTno = new TextField();
		tfTno.setPrefColumnCount(20);
		pane.add(tfTno, 1, 0);
		//
		pane.add(new Label("����:"), 0, 1);
		TextField tfName = new TextField();
		tfName.setPrefColumnCount(20);
		pane.add(tfName, 1, 1);
		//
		pane.add(new Label("Ժϵ:"), 0, 2);
		TextField tfDepart = new TextField();
		tfDepart.setPrefColumnCount(20);
		pane.add(tfDepart, 1, 2);
		//
		pane.add(new Label("�οΰ༶:"), 0, 3);
		TextField tfClasses = new TextField();
		tfClasses.setPrefColumnCount(20);
		pane.add(tfClasses, 1, 3);
		//
		pane.add(new Label("��ϵ��ʽ:"), 0, 4);
		TextField tfPhone = new TextField();
		tfPhone.setPrefColumnCount(20);
		pane.add(tfPhone, 1, 4);
		//
		Button btnAdd = new Button("ȷ�����");
		pane.add(btnAdd, 1, 5);
		GridPane.setHalignment(btnAdd, HPos.RIGHT);
		//
		Scene scene = new Scene(pane);
		stage.setTitle("��ӽ�ʦ��Ϣ");
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
		// ��Ӱ�ť����¼�
		btnAdd.setOnAction(e -> {
			// �������
			if (tfTno.getText().trim().isEmpty() || tfName.getText().trim().isEmpty()
					|| tfDepart.getText().trim().isEmpty() || tfClasses.getText().trim().isEmpty()
					|| tfPhone.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "���Ȳ�ȫ��ʦ��Ϣ!", "��ʾ", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (!StringUtils.isMobilePhone(tfPhone.getText().trim())) {
				JOptionPane.showMessageDialog(null, "����������ȷ����ϵ��ʽ!", "��ʾ", JOptionPane.ERROR_MESSAGE);
				return;
			}
			// ʵ������ʦ����
			Teacher teacher = new Teacher(tfTno.getText().trim(), tfName.getText().trim(), tfDepart.getText().trim(),
					tfClasses.getText().trim(), tfPhone.getText().trim());
			// ��ʼ��ӽ�ʦ��Ϣ
			if (teacherDao.doAddTeacher(teacher)) {
				// ��ӽ�ʦ��Ϣ�ɹ�
				JOptionPane.showMessageDialog(null, "��ӽ�ʦ��Ϣ�ɹ�!", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
				// ˢ�½�ʦ��Ϣ
				refreshTeacher();
				// �رս���
				stage.close();
			} else {
				// ��ӽ�ʦ��Ϣʧ��
				JOptionPane.showMessageDialog(null, "��ӽ�ʦ��Ϣʧ��,�����Ǹý�ʦ��Ϣ�Ѵ���!", "��ʾ", JOptionPane.ERROR_MESSAGE);
			}
		});
	}

	/**
	 * �޸Ľ�ʦ��ʦ
	 */
	private void modifyTeacher() {
		// �жϵ�ǰ�Ƿ�ѡ���ʦ��Ϣ
		if (teacherTable.getSelectionModel().getSelectedItem() == null) {
			JOptionPane.showMessageDialog(null, "����ѡ����Ҫ�޸ĵĽ�ʦ��Ϣ!", "��ʾ", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// ��ȡѡ��Ľ�ʦ��Ϣ
		Teacher stuinfo = teacherTable.getSelectionModel().getSelectedItem();
		// ��ʼ׼��״̬
		Stage stage = new Stage();
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(15, 15, 15, 15));
		pane.setHgap(5);
		pane.setVgap(5);
		// �����ؿؼ�
		pane.add(new Label("����:"), 0, 0);
		TextField tfTno = new TextField();
		tfTno.setPrefColumnCount(20);
		tfTno.setEditable(false);
		tfTno.setText(stuinfo.getTno());
		pane.add(tfTno, 1, 0);
		//
		pane.add(new Label("����:"), 0, 1);
		TextField tfName = new TextField();
		tfName.setPrefColumnCount(20);
		tfName.setText(stuinfo.getName());
		pane.add(tfName, 1, 1);
		//
		pane.add(new Label("Ժϵ:"), 0, 2);
		TextField tfDepart = new TextField();
		tfDepart.setPrefColumnCount(20);
		tfDepart.setText(stuinfo.getDepart());
		pane.add(tfDepart, 1, 2);
		//
		pane.add(new Label("�οΰ༶:"), 0, 3);
		TextField tfClasses = new TextField();
		tfClasses.setPrefColumnCount(20);
		tfClasses.setText(stuinfo.getClasses());
		pane.add(tfClasses, 1, 3);
		//
		pane.add(new Label("��ϵ��ʽ:"), 0, 4);
		TextField tfPhone = new TextField();
		tfPhone.setPrefColumnCount(20);
		tfPhone.setText(stuinfo.getPhone());
		pane.add(tfPhone, 1, 4);
		//
		Button btnModify = new Button("ȷ���޸�");
		pane.add(btnModify, 1, 5);
		GridPane.setHalignment(btnModify, HPos.RIGHT);
		//
		Scene scene = new Scene(pane);
		stage.setTitle("�޸Ľ�ʦ��Ϣ");
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
		// �޸İ�ť����¼�
		btnModify.setOnAction(e -> {
			// �������
			if (tfTno.getText().trim().isEmpty() || tfName.getText().trim().isEmpty()
					|| tfDepart.getText().trim().isEmpty() || tfClasses.getText().trim().isEmpty()
					|| tfPhone.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "���Ȳ�ȫ��ʦ��Ϣ!", "��ʾ", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (!StringUtils.isMobilePhone(tfPhone.getText().trim())) {
				JOptionPane.showMessageDialog(null, "����������ȷ����ϵ��ʽ!", "��ʾ", JOptionPane.ERROR_MESSAGE);
				return;
			}
			// ʵ������ʦ����
			Teacher teacher = new Teacher(tfTno.getText().trim(), tfName.getText().trim(), tfDepart.getText().trim(),
					tfClasses.getText().trim(), tfPhone.getText().trim());
			// ��ʼ�޸Ľ�ʦ��Ϣ
			if (teacherDao.doModifyTeacher(teacher)) {
				// �޸Ľ�ʦ��Ϣ�ɹ�
				JOptionPane.showMessageDialog(null, "�޸Ľ�ʦ��Ϣ�ɹ�!", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
				// ˢ�½�ʦ��Ϣ
				refreshTeacher();
				// �رս���
				stage.close();
			} else {
				// �޸Ľ�ʦ��Ϣʧ��
				JOptionPane.showMessageDialog(null, "�޸Ľ�ʦ��Ϣʧ��,�����Ǹý�ʦ��Ϣ������!", "��ʾ", JOptionPane.ERROR_MESSAGE);
			}
		});
	}

	/**
	 * ɾ����ʦ��Ϣ
	 */
	private void deleteTeacher() {
		// �жϵ�ǰ�Ƿ�ѡ���ʦ��Ϣ
		if (teacherTable.getSelectionModel().getSelectedItem() == null) {
			JOptionPane.showMessageDialog(null, "����ѡ����Ҫɾ���Ľ�ʦ��Ϣ!", "��ʾ", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// ��ȡѡ��Ľ�ʦ��Ϣ
		Teacher teacher = teacherTable.getSelectionModel().getSelectedItem();
		// ѯ���Ƿ�ȷ��ɾ��
		int res = JOptionPane.showConfirmDialog(null, String.format("��ȷ��ɾ������[%s]�Ľ�ʦ��Ϣ��", teacher.getTno()), "������ʾ",
				JOptionPane.YES_NO_OPTION);
		if (res == JOptionPane.YES_OPTION) {
			// ��ʼɾ����ʦ��Ϣ
			if (teacherDao.doDeleteTeacher(teacher)) {
				// ɾ����ʦ��Ϣ�ɹ�
				JOptionPane.showMessageDialog(null, "ɾ����ʦ��Ϣ�ɹ�!", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
				// ˢ�½�ʦ��Ϣ
				refreshTeacher();
			} else {
				// ɾ����ʦ��Ϣʧ��
				JOptionPane.showMessageDialog(null, "ɾ����ʦ��Ϣʧ��!", "��ʾ", JOptionPane.ERROR_MESSAGE);
			}

		}
	}

	/**
	 * �ɼ���Ϣ�������
	 * 
	 * @return
	 */
	private GridPane scoreTab() {
		GridPane pane = new GridPane();
		// ��ť
		Button btnQuery = new Button("��ѯ");
		btnQuery.setPrefWidth(60);
		Button btnAdd = new Button("���");
		btnAdd.setPrefWidth(60);
		Button btnModify = new Button("�޸�");
		btnModify.setPrefWidth(60);
		Button btnDelete = new Button("ɾ��");
		btnDelete.setPrefWidth(60);
		Button btnRefresh = new Button("ˢ��");
		btnRefresh.setPrefWidth(60);
		HBox hBox = new HBox(5);
		hBox.getChildren().addAll(new Label("ѧ��:"), tfScoSno, btnQuery, btnAdd, btnModify, btnDelete, btnRefresh);
		tfScoSno.setPrefColumnCount(15);
		VBox vBox = new VBox(10);
		vBox.getChildren().addAll(hBox);
		// �¼�����
		btnQuery.setOnAction(e -> queryScore());
		btnAdd.setOnAction(e -> addScore());
		btnModify.setOnAction(e -> modifyScore());
		btnDelete.setOnAction(e -> deleteScore());
		btnRefresh.setOnAction(e -> refreshScore());
		// ���table������
		TableColumn snoCol = new TableColumn("ѧ��");
		snoCol.setCellValueFactory(new PropertyValueFactory<Score, String>("sno"));
		TableColumn score1Col = new TableColumn("���ĳɼ�");
		score1Col.setCellValueFactory(new PropertyValueFactory<Score, String>("score1"));
		TableColumn score2Col = new TableColumn("��ѧ�ɼ�");
		score2Col.setCellValueFactory(new PropertyValueFactory<Score, String>("score2"));
		TableColumn score3Col = new TableColumn("Ӣ��ɼ�");
		score3Col.setCellValueFactory(new PropertyValueFactory<Score, String>("score3"));
		TableColumn score4Col = new TableColumn("�����ɼ�");
		score4Col.setCellValueFactory(new PropertyValueFactory<Score, String>("score4"));
		TableColumn score5Col = new TableColumn("�ܳɼ�");
		score5Col.setCellValueFactory(new PropertyValueFactory<Score, String>("tscore"));
		
		scoreTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		scoreTable.getColumns().addAll(snoCol, score1Col, score2Col, score3Col, score4Col, score5Col);
		scoreTable.setItems(scoreObservableList);
		vBox.getChildren().addAll(scoreTable);
		vBox.setPadding(new Insets(15, 15, 15, 15));
		pane.getChildren().add(vBox);
		// ˢ�³ɼ���Ϣ
		refreshScore();
		return pane;
	}

	/**
	 * ˢ�³ɼ���Ϣ
	 */
	private void refreshScore() {
		// ��ųɼ���Ϣ
		List<Score> stuList = scoreDao.doGetScoreList();
		// ��ձ��
		scoreObservableList.clear();
		// ���ɼ���Ϣ��ӵ������
		scoreObservableList.addAll(stuList);
	}

	/**
	 * ��ѯ�ɼ���Ϣ
	 */
	private void queryScore() {
		// �������
		if (tfScoSno.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "���Ȳ�ȫ��ѯ��Ϣ!", "��ʾ", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// ʵ�����ɼ�����
		Score score = new Score(tfScoSno.getText().trim(), -1, -1, -1, -1);
		// ��ʼ��ѯ
		Score scoinfo = scoreDao.doQueryScoreBySno(score);
		// ��ձ��
		scoreObservableList.clear();
		// ���ɼ���Ϣ��ӵ������
		scoreObservableList.add(scoinfo);
	}

	/**
	 * ��ӳɼ���Ϣ
	 */
	private void addScore() {
		Stage stage = new Stage();
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(15, 15, 15, 15));
		pane.setHgap(5);
		pane.setVgap(5);
		// �����ؿؼ�
		pane.add(new Label("ѧ��:"), 0, 0);
		TextField tfScoSno = new TextField();
		tfScoSno.setPrefColumnCount(20);
		pane.add(tfScoSno, 1, 0);
		//
		pane.add(new Label("���ĳɼ�:"), 0, 1);
		TextField tfScore1 = new TextField();
		tfScore1.setPrefColumnCount(20);
		pane.add(tfScore1, 1, 1);
		//
		pane.add(new Label("��ѧ�ɼ�:"), 0, 2);
		TextField tfScore2 = new TextField();
		tfScore2.setPrefColumnCount(20);
		pane.add(tfScore2, 1, 2);
		//
		pane.add(new Label("Ӣ��ɼ�:"), 0, 3);
		TextField tfScore3 = new TextField();
		tfScore3.setPrefColumnCount(20);
		pane.add(tfScore3, 1, 3);
		//
		pane.add(new Label("�����ɼ�:"), 0, 4);
		TextField tfScore4 = new TextField();
		tfScore4.setPrefColumnCount(20);
		pane.add(tfScore4, 1, 4);
		
		//
		Button btnAdd = new Button("ȷ�����");
		pane.add(btnAdd, 1, 5);
		GridPane.setHalignment(btnAdd, HPos.RIGHT);
		//
		Scene scene = new Scene(pane);
		stage.setTitle("��ӳɼ���Ϣ");
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
		// ��Ӱ�ť����¼�
		btnAdd.setOnAction(e -> {
			// �������
			if (tfScoSno.getText().trim().isEmpty() || tfScore1.getText().trim().isEmpty()
					|| tfScore2.getText().trim().isEmpty() || tfScore3.getText().trim().isEmpty()
					|| tfScore4.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "���Ȳ�ȫ�ɼ���Ϣ!", "��ʾ", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (!StringUtils.validateNumber(tfScore1.getText().trim())
					|| !StringUtils.isScore(tfScore1.getText().trim())) {
				JOptionPane.showMessageDialog(null, "����������ȷ�����ĳɼ�!", "��ʾ", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (!StringUtils.validateNumber(tfScore2.getText().trim())
					|| !StringUtils.isScore(tfScore2.getText().trim())) {
				JOptionPane.showMessageDialog(null, "����������ȷ����ѧ�ɼ�!", "��ʾ", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (!StringUtils.validateNumber(tfScore3.getText().trim())
					|| !StringUtils.isScore(tfScore3.getText().trim())) {
				JOptionPane.showMessageDialog(null, "����������ȷ��Ӣ��ɼ�!", "��ʾ", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (!StringUtils.validateNumber(tfScore4.getText().trim())
					|| !StringUtils.isScore(tfScore4.getText().trim())) {
				JOptionPane.showMessageDialog(null, "����������ȷ�������ɼ�!", "��ʾ", JOptionPane.ERROR_MESSAGE);
				return;
			}
			// ʵ�����ɼ�����
			Score score = new Score(tfScoSno.getText().trim(), Float.parseFloat(tfScore1.getText().trim()),
					Float.parseFloat(tfScore2.getText().trim()), Float.parseFloat(tfScore3.getText().trim()),
					Float.parseFloat(tfScore4.getText().trim()));
			// ��ȡѧ����Ϣ
			Student student = studentDao.doQueryStudentBySno(new Student(score.getSno(), null, null, null, null));
			// �ж�ѧ����Ϣ�Ƿ���ȷ
			if (student == null) {
				// ѧ����Ϣ����ȷ
				JOptionPane.showMessageDialog(null, "����������ȷ��ѧ��ѧ��!", "��ʾ", JOptionPane.ERROR_MESSAGE);
				return;
			}
			// ��ʼ��ӳɼ���Ϣ
			if (scoreDao.doAddScore(score)) {
				// ��ӳɼ���Ϣ�ɹ�
				JOptionPane.showMessageDialog(null, "��ӳɼ���Ϣ�ɹ�!", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
				// ˢ�³ɼ���Ϣ
				refreshScore();
				// �رս���
				stage.close();
			} else {
				// ��ӳɼ���Ϣʧ��
				JOptionPane.showMessageDialog(null, "��ӳɼ���Ϣʧ��,�����Ǹóɼ���Ϣ�Ѵ���!", "��ʾ", JOptionPane.ERROR_MESSAGE);
			}
		});
	}

	/**
	 * �޸ĳɼ��ɼ�
	 */
	private void modifyScore() {
		// �жϵ�ǰ�Ƿ�ѡ��ɼ���Ϣ
		if (scoreTable.getSelectionModel().getSelectedItem() == null) {
			JOptionPane.showMessageDialog(null, "����ѡ����Ҫ�޸ĵĳɼ���Ϣ!", "��ʾ", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// ��ȡѡ��ĳɼ���Ϣ
		Score stuinfo = scoreTable.getSelectionModel().getSelectedItem();
		// ��ʼ׼��״̬
		Stage stage = new Stage();
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(15, 15, 15, 15));
		pane.setHgap(5);
		pane.setVgap(5);
		// �����ؿؼ�
		pane.add(new Label("ѧ��:"), 0, 0);
		TextField tfScoSno = new TextField();
		tfScoSno.setPrefColumnCount(20);
		tfScoSno.setEditable(false);
		tfScoSno.setText(stuinfo.getSno());
		pane.add(tfScoSno, 1, 0);
		//
		pane.add(new Label("���ĳɼ�:"), 0, 1);
		TextField tfScore1 = new TextField();
		tfScore1.setPrefColumnCount(20);
		tfScore1.setText(Float.toString(stuinfo.getScore1()));
		pane.add(tfScore1, 1, 1);
		//
		pane.add(new Label("��ѧ�ɼ�:"), 0, 2);
		TextField tfScore2 = new TextField();
		tfScore2.setPrefColumnCount(20);
		tfScore2.setText(Float.toString(stuinfo.getScore2()));
		pane.add(tfScore2, 1, 2);
		//
		pane.add(new Label("Ӣ��ɼ�:"), 0, 3);
		TextField tfScore3 = new TextField();
		tfScore3.setPrefColumnCount(20);
		tfScore3.setText(Float.toString(stuinfo.getScore3()));
		pane.add(tfScore3, 1, 3);
		//
		pane.add(new Label("�����ɼ�:"), 0, 4);
		TextField tfScore4 = new TextField();
		tfScore4.setPrefColumnCount(20);
		tfScore4.setText(Float.toString(stuinfo.getScore4()));
		pane.add(tfScore4, 1, 4);
		//
		Button btnModify = new Button("ȷ���޸�");
		pane.add(btnModify, 1, 5);
		GridPane.setHalignment(btnModify, HPos.RIGHT);
		//
		Scene scene = new Scene(pane);
		stage.setTitle("�޸ĳɼ���Ϣ");
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
		// �޸İ�ť����¼�
		btnModify.setOnAction(e -> {
			// �������
			if (tfScoSno.getText().trim().isEmpty() || tfScore1.getText().trim().isEmpty()
					|| tfScore2.getText().trim().isEmpty() || tfScore3.getText().trim().isEmpty()
					|| tfScore4.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "���Ȳ�ȫ�ɼ���Ϣ!", "��ʾ", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (!StringUtils.validateNumber(tfScore1.getText().trim())
					|| !StringUtils.isScore(tfScore1.getText().trim())) {
				JOptionPane.showMessageDialog(null, "����������ȷ�����ĳɼ�!", "��ʾ", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (!StringUtils.validateNumber(tfScore2.getText().trim())
					|| !StringUtils.isScore(tfScore2.getText().trim())) {
				JOptionPane.showMessageDialog(null, "����������ȷ����ѧ�ɼ�!", "��ʾ", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (!StringUtils.validateNumber(tfScore3.getText().trim())
					|| !StringUtils.isScore(tfScore3.getText().trim())) {
				JOptionPane.showMessageDialog(null, "����������ȷ��Ӣ��ɼ�!", "��ʾ", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (!StringUtils.validateNumber(tfScore4.getText().trim())
					|| !StringUtils.isScore(tfScore4.getText().trim())) {
				JOptionPane.showMessageDialog(null, "����������ȷ�������ɼ�!", "��ʾ", JOptionPane.ERROR_MESSAGE);
				return;
			}
			// ʵ�����ɼ�����
			Score score = new Score(tfScoSno.getText().trim(), Float.parseFloat(tfScore1.getText().trim()),
					Float.parseFloat(tfScore2.getText().trim()), Float.parseFloat(tfScore3.getText().trim()),
					Float.parseFloat(tfScore4.getText().trim()));
			// ��ʼ�޸ĳɼ���Ϣ
			if (scoreDao.doModifyScore(score)) {
				// �޸ĳɼ���Ϣ�ɹ�
				JOptionPane.showMessageDialog(null, "�޸ĳɼ���Ϣ�ɹ�!", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
				// ˢ�³ɼ���Ϣ
				refreshScore();
				// �رս���
				stage.close();
			} else {
				// �޸ĳɼ���Ϣʧ��
				JOptionPane.showMessageDialog(null, "�޸ĳɼ���Ϣʧ��,�����Ǹóɼ���Ϣ������!", "��ʾ", JOptionPane.ERROR_MESSAGE);
			}
		});
	}

	/**
	 * ɾ���ɼ���Ϣ
	 */
	private void deleteScore() {
		// �жϵ�ǰ�Ƿ�ѡ��ɼ���Ϣ
		if (scoreTable.getSelectionModel().getSelectedItem() == null) {
			JOptionPane.showMessageDialog(null, "����ѡ����Ҫɾ���ĳɼ���Ϣ!", "��ʾ", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// ��ȡѡ��ĳɼ���Ϣ
		Score score = scoreTable.getSelectionModel().getSelectedItem();
		// ѯ���Ƿ�ȷ��ɾ��
		int res = JOptionPane.showConfirmDialog(null, String.format("��ȷ��ɾ��ѧ��[%s]�ĳɼ���Ϣ��", score.getSno()), "������ʾ",
				JOptionPane.YES_NO_OPTION);
		if (res == JOptionPane.YES_OPTION) {
			// ��ʼɾ���ɼ���Ϣ
			if (scoreDao.doDeleteScore(score)) {
				// ɾ���ɼ���Ϣ�ɹ�
				JOptionPane.showMessageDialog(null, "ɾ���ɼ���Ϣ�ɹ�!", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
				// ˢ�³ɼ���Ϣ
				refreshScore();
			} else {
				// ɾ���ɼ���Ϣʧ��
				JOptionPane.showMessageDialog(null, "ɾ���ɼ���Ϣʧ��!", "��ʾ", JOptionPane.ERROR_MESSAGE);
			}

		}
	}

	/**
	 * �ɼ�����������
	 * 
	 * @return
	 */
	private GridPane scoreSortTab() {
		GridPane pane = new GridPane();
		// �������������
		cbSortObj = new ChoiceBox<String>(FXCollections.observableArrayList("��[ѧ��]����", "��[���ĳɼ�]����", "��[��ѧ�ɼ�]����",
				"��[Ӣ��ɼ�]����", "��[�����ɼ�]����", "��[�ܳɼ�]����"));
		cbSortObj.setPrefWidth(500);
		// ����ʽ������
		cbSortType = new ChoiceBox<String>(FXCollections.observableArrayList("����[����]����", "����[����]����"));
		cbSortType.setPrefWidth(500);
		// ����������
		HBox tBox = new HBox(2);
		tBox.getChildren().addAll(new Label("�������:"), cbSortObj);
		HBox hBox = new HBox(2);
		hBox.getChildren().addAll(new Label("����ʽ:"), cbSortType);
		VBox vBox = new VBox(10);
		vBox.getChildren().addAll(tBox, hBox);
		// �¼�����
		cbSortObj.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				// ����ɼ���Ϣ
				scoreSort();
			}
		});
		cbSortType.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				// ����ɼ���Ϣ
				scoreSort();
			}
		});
		// ���table������
		TableColumn snoCol = new TableColumn("ѧ��");
		snoCol.setCellValueFactory(new PropertyValueFactory<Score, String>("sno"));
		TableColumn score1Col = new TableColumn("���ĳɼ�");
		score1Col.setCellValueFactory(new PropertyValueFactory<Score, String>("score1"));
		TableColumn score2Col = new TableColumn("��ѧ�ɼ�");
		score2Col.setCellValueFactory(new PropertyValueFactory<Score, String>("score2"));
		TableColumn score3Col = new TableColumn("Ӣ��ɼ�");
		score3Col.setCellValueFactory(new PropertyValueFactory<Score, String>("score3"));
		TableColumn score4Col = new TableColumn("�����ɼ�");
		score4Col.setCellValueFactory(new PropertyValueFactory<Score, String>("score4"));
		TableColumn score5Col = new TableColumn("�ܳɼ�");
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
	 * ����ɼ���Ϣ
	 */
	private void scoreSort() {
		// ��ȡѡ����������
		int objIndex = cbSortObj.getSelectionModel().getSelectedIndex();
		// ��ȡѡ�����������
		int typeIndex = cbSortType.getSelectionModel().getSelectedIndex();
		// �ж��Ƿ���Խ�������
		if (objIndex != -1 && typeIndex != -1) {
			// ��ʼ����
			// ��ȡ���гɼ���Ϣ
			List<Score> list = scoreDao.doGetScoreList();
			// ʹ��ð�����򷨽�������
			for (int i = 0; i < list.size() - 1; i++) {
				for (int j = 0; j < list.size() - 1 - i; j++) {
					// �ж��Ƿ�ﵽ��������
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
						// ���������ɼ���Ϣ
						Score stmp = list.get(j);
						list.set(j, list.get(j + 1));
						list.set(j + 1, stmp);
					}
				}
			}
			// �������
			// ��ձ��
			scoreSortObservableList.clear();
			// ���ɼ���Ϣ��ӵ������
			scoreSortObservableList.addAll(list);
		}
	}

	/**
	 * �ɼ������������
	 * 
	 * @return
	 */
	private GridPane scoreAnalysisTab() {
		GridPane pane = new GridPane();
		// ��ѯ�γ�������
		cbScoreType = new ChoiceBox<String>(FXCollections.observableArrayList("���Ŀγ�", "��ѧ�γ�", "Ӣ��γ�", "�����γ�"));
		cbScoreType.setPrefWidth(500);
		// ����������
		HBox tBox = new HBox(2);
		tBox.getChildren().addAll(new Label("��ѯ�γ�:"), cbScoreType);
		VBox vBox = new VBox(10);
		vBox.getChildren().addAll(tBox);
		// �¼�����
		cbScoreType.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				// ��ȡѡ��Ŀγ�
				int indexType = cbScoreType.getSelectionModel().getSelectedIndex();
				// �ж��Ƿ���ѡ��γ�
				if (indexType == -1) {
					return;
				}
				// ��ѯ��ǰ���гɼ���Ϣ
				List<Score> list = scoreDao.doGetScoreList();
				// �жϳɼ���Ϣ����
				if (list.isEmpty()) {
					return;
				}
				// ��ʼ������߷�����ͷֵĳɼ���Ϣ
				Score max = list.get(0);
				Score min = list.get(0);
				for (int i = 1; i < list.size(); i++) {
					// �ж���߷�
					if ((indexType == 0 && max.getScore1() < list.get(i).getScore1())
							|| (indexType == 1 && max.getScore2() < list.get(i).getScore2())
							|| (indexType == 2 && max.getScore3() < list.get(i).getScore3())
							|| (indexType == 3 && max.getScore4() < list.get(i).getScore4())) {
						max = list.get(i);
					}
					// �ж���ͷ�
					if ((indexType == 0 && min.getScore1() > list.get(i).getScore1())
							|| (indexType == 1 && min.getScore2() > list.get(i).getScore2())
							|| (indexType == 2 && min.getScore3() > list.get(i).getScore3())
							|| (indexType == 3 && min.getScore4() > list.get(i).getScore4())) {
						min = list.get(i);
					}
				}
				// �ҳ���߷���ͷֵ�ѧ����Ϣ
				Student stu_max = studentDao.doQueryStudentBySno(new Student(max.getSno(), null, null, null, null));
				Student stu_min = studentDao.doQueryStudentBySno(new Student(min.getSno(), null, null, null, null));
				// ������߷�����ͷ���Ϣ
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
				stu_max.setPhone("��߷�");
				stu_min.setPhone("��ͷ�");
				// ����߷�����ͷֵ�ѧ����Ϣ��ʾ�������
				scoreAnalysisObservableList.clear();
				// ��ѧ����Ϣ��ӵ������
				scoreAnalysisObservableList.addAll(stu_max, stu_min);
			}
		});
		// ���table������
		TableColumn snoCol = new TableColumn("ѧ��");
		snoCol.setCellValueFactory(new PropertyValueFactory<Student, String>("sno"));
		TableColumn nameCol = new TableColumn("����");
		nameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
		TableColumn classesCol = new TableColumn("�༶");
		classesCol.setCellValueFactory(new PropertyValueFactory<Student, String>("classes"));
		TableColumn majorCol = new TableColumn("�ɼ�");
		majorCol.setCellValueFactory(new PropertyValueFactory<Student, String>("major"));
		TableColumn phoneCol = new TableColumn("��ע");
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
	 * �ɼ�ͳ�Ʋ������
	 * 
	 * @return
	 */
	private GridPane scoreStatisTab() {
		GridPane pane = new GridPane();
		// ͳ�ƶ���������
		cbStatisScoreType=new ChoiceBox<String>(
				FXCollections.observableArrayList("���Ŀγ�", "��ѧ�γ�", "Ӣ��γ�", "�����γ�"));
		cbStatisScoreType.setPrefWidth(500);
		// �������������
		cbStatisObj = new ChoiceBox<String>(
				FXCollections.observableArrayList("��[������]����", "��[�Ϻ���]����", "��[������]����", "��[�ϸ���]����", "��[���ϸ���]����"));
		cbStatisObj.setPrefWidth(500);
		// ����ʽ������
		cbStatisType = new ChoiceBox<String>(FXCollections.observableArrayList("����[����]����", "����[����]����"));
		cbStatisType.setPrefWidth(500);
		// ����������
		HBox fBox = new HBox(2);
		fBox.getChildren().addAll(new Label("ͳ�ƶ���:"), cbStatisScoreType);
		HBox tBox = new HBox(2);
		tBox.getChildren().addAll(new Label("�������:"), cbStatisObj);
		HBox hBox = new HBox(2);
		hBox.getChildren().addAll(new Label("����ʽ:"), cbStatisType);
		VBox vBox = new VBox(10);
		vBox.getChildren().addAll(fBox,tBox, hBox);
		// �¼�����
		cbStatisScoreType.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				// ����ͳ����Ϣ
				statisSort();
			}
		});
		cbStatisObj.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				// ����ͳ����Ϣ
				statisSort();
			}
		});
		cbStatisType.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				// ����ͳ����Ϣ
				statisSort();
			}
		});
		// ���table������
		TableColumn snoCol = new TableColumn("�༶");
		snoCol.setCellValueFactory(new PropertyValueFactory<Score, String>("sno"));
		TableColumn score1Col = new TableColumn("������");
		score1Col.setCellValueFactory(new PropertyValueFactory<Score, String>("score1"));
		TableColumn score2Col = new TableColumn("�Ϻ���");
		score2Col.setCellValueFactory(new PropertyValueFactory<Score, String>("score2"));
		TableColumn score3Col = new TableColumn("������");
		score3Col.setCellValueFactory(new PropertyValueFactory<Score, String>("score3"));
		TableColumn score4Col = new TableColumn("�ϸ���");
		score4Col.setCellValueFactory(new PropertyValueFactory<Score, String>("score4"));
		TableColumn score5Col = new TableColumn("���ϸ���");
		score5Col.setCellValueFactory(new PropertyValueFactory<Score, String>("tscore"));
		scoreStatisTable.getColumns().addAll(snoCol, score1Col, score2Col, score3Col, score4Col, score5Col);
		scoreStatisTable.setItems(scoreStatisObservableList);
		vBox.getChildren().addAll(scoreStatisTable);
		vBox.setPadding(new Insets(15, 15, 15, 15));
		pane.getChildren().add(vBox);
		return pane;
	}

	/**
	 * ����ͳ����Ϣ
	 */
	private void statisSort() {
		// ��ȡѡ���ͳ�ƶ���
		int stypeIndex = cbStatisScoreType.getSelectionModel().getSelectedIndex();
		// ��ȡѡ����������
		int objIndex = cbStatisObj.getSelectionModel().getSelectedIndex();
		// ��ȡѡ�����������
		int typeIndex = cbStatisType.getSelectionModel().getSelectedIndex();
		// �ж��Ƿ���Խ�������
		if (stypeIndex!=-1 && objIndex != -1 && typeIndex != -1) {
			// ��ʼ����
			List<Score> slist=new ArrayList<Score>();
			// ��ȡ���гɼ���Ϣ
			List<Score> list = scoreDao.doGetScoreList();
			// ͳ�����а༶
			Set st_classes = new HashSet();
			for(int i=0;i<list.size();i++) {
				//��ѯָ��ѧ�ŵ�ѧ����Ϣ
				Student student=studentDao.doQueryStudentBySno(new Student(list.get(i).getSno(),null,null,null,null));
				st_classes.add(student.getClasses());
			}
			// ���հ༶ͳ�������ʡ��Ϻ��ʡ������ʡ��ϸ��ʡ����ϸ���
			Iterator it = st_classes.iterator();
	        while (it.hasNext()) {
	        	String classes=it.next().toString();
	        	Score score=new Score(classes,0.0f,0.0f,0.0f,0.0f);
	        	int ccount=0;
	        	//ͳ�Ƶ�ǰ�༶��ָ���γ̵ĸ�������������
	        	for(int i=0;i<list.size();i++) {
	        		//��ѯָ��ѧ�ŵ�ѧ����Ϣ
					Student student=studentDao.doQueryStudentBySno(new Student(list.get(i).getSno(),null,null,null,null));
					if(student.getClasses().equals(classes)) {
						//�ۼӰ༶����
						ccount++;
						//ͳ�Ʒ���������
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
	        	//���������ʡ��Ϻ��ʡ������ʡ��ϸ��ʡ����ϸ���
	        	score.setScore1(score.getScore1()/ccount);
	        	score.setScore2(score.getScore2()/ccount);
	        	score.setScore3(score.getScore3()/ccount);
	        	score.setScore4(score.getScore4()/ccount);
	        	score.setTscore(score.getTscore()/ccount);
	        	slist.add(score);
	        }
			// ʹ��ð�����򷨽�������
			for (int i = 0; i < slist.size() - 1; i++) {
				for (int j = 0; j < slist.size() - 1 - i; j++) {
					// �ж��Ƿ�ﵽ��������
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
						// ���������ɼ���Ϣ
						Score stmp = slist.get(j);
						slist.set(j, slist.get(j + 1));
						slist.set(j + 1, stmp);
					}
				}
			}
			// �������
			// ��ձ��
			scoreStatisObservableList.clear();
			// ���ɼ���Ϣ��ӵ������
			scoreStatisObservableList.addAll(slist);
		}
	}
}
