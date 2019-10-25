package comments.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import comments.model.service.CommentsService;
import comments.model.vo.Comments;

/**
 * Servlet implementation class CommentsListServlet
 */
@WebServlet("/commentslist")
public class CommentsListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CommentsListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String noticeNo = request.getParameter("noticeno");
		ArrayList<Comments> list = new CommentsService().selectAll(noticeNo);

		JSONObject sendJson = new JSONObject();
		JSONArray jarr = new JSONArray();

		for (Comments c : list) {
			JSONObject job = new JSONObject();
			job.put("user", c.getUserId());
			job.put("content", URLEncoder.encode(c.getCommentscontent(), "UTF-8"));
			job.put("date", c.getCommentsdate().toString());

			jarr.add(job);
		}
		sendJson.put("list", jarr);

		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.write(sendJson.toJSONString());
		out.flush();
		out.close();

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
