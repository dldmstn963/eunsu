package calendar.model.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import calendar.model.service.CalendarService;
import calendar.model.vo.Calendar;
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
	
	
	
	
	
}
