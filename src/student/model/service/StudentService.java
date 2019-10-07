package student.model.service;

import student.model.dao.StudentDao;
import student.model.vo.Student;

public class StudentService {
	private StudentDao sdao = new StudentDao();

	public StudentService() {
	}

	public Student loginCheck(String userId, String userPwd) {
	}

	public Student selectStudent(String userId) {
	}

	public ArrayList<Student> selectList() {
	}

	public int insertStudent(Student student) {
	}

	public int updateStudent(Student student) {
	}

	public int deleteStudent(String userId) {
	}

}
