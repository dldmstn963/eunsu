package student.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import department.model.vo.Department;
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

	public int confirmStudent(String studentno) {
		Connection conn = getConnection();
		int result = sdao.confirmStudent(conn, studentno);
		return result;
	}

	public int getListCount() {
		Connection conn = getConnection();
		int listCount = sdao.getListCount(conn);
		close(conn);
		return listCount;
	}

	public ArrayList<Student> selectList(int startRow, int endRow, int sort) {
		Connection conn = getConnection();
		ArrayList<Student> list = sdao.selectList(conn, startRow, endRow, sort);
		close(conn);
		return list;
	}

	public int updateStudent(Student student) {
		Connection conn = getConnection();
		int result = sdao.updateStudent(conn, student);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int deleteStudent(String STUDENT_NO) {
		Connection conn = getConnection();
		int result = sdao.deleteStudent(conn, STUDENT_NO);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

	public int getSearchCount(Student student) {
		Connection conn = getConnection();
		int listCount = sdao.getSearchCount(conn,student);
		close(conn);
		return listCount;
	}

	public ArrayList<Student> searchStudent(int startRow, int endRow, Student student, int sort) {
		Connection conn = getConnection();
		ArrayList<Student> list = sdao.searchStudent(conn, startRow, endRow, student, sort);
		close(conn);
		return list;
	}


}
