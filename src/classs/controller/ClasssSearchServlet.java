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
		int currentPage = 1;
		if(request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		int limit = 10;
		ClasssService cservice = new ClasssService();
		
		Classs classs = new Classs();
		classs.setClassNo(request.getParameter("searchno"));
		classs.setClassName(request.getParameter("searchname"));
		classs.setClassType(request.getParameter("searchtype"));
		classs.setDepartmentNo(request.getParameter("searchdepart"));

		int listCount = cservice.getSearchCount(classs);
		
		int maxPage = listCount/limit;
		if(listCount % limit > 0) {
			maxPage++;
		}
		int beginPage = 0;
		if(currentPage%limit == 0) {
			beginPage = currentPage-9;
		}else {
			beginPage = (currentPage / limit) * limit + 1;
		}
		int endPage = beginPage + 9;
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		int startRow = (currentPage * limit)-9;
		int endRow = currentPage * limit;
		ArrayList<Classs> list = cservice.searchClasss(startRow, endRow, classs);
		
		RequestDispatcher view = null;
		if (list.size() > 0) {
			view = request.getRequestDispatcher("views/classcrud/classSearch.jsp");
			request.setAttribute("list", list);
			request.setAttribute("maxPage", maxPage);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("beginPage", beginPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("searchno", request.getParameter("searchno"));
			request.setAttribute("searchname", request.getParameter("searchname"));
			request.setAttribute("searchtype", request.getParameter("searchtype"));
			request.setAttribute("searchdepart", request.getParameter("searchdepart"));
			view.forward(request, response);
			System.out.println("검색 서블릿 : " + list.get(0)+"\n검색 서블릿 : "+list.get(1));
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
