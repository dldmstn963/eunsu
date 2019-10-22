package classs.model.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;

import classs.model.vo.Classs;

public class ClasssDao {

	public int insertClass(Connection conn, Classs classs) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "insert all into tb_class values(?,?,?,?,?) into tb_class_professor values(?,?) select * from dual";
		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, classs.getClassNo());
			pstmt.setString(2, classs.getDepartmentNo());
			pstmt.setString(3, classs.getPreatendingClassNo());
			pstmt.setString(4, classs.getClassName());
			pstmt.setString(5, classs.getClassType());
			pstmt.setString(6, classs.getClassNo());
			pstmt.setString(7, classs.getProfessorNo());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int confirmClasss(Connection conn, String classno) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "select CLASS_NO from TB_CLASS where CLASS_NO = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, classno);

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

		String query = "select count(*) from TB_CLASS";

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

	public ArrayList<Classs> selectList(Connection conn, int startRow, int endRow, int sort) {
		ArrayList<Classs> list = new ArrayList<Classs>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "";
		if (sort == 0) {
			query = "SELECT * FROM (SELECT ROWNUM RNUM, CLASS_NO, DEPARTMENT_NO, PREATTENDING_CLASS_NO,CLASS_NAME,CLASS_TYPE FROM (SELECT * FROM tb_class ORDER BY CLASS_NO ASC)) WHERE RNUM >= ?  AND RNUM <= ?";
		} else if (sort == 1) {
			query = "SELECT * FROM (SELECT ROWNUM RNUM, CLASS_NO, DEPARTMENT_NO, PREATTENDING_CLASS_NO,CLASS_NAME,CLASS_TYPE FROM (SELECT * FROM tb_class ORDER BY CLASS_NO DESC)) WHERE RNUM >= ?  AND RNUM <= ?";
		} else if (sort == 2) {
			query = "SELECT * FROM (SELECT ROWNUM RNUM, CLASS_NO, DEPARTMENT_NO, PREATTENDING_CLASS_NO,CLASS_NAME,CLASS_TYPE FROM (SELECT * FROM tb_class ORDER BY CLASS_NAME DESC)) WHERE RNUM >= ?  AND RNUM <= ?";
		} else if (sort == 3) {
			query = "SELECT * FROM (SELECT ROWNUM RNUM, CLASS_NO, DEPARTMENT_NO, PREATTENDING_CLASS_NO,CLASS_NAME,CLASS_TYPE FROM (SELECT * FROM tb_class ORDER BY CLASS_NAME ASC)) WHERE RNUM >= ?  AND RNUM <= ?";
		} else if (sort == 4) {
			query = "SELECT * FROM (SELECT ROWNUM RNUM, CLASS_NO, DEPARTMENT_NO, PREATTENDING_CLASS_NO,CLASS_NAME,CLASS_TYPE FROM (SELECT * FROM tb_class ORDER BY CLASS_TYPE DESC)) WHERE RNUM >= ?  AND RNUM <= ?";
		} else if (sort == 5) {
			query = "SELECT * FROM (SELECT ROWNUM RNUM, CLASS_NO, DEPARTMENT_NO, PREATTENDING_CLASS_NO,CLASS_NAME,CLASS_TYPE FROM (SELECT * FROM tb_class ORDER BY CLASS_TYPE ASC)) WHERE RNUM >= ?  AND RNUM <= ?";
		} else if (sort == 6) {
			query = "SELECT * FROM (SELECT ROWNUM RNUM, CLASS_NO, DEPARTMENT_NO, PREATTENDING_CLASS_NO,CLASS_NAME,CLASS_TYPE FROM (SELECT * FROM tb_class ORDER BY DEPARTMENT_NO DESC)) WHERE RNUM >= ?  AND RNUM <= ?";
		} else if (sort == 7) {
			query = "SELECT * FROM (SELECT ROWNUM RNUM, CLASS_NO, DEPARTMENT_NO, PREATTENDING_CLASS_NO,CLASS_NAME,CLASS_TYPE FROM (SELECT * FROM tb_class ORDER BY DEPARTMENT_NO ASC)) WHERE RNUM >= ?  AND RNUM <= ?";
		} else if (sort == 8) {
			query = "SELECT * FROM (SELECT ROWNUM RNUM, CLASS_NO, DEPARTMENT_NO, PREATTENDING_CLASS_NO,CLASS_NAME,CLASS_TYPE FROM (SELECT * FROM tb_class ORDER BY PREATTENDING_CLASS_NO DESC)) WHERE RNUM >= ?  AND RNUM <= ?";
		} else if (sort == 9) {
			query = "SELECT * FROM (SELECT ROWNUM RNUM, CLASS_NO, DEPARTMENT_NO, PREATTENDING_CLASS_NO,CLASS_NAME,CLASS_TYPE FROM (SELECT * FROM tb_class ORDER BY PREATTENDING_CLASS_NO ASC)) WHERE RNUM >= ?  AND RNUM <= ?";
		}
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Classs classs = new Classs();

				classs.setClassNo(rset.getString("CLASS_NO"));
				classs.setDepartmentNo(rset.getString("DEPARTMENT_NO"));
				classs.setClassName(rset.getString("CLASS_NAME"));
				classs.setClassType(rset.getString("CLASS_TYPE"));
				classs.setPreatendingClassNo(rset.getString("PREATTENDING_CLASS_NO"));
				list.add(classs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int updateClasss(Connection conn, Classs classs) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE TB_CLASS SET DEPARTMENT_NO=?,PREATTENDING_CLASS_NO= ?,CLASS_NAME = ?,CLASS_TYPE=? WHERE CLASS_NO=?";
		try {
			pstmt = conn.prepareStatement(query);
			String pre = "";
			if (classs.getPreatendingClassNo().equals("없음")) {
				pre = null;
			} else {
				pre = classs.getPreatendingClassNo();
			}
			pstmt.setString(1, classs.getDepartmentNo());
			pstmt.setString(2, pre);
			pstmt.setString(3, classs.getClassName());
			pstmt.setString(4, classs.getClassType());
			pstmt.setString(5, classs.getClassNo());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteClasss(Connection conn, String classno) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "DELETE FROM TB_CLASS WHERE CLASS_NO = ?";
		try {
			pstmt = conn.prepareStatement("ALTER TABLE TB_CLASS_PROFESSOR DROP CONSTRAINTS FK_CLASS_PROFESSOR_02");
			result = pstmt.executeUpdate();
			pstmt = conn.prepareStatement(
					"ALTER TABLE TB_CLASS_PROFESSOR ADD CONSTRAINTS FK_CLASS_PROFESSOR_02 FOREIGN KEY(CLASS_NO) REFERENCES TB_CLASS ON DELETE CASCADE");
			result = pstmt.executeUpdate();
			pstmt = conn.prepareStatement("ALTER TABLE TB_GRADE DROP CONSTRAINTS FK_GRADE_02");
			result = pstmt.executeUpdate();
			pstmt = conn.prepareStatement(
					"ALTER TABLE TB_GRADE ADD CONSTRAINTS FK_GRADE_02 FOREIGN KEY(CLASS_NO) REFERENCES TB_CLASS ON DELETE CASCADE");
			result = pstmt.executeUpdate();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, classno);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public ArrayList<Classs> searchClasss(Connection conn, int startRow, int endRow, Classs classs, int sort) {
		ArrayList<Classs> list = new ArrayList<Classs>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "";

		if (sort == 0) {
			query = "select * from (select rownum rnum, CLASS_NO ,DEPARTMENT_NO ,PREATTENDING_CLASS_NO, CLASS_NAME ,CLASS_TYPE from (select * from tb_class where class_no like ? and DEPARTMENT_NO like ? and CLASS_NAME like ? and CLASS_TYPE like ? ORDER BY CLASS_NO ASC)) where rnum >= ? and rnum <= ?";
		} else if (sort == 1) {
			query = "select * from (select rownum rnum, CLASS_NO ,DEPARTMENT_NO ,PREATTENDING_CLASS_NO, CLASS_NAME ,CLASS_TYPE from (select * from tb_class where class_no like ? and DEPARTMENT_NO like ? and CLASS_NAME like ? and CLASS_TYPE like ? ORDER BY CLASS_NO DESC)) where rnum >= ? and rnum <= ?";
		} else if (sort == 2) {
			query = "select * from (select rownum rnum, CLASS_NO ,DEPARTMENT_NO ,PREATTENDING_CLASS_NO, CLASS_NAME ,CLASS_TYPE from (select * from tb_class where class_no like ? and DEPARTMENT_NO like ? and CLASS_NAME like ? and CLASS_TYPE like ? order by CLASS_NAME DESC)) where rnum >= ? and rnum <= ?";
		} else if (sort == 3) {
			query = "select * from (select rownum rnum, CLASS_NO ,DEPARTMENT_NO ,PREATTENDING_CLASS_NO, CLASS_NAME ,CLASS_TYPE from (select * from tb_class where class_no like ? and DEPARTMENT_NO like ? and CLASS_NAME like ? and CLASS_TYPE like ? order by CLASS_NAME ASC)) where rnum >= ? and rnum <= ?";
		} else if (sort == 4) {
			query = "select * from (select rownum rnum, CLASS_NO ,DEPARTMENT_NO ,PREATTENDING_CLASS_NO, CLASS_NAME ,CLASS_TYPE from (select * from tb_class where class_no like ? and DEPARTMENT_NO like ? and CLASS_NAME like ? and CLASS_TYPE like ? order by CLASS_TYPE DESC)) where rnum >= ? and rnum <= ?";
		} else if (sort == 5) {
			query = "select * from (select rownum rnum, CLASS_NO ,DEPARTMENT_NO ,PREATTENDING_CLASS_NO, CLASS_NAME ,CLASS_TYPE from (select * from tb_class where class_no like ? and DEPARTMENT_NO like ? and CLASS_NAME like ? and CLASS_TYPE like ? order by CLASS_TYPE ASC)) where rnum >= ? and rnum <= ?";
		} else if (sort == 6) {
			query = "select * from (select rownum rnum, CLASS_NO ,DEPARTMENT_NO ,PREATTENDING_CLASS_NO, CLASS_NAME ,CLASS_TYPE from (select * from tb_class where class_no like ? and DEPARTMENT_NO like ? and CLASS_NAME like ? and CLASS_TYPE like ? order by DEPARTMENT_NO DESC)) where rnum >= ? and rnum <= ?";
		} else if (sort == 7) {
			query = "select * from (select rownum rnum, CLASS_NO ,DEPARTMENT_NO ,PREATTENDING_CLASS_NO, CLASS_NAME ,CLASS_TYPE from (select * from tb_class where class_no like ? and DEPARTMENT_NO like ? and CLASS_NAME like ? and CLASS_TYPE like ? order by DEPARTMENT_NO ASC)) where rnum >= ? and rnum <= ?";
		} else if (sort == 8) {
			query = "select * from (select rownum rnum, CLASS_NO ,DEPARTMENT_NO ,PREATTENDING_CLASS_NO, CLASS_NAME ,CLASS_TYPE from (select * from tb_class where class_no like ? and DEPARTMENT_NO like ? and CLASS_NAME like ? and CLASS_TYPE like ? order by PREATTENDING_CLASS_NO DESC)) where rnum >= ? and rnum <= ?";
		} else if (sort == 9) {
			query = "select * from (select rownum rnum, CLASS_NO ,DEPARTMENT_NO ,PREATTENDING_CLASS_NO, CLASS_NAME ,CLASS_TYPE from (select * from tb_class where class_no like ? and DEPARTMENT_NO like ? and CLASS_NAME like ? and CLASS_TYPE like ? order by PREATTENDING_CLASS_NO ASC)) where rnum >= ? and rnum <= ?";
		}

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + classs.getClassNo() + "%");
			pstmt.setString(2, "%" + classs.getDepartmentNo() + "%");
			pstmt.setString(3, "%" + classs.getClassName() + "%");
			pstmt.setString(4, "%" + classs.getClassType() + "%");
			pstmt.setInt(5, startRow);
			pstmt.setInt(6, endRow);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				Classs classslist = new Classs();
				classslist.setClassNo(rset.getString("CLASS_NO"));
				classslist.setDepartmentNo(rset.getString("DEPARTMENT_NO"));
				classslist.setClassName(rset.getString("CLASS_NAME"));
				classslist.setClassType(rset.getString("CLASS_TYPE"));
				classslist.setPreatendingClassNo(rset.getString("PREATTENDING_CLASS_NO"));
				list.add(classslist);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int getSearchCount(Connection conn, Classs classs) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select count(*) from tb_class where class_no like ? and DEPARTMENT_NO like ? and CLASS_NAME like ? and CLASS_TYPE like ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + classs.getClassNo() + "%");
			pstmt.setString(2, "%" + classs.getDepartmentNo() + "%");
			pstmt.setString(3, "%" + classs.getClassName() + "%");
			pstmt.setString(4, "%" + classs.getClassType() + "%");

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

	public int classOpen(Connection conn, String term) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "INSERT INTO TB_COURSE(CLASS_NO,TERM_NO,CAPACITY) SELECT CLASS_NO,?,CAPACITY FROM TB_CLASS NATURAL JOIN TB_DEPARTMENT";
		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, term);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int getEnrollListCount(Connection conn, String studentNo) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select count(*) from TB_CLASS natural join TB_DEPARTMENT natural join TB_STUDENT where student_no = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, studentNo);
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

	public ArrayList<Classs> EnrollClasss(Connection conn, int startRow, int endRow, String studentNo) {
		ArrayList<Classs> list = new ArrayList<Classs>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT* FROM"
				+ " (SELECT ROWNUM RNUM,CLASS_NO,CLASS_NAME,DEPARTMENT_NO,STUDENT_NO,CLASS_TYPE,PREATTENDING_CLASS_NO FROM(SELECT CLASS_NO,CLASS_NAME,DEPARTMENT_NO,STUDENT_NO,CLASS_TYPE,PREATTENDING_CLASS_NO "
				+ " FROM PROJECT.TB_CLASS" + " LEFT JOIN PROJECT.TB_STUDENT USING(DEPARTMENT_NO) "
				+ " WHERE STUDENT_NO = ?))where rnum >= ? and rnum <= ?";

		try {

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, studentNo);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				Classs classs = new Classs();

				classs.setClassNo(rset.getString("CLASS_NO"));
				classs.setDepartmentNo(rset.getString("DEPARTMENT_NO"));
				classs.setClassName(rset.getString("CLASS_NAME"));
				classs.setClassType(rset.getString("CLASS_TYPE"));
				classs.setPreatendingClassNo(rset.getString("PREATTENDING_CLASS_NO"));
				list.add(classs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int enrollClasss(Connection conn, String classNo, String studentNo, String termNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int capacity = 0;
		int current = 0;

		try {
			pstmt = conn
					.prepareStatement("SELECT CAPACITY, CURRENT_STUDENT" + " FROM TB_COURSE" + " WHERE CLASS_NO= ?");
			pstmt.setString(1, classNo);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				capacity = rset.getInt("CAPACITY");
				current = rset.getInt("CURRENT_STUDENT");
			}
			if (capacity > current) {
				pstmt = conn.prepareStatement("INSERT INTO TB_GRADE VALUES(?,?,?,0)");
				pstmt.setString(1, termNo);
				pstmt.setString(2, studentNo);
				pstmt.setString(3, classNo);
				result = pstmt.executeUpdate();
				
				pstmt = conn.prepareStatement("UPDATE TB_COURSE" + " SET CURRENT_STUDENT = ("
						+ " SELECT CURRENT_STUDENT+1" + " FROM TB_COURSE" + " WHERE CLASS_NO = ? AND TERM_NO = ?"
						+ " ) WHERE CLASS_NO = ? AND TERM_NO = ?");
				pstmt.setString(1, classNo);
				pstmt.setString(2, termNo);
				pstmt.setString(3, classNo);
				pstmt.setString(4, termNo);
				result = pstmt.executeUpdate();

				
			} else {
				result = 0;
			}
		}catch(SQLIntegrityConstraintViolationException e) {
			result = -100;
		}
		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

}
