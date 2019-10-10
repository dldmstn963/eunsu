package professor.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import professor.model.dao.ProfessorDao;
import professor.model.vo.Professor;
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

	public Professor selectProfessor(String userId) {
	}

	public ArrayList<Professor> selectList() {
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

	public int updateProfessor(Professor professor) {
	}

	public int deleteProfessor(String userId) {
	}

}
