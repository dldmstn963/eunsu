package employee.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import employee.model.dao.EmployeeDao;
import employee.model.vo.Employee;
import professor.model.vo.Professor;

public class EmployeeService {
	private EmployeeDao edao = new EmployeeDao();

	public EmployeeService() {
	}

	public Employee loginCheck(String no, String pass) {
		Connection conn = getConnection();
		Employee employee = edao.loginCheck(conn, no, pass);
		close(conn);
		return employee;
	}

	public Employee selectProfessor(String userId) {
	}

	public ArrayList<Employee> selectList() {
	}

	public int insertEmployee(Employee employee) {
	}

	public int updateEmployee(Employee employee) {
	}

	public int deleteEmployee(String userId) {
	}

}
