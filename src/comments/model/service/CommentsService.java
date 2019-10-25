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
	public ArrayList<Comments> selectAll(int noticeNo) {
		Connection conn = getConnection();
		ArrayList<Comments> list = cdao.selectAll(conn, noticeNo);
		close(conn);
		return list;
	}
	public int deleteComment(int commentNo) {
		Connection conn = getConnection();
		int result = cdao.deleteComment(conn, commentNo);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	public int UpdateComent(String coNo, String comments) {
		Connection conn = getConnection();
		int result = cdao.UpdateComent(conn, coNo, comments);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	public int CommentsReply(Comments comments) {
		Connection conn = getConnection();
		int result = cdao.CommentsReply(conn, comments);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	public int deleteReply(int commentNo) {
		Connection conn = getConnection();
		int result = cdao.deleteReply(conn, commentNo);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
}
