package common;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import student.model.service.StudentService;
import student.model.vo.Student;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 처리용 컨트롤러
		
		String no = request.getParameter("no");
		String pass = request.getParameter("pass");
		System.out.println(no + "," + pass);
		
		Student loginStudent = new StudentService().loginCheck(no, pass);
		
		if(loginStudent != null) {
			System.out.println("로그인 성공");
			HttpSession session = request.getSession();
			session.setAttribute("loginStudent", loginStudent);
			response.sendRedirect("/eunsu/views/main.jsp");
		}else {
			System.out.println("로그인 실패");
			response.setContentType("text/html; charset=utf-8");

			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인 실패! 번호와 비밀번호를 확인해주세요');");
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
