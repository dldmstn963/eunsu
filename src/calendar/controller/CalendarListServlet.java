package calendar.controller;

import java.io.IOException;
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
		
		request.setAttribute("list", list);
		view = request.getRequestDispatcher("views/calendar/calendar.jsp");
		request.setAttribute("list", list);
		view.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
