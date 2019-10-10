package department.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;

import department.model.dao.DepartmentDao;
import department.model.vo.Department;

public class DepartmentService {
	
	private DepartmentDao ddao = new DepartmentDao();
	public DepartmentService() {};
	
	public int insertDepartment(Department department) {
		Connection conn = getConnection();
		int result = ddao.insertDepartment(conn,department);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

}
