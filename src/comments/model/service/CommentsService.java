package comments.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import comments.model.dao.CommentsDao;
import comments.model.vo.Comments;

public class CommentsService {
	public CommentsService() {}
	CommentsDao cdao = new CommentsDao();
	public int insertComments(Comments comments) {
		Connection conn = getConnection();
		int result = cdao.insertComments(conn, comments);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	public ArrayList<Comments> selectAll(String noticeNo) {
		Connection conn = getConnection();
		ArrayList<Comments> list = cdao.selectAll(conn, noticeNo);
		close(conn);
		return list;
	}
	
}
