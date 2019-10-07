package employee.model.dao;

import java.sql.Connection;
import java.util.ArrayList;

import employee.model.vo.Employee;


public class EmployeeDao {
	public EmployeeDao() {
	};

	public Employee loginCheck(Connection conn, String userId, String userPwd) {
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
