package classs.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classs.model.service.ClasssService;
import classs.model.vo.Classs;

/**
 * Servlet implementation class ClasssSearchServlet
 */
@WebServlet("/classssearch")
public class ClasssSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClasssSearchServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		Classs classs = new Classs();

		classs.setClassNo(request.getParameter("searchno"));
		classs.setClassName(request.getParameter("searchname"));
		classs.setClassType(request.getParameter("searchtype"));
		classs.setDepartmentNo(request.getParameter("searchdepart"));

		ClasssService cservice = new ClasssService();
		ArrayList<Classs> list = cservice.searchClasss(classs);

		RequestDispatcher view = null;
		if (list.size() > 0) {
			view = request.getRequestDispatcher("views/classcrud/classUpdate.jsp");
			request.setAttribute("list", list);
			view.forward(request, response);
		} else {
			response.setContentType("text/html; charset=utf-8");

			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('해당하는 결과가 없습니다');");
			out.println("history.back();");
			out.println("</script>");
		}

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
