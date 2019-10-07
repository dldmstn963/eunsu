package student.model.dao;

import java.sql.Connection;
import java.util.ArrayList;

import student.model.vo.Student;

public class StudentDao {
	public StudentDao() {
	};

	public Student loginCheck(Connection conn, String userId, String userPwd) {
	}

	public Student selectOne(Connection conn, String userId) {
	}

	public ArrayList<Student> selectList(Connection conn) {
	}

	public int insertStudent(Connection conn, Student student) {
	}

	public int updateStudent(Connection conn, Student student) {
	}

	public int deleteStudent(Connection conn, String userId) {
	}
}
