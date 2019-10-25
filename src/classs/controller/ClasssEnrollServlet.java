package classs.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classs.model.service.ClasssService;

/**
 * Servlet implementation class ClasssEnrollServlet
 */
@WebServlet("/classsenroll")
public class ClasssEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClasssEnrollServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String checkBox = request.getParameter("lists");
		String studentNo = request.getParameter("studentNo");
		String termNo = "201901";
		String check[] = checkBox.split(",");
		int result = 0;
		for (int i = 0; i < check.length; i++) {
			
			result = new ClasssService().enrollClass(check[i], studentNo, termNo);
		}
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		if (result > 0) {
			out.println("<script>");
			out.println("alert('신청 성공!');");
			out.println("</script>");
		} else if(result == -100){
			out.println("<script>alert('이미 신청하셨습니다');</script");
		}else {
			out.println("<script>");
			out.println("alert('정원 초과 되었습니다');");
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
