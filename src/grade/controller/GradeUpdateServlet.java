package grade.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import employee.model.service.EmployeeService;
import employee.model.vo.Employee;
import grade.model.service.GradeService;
import grade.model.vo.Grade;

/**
 * Servlet implementation class GradeUpdateServlet
 */
@WebServlet("/gradeupdate")
public class GradeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GradeUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int result = 0;
		String[] studentNo = request.getParameterValues("studentNo");
		String[] classNo = request.getParameterValues("classNo");
		String[] termNo = request.getParameterValues("termNo");
		String[] point = request.getParameterValues("point");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();

		for (int i = 0; i < studentNo.length; i++) {
			Grade grade = new Grade();
			if(Double.parseDouble(point[i]) > 4.5 || Double.parseDouble(point[i]) < 0) {
				out.println("<script>");
				out.println("alert('성적 값이 잘못되었습니다 확인해주세요!');");
				out.println("history.back();");
				out.println("</script>");
			}else {
			grade.setStudentNo(studentNo[i]);
			grade.setClassNo(classNo[i]);
			grade.setTermNo(termNo[i]);
			grade.setPoint(Double.parseDouble(point[i]));
			
			result = new GradeService().updateGrade(grade);
			}
		}
		
		if (result > 0) {
			out.println("<script>");
			out.println("alert('성적 수정 성공!');");
			out.println("history.back();");
			out.println("</script>");
		} else {

			out.println("<script>");
			out.println("alert('성적 수정 실패!');");
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
