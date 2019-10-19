package employee.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import employee.model.service.EmployeeService;
import employee.model.vo.Employee;

/**
 * Servlet implementation class EmployeeUpdateServlet
 */
@WebServlet("/employeeupdate")
public class EmployeeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int result = 0;
		String[] EMPLOYEE_NO = request.getParameterValues("EMPLOYEE_NO");
		String[] EMPDEPART_NO = request.getParameterValues("EMPDEPART_NO");
		String[] SALARY = request.getParameterValues("SALARY");

		for (int i = 0; i < EMPLOYEE_NO.length; i++) {
			Employee employee = new Employee();

			employee.setEmployeeNo(EMPLOYEE_NO[i]);
			employee.setEmpdepart_no(EMPDEPART_NO[i]);
			employee.setSalary(Integer.parseInt(SALARY[i]));
			result = new EmployeeService().updateEmployee(employee);
		}
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		if (result > 0) {
			out.println("<script>");
			out.println("alert('직원 수정 성공!');");
			out.println("history.back();");
			out.println("</script>");
		} else {

			out.println("<script>");
			out.println("alert('직원 수정 실패!');");
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
