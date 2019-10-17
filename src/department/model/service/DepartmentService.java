package department.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import classs.model.vo.Classs;
import department.model.dao.DepartmentDao;
import department.model.vo.Department;

public class DepartmentService {

	private DepartmentDao ddao = new DepartmentDao();

	public DepartmentService() {
	};

	public int insertDepartment(Department department) {
		Connection conn = getConnection();
		int result = ddao.insertDepartment(conn, department);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int confirmDepartment(String departno) {
		Connection conn = getConnection();
		int result = ddao.confirmDepartment(conn, departno);
		return result;
	}

	public int getListCount() {
		Connection conn = getConnection();
		int listCount = ddao.getListCount(conn);
		close(conn);
		return listCount;
	}

	public ArrayList<Department> selectList(int startRow, int endRow, int sort) {
		Connection conn = getConnection();
		ArrayList<Department> list = ddao.selectList(conn, startRow, endRow, sort);
		close(conn);
		return list;
	}

	public int updateClass(Department department) {
		Connection conn = getConnection();
		int result = ddao.updateClasss(conn, department);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int deleteDepartment(String departNo) {
		Connection conn = getConnection();
		int result = ddao.deleteDepartment(conn, departNo);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

	public int getSearchCount(Department department) {
		Connection conn = getConnection();
		int listCount = ddao.getSearchCount(conn, department);
		close(conn);
		return listCount;
	}

	public ArrayList<Department> searchDepartment(int startRow, int endRow, Department department, int sort) {
		Connection conn = getConnection();
		ArrayList<Department> list = ddao.searchClasss(conn, startRow, endRow, department, sort);
		close(conn);
		return list;
	}

}
