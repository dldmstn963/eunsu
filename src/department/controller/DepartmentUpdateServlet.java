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
 * Servlet implementation class DepartmentUpdateServlet
 */
@WebServlet("/departmentupdate")
public class DepartmentUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DepartmentUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int result = 0;
		String[] departNo = request.getParameterValues("departNo");
		String[] departName = request.getParameterValues("departName");
		String[] deprtCategory = request.getParameterValues("deprtCategory");
		String[] departOpen = request.getParameterValues("departOpen");
		String[] departCapacity = request.getParameterValues("departCapacity");

		for (int i = 0; i < departNo.length; i++) {
			Department department = new Department();

			department.setDepartmentNo(departNo[i]);
			department.setDepartmentName(departName[i]);
			department.setCategory(deprtCategory[i]);
			department.setOpenYN(departOpen[i]);
			department.setCapacity(Integer.parseInt(departCapacity[i]));
			result = new DepartmentService().updateDepart(department);
		}
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		if (result > 0) {
			out.println("<script>");
			out.println("alert('학과 수정 성공!');");
			out.println("history.back();");
			out.println("</script>");
		} else {

			out.println("<script>");
			out.println("alert('학과 수정 실패!');");
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
