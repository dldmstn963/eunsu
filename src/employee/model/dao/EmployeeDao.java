package employee.model.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import employee.model.vo.Employee;
import professor.model.vo.Professor;


public class EmployeeDao {
	public EmployeeDao() {
	};

	public Employee loginCheck(Connection conn, String no, String pass) {
		Employee employee = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from TB_EMPLOYEE NATURAL join TB_EMPDEPART where EMPLOYEE_NO = ? and EMPLOYEE_PASSWORD = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, no);
			pstmt.setString(2, pass);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				employee = new Employee();
				
				employee.setEmployeeNo(rset.getString("EMPLOYEE_NO"));
				employee.setEmployeeName(rset.getString("EMPLOYEE_NAME"));
				employee.setEmployeeSSN(rset.getString("EMPLOYEE_SSN"));
				employee.setEmployeeAddress(rset.getString("EMPLOYEE_ADDRESS"));
				employee.setEmpdepart_no(rset.getString("EMPDEPART_NO"));
				employee.setEmployeePassword(rset.getString("EMPLOYEE_PASSWORD"));
				employee.setEmployeeimage(rset.getString("EMPLOYEE_IMAGE"));
				employee.setEmpDepartment(rset.getString("EMPDEPART_NAME"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		
		return employee;
	}

	public Employee selectOne(Connection conn, String userId) {
	}

	public ArrayList<Employee> selectList(Connection conn) {
	}

	public int insertEmployee(Connection conn, Employee employee) {
	}

	public int updateEmployee(Connection conn, Employee employee) {
	}

	public int deleteEmployee(Connection conn, String userId) {
	}
}
