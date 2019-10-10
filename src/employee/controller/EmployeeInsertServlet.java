package employee.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import employee.model.service.EmployeeService;
import employee.model.vo.Employee;

/**
 * Servlet implementation class EmployeeInsertServlet
 */
@WebServlet("/employeeinsert")
public class EmployeeInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmployeeInsertServlet() {
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

		int maxSize = 1024 * 1024 * 10;
		String savePath = request.getSession().getServletContext().getRealPath("/resources/images/user");

		MultipartRequest mrequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", null);
		Employee employee = new Employee();

		employee.setEmployeeNo(mrequest.getParameter("EMPLOYEE_NO"));
		employee.setEmployeeName(mrequest.getParameter("EMPLOYEE_NAME"));
		employee.setEmployeeSSN(mrequest.getParameter("EMPLOYEE_SSN"));
		employee.setEmployeeAddress(mrequest.getParameter("EMPLOYEE_ADDRESS"));
		employee.setEmpdepart_no(mrequest.getParameter("EMPDEPART_NO"));
		employee.setEmployeePassword(mrequest.getParameter("EMPLOYEE_PASSWORD"));
		employee.setEmployeeimage(mrequest.getParameter("EMPLOYEE_IMAGE"));
		employee.setHire_date(java.sql.Date.valueOf(mrequest.getParameter("HIRE_DATE")));
		employee.setSalary(Integer.valueOf(mrequest.getParameter("SALARY")));
		String originalFileName = mrequest.getFilesystemName("EMPLOYEE_IMAGE");

		if (originalFileName != null) {
			String renameFileName = mrequest.getParameter("EMPLOYEE_NO") + "."
					+ originalFileName.substring(originalFileName.lastIndexOf(".") + 1);

			File originFile = new File(savePath + "\\" + originalFileName);
			File renameFile = new File(savePath + "\\" + renameFileName);

			if (!originFile.renameTo(renameFile)) {
				int read = -1;
				byte[] buf = new byte[1024];
				FileInputStream fin = new FileInputStream(originFile);
				FileOutputStream fout = new FileOutputStream(renameFile);
				while ((read = fin.read(buf, 0, buf.length)) != -1) {
					fout.write(buf, 0, read);
				}
				fin.close();
				fout.close();
				originFile.delete();
			}
			employee.setEmployeeimage("/eunsu/resources/images/user/" + renameFileName);
		}

		int result = new EmployeeService().insertEmployee(employee);
		if (result > 0) {
			System.out.println("직원 등록 성공");
			response.setContentType("text/html; charset=utf-8");
			
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('직원 등록 성공!');");
			out.println("history.back();");
			out.println("</script>");
		} else {
			System.out.println("직원 등록 실패");
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('직원 등록 실패!');");
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
