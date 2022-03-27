package com.ssms.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.ssms.entity.Score;

public class ScoreDao {

	/**
	 * ��ȡ�ļ���Ϣ
	 */
	public static List<Score> readScoreFile() {
		List<Score> list = new ArrayList<Score>();
		File file = new File("Score.dat");
		// �ж��ļ��Ƿ����
		if (!file.exists()) {
			// �ļ�������,���ؿն�̬����
			return list;
		}
		try {
			// ��ʼ�Է����л�����ʽ��ȡ�ļ�
			FileInputStream fs = new FileInputStream(file);
			ObjectInputStream os = new ObjectInputStream(fs);
			list = (List<Score>) os.readObject();
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
	public static void saveScoreFile(List<Score> list) {
		File file = new File("Score.dat");
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
	 * ��ӳɼ���Ϣ
	 * 
	 * @param score
	 */
	public boolean doAddScore(Score score) {
		// �����жϵ�ǰѧ��ѧ���Ƿ����
		if (doQueryScoreBySno(score) != null) {
			// ��ǰѧ��ѧ���Ѵ���,����ʧ��
			return false;
		}
		// ���ȶ�ȡ�ɼ��ļ���Ϣ
		List<Score> list = readScoreFile();
		// ��ӳɼ���Ϣ
		list.add(score);
		// д��ɼ��ļ���Ϣ
		saveScoreFile(list);
		// ���سɹ�
		return true;
	}

	/**
	 * ����ѧ�Ż�ȡ�ɼ���Ϣ
	 * 
	 * @param score
	 * @return
	 */
	public Score doQueryScoreBySno(Score score) {
		// ���ȶ�ȡ�ɼ��ļ���Ϣ
		List<Score> list = readScoreFile();
		// �жϳɼ���Ϣ�Ƿ�Ϊ��
		if (!list.isEmpty()) {
			// �������гɼ���Ϣ
			for (Score each : list) {
				// �жϵ�ǰѧ���Ƿ�Ͳ�ѯ��ѧ��һ��
				if (each.getSno().equals(score.getSno())) {
					// ���ظóɼ�����Ϣ
					return each;
				}
			}
		}
		// ����null
		return null;
	}

	/**
	 * ��ȡ���гɼ���Ϣ
	 * 
	 * @return
	 */
	public List<Score> doGetScoreList() {
		// ���ȶ�ȡ�ɼ��ļ���Ϣ
		List<Score> list = readScoreFile();
		// ���سɼ���Ϣ
		return list;
	}

	/**
	 * ɾ���ɼ���Ϣ
	 * 
	 * @param score
	 */
	public boolean doDeleteScore(Score score) {
		// ���ȶ�ȡ�ɼ��ļ���Ϣ
		List<Score> list = readScoreFile();
		// ɾ��ָ���ĳɼ���Ϣ
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getSno().equals(score.getSno())) {
				//ɾ���ɼ���Ϣ
				list.remove(i);
				// д��ɼ��ļ���Ϣ
				saveScoreFile(list);
				//���سɹ�
				return true;
			}
		}
		//����ʧ��
		return false;
	}

	/**
	 * �޸ĳɼ���Ϣ
	 * 
	 * @param score
	 */
	public boolean doModifyScore(Score score) {
		// ���ȶ�ȡ�ɼ��ļ���Ϣ
		List<Score> list = readScoreFile();
		// �޸�ָ���ĳɼ���Ϣ
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getSno().equals(score.getSno())) {
				//�޸ĳɼ���Ϣ
				list.set(i, score);
				// д��ɼ��ļ���Ϣ
				saveScoreFile(list);
				//���سɹ�
				return true;
			}
		}
		// ����ʧ��
		return false;
	}
}
