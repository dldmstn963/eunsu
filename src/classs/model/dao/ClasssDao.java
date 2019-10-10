package classs.model.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

}
