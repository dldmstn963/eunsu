package calendar.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import calendar.model.service.CalendarService;
import calendar.model.vo.Calendar;
import classs.model.service.ClasssService;
import classs.model.vo.Classs;

/**
 * Servlet implementation class CalendarUpdateServlet
 */
@WebServlet("/calendarupdate")
public class CalendarUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalendarUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int result = 0;
		String[] calNo = request.getParameterValues("calNo");
		String[] calTitle = request.getParameterValues("calTitle");
		String[] calStart = request.getParameterValues("calStart");
		String[] calEnd = request.getParameterValues("calEnd");

		for (int i = 0; i < calNo.length; i++) {
			Calendar calendar = new Calendar();

			calendar.setCalendarNo(Integer.parseInt(calNo[i]));
			calendar.setTitle(calTitle[i]);
			calendar.setStart(java.sql.Date.valueOf(calStart[i]));
			calendar.setEnd(java.sql.Date.valueOf(calEnd[i]));
			result = new CalendarService().updateCalendar(calendar);
		}
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		if (result > 0) {
			out.println("<script>");
			out.println("alert('일정 수정 성공!');");
			out.println("window.location = document.referrer;");
			out.println("</script>");
		} else {

			out.println("<script>");
			out.println("alert('과목 수정 실패!');");
			out.println("window.location = document.referrer;");
			out.println("</script>");

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
