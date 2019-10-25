package comments.model.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import comments.model.vo.Comments;
import notice.model.vo.Notice;

public class CommentsDao {
	public CommentsDao() {
		
	}

	public int insertComments(Connection conn, Comments comments) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query="insert into TB_COMMENTS values(COMMENTS_NO.nextval,?,?,SYSDATE,?,COMMENTS_NO.currval,?)";
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, comments.getUserId());
			pstmt.setString(2, comments.getCommentscontent());
			pstmt.setInt(3, comments.getCommentRef());
			//pstmt.setInt(4, comments.getCommentReplyRef());
			pstmt.setInt(4, comments.getCommentLev());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public ArrayList<Comments> selectAll(Connection conn, int noticeNo) {
		ArrayList<Comments> list = new ArrayList<Comments>();
		PreparedStatement stmt = null;
		ResultSet rset = null;
		String query = "select * from TB_COMMENTS where CM_REF = ? order by CM_NO asc";
		try {
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, noticeNo);
			
			rset = stmt.executeQuery();
			while (rset.next()) {
				Comments comments = new Comments();
				comments.setCommentsNo(rset.getInt("CM_NO"));
				comments.setCommentscontent(rset.getString("CM_CONTENT"));
				comments.setUserId(rset.getString("USER_ID"));
				comments.setCommentsdate(rset.getDate("CM_DATE"));
				list.add(comments);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}

		return list;
	}

	public int deleteComment(Connection conn, int commentNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "delete from TB_COMMENTS where CM_NO = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, commentNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int UpdateComent(Connection conn, String coNo, String comments) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "update TB_COMMENTS set CM_CONTENT = ? where CM_NO = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, comments);
			pstmt.setString(2, coNo);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}
}
