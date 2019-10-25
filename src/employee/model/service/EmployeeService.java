package employee.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import employee.model.dao.EmployeeDao;
import employee.model.vo.Employee;
import professor.model.vo.Professor;
import student.model.vo.Student;

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

	public int insertEmployee(Employee employee) {
		Connection conn = getConnection();
		int result = edao.insertEmployee(conn, employee);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int confirmEmployee(String empno) {
		Connection conn = getConnection();
		int result = edao.confirmEmployee(conn, empno);
		return result;
	}

	public int getListCount() {
		Connection conn = getConnection();
		int listCount = edao.getListCount(conn);
		close(conn);
		return listCount;
	}

	public ArrayList<Employee> selectList(int startRow, int endRow, int sort) {
		Connection conn = getConnection();
		ArrayList<Employee> list = edao.selectList(conn, startRow, endRow, sort);
		close(conn);
		return list;
	}

	public int updateEmployee(Employee employee) {
		Connection conn = getConnection();
		int result = edao.updateEmployee(conn, employee);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int deleteEmployee(String EMPLOYEE_NO) {
		Connection conn = getConnection();
		int result = edao.deleteStudent(conn, EMPLOYEE_NO);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

	public int getSearchCount(Employee employee) {
		Connection conn = getConnection();
		int listCount = edao.getSearchCount(conn,employee);
		close(conn);
		return listCount;
	}

	public ArrayList<Employee> searchEmployee(int startRow, int endRow, Employee employee, int sort) {
		Connection conn = getConnection();
		ArrayList<Employee> list = edao.searchEmployee(conn, startRow, endRow, employee, sort);
		close(conn);
		return list;
	}

	public int EMyUpdateEmployee(Employee employee) {
		Connection conn = getConnection();
		int result = edao.EMyUpdateEmployee(conn, employee);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

}
