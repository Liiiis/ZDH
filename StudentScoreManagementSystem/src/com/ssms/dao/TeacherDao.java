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
	 * ��ȡ�ļ���Ϣ
	 */
	public static List<Teacher> readTeacherFile() {
		List<Teacher> list = new ArrayList<Teacher>();
		File file = new File("Teacher.dat");
		// �ж��ļ��Ƿ����
		if (!file.exists()) {
			// �ļ�������,���ؿն�̬����
			return list;
		}
		try {
			// ��ʼ�Է����л�����ʽ��ȡ�ļ�
			FileInputStream fs = new FileInputStream(file);
			ObjectInputStream os = new ObjectInputStream(fs);
			list = (List<Teacher>) os.readObject();
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
	public static void saveTeacherFile(List<Teacher> list) {
		File file = new File("Teacher.dat");
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
	 * ��ӽ�ʦ��Ϣ
	 * 
	 * @param teacher
	 */
	public boolean doAddTeacher(Teacher teacher) {
		// �����жϵ�ǰ��ʦ�����Ƿ����
		if (doQueryTeacherByTno(teacher) != null) {
			// ��ǰ��ʦ�����Ѵ���,����ʧ��
			return false;
		}
		// ���ȶ�ȡ��ʦ�ļ���Ϣ
		List<Teacher> list = readTeacherFile();
		// ��ӽ�ʦ��Ϣ
		list.add(teacher);
		// д���ʦ�ļ���Ϣ
		saveTeacherFile(list);
		// ���سɹ�
		return true;
	}

	/**
	 * ���ݹ��Ż�ȡ��ʦ��Ϣ
	 * 
	 * @param teacher
	 * @return
	 */
	public Teacher doQueryTeacherByTno(Teacher teacher) {
		// ���ȶ�ȡ��ʦ�ļ���Ϣ
		List<Teacher> list = readTeacherFile();
		// �жϽ�ʦ��Ϣ�Ƿ�Ϊ��
		if (!list.isEmpty()) {
			// �������н�ʦ��Ϣ
			for (Teacher each : list) {
				// �жϵ�ǰ�����Ƿ�Ͳ�ѯ�Ĺ���һ��
				if (each.getTno().equals(teacher.getTno())) {
					// ���ظý�ʦ����Ϣ
					return each;
				}
			}
		}
		// ����null
		return null;
	}

	/**
	 * ��ȡ���н�ʦ��Ϣ
	 * 
	 * @return
	 */
	public List<Teacher> doGetTeacherList() {
		// ���ȶ�ȡ��ʦ�ļ���Ϣ
		List<Teacher> list = readTeacherFile();
		// ���ػ�ȡ����Ϣ
		return list;
	}
	
	/**
	 * ���ݹ��Ż�������ѯѧ����Ϣ
	 * 
	 * @param teacher
	 * @return
	 */
	public List<Teacher> doQueryTeacherByTnoOrName(Teacher teacher) {
		// ���ȶ�ȡ��ʦ�ļ���Ϣ
		List<Teacher> list = readTeacherFile();
		// �洢��ѯ��ʦ��Ϣ���
		List<Teacher> slist=new ArrayList<Teacher>();
		// �жϽ�ʦ��Ϣ�Ƿ�Ϊ��
		if (!list.isEmpty()) {
			// �������н�ʦ��Ϣ
			for (Teacher each : list) {
				// �жϵ�ǰ�����������Ƿ�Ͳ�ѯ�Ĺ���������һ��
				if (each.getTno().contains(teacher.getTno()) || each.getName().contains(teacher.getName())) {
					// ��ӵ�ǰ��ʦ��Ϣ
					slist.add(each);
				}
			}
		}
		// ���ز�ѯ���
		return slist;
	}

	/**
	 * ɾ����ʦ��Ϣ
	 * 
	 * @param teacher
	 */
	public boolean doDeleteTeacher(Teacher teacher) {
		// ���ȶ�ȡ��ʦ�ļ���Ϣ
		List<Teacher> list = readTeacherFile();
		// ɾ��ָ���Ľ�ʦ��Ϣ
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getTno().equals(teacher.getTno())) {
				//ɾ����ʦ��Ϣ
				list.remove(i);
				// �����ļ���Ϣ
				saveTeacherFile(list);
				//���سɹ�
				return true;
			}
		}
		//����ʧ��
		return false;
	}

	/**
	 * �޸Ľ�ʦ��Ϣ
	 * 
	 * @param teacher
	 */
	public boolean doModifyTeacher(Teacher teacher) {
		// ���ȶ�ȡ��ʦ�ļ���Ϣ
		List<Teacher> list = readTeacherFile();
		// �޸�ָ���Ľ�ʦ��Ϣ
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getTno().equals(teacher.getTno())) {
				//�޸Ľ�ʦ��Ϣ
				list.set(i, teacher);
				// �����ļ���Ϣ
				saveTeacherFile(list);
				//���سɹ�
				return true;
			}
		}
		//����ʧ��
		return false;
	}

}
