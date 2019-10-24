package notice.model.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import employee.model.vo.Employee;
import notice.model.vo.Notice;

public class NoticeDao {
	public NoticeDao() {}

	public int getListCount(Connection conn) {
		int listCount = 0;

		Statement stmt = null;
		ResultSet rset = null;

		String query = "select count(*) from TB_NOTICE";
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			if (rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return listCount;
	}

	public ArrayList<Notice> selectList(Connection conn, int startRow, int endRow) {
		ArrayList<Notice> list = new ArrayList<Notice>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM(SELECT ROWNUM RNUM,NOTICE_NO" + 
				",EMPLOYEE_NO" + 
				",NOTICE_TITLE" + 
				",NOTICE_DATE" + 
				",NOTICE_VIEWS" + 
				",NOTICE_CONTENT" + 
				",NOTICE_ORIFILE" + 
				",NOTICE_REFILE FROM" + 
				"(SELECT * FROM TB_NOTICE ORDER BY NOTICE_NO DESC))WHERE RNUM >= ? AND RNUM <= ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				Notice notice = new Notice();
				
				notice.setNoticeNo(rset.getInt("NOTICE_NO"));
				notice.setEmployeeNo(rset.getString("EMPLOYEE_NO"));
				notice.setNoticeTitle(rset.getString("NOTICE_TITLE"));
				notice.setNoticeDate(rset.getDate("NOTICE_DATE"));
				notice.setViews(rset.getInt("NOTICE_VIEWS"));
				notice.setNoticecontent(rset.getString("NOTICE_CONTENT"));
				notice.setOriFile(rset.getString("NOTICE_ORIFILE"));
				notice.setReFile(rset.getString("NOTICE_REFILE"));
				list.add(notice);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int insertNotice(Connection conn, Notice notice) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "insert into TB_NOTICE values(NOTICE_SEQ.nextval, ?,?,sysdate,0,?,?,?)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, notice.getEmployeeNo());
			pstmt.setString(2, notice.getNoticeTitle());
			pstmt.setString(3, notice.getNoticecontent());
			pstmt.setString(4, notice.getOriFile());
			pstmt.setString(5, notice.getReFile());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int updateViews(Connection conn, int noticeNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "update TB_NOTICE set NOTICE_VIEWS = NOTICE_VIEWS + 1 where NOTICE_NO = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public Notice selectOne(Connection conn, int noticeNo) {
		Notice notice = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from TB_NOTICE where NOTICE_NO = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);

			rset = pstmt.executeQuery();
			if (rset.next()) {
				notice = new Notice();

				notice.setNoticeNo(rset.getInt("NOTICE_NO"));
				notice.setEmployeeNo(rset.getString("EMPLOYEE_NO"));
				notice.setNoticeTitle(rset.getString("NOTICE_TITLE"));
				notice.setNoticeDate(rset.getDate("NOTICE_DATE"));
				notice.setViews(rset.getInt("NOTICE_VIEWS"));
				notice.setNoticecontent(rset.getString("NOTICE_CONTENT"));
				notice.setOriFile(rset.getString("NOTICE_ORIFILE"));
				notice.setReFile(rset.getString("NOTICE_REFILE"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return notice;
	}

	public int updateNotice(Connection conn, Notice notice) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "update TB_NOTICE set NOTICE_TITLE = ?, NOTICE_CONTENT = ?, NOTICE_ORIFILE = ?, NOTICE_REFILE=? where NOTICE_NO = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, notice.getNoticeTitle());
			pstmt.setString(2, notice.getNoticecontent());
			pstmt.setString(3, notice.getOriFile());
			pstmt.setString(4, notice.getReFile());
			pstmt.setInt(5, notice.getNoticeNo());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int deleteNotice(Connection conn, int noticeNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "delete from TB_NOTICE where NOTICE_NO = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}
}
