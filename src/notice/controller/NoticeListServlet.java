package notice.controller;

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

import employee.model.vo.Employee;
import notice.model.service.NoticeService;
import notice.model.vo.Notice;
import professor.model.vo.Professor;
import student.model.vo.Student;

/**
 * Servlet implementation class NoticeListServlet
 */
@WebServlet("/noticelist")
public class NoticeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NoticeListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int currentPage = 1;
		if (request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}

		int limit = 10;
		NoticeService nservice = new NoticeService();

		int listCount = nservice.getListCount();

		int maxPage = listCount / limit;
		if (listCount % limit > 0) {
			maxPage++;
		}

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

		int startRow = (currentPage * limit) - 9;
		int endRow = currentPage * limit;
		ArrayList<Notice> list = nservice.selectList(startRow, endRow);

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
		
		
			view = request.getRequestDispatcher("views/noticecrud/noticeList.jsp");
			request.setAttribute("list", list);
			request.setAttribute("maxPage", maxPage);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("beginPage", beginPage);
			request.setAttribute("endPage", endPage);
			view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
