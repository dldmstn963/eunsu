package calendar.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import calendar.model.dao.CalendarDao;
import calendar.model.vo.Calendar;

public class CalendarService {
	CalendarDao cdao = new CalendarDao();
	public ArrayList<Calendar> selectAll() {
		Connection conn = getConnection();
		ArrayList<Calendar> list = cdao.selectAll(conn);
		close(conn);
		return list;
	}
	public int insertCalendar(Calendar calendar) {
		Connection conn = getConnection();
		int result = cdao.insertCalendar(conn, calendar);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

}
