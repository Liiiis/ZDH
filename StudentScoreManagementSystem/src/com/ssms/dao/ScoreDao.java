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
	 * 读取文件信息
	 */
	public static List<Score> readScoreFile() {
		List<Score> list = new ArrayList<Score>();
		File file = new File("Score.dat");
		// 判断文件是否存在
		if (!file.exists()) {
			// 文件不存在,返回空动态数组
			return list;
		}
		try {
			// 开始以反序列化的形式读取文件
			FileInputStream fs = new FileInputStream(file);
			ObjectInputStream os = new ObjectInputStream(fs);
			list = (List<Score>) os.readObject();
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
	public static void saveScoreFile(List<Score> list) {
		File file = new File("Score.dat");
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
	 * 添加成绩信息
	 * 
	 * @param score
	 */
	public boolean doAddScore(Score score) {
		// 首先判断当前学生学号是否存在
		if (doQueryScoreBySno(score) != null) {
			// 当前学生学号已存在,返回失败
			return false;
		}
		// 首先读取成绩文件信息
		List<Score> list = readScoreFile();
		// 添加成绩信息
		list.add(score);
		// 写入成绩文件信息
		saveScoreFile(list);
		// 返回成功
		return true;
	}

	/**
	 * 根据学号获取成绩信息
	 * 
	 * @param score
	 * @return
	 */
	public Score doQueryScoreBySno(Score score) {
		// 首先读取成绩文件信息
		List<Score> list = readScoreFile();
		// 判断成绩信息是否为空
		if (!list.isEmpty()) {
			// 遍历所有成绩信息
			for (Score each : list) {
				// 判断当前学号是否和查询的学号一致
				if (each.getSno().equals(score.getSno())) {
					// 返回该成绩的信息
					return each;
				}
			}
		}
		// 返回null
		return null;
	}

	/**
	 * 获取所有成绩信息
	 * 
	 * @return
	 */
	public List<Score> doGetScoreList() {
		// 首先读取成绩文件信息
		List<Score> list = readScoreFile();
		// 返回成绩信息
		return list;
	}

	/**
	 * 删除成绩信息
	 * 
	 * @param score
	 */
	public boolean doDeleteScore(Score score) {
		// 首先读取成绩文件信息
		List<Score> list = readScoreFile();
		// 删除指定的成绩信息
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getSno().equals(score.getSno())) {
				//删除成绩信息
				list.remove(i);
				// 写入成绩文件信息
				saveScoreFile(list);
				//返回成功
				return true;
			}
		}
		//返回失败
		return false;
	}

	/**
	 * 修改成绩信息
	 * 
	 * @param score
	 */
	public boolean doModifyScore(Score score) {
		// 首先读取成绩文件信息
		List<Score> list = readScoreFile();
		// 修改指定的成绩信息
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getSno().equals(score.getSno())) {
				//修改成绩信息
				list.set(i, score);
				// 写入成绩文件信息
				saveScoreFile(list);
				//返回成功
				return true;
			}
		}
		// 返回失败
		return false;
	}
}
