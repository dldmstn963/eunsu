package student.controller;

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

import student.model.service.StudentService;
import student.model.vo.Student;

/**
 * Servlet implementation class SMyUpdateServlet
 */
@WebServlet("/SMyUpdateServlet")
public class SMyUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SMyUpdateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int maxSize = 1024 * 1024 * 10;
		String savePath = request.getSession().getServletContext().getRealPath("/resources/images/user");

		MultipartRequest mrequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", null);
		Student student = new Student();
		student.setStudentNo(mrequest.getParameter("STUDENT_NO"));
		student.setStudentName(mrequest.getParameter("STUDENT_NAME"));
		student.setStudentSSN(mrequest.getParameter("STUDENT_SSN"));
		student.setStudentAddress(mrequest.getParameter("STUDENT_ADDRESS"));
		student.setStudentPassword(mrequest.getParameter("STUDENT_PASSWORD"));
		if (mrequest.getFilesystemName("STUDENT_IMAGE") != null) {
			String originalFileName = mrequest.getFilesystemName("STUDENT_IMAGE");
			if (originalFileName != null) {
				String renameFileName = mrequest.getParameter("STUDENT_NO") + "."
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
				student.setStudentImage("/eunsu/resources/images/user/" + renameFileName);
			}
		}
		int result = new StudentService().SMyUpdateStudent(student);
		if (result > 0) {
			response.setContentType("text/html; charset=utf-8");

			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정 성공!');");
			out.println("history.back();");
			out.println("</script>");
		} else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정 실패!');");
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
