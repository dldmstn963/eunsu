package professor.model.dao;

import java.sql.Connection;
import java.util.ArrayList;

import professor.model.vo.Professor;



public class ProfessorDao {
	public ProfessorDao() {
	};

	public Professor loginCheck(Connection conn, String userId, String userPwd) {
	}

	public Professor selectOne(Connection conn, String userId) {
	}

	public ArrayList<Professor> selectList(Connection conn) {
	}

	public int insertProfessor(Connection conn, Professor professor) {
	}

	public int updateProfessor(Connection conn, Professor professor) {
	}

	public int deleteProfessor(Connection conn, String userId) {
	}
}
