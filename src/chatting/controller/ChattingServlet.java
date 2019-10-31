package chatting.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chatting.model.service.ChattingService;
import chatting.model.vo.Chat;

/**
 * Servlet implementation class ChattingServlet
 */
@WebServlet("/chatting")
public class ChattingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChattingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Chat chat = new Chat();
		chat.setSender(request.getParameter("sender"));
		chat.setReceiver(request.getParameter("receiver"));
		chat.setContent(request.getParameter("inputMessage"));
		
		int result = new ChattingService().insertChat(chat);
		if (result > 0) {
			
		} else {
			response.setContentType("text/html; charset=utf-8");

			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('없는 아이디 입니다!');");
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
