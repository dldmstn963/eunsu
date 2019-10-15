package classs.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classs.model.service.ClasssService;
import classs.model.vo.Classs;

/**
 * Servlet implementation class ClasssUpdateServlet
 */
@WebServlet("/classsupdate")
public class ClasssUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClasssUpdateServlet() {
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
		String[] classNo = request.getParameterValues("classNo");
		String[] className = request.getParameterValues("className");
		String[] classType = request.getParameterValues("classType");
		String[] departmentNo = request.getParameterValues("departmentNo");
		String[] preatendingClassNo = request.getParameterValues("preatendingClassNo");

		for (int i = 0; i < classNo.length; i++) {
			Classs classs = new Classs();

			classs.setClassNo(classNo[i]);
			classs.setClassName(className[i]);
			classs.setClassType(classType[i]);
			classs.setDepartmentNo(departmentNo[i]);
			classs.setPreatendingClassNo(preatendingClassNo[i]);
			result = new ClasssService().updateClass(classs);
		}
		String[] value1 = request.getParameterValues("classNo");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		if (result > 0) {
			out.println("<script>");
			out.println("alert('과목 수정 성공!');");
			out.println("history.back();");
			out.println("</script>");
		} else {

			out.println("<script>");
			out.println("alert('과목 수정 실패!');");
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
