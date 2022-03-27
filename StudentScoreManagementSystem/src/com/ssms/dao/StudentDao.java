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
	 * ��ȡ�ļ���Ϣ
	 */
	public static List<Student> readStudentFile() {
		List<Student> list = new ArrayList<Student>();
		File file = new File("Student.dat");
		// �ж��ļ��Ƿ����
		if (!file.exists()) {
			// �ļ�������,���ؿն�̬����
			return list;
		}
		try {
			// ��ʼ�Է����л�����ʽ��ȡ�ļ�
			FileInputStream fs = new FileInputStream(file);
			ObjectInputStream os = new ObjectInputStream(fs);
			list = (List<Student>) os.readObject();
			os.close();
			fs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ���ض�ȡ�Ľ��
		return list;
	}

	/**
	 * �����ļ���Ϣ
	 */
	public static void saveStudentFile(List<Student> list) {
		File file = new File("Student.dat");
		try {
			// ��ʼ�����л�����ʽд���ļ�
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
	 * ���ѧ����Ϣ
	 * 
	 * @param student
	 */
	public boolean doAddStudent(Student student) {
		//�����жϵ�ǰѧ��ѧ���Ƿ����
		if(doQueryStudentBySno(student)!=null) {
			//��ǰѧ��ѧ���Ѵ���,����ʧ��
			return false;
		}
		// �ٶ�ȡѧ���ļ���Ϣ
		List<Student> list = readStudentFile();
		// ���ѧ����Ϣ
		list.add(student);
		// д��ѧ���ļ���Ϣ
		saveStudentFile(list);
		//���سɹ�
		return true;
	}

	/**
	 * ����ѧ�Ż�ȡѧ����Ϣ
	 * 
	 * @param student
	 * @return
	 */
	public Student doQueryStudentBySno(Student student) {
		// ���ȶ�ȡѧ���ļ���Ϣ
		List<Student> list = readStudentFile();
		// �ж�ѧ����Ϣ�Ƿ�Ϊ��
		if (!list.isEmpty()) {
			// ��������ѧ����Ϣ
			for (Student each : list) {
				// �жϵ�ǰѧ���Ƿ�Ͳ�ѯ��ѧ��һ��
				if (each.getSno().equals(student.getSno())) {
					// ���ظ�ѧ������Ϣ
					return each;
				}
			}
		}
		// ����null
		return null;
	}

	/**
	 * ��ȡ����ѧ����Ϣ
	 * 
	 * @return
	 */
	public List<Student> doGetStudentList() {
		// ���ȶ�ȡѧ���ļ���Ϣ
		List<Student> list = readStudentFile();
		// ���ػ�ȡ������ѧ����Ϣ
		return list;
	}
	
	/**
	 * ����ѧ�Ż�������ѯѧ����Ϣ
	 * 
	 * @param student
	 * @return
	 */
	public List<Student> doQueryStudentBySnoOrName(Student student) {
		// ���ȶ�ȡѧ���ļ���Ϣ
		List<Student> list = readStudentFile();
		// �洢��ѯѧ����Ϣ���
		List<Student> slist=new ArrayList<Student>();
		// �ж�ѧ����Ϣ�Ƿ�Ϊ��
		if (!list.isEmpty()) {
			// ��������ѧ����Ϣ
			for (Student each : list) {
				// �жϵ�ǰѧ���������Ƿ�Ͳ�ѯ��ѧ��������һ��
				if (each.getSno().contains(student.getSno()) || each.getName().contains(student.getName())) {
					// ��ӵ�ǰѧ����Ϣ
					slist.add(each);
				}
			}
		}
		// ���ز�ѯ���
		return slist;
	}

	/**
	 * ɾ��ѧ����Ϣ
	 * 
	 * @param student
	 */
	public boolean doDeleteStudent(Student student) {
		// ���ȶ�ȡѧ���ļ���Ϣ
		List<Student> list = readStudentFile();
		// ɾ��ָ����ѧ����Ϣ
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getSno().equals(student.getSno())) {
				//ɾ��ѧ����Ϣ
				list.remove(i);
				// �����ļ���Ϣ
				saveStudentFile(list);
				//���سɹ�
				return true;
			}
		}
		// ����ʧ��
		return false;
	}

	/**
	 * �޸�ѧ����Ϣ
	 * 
	 * @param student
	 */
	public boolean doModifyStudent(Student student) {
		// ���ȶ�ȡѧ���ļ���Ϣ
		List<Student> list = readStudentFile();
		// �޸�ָ����ѧ����Ϣ
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getSno().equals(student.getSno())) {
				//�޸�ѧ����Ϣ
				list.set(i, student);
				// �����ļ���Ϣ
				saveStudentFile(list);
				//���سɹ�
				return true;
			}
		}
		// ����ʧ��
		return false;
	}

}
