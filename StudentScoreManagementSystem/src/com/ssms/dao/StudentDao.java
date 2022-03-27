package com.ssms.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.ssms.entity.Student;

public class StudentDao {

	/**
	 * 读取文件信息
	 */
	public static List<Student> readStudentFile() {
		List<Student> list = new ArrayList<Student>();
		File file = new File("Student.dat");
		// 判断文件是否存在
		if (!file.exists()) {
			// 文件不存在,返回空动态数组
			return list;
		}
		try {
			// 开始以反序列化的形式读取文件
			FileInputStream fs = new FileInputStream(file);
			ObjectInputStream os = new ObjectInputStream(fs);
			list = (List<Student>) os.readObject();
			os.close();
			fs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 返回读取的结果
		return list;
	}

	/**
	 * 保存文件信息
	 */
	public static void saveStudentFile(List<Student> list) {
		File file = new File("Student.dat");
		try {
			// 开始以序列化的形式写入文件
			FileOutputStream fs = new FileOutputStream(file);
			ObjectOutputStream os = new ObjectOutputStream(fs);
			os.writeObject(list);
			os.close();
			fs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加学生信息
	 * 
	 * @param student
	 */
	public boolean doAddStudent(Student student) {
		//首先判断当前学生学号是否存在
		if(doQueryStudentBySno(student)!=null) {
			//当前学生学号已存在,返回失败
			return false;
		}
		// 再读取学生文件信息
		List<Student> list = readStudentFile();
		// 添加学生信息
		list.add(student);
		// 写入学生文件信息
		saveStudentFile(list);
		//返回成功
		return true;
	}

	/**
	 * 根据学号获取学生信息
	 * 
	 * @param student
	 * @return
	 */
	public Student doQueryStudentBySno(Student student) {
		// 首先读取学生文件信息
		List<Student> list = readStudentFile();
		// 判断学生信息是否为空
		if (!list.isEmpty()) {
			// 遍历所有学生信息
			for (Student each : list) {
				// 判断当前学号是否和查询的学号一致
				if (each.getSno().equals(student.getSno())) {
					// 返回该学生的信息
					return each;
				}
			}
		}
		// 返回null
		return null;
	}

	/**
	 * 获取所有学生信息
	 * 
	 * @return
	 */
	public List<Student> doGetStudentList() {
		// 首先读取学生文件信息
		List<Student> list = readStudentFile();
		// 返回获取的所有学生信息
		return list;
	}
	
	/**
	 * 根据学号或姓名查询学生信息
	 * 
	 * @param student
	 * @return
	 */
	public List<Student> doQueryStudentBySnoOrName(Student student) {
		// 首先读取学生文件信息
		List<Student> list = readStudentFile();
		// 存储查询学生信息结果
		List<Student> slist=new ArrayList<Student>();
		// 判断学生信息是否为空
		if (!list.isEmpty()) {
			// 遍历所有学生信息
			for (Student each : list) {
				// 判断当前学号与姓名是否和查询的学号与姓名一致
				if (each.getSno().contains(student.getSno()) || each.getName().contains(student.getName())) {
					// 添加当前学生信息
					slist.add(each);
				}
			}
		}
		// 返回查询结果
		return slist;
	}

	/**
	 * 删除学生信息
	 * 
	 * @param student
	 */
	public boolean doDeleteStudent(Student student) {
		// 首先读取学生文件信息
		List<Student> list = readStudentFile();
		// 删除指定的学生信息
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getSno().equals(student.getSno())) {
				//删除学生信息
				list.remove(i);
				// 保存文件信息
				saveStudentFile(list);
				//返回成功
				return true;
			}
		}
		// 返回失败
		return false;
	}

	/**
	 * 修改学生信息
	 * 
	 * @param student
	 */
	public boolean doModifyStudent(Student student) {
		// 首先读取学生文件信息
		List<Student> list = readStudentFile();
		// 修改指定的学生信息
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getSno().equals(student.getSno())) {
				//修改学生信息
				list.set(i, student);
				// 保存文件信息
				saveStudentFile(list);
				//返回成功
				return true;
			}
		}
		// 返回失败
		return false;
	}

}
