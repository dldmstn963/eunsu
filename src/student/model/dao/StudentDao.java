package student.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static common.JDBCTemplate.*;
import student.model.vo.Student;

public class StudentDao {
	public StudentDao() {
	};

	public Student loginCheck(Connection conn, String no, String pass) {
		Student student = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from TB_STUDENT NATURAL join tb_department where STUDENT_NO = ? and STUDENT_PASSWORD = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, no);
			pstmt.setString(2, pass);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				student = new Student();

				student.setStudentNo(rset.getString("STUDENT_NO"));
				student.setDepartmentNo(rset.getString("DEPARTMENT_NO"));
				student.setStudentName(rset.getString("STUDENT_NAME"));
				student.setStudentSSN(rset.getString("STUDENT_SSN"));
				student.setStudentAddress(rset.getString("STUDENT_ADDRESS"));
				student.setEntranceDate(rset.getDate("ENTRANCE_DATE"));
				student.setAbsenceYN(rset.getString("ABSENCE_YN"));
				student.setCoachprofessor(rset.getString("COACH_PROFESSOR_NO"));
				student.setStudentPassword(rset.getString("STUDENT_PASSWORD"));
				student.setStudentImage(rset.getString("STUDENT_IMAGE"));
				student.setDepartmentname(rset.getString("DEPARTMENT_NAME"));
				student.setCategory(rset.getString("CATEGORY"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return student;
	}

	public Student selectOne(Connection conn, String userId) {
	}

	public ArrayList<Student> selectList(Connection conn) {
	}

	public int insertStudent(Connection conn, Student student) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "insert into tb_student values(?, ?, ?, ?, ?, ?,'N',?,?,?)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, student.getStudentNo());
			pstmt.setString(2, student.getDepartmentNo());
			pstmt.setString(3, student.getStudentName());
			pstmt.setString(4, student.getStudentSSN());
			pstmt.setString(5, student.getStudentAddress());
			pstmt.setDate(6, student.getEntranceDate());
			pstmt.setString(7, student.getCoachprofessor());
			pstmt.setString(8, student.getStudentPassword());
			pstmt.setString(9, student.getStudentImage());
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateStudent(Connection conn, Student student) {
	}

	public int deleteStudent(Connection conn, String userId) {
	}
}
