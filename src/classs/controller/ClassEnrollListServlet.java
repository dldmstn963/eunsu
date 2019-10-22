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
 * Servlet implementation class ClassEnrollListServlet
 */
@WebServlet("/ClassEnrollListServlet")
public class ClassEnrollListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClassEnrollListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public String studentNo = "";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int currentPage = 1;
		if (request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		int limit = 10; // 한 페이지에 출력할 목록 갯수
		ClasssService cservice = new ClasssService();
		if (studentNo == "" ||(!(request.getParameter("studentNo") == null) && !studentNo.equals(request.getParameter("studentNo")))
				) {
			studentNo = request.getParameter("studentNo");
		}

		int listCount = cservice.getEnrollListCount(studentNo); // 테이블의 전체 목록 갯수 조회
		if (listCount == 0) {
			response.setContentType("text/html; charset=utf-8");

			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('해당하는 결과가 없습니다');");
			out.println("history.back();");
			out.println("</script>");
		}
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
		int endRow = 0;

		endRow = currentPage * limit;

		ArrayList<Classs> list = cservice.selectEnrollList(startRow, endRow, studentNo);

		RequestDispatcher view = null;

		if (list.size() > 0) {
			view = request.getRequestDispatcher("views/classcrud/classEnroll.jsp");
			request.setAttribute("list", list);
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
