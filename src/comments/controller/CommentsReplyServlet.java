package comments.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comments.model.service.CommentsService;
import comments.model.vo.Comments;

/**
 * Servlet implementation class CommentsReplyServlet
 */
@WebServlet("/commentsreply")
public class CommentsReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentsReplyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Comments comments = new Comments();
		comments.setUserId(request.getParameter("userid"));
		comments.setCommentRef(Integer.parseInt(request.getParameter("noticeNo")));
		comments.setCommentLev(Integer.parseInt(request.getParameter("commentlev")));
		comments.setCommentscontent(request.getParameter("comments"));
		comments.setCommentReplyRef(Integer.parseInt(request.getParameter("coNo")));
		int result = new CommentsService().CommentsReply(comments);
		
		if (result > 0) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("history.back();");
			//out.println("opener.window.location.reload(true);");
			out.println("</script>");
		} else {
			
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
