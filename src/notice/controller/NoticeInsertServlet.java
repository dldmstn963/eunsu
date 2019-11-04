package notice.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import notice.model.service.NoticeService;
import notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeInsertServlet
 */
@WebServlet("/noticeinsert")
public class NoticeInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		int maxSize = 1024 * 1024 * 10;

		String savePath = request.getSession().getServletContext().getRealPath("/resources/nofile");
		MultipartRequest mrequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", null);
		Notice notice = new Notice();
		notice.setNoticeTitle(mrequest.getParameter("title"));
		notice.setEmployeeNo(mrequest.getParameter("writer"));
		notice.setNoticecontent(mrequest.getParameter("content"));
		String[] originalFileName = new String[] {
				mrequest.getFilesystemName("nofile1"),
				mrequest.getFilesystemName("nofile2"),
				mrequest.getFilesystemName("nofile3"),
				mrequest.getFilesystemName("nofile4"),
				mrequest.getFilesystemName("nofile5")
		};
		System.out.println("인서트 서블릿 배열 길이 " + originalFileName.length);
		String original = "";
		String rename = "";
		for(int i = 0; i<originalFileName.length; i++) {
		if (originalFileName[i] != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String renameFileName = sdf.format(new java.sql.Date(System.currentTimeMillis()))+i+ "."
					+ originalFileName[i].substring(originalFileName[i].lastIndexOf(".") + 1);
			
			original += originalFileName[i]+"/";
			rename += renameFileName+"/";
			
			File originFile = new File(savePath + "\\" + originalFileName[i]);
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


		} }
		notice.setOriFile(original);
		notice.setReFile(rename);
		int result = new NoticeService().insertBoard(notice);
		RequestDispatcher view = null;
		if (result > 0) {
			response.sendRedirect("/eunsu/noticelist");
		} else {
			response.setContentType("text/html; charset=utf-8");
   			PrintWriter out = response.getWriter();
   			out.println("<script>");
   			out.println("alert('등록 실패');");
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
