package notice.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import comments.model.service.CommentsService;
import comments.model.vo.Comments;
import employee.model.vo.Employee;
import notice.model.service.NoticeService;
import notice.model.vo.Notice;
import professor.model.vo.Professor;
import student.model.vo.Student;

/**
 * Servlet implementation class NoticeDetailServlet
 */
@WebServlet("/NoticeDetailServlet")
public class NoticeDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int noticeNo = Integer.parseInt(request.getParameter("nno"));
		int currentPage = Integer.parseInt(request.getParameter("page"));
		ArrayList<Comments> list = new CommentsService().selectAll(noticeNo);
		NoticeService nservice = new NoticeService();
		nservice.updateViews(noticeNo);
		Notice notice = nservice.selectOne(noticeNo);
		
		RequestDispatcher view = null;
		
		Student loginStudent = null;
		Professor loginProfessor = null;
		Employee loginEmployee = null;
	
		if (loginStudent != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loginStudent", loginStudent);
		} else if (loginProfessor != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loginProfessor", loginProfessor);
		} else if (loginEmployee != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loginEmployee", loginEmployee);
		}
		String[] Ofiles = notice.getOriFile().split("|");
		String[] Rfiles = notice.getReFile().split("|");
		System.out.println("-------------------");
		System.out.println("1 : " + Ofiles[0]);
		System.out.println("2 : " + Ofiles[1]);
		System.out.println("3 : " + Rfiles[0]);
		System.out.println("4 : " + Rfiles[1]);
		System.out.println("5 : " + notice.getOriFile());
		System.out.println("6 : " + notice.getReFile());
		
		if(notice != null) {
			view = request.getRequestDispatcher("views/noticecrud/noticeDetailView.jsp");
			request.setAttribute("list", list);
			request.setAttribute("notice", notice);
			request.setAttribute("currentPage", currentPage);
		}else {
			System.out.println("조회 실패");
		}
		view.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
