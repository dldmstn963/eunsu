package professor.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import professor.model.service.ProfessorService;
import professor.model.vo.Professor;

/**
 * Servlet implementation class ProfessorUpdateServlet
 */
@WebServlet("/professorupdate")
public class ProfessorUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProfessorUpdateServlet() {
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
		int result = 0;
		String[] PROFESSOR_NO = request.getParameterValues("PROFESSOR_NO");
		String[] DEPARTMENT_NO = request.getParameterValues("DEPARTMENT_NO");

		for (int i = 0; i < PROFESSOR_NO.length; i++) {
			Professor professor = new Professor();

			professor.setProfessorNo(PROFESSOR_NO[i]);
			professor.setDepartmentNo(DEPARTMENT_NO[i]);
			result = new ProfessorService().updateProfessor(professor);
		}

		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		if (result > 0) {
			out.println("<script>");
			out.println("alert('교수 수정 성공!');");
			out.println("history.back();");
			out.println("</script>");
		} else {

			out.println("<script>");
			out.println("alert('교수 수정 실패!');");
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
