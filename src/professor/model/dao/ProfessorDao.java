package professor.model.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import professor.model.vo.Professor;
import student.model.vo.Student;

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

	public int getListCount(Connection conn) {
		int listCount = 0;

		Statement stmt = null;
		ResultSet rset = null;

		String query = "select count(*) from TB_PROFESSOR";
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			if (rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return listCount;
	}

	public ArrayList<Professor> selectList(Connection conn, int startRow, int endRow, int sort) {
		ArrayList<Professor> list = new ArrayList<Professor>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "";
		
		if (sort == 0) {
			query = "select * from(select rownum rnum, PROFESSOR_NO,PROFESSOR_NAME,PROFESSOR_SSN,PROFESSOR_ADDRESS,DEPARTMENT_NO,PROFESSOR_PASSWORD,PROFESSOR_IMAGE from(select * from TB_PROFESSOR order by PROFESSOR_NO desc)) where rnum >= ? and rnum <= ?";
		} else if (sort == 1) {
			query = "select * from(select rownum rnum, PROFESSOR_NO,PROFESSOR_NAME,PROFESSOR_SSN,PROFESSOR_ADDRESS,DEPARTMENT_NO,PROFESSOR_PASSWORD,PROFESSOR_IMAGE from(select * from TB_PROFESSOR order by PROFESSOR_NO asc)) where rnum >= ? and rnum <= ?";
		} else if (sort == 2) {
			query = "select * from(select rownum rnum, PROFESSOR_NO,PROFESSOR_NAME,PROFESSOR_SSN,PROFESSOR_ADDRESS,DEPARTMENT_NO,PROFESSOR_PASSWORD,PROFESSOR_IMAGE from(select * from TB_PROFESSOR order by PROFESSOR_NAME desc)) where rnum >= ? and rnum <= ?";
		} else if (sort == 3) {
			query = "select * from(select rownum rnum, PROFESSOR_NO,PROFESSOR_NAME,PROFESSOR_SSN,PROFESSOR_ADDRESS,DEPARTMENT_NO,PROFESSOR_PASSWORD,PROFESSOR_IMAGE from(select * from TB_PROFESSOR order by PROFESSOR_NAME asc)) where rnum >= ? and rnum <= ?";
		} else if (sort == 4) {
			query = "select * from(select rownum rnum, PROFESSOR_NO,PROFESSOR_NAME,PROFESSOR_SSN,PROFESSOR_ADDRESS,DEPARTMENT_NO,PROFESSOR_PASSWORD,PROFESSOR_IMAGE from(select * from TB_PROFESSOR order by DEPARTMENT_NO desc)) where rnum >= ? and rnum <= ?";
		} else if (sort == 5) {
			query = "select * from(select rownum rnum, PROFESSOR_NO,PROFESSOR_NAME,PROFESSOR_SSN,PROFESSOR_ADDRESS,DEPARTMENT_NO,PROFESSOR_PASSWORD,PROFESSOR_IMAGE from(select * from TB_PROFESSOR order by DEPARTMENT_NO asc)) where rnum >= ? and rnum <= ?";
		} 
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				Professor professor = new Professor();

				professor.setProfessorNo(rset.getString("PROFESSOR_NO"));
				professor.setProfessorName(rset.getString("PROFESSOR_NAME"));
				professor.setProfessorSSN(rset.getString("PROFESSOR_SSN"));
				professor.setProfessorAddress(rset.getString("PROFESSOR_ADDRESS"));
				professor.setDepartmentNo(rset.getString("DEPARTMENT_NO"));
				professor.setProfessorPassword(rset.getString("PROFESSOR_PASSWORD"));
				professor.setProfessorImage(rset.getString("PROFESSOR_IMAGE"));
				list.add(professor);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int updateProfessor(Connection conn, Professor professor) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE TB_PROFESSOR SET DEPARTMENT_NO= ? WHERE PROFESSOR_NO=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(2, professor.getProfessorNo());
			pstmt.setString(1, professor.getDepartmentNo());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteProfessor(Connection conn, String PROFESSOR_NO) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "DELETE FROM TB_PROFESSOR WHERE PROFESSOR_NO = ?";
		try {
			pstmt = conn.prepareStatement("ALTER TABLE TB_CLASS_PROFESSOR DROP CONSTRAINTS FK_CLASS_PROFESSOR_01");
			result = pstmt.executeUpdate();
			pstmt = conn.prepareStatement(
					"ALTER TABLE TB_CLASS_PROFESSOR ADD CONSTRAINTS FK_CLASS_PROFESSOR_01 FOREIGN KEY(PROFESSOR_NO) REFERENCES TB_PROFESSOR ON DELETE CASCADE");
			result = pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, PROFESSOR_NO);
			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int getSearchCount(Connection conn, Professor professor) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select count(*) from TB_PROFESSOR where PROFESSOR_NO like ? and PROFESSOR_NAME like ? and DEPARTMENT_NO like ? ";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + professor.getProfessorNo() + "%");
			pstmt.setString(2, "%" + professor.getProfessorName() + "%");
			pstmt.setString(3, "%" + professor.getDepartmentNo() + "%");

			rset = pstmt.executeQuery();
			if (rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			listCount = 0;
		} finally {
			close(rset);
			close(pstmt);
		}
		return listCount;
	}

	public ArrayList<Professor> searchStudent(Connection conn, int startRow, int endRow, Professor professor,
			int sort) {
		ArrayList<Professor> list = new ArrayList<Professor>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "";
		if (sort == 0) {
			query = "select * from(select rownum rnum, PROFESSOR_NO,PROFESSOR_NAME,PROFESSOR_SSN,PROFESSOR_ADDRESS,DEPARTMENT_NO,PROFESSOR_PASSWORD,PROFESSOR_IMAGE from(select * from TB_PROFESSOR where PROFESSOR_NO like ? and PROFESSOR_NAME like ? and DEPARTMENT_NO like ? order by PROFESSOR_NO desc)) where rnum >= ? and rnum <= ?";
		} else if (sort == 1) {
			query = "select * from(select rownum rnum, PROFESSOR_NO,PROFESSOR_NAME,PROFESSOR_SSN,PROFESSOR_ADDRESS,DEPARTMENT_NO,PROFESSOR_PASSWORD,PROFESSOR_IMAGE from(select * from TB_PROFESSOR where PROFESSOR_NO like ? and PROFESSOR_NAME like ? and DEPARTMENT_NO like ? order by PROFESSOR_NO asc)) where rnum >= ? and rnum <= ?";
		} else if (sort == 2) {
			query = "select * from(select rownum rnum, PROFESSOR_NO,PROFESSOR_NAME,PROFESSOR_SSN,PROFESSOR_ADDRESS,DEPARTMENT_NO,PROFESSOR_PASSWORD,PROFESSOR_IMAGE from(select * from TB_PROFESSOR where PROFESSOR_NO like ? and PROFESSOR_NAME like ? and DEPARTMENT_NO like ? order by PROFESSOR_NAME desc)) where rnum >= ? and rnum <= ?";
		} else if (sort == 3) {
			query = "select * from(select rownum rnum, PROFESSOR_NO,PROFESSOR_NAME,PROFESSOR_SSN,PROFESSOR_ADDRESS,DEPARTMENT_NO,PROFESSOR_PASSWORD,PROFESSOR_IMAGE from(select * from TB_PROFESSOR where PROFESSOR_NO like ? and PROFESSOR_NAME like ? and DEPARTMENT_NO like ? order by PROFESSOR_NAME asc)) where rnum >= ? and rnum <= ?";
		} else if (sort == 4) {
			query = "select * from(select rownum rnum, PROFESSOR_NO,PROFESSOR_NAME,PROFESSOR_SSN,PROFESSOR_ADDRESS,DEPARTMENT_NO,PROFESSOR_PASSWORD,PROFESSOR_IMAGE from(select * from TB_PROFESSOR where PROFESSOR_NO like ? and PROFESSOR_NAME like ? and DEPARTMENT_NO like ? order by DEPARTMENT_NO desc)) where rnum >= ? and rnum <= ?";
		} else if (sort == 5) {
			query = "select * from(select rownum rnum, PROFESSOR_NO,PROFESSOR_NAME,PROFESSOR_SSN,PROFESSOR_ADDRESS,DEPARTMENT_NO,PROFESSOR_PASSWORD,PROFESSOR_IMAGE from(select * from TB_PROFESSOR where PROFESSOR_NO like ? and PROFESSOR_NAME like ? and DEPARTMENT_NO like ? order by DEPARTMENT_NO asc)) where rnum >= ? and rnum <= ?";
		} 
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + professor.getProfessorNo() + "%");
			pstmt.setString(2, "%" + professor.getProfessorName() + "%");
			pstmt.setString(3, "%" + professor.getDepartmentNo() + "%");
			pstmt.setInt(4, startRow);
			pstmt.setInt(5, endRow);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				Professor professor1 = new Professor();

				professor1.setProfessorNo(rset.getString("PROFESSOR_NO"));
				professor1.setProfessorName(rset.getString("PROFESSOR_NAME"));
				professor1.setDepartmentNo(rset.getString("DEPARTMENT_NO"));
				list.add(professor1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
}
