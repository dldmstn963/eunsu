package classs.model.service;

import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import static common.JDBCTemplate.*;
import classs.model.dao.ClasssDao;
import classs.model.vo.Classs;

public class ClasssService {

	private ClasssDao cdao = new ClasssDao();

	public ClasssService() {
	};

	public int insertClass(Classs classs) {
		Connection conn = getConnection();
		int result = cdao.insertClass(conn, classs);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;

	}

}
