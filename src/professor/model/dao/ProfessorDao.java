package professor.model.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import professor.model.vo.Professor;

public class ProfessorDao {
	public ProfessorDao() {
	};

	public Professor loginCheck(Connection conn, String no, String pass) {
		Professor professor = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from TB_PROFESSOR NATURAL join tb_department where PROFESSOR_NO = ? and PROFESSOR_PASSWORD = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, no);
			pstmt.setString(2, pass);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				professor = new Professor();

				professor.setProfessorNo(rset.getString("PROFESSOR_NO"));
				professor.setProfessorName(rset.getString("PROFESSOR_NAME"));
				professor.setProfessorSSN(rset.getString("PROFESSOR_SSN"));
				professor.setProfessorAddress(rset.getString("PROFESSOR_ADDRESS"));
				professor.setDepartmentNo(rset.getString("DEPARTMENT_NO"));
				professor.setProfessorPassword(rset.getString("PROFESSOR_PASSWORD"));
				professor.setProfessorImage(rset.getString("PROFESSOR_IMAGE"));
				professor.setDepartmentName(rset.getString("DEPARTMENT_NAME"));
				professor.setCategory(rset.getString("CATEGORY"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return professor;
	}

	public Professor selectOne(Connection conn, String userId) {
	}

	public ArrayList<Professor> selectList(Connection conn) {
	}

	public int insertProfessor(Connection conn, Professor professor) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "insert into tb_professor values(?,?,?,?,?,?,?)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, professor.getProfessorNo());
			pstmt.setString(2, professor.getProfessorName());
			pstmt.setString(3, professor.getProfessorSSN());
			pstmt.setString(4, professor.getProfessorAddress());
			pstmt.setString(5, professor.getDepartmentNo());
			pstmt.setString(6, professor.getProfessorPassword());
			pstmt.setString(7, professor.getProfessorImage());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;

	}

	public int updateProfessor(Connection conn, Professor professor) {
	}

	public int deleteProfessor(Connection conn, String userId) {
	}

	public int confirmProfessor(Connection conn, String professorno) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "select PROFESSOR_NO from TB_PROFESSOR where PROFESSOR_NO = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, professorno);

			result = pstmt.executeUpdate();
			System.out.println(result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
}
