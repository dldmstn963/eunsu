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
import javax.servlet.http.HttpSession;

import calendar.model.service.CalendarService;
import calendar.model.vo.Calendar;
import employee.model.vo.Employee;
import professor.model.vo.Professor;
import student.model.vo.Student;

/**
 * Servlet implementation class CalendarListServlet
 */
@WebServlet("/calendarlist")
public class CalendarListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalendarListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Calendar> list = new CalendarService().selectAll();
		RequestDispatcher view = null;
		
		Student loginStudent = null;
		Professor loginProfessor = null;
		Employee loginEmployee = null;
	
		if (loginStudent != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loginStudent", loginStudent);
		} else if (loginProfessor != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loginProfessor", loginProfessor);
		} else if (loginEmployee != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loginEmployee", loginEmployee);
		}
		
		
		
		int currentPage = 1;
		if (request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));

		}
		int limit = 10; // 한 페이지에 출력할 목록 갯수
		CalendarService cservice = new CalendarService();

		int listCount = cservice.getListCount(); // 테이블의 전체 목록 갯수 조회
		// 총 페이지 수 계산
		int maxPage = listCount / limit;
		if (listCount % limit > 0) {
			maxPage++;
		}
		// currentPage 가 속한 페이지그룹의 시작 페이지숫자와 끝숫자 계산
		// 예, 현재 34페이지이면 31~40 이 됨. (페이지그룹의 수를 10개로 한 경우)
		int beginPage = 0;
		if (currentPage % limit == 0) {
			beginPage = currentPage - 9;
		} else {
			beginPage = (currentPage / limit) * limit + 1;
		}
		int endPage = beginPage + 9;
		if (endPage > maxPage) {
			endPage = maxPage;
		}
		// currentPage 에 출력할 목록의 조회할 행 번호 계산
		int startRow = (currentPage * limit) - 9;
		int endRow = currentPage * limit;
		ArrayList<Calendar> list2 = cservice.selectList(startRow, endRow);

		if (list2.size() > 0) {
			view = request.getRequestDispatcher("views/calendar/calendar.jsp");
			request.setAttribute("list", list);
			request.setAttribute("list2", list2);
			request.setAttribute("maxPage", maxPage);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("beginPage", beginPage);
			request.setAttribute("endPage", endPage);
			view.forward(request, response);
		} else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('과목 목록 조회 실패');");
			out.println("history.back();");
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
