package classs.model.service;

import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import static common.JDBCTemplate.*;
import classs.model.dao.ClasssDao;
import classs.model.vo.Classs;

public class ClasssService {

	private ClasssDao cdao = new ClasssDao();

	public ClasssService() {
	};

	public int insertClass(Classs classs) {
		Connection conn = getConnection();
		int result = cdao.insertClass(conn, classs);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;

	}

	public int confirmClasss(String classno) {
		Connection conn = getConnection();
		int result = cdao.confirmClasss(conn, classno);
		return result;
	}

	public int getListCount() {
		Connection conn = getConnection();
		int listCount = cdao.getListCount(conn);
		close(conn);
		return listCount;
	}

	public ArrayList<Classs> selectList(int startRow, int endRow) {
		Connection conn = getConnection();
		ArrayList<Classs> list = cdao.selectList(conn, startRow, endRow);
		close(conn);
		return list;
	}

	public ArrayList<Classs> selectList2(int startRow, int endRow) {
		Connection conn = getConnection();
		ArrayList<Classs> list = cdao.selectList2(conn, startRow, endRow);
		close(conn);
		return list;
	}

}
