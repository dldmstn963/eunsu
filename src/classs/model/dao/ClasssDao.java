package classs.model.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

	public static int num = 0;

	public ArrayList<Classs> selectList(Connection conn, int startRow, int endRow) {
		ArrayList<Classs> list = new ArrayList<Classs>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "SELECT * FROM (SELECT ROWNUM RNUM, CLASS_NO, DEPARTMENT_NO, PREATTENDING_CLASS_NO,CLASS_NAME,CLASS_TYPE FROM (SELECT * FROM tb_class ORDER BY CLASS_NO ASC)) WHERE RNUM >= ?  AND RNUM <= ?";

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

	public ArrayList<Classs> selectList2(Connection conn, int startRow, int endRow) {
		ArrayList<Classs> list = new ArrayList<Classs>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "";

		query = "SELECT * FROM (SELECT ROWNUM RNUM, CLASS_NO, DEPARTMENT_NO, PREATTENDING_CLASS_NO,CLASS_NAME,CLASS_TYPE FROM (SELECT * FROM tb_class ORDER BY CLASS_NO DESC)) WHERE RNUM >= ?  AND RNUM <= ?";

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

	public ArrayList<Classs> searchClasss(Connection conn,int startRow,int endRow, Classs classs) {
		ArrayList<Classs> list = new ArrayList<Classs>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from (select rownum rnum, CLASS_NO ,DEPARTMENT_NO ,PREATTENDING_CLASS_NO, CLASS_NAME ,CLASS_TYPE from (select * from tb_class where class_no like ? and DEPARTMENT_NO like ? and CLASS_NAME like ? and CLASS_TYPE like ?)) where rnum >= ? and rnum <= ?";
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
				System.out.println(classs);
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
			System.out.println("listCount : " + listCount);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return listCount;
	}

}
