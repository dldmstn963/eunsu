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
		String query="insert into TB_COMMENTS values(COMMENTS_NO.nextval,?,?,SYSDATE,?,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, comments.getUserId());
			pstmt.setString(2, comments.getCommentscontent());
			pstmt.setInt(3, comments.getCommentRef());
			pstmt.setInt(4, comments.getCommentReplyRef());
			pstmt.setInt(5, comments.getCommentLev());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public ArrayList<Comments> selectAll(Connection conn) {
		ArrayList<Comments> list = new ArrayList<Comments>();
		Statement stmt = null;
		ResultSet rset = null;
		String query = "select * from TB_COMMENTS order by CM_NO desc";
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
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
