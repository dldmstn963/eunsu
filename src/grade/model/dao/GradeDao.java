package grade.model.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import grade.model.vo.Grade;

public class GradeDao {

	public int getEnrollListCount(Connection conn, String studentNo) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT COUNT(*)" + 
				" FROM TB_GRADE" + 
				" WHERE STUDENT_NO= ?";
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

	public ArrayList<Grade> gradeCheckList(Connection conn, int startRow, int endRow, String studentNo) {
		ArrayList<Grade> list = new ArrayList<Grade>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * " + 
				" FROM(SELECT ROWNUM RNUM,CLASS_NAME, TERM_NO, POINT, CLASS_TYPE, CLASS_NO,DEPARTMENT_NAME   " + 
				" FROM (SELECT CLASS_NAME, TERM_NO, POINT, CLASS_TYPE, CLASS_NO,DEPARTMENT_NAME  " + 
				" FROM TB_GRADE" + 
				" NATURAL JOIN TB_CLASS" + 
				" NATURAL JOIN TB_DEPARTMENT" + 
				" WHERE STUDENT_NO= ? order by term_no))WHERE RNUM >= ? AND RNUM <= ?";

		try {

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, studentNo);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				Grade grade = new Grade();

				grade.setClassName(rset.getString("CLASS_NAME"));
				grade.setTermNo(rset.getString("TERM_NO"));
				grade.setPoint(rset.getDouble("POINT"));
				grade.setClassType(rset.getString("CLASS_TYPE"));
				grade.setClassNo(rset.getString("CLASS_NO"));
				grade.setDepartmentName(rset.getString("DEPARTMENT_NAME"));
				list.add(grade);
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
