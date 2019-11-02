package calendar.model.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import calendar.model.service.CalendarService;
import calendar.model.vo.Calendar;
import classs.model.vo.Classs;
import comments.model.vo.Comments;

public class CalendarDao {

	public ArrayList<Calendar> selectAll(Connection conn) {
		ArrayList<Calendar> list = new ArrayList<Calendar>();
		PreparedStatement stmt = null;
		ResultSet rset = null;
		String query = "select * from TB_EVENTS";
		try {
			stmt = conn.prepareStatement(query);

			rset = stmt.executeQuery();
			while (rset.next()) {
				Calendar calendar = new Calendar();
				calendar.setStart(rset.getDate("START_DATE"));
				calendar.setEnd(rset.getDate("END_DATE"));
				calendar.setCalendarNo(rset.getInt("EVENT_ID"));
				calendar.setTitle(rset.getString("EVENT_NAME"));
				list.add(calendar);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}

		return list;
	}

	public int insertCalendar(Connection conn, Calendar calendar) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "insert into TB_EVENTS values(SEQ_EVENTS.NEXTVAL,?,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, calendar.getTitle());
			pstmt.setDate(2, calendar.getStart());
			pstmt.setDate(3, calendar.getEnd());
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int getListCount(Connection conn) {
		int listCount = 0;
		Statement stmt = null;
		ResultSet rset = null;

		String query = "select count(*) from TB_EVENTS";

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

	public ArrayList<Calendar> selectList(Connection conn, int startRow, int endRow) {
		ArrayList<Calendar> list = new ArrayList<Calendar>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM "
				+ " (SELECT ROWNUM RNUM,EVENT_ID,EVENT_NAME,START_DATE,END_DATE FROM (SELECT * FROM TB_EVENTS ORDER BY START_DATE desc)) WHERE RNUM >= ?  AND RNUM <= ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Calendar calendar = new Calendar();
				calendar.setCalendarNo(rset.getInt("EVENT_ID"));
				calendar.setTitle(rset.getString("EVENT_NAME"));
				calendar.setStart(rset.getDate("START_DATE"));
				calendar.setEnd(rset.getDate("END_DATE"));

				list.add(calendar);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int updateCalendar(Connection conn, Calendar calendar) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE TB_EVENTS SET EVENT_NAME=?,START_DATE= ?,END_DATE = ? WHERE EVENT_ID=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, calendar.getTitle());
			pstmt.setDate(2, calendar.getStart());
			pstmt.setDate(3, calendar.getEnd());
			pstmt.setInt(4, calendar.getCalendarNo());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteCalendar(Connection conn, String calendarno) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "DELETE FROM TB_EVENTS WHERE EVENT_ID = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, calendarno);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

}
