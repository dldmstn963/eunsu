package student.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import student.model.dao.StudentDao;
import student.model.vo.Student;
import static common.JDBCTemplate.*;

public class StudentService {
	private StudentDao sdao = new StudentDao();

	public StudentService() {
	}

	public Student loginCheck(String no, String pass) {
		Connection conn = getConnection();
		Student student = sdao.loginCheck(conn, no, pass);
		close(conn);
		return student;
	}

	public Student selectStudent(String userId) {
	}

	public ArrayList<Student> selectList() {
	}

	public int insertStudent(Student student) {
		Connection conn = getConnection();
		int result = sdao.insertStudent(conn, student);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;

	}

	public int updateStudent(Student student) {
	}

	public int deleteStudent(String userId) {
	}

}
