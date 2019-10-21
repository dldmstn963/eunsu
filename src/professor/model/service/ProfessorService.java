package professor.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import professor.model.dao.ProfessorDao;
import professor.model.vo.Professor;
import student.model.vo.Student;

import static common.JDBCTemplate.*;

public class ProfessorService {
	private ProfessorDao pdao = new ProfessorDao();

	public ProfessorService() {
	}

	public Professor loginCheck(String no, String pass) {
		Connection conn = getConnection();
		Professor professor = pdao.loginCheck(conn, no, pass);
		close(conn);
		return professor;

	}

	public int insertProfessor(Professor professor) {
		Connection conn = getConnection();
		int result = pdao.insertProfessor(conn, professor);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;

	}

	public int confirmProfessor(String professorno) {
		Connection conn = getConnection();
		int result = pdao.confirmProfessor(conn, professorno);
		return result;
	}

	public int getListCount() {
		Connection conn = getConnection();
		int listCount = pdao.getListCount(conn);
		close(conn);
		return listCount;
	}

	public ArrayList<Professor> selectList(int startRow, int endRow, int sort) {
		Connection conn = getConnection();
		ArrayList<Professor> list = pdao.selectList(conn, startRow, endRow, sort);
		close(conn);
		return list;
	}

	public int updateProfessor(Professor professor) {
		Connection conn = getConnection();
		int result = pdao.updateProfessor(conn, professor);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int deleteProfessor(String PROFESSOR_NO) {
		Connection conn = getConnection();
		int result = pdao.deleteProfessor(conn, PROFESSOR_NO);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int getSearchCount(Professor professor) {
		Connection conn = getConnection();
		int listCount = pdao.getSearchCount(conn,professor);
		close(conn);
		return listCount;
	}

	public ArrayList<Professor> searchProfessor(int startRow, int endRow, Professor professor, int sort) {
		Connection conn = getConnection();
		ArrayList<Professor> list = pdao.searchStudent(conn, startRow, endRow, professor, sort);
		close(conn);
		return list;
	}

	public int PMyUpdateProfessor(Professor professor) {
		Connection conn = getConnection();
		int result = pdao.PMyUpdateEmployee(conn, professor);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

}
