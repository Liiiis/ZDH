package com.ssms.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.ssms.entity.Student;
import com.ssms.entity.Teacher;

public class TeacherDao {

	/**
	 * 读取文件信息
	 */
	public static List<Teacher> readTeacherFile() {
		List<Teacher> list = new ArrayList<Teacher>();
		File file = new File("Teacher.dat");
		// 判断文件是否存在
		if (!file.exists()) {
			// 文件不存在,返回空动态数组
			return list;
		}
		try {
			// 开始以反序列化的形式读取文件
			FileInputStream fs = new FileInputStream(file);
			ObjectInputStream os = new ObjectInputStream(fs);
			list = (List<Teacher>) os.readObject();
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
	public static void saveTeacherFile(List<Teacher> list) {
		File file = new File("Teacher.dat");
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
	 * 添加教师信息
	 * 
	 * @param teacher
	 */
	public boolean doAddTeacher(Teacher teacher) {
		// 首先判断当前教师工号是否存在
		if (doQueryTeacherByTno(teacher) != null) {
			// 当前教师工号已存在,返回失败
			return false;
		}
		// 首先读取教师文件信息
		List<Teacher> list = readTeacherFile();
		// 添加教师信息
		list.add(teacher);
		// 写入教师文件信息
		saveTeacherFile(list);
		// 返回成功
		return true;
	}

	/**
	 * 根据工号获取教师信息
	 * 
	 * @param teacher
	 * @return
	 */
	public Teacher doQueryTeacherByTno(Teacher teacher) {
		// 首先读取教师文件信息
		List<Teacher> list = readTeacherFile();
		// 判断教师信息是否为空
		if (!list.isEmpty()) {
			// 遍历所有教师信息
			for (Teacher each : list) {
				// 判断当前工号是否和查询的工号一致
				if (each.getTno().equals(teacher.getTno())) {
					// 返回该教师的信息
					return each;
				}
			}
		}
		// 返回null
		return null;
	}

	/**
	 * 获取所有教师信息
	 * 
	 * @return
	 */
	public List<Teacher> doGetTeacherList() {
		// 首先读取教师文件信息
		List<Teacher> list = readTeacherFile();
		// 返回获取的信息
		return list;
	}
	
	/**
	 * 根据工号或姓名查询学生信息
	 * 
	 * @param teacher
	 * @return
	 */
	public List<Teacher> doQueryTeacherByTnoOrName(Teacher teacher) {
		// 首先读取教师文件信息
		List<Teacher> list = readTeacherFile();
		// 存储查询教师信息结果
		List<Teacher> slist=new ArrayList<Teacher>();
		// 判断教师信息是否为空
		if (!list.isEmpty()) {
			// 遍历所有教师信息
			for (Teacher each : list) {
				// 判断当前工号与姓名是否和查询的工号与姓名一致
				if (each.getTno().contains(teacher.getTno()) || each.getName().contains(teacher.getName())) {
					// 添加当前教师信息
					slist.add(each);
				}
			}
		}
		// 返回查询结果
		return slist;
	}

	/**
	 * 删除教师信息
	 * 
	 * @param teacher
	 */
	public boolean doDeleteTeacher(Teacher teacher) {
		// 首先读取教师文件信息
		List<Teacher> list = readTeacherFile();
		// 删除指定的教师信息
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getTno().equals(teacher.getTno())) {
				//删除教师信息
				list.remove(i);
				// 保存文件信息
				saveTeacherFile(list);
				//返回成功
				return true;
			}
		}
		//返回失败
		return false;
	}

	/**
	 * 修改教师信息
	 * 
	 * @param teacher
	 */
	public boolean doModifyTeacher(Teacher teacher) {
		// 首先读取教师文件信息
		List<Teacher> list = readTeacherFile();
		// 修改指定的教师信息
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getTno().equals(teacher.getTno())) {
				//修改教师信息
				list.set(i, teacher);
				// 保存文件信息
				saveTeacherFile(list);
				//返回成功
				return true;
			}
		}
		//返回失败
		return false;
	}

}
