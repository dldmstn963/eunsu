package department.model.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import department.model.vo.Department;

public class DepartmentDao {

	public int insertDepartment(Connection conn, Department department) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "insert into TB_DEPARTMENT values(?,?,?,'Y',?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, department.getDepartmentNo());
			pstmt.setString(2, department.getDepartmentName());
			pstmt.setString(3, department.getCategory());
			pstmt.setInt(4, department.getCapacity());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

}
