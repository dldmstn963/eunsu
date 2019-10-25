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

	public ArrayList<Comments> selectAll(Connection conn, String noticeNo) {
		ArrayList<Comments> list = new ArrayList<Comments>();
		PreparedStatement stmt = null;
		ResultSet rset = null;
		String query = "select * from TB_COMMENTS where CM_REF = ? order by CM_NO asc";
		try {
			stmt = conn.prepareStatement(query);
			stmt.setString(1, noticeNo);
			
			rset = stmt.executeQuery();
			while (rset.next()) {
				Comments comments = new Comments();
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
}
