package student.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import department.model.service.DepartmentService;
import student.model.service.StudentService;
import student.model.vo.Student;

/**
 * Servlet implementation class StudentUpdateServlet
 */
@WebServlet("/studentupdate")
public class StudentUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int result = 0;
		String[] STUDENT_NO = request.getParameterValues("STUDENT_NO");
		String[] DEPARTMENT_NO = request.getParameterValues("DEPARTMENT_NO");
		String[] STUDENT_NAME = request.getParameterValues("STUDENT_NAME");
		String[] ABSENCE_YN = request.getParameterValues("ABSENCE_YN");
		String[] COACH_PROFESSOR_NO = request.getParameterValues("COACH_PROFESSOR_NO");

		for (int i = 0; i < STUDENT_NO.length; i++) {
			Student student = new Student();

			student.setStudentNo(STUDENT_NO[i]);
			student.setDepartmentNo(DEPARTMENT_NO[i]);
			student.setStudentName(STUDENT_NAME[i]);
			student.setAbsenceYN(ABSENCE_YN[i]);
			student.setCoachprofessor(COACH_PROFESSOR_NO[i]);
			result = new StudentService().updateStudent(student);
		}
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		if (result > 0) {
			out.println("<script>");
			out.println("alert('학생 수정 성공!');");
			out.println("history.back();");
			out.println("</script>");
		} else {

			out.println("<script>");
			out.println("alert('학생 수정 실패!');");
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
