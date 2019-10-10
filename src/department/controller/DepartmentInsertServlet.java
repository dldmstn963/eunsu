package department.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import department.model.service.DepartmentService;
import department.model.vo.Department;

/**
 * Servlet implementation class DepartmentInsertServlet
 */
@WebServlet("/departmentinsert")
public class DepartmentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DepartmentInsertServlet() {
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

		Department department = new Department();

		department.setDepartmentNo(request.getParameter("DEPARTMENT_NO"));
		department.setDepartmentName(request.getParameter("DEPARTMENT_NAME"));
		department.setCategory(request.getParameter("CATEGORY"));
		department.setCapacity(Integer.valueOf(request.getParameter("CAPACITY")));

		int result = new DepartmentService().insertDepartment(department);

		if (result > 0) {
			System.out.println("학과 등록 성공");
			response.setContentType("text/html; charset=utf-8");

			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('학과 등록 성공!');");
			out.println("history.back();");
			out.println("</script>");
		} else {
			System.out.println("학과 등록 실패");
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('학과 등록 성공!');");
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
