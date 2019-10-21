package student.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import department.model.vo.Department;

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

	public int confirmStudent(Connection conn, String studentno) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "select STUDENT_NO from TB_STUDENT where STUDENT_NO = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, studentno);
			result = pstmt.executeUpdate();
			System.out.println(result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int getListCount(Connection conn) {
		int listCount = 0;

		Statement stmt = null;
		ResultSet rset = null;

		String query = "select count(*) from TB_STUDENT";
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			if (rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return listCount;
	}

	public ArrayList<Student> selectList(Connection conn, int startRow, int endRow, int sort) {
		ArrayList<Student> list = new ArrayList<Student>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "";
		if (sort == 0) {
			query = "select * from(select rownum rnum, STUDENT_NO,DEPARTMENT_NO,STUDENT_NAME,STUDENT_SSN,STUDENT_ADDRESS,ENTRANCE_DATE,ABSENCE_YN,COACH_PROFESSOR_NO,STUDENT_PASSWORD,STUDENT_IMAGE from(select * from TB_STUDENT order by STUDENT_NO asc)) where rnum >= ? and rnum <= ?";
		} else if (sort == 1) {
			query = "select * from(select rownum rnum, STUDENT_NO,DEPARTMENT_NO,STUDENT_NAME,STUDENT_SSN,STUDENT_ADDRESS,ENTRANCE_DATE,ABSENCE_YN,COACH_PROFESSOR_NO,STUDENT_PASSWORD,STUDENT_IMAGE from(select * from TB_STUDENT order by STUDENT_NO desc)) where rnum >= ? and rnum <= ?";
		} else if (sort == 2) {
			query = "select * from(select rownum rnum, STUDENT_NO,DEPARTMENT_NO,STUDENT_NAME,STUDENT_SSN,STUDENT_ADDRESS,ENTRANCE_DATE,ABSENCE_YN,COACH_PROFESSOR_NO,STUDENT_PASSWORD,STUDENT_IMAGE from(select * from TB_STUDENT order by DEPARTMENT_NO desc)) where rnum >= ? and rnum <= ?";
		} else if (sort == 3) {
			query = "select * from(select rownum rnum, STUDENT_NO,DEPARTMENT_NO,STUDENT_NAME,STUDENT_SSN,STUDENT_ADDRESS,ENTRANCE_DATE,ABSENCE_YN,COACH_PROFESSOR_NO,STUDENT_PASSWORD,STUDENT_IMAGE from(select * from TB_STUDENT order by DEPARTMENT_NO asc)) where rnum >= ? and rnum <= ?";
		} else if (sort == 4) {
			query = "select * from(select rownum rnum, STUDENT_NO,DEPARTMENT_NO,STUDENT_NAME,STUDENT_SSN,STUDENT_ADDRESS,ENTRANCE_DATE,ABSENCE_YN,COACH_PROFESSOR_NO,STUDENT_PASSWORD,STUDENT_IMAGE from(select * from TB_STUDENT order by STUDENT_NAME desc)) where rnum >= ? and rnum <= ?";
		} else if (sort == 5) {
			query = "select * from(select rownum rnum, STUDENT_NO,DEPARTMENT_NO,STUDENT_NAME,STUDENT_SSN,STUDENT_ADDRESS,ENTRANCE_DATE,ABSENCE_YN,COACH_PROFESSOR_NO,STUDENT_PASSWORD,STUDENT_IMAGE from(select * from TB_STUDENT order by STUDENT_NAME asc)) where rnum >= ? and rnum <= ?";
		} else if (sort == 6) {
			query = "select * from(select rownum rnum, STUDENT_NO,DEPARTMENT_NO,STUDENT_NAME,STUDENT_SSN,STUDENT_ADDRESS,ENTRANCE_DATE,ABSENCE_YN,COACH_PROFESSOR_NO,STUDENT_PASSWORD,STUDENT_IMAGE from(select * from TB_STUDENT order by ABSENCE_YN desc)) where rnum >= ? and rnum <= ?";
		} else if (sort == 7) {
			query = "select * from(select rownum rnum, STUDENT_NO,DEPARTMENT_NO,STUDENT_NAME,STUDENT_SSN,STUDENT_ADDRESS,ENTRANCE_DATE,ABSENCE_YN,COACH_PROFESSOR_NO,STUDENT_PASSWORD,STUDENT_IMAGE from(select * from TB_STUDENT order by ABSENCE_YN asc)) where rnum >= ? and rnum <= ?";
		} else if (sort == 8) {
			query = "select * from(select rownum rnum, STUDENT_NO,DEPARTMENT_NO,STUDENT_NAME,STUDENT_SSN,STUDENT_ADDRESS,ENTRANCE_DATE,ABSENCE_YN,COACH_PROFESSOR_NO,STUDENT_PASSWORD,STUDENT_IMAGE from(select * from TB_STUDENT order by COACH_PROFESSOR_NO desc)) where rnum >= ? and rnum <= ?";
		} else if (sort == 9) {
			query = "select * from(select rownum rnum, STUDENT_NO,DEPARTMENT_NO,STUDENT_NAME,STUDENT_SSN,STUDENT_ADDRESS,ENTRANCE_DATE,ABSENCE_YN,COACH_PROFESSOR_NO,STUDENT_PASSWORD,STUDENT_IMAGE from(select * from TB_STUDENT order by COACH_PROFESSOR_NO asc)) where rnum >= ? and rnum <= ?";
		}
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				Student student = new Student();

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
				list.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int updateStudent(Connection conn, Student student) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE TB_STUDENT SET COACH_PROFESSOR_NO= ?,DEPARTMENT_NO = ?,STUDENT_NAME=?,ABSENCE_YN=? WHERE STUDENT_NO=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(5, student.getStudentNo());
			pstmt.setString(2, student.getDepartmentNo());
			pstmt.setString(3, student.getStudentName());
			pstmt.setString(4, student.getAbsenceYN());
			pstmt.setString(1, student.getCoachprofessor());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteStudent(Connection conn, String STUDENT_NO) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "DELETE FROM TB_STUDENT WHERE STUDENT_NO = ?";
		try {
			pstmt = conn.prepareStatement("ALTER TABLE TB_GRADE DROP CONSTRAINTS FK_GRADE_01");
			result = pstmt.executeUpdate();
			pstmt = conn.prepareStatement(
					"ALTER TABLE TB_GRADE ADD CONSTRAINTS FK_GRADE_01 FOREIGN KEY(STUDENT_NO) REFERENCES TB_STUDENT ON DELETE CASCADE");
			result = pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, STUDENT_NO);
			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int getSearchCount(Connection conn, Student student) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select count(*) from TB_STUDENT where STUDENT_NO like ? and DEPARTMENT_NO like ? and STUDENT_NAME like ? and ABSENCE_YN like ? and COACH_PROFESSOR_NO like ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + student.getStudentNo() + "%");
			pstmt.setString(2, "%" + student.getDepartmentNo() + "%");
			pstmt.setString(3, "%" + student.getStudentName() + "%");
			pstmt.setString(4, "%" + student.getAbsenceYN() + "%");
			pstmt.setString(5, "%" + student.getCoachprofessor() + "%");

			rset = pstmt.executeQuery();
			if (rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			System.out.println("카운트 0됨");
			listCount = 0;
		} finally {
			close(rset);
			close(pstmt);
		}
		return listCount;
	}

	public ArrayList<Student> searchStudent(Connection conn, int startRow, int endRow, Student student, int sort) {
		ArrayList<Student> list = new ArrayList<Student>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "";
		if (sort == 0) {
			query = "select * from(select rownum rnum, STUDENT_NO,DEPARTMENT_NO,STUDENT_NAME,STUDENT_SSN,STUDENT_ADDRESS,ENTRANCE_DATE,ABSENCE_YN,COACH_PROFESSOR_NO,STUDENT_PASSWORD,STUDENT_IMAGE from(select * from TB_STUDENT where STUDENT_NO like ? and DEPARTMENT_NO like ? and STUDENT_NAME like ? and ABSENCE_YN like ? and COACH_PROFESSOR_NO like ? order by STUDENT_NO asc)) where rnum >= ? and rnum <= ?";
		} else if (sort == 1) {
			query = "select * from(select rownum rnum, STUDENT_NO,DEPARTMENT_NO,STUDENT_NAME,STUDENT_SSN,STUDENT_ADDRESS,ENTRANCE_DATE,ABSENCE_YN,COACH_PROFESSOR_NO,STUDENT_PASSWORD,STUDENT_IMAGE from(select * from TB_STUDENT where STUDENT_NO like ? and DEPARTMENT_NO like ? and STUDENT_NAME like ? and ABSENCE_YN like ? and COACH_PROFESSOR_NO like ? order by STUDENT_NO desc)) where rnum >= ? and rnum <= ?";
		} else if (sort == 2) {
			query = "select * from(select rownum rnum, STUDENT_NO,DEPARTMENT_NO,STUDENT_NAME,STUDENT_SSN,STUDENT_ADDRESS,ENTRANCE_DATE,ABSENCE_YN,COACH_PROFESSOR_NO,STUDENT_PASSWORD,STUDENT_IMAGE from(select * from TB_STUDENT where STUDENT_NO like ? and DEPARTMENT_NO like ? and STUDENT_NAME like ? and ABSENCE_YN like ? and COACH_PROFESSOR_NO like ? order by DEPARTMENT_NO desc)) where rnum >= ? and rnum <= ?";
		} else if (sort == 3) {
			query = "select * from(select rownum rnum, STUDENT_NO,DEPARTMENT_NO,STUDENT_NAME,STUDENT_SSN,STUDENT_ADDRESS,ENTRANCE_DATE,ABSENCE_YN,COACH_PROFESSOR_NO,STUDENT_PASSWORD,STUDENT_IMAGE from(select * from TB_STUDENT where STUDENT_NO like ? and DEPARTMENT_NO like ? and STUDENT_NAME like ? and ABSENCE_YN like ? and COACH_PROFESSOR_NO like ? order by DEPARTMENT_NO asc)) where rnum >= ? and rnum <= ?";
		} else if (sort == 4) {
			query = "select * from(select rownum rnum, STUDENT_NO,DEPARTMENT_NO,STUDENT_NAME,STUDENT_SSN,STUDENT_ADDRESS,ENTRANCE_DATE,ABSENCE_YN,COACH_PROFESSOR_NO,STUDENT_PASSWORD,STUDENT_IMAGE from(select * from TB_STUDENT where STUDENT_NO like ? and DEPARTMENT_NO like ? and STUDENT_NAME like ? and ABSENCE_YN like ? and COACH_PROFESSOR_NO like ? order by STUDENT_NAME desc)) where rnum >= ? and rnum <= ?";
		} else if (sort == 5) {
			query = "select * from(select rownum rnum, STUDENT_NO,DEPARTMENT_NO,STUDENT_NAME,STUDENT_SSN,STUDENT_ADDRESS,ENTRANCE_DATE,ABSENCE_YN,COACH_PROFESSOR_NO,STUDENT_PASSWORD,STUDENT_IMAGE from(select * from TB_STUDENT where STUDENT_NO like ? and DEPARTMENT_NO like ? and STUDENT_NAME like ? and ABSENCE_YN like ? and COACH_PROFESSOR_NO like ? order by STUDENT_NAME asc)) where rnum >= ? and rnum <= ?";
		} else if (sort == 6) {
			query = "select * from(select rownum rnum, STUDENT_NO,DEPARTMENT_NO,STUDENT_NAME,STUDENT_SSN,STUDENT_ADDRESS,ENTRANCE_DATE,ABSENCE_YN,COACH_PROFESSOR_NO,STUDENT_PASSWORD,STUDENT_IMAGE from(select * from TB_STUDENT where STUDENT_NO like ? and DEPARTMENT_NO like ? and STUDENT_NAME like ? and ABSENCE_YN like ? and COACH_PROFESSOR_NO like ? order by ABSENCE_YN desc)) where rnum >= ? and rnum <= ?";
		} else if (sort == 7) {
			query = "select * from(select rownum rnum, STUDENT_NO,DEPARTMENT_NO,STUDENT_NAME,STUDENT_SSN,STUDENT_ADDRESS,ENTRANCE_DATE,ABSENCE_YN,COACH_PROFESSOR_NO,STUDENT_PASSWORD,STUDENT_IMAGE from(select * from TB_STUDENT where STUDENT_NO like ? and DEPARTMENT_NO like ? and STUDENT_NAME like ? and ABSENCE_YN like ? and COACH_PROFESSOR_NO like ? order by ABSENCE_YN asc)) where rnum >= ? and rnum <= ?";
		} else if (sort == 8) {
			query = "select * from(select rownum rnum, STUDENT_NO,DEPARTMENT_NO,STUDENT_NAME,STUDENT_SSN,STUDENT_ADDRESS,ENTRANCE_DATE,ABSENCE_YN,COACH_PROFESSOR_NO,STUDENT_PASSWORD,STUDENT_IMAGE from(select * from TB_STUDENT where STUDENT_NO like ? and DEPARTMENT_NO like ? and STUDENT_NAME like ? and ABSENCE_YN like ? and COACH_PROFESSOR_NO like ? order by COACH_PROFESSOR_NO desc)) where rnum >= ? and rnum <= ?";
		} else if (sort == 9) {
			query = "select * from(select rownum rnum, STUDENT_NO,DEPARTMENT_NO,STUDENT_NAME,STUDENT_SSN,STUDENT_ADDRESS,ENTRANCE_DATE,ABSENCE_YN,COACH_PROFESSOR_NO,STUDENT_PASSWORD,STUDENT_IMAGE from(select * from TB_STUDENT where STUDENT_NO like ? and DEPARTMENT_NO like ? and STUDENT_NAME like ? and ABSENCE_YN like ? and COACH_PROFESSOR_NO like ? order by COACH_PROFESSOR_NO asc)) where rnum >= ? and rnum <= ?";
		}
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + student.getStudentNo() + "%");
			pstmt.setString(2, "%" + student.getDepartmentNo() + "%");
			pstmt.setString(3, "%" + student.getStudentName() + "%");
			pstmt.setString(4, "%" + student.getAbsenceYN() + "%");
			pstmt.setString(5, "%" + student.getCoachprofessor() + "%");
			pstmt.setInt(6, startRow);
			pstmt.setInt(7, endRow);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				Student student1 = new Student();

				student1.setStudentNo(rset.getString("STUDENT_NO"));
				student1.setDepartmentNo(rset.getString("DEPARTMENT_NO"));
				student1.setStudentName(rset.getString("STUDENT_NAME"));
				student1.setAbsenceYN(rset.getString("ABSENCE_YN"));
				student1.setCoachprofessor(rset.getString("COACH_PROFESSOR_NO"));
				list.add(student1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int SMyUpdateEmployee(Connection conn, Student student) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE TB_STUDENT SET STUDENT_NAME= ?, STUDENT_SSN = ?,STUDENT_ADDRESS= ?, STUDENT_PASSWORD = ?,STUDENT_IMAGE = ? WHERE STUDENT_NO=?";
		if (student.getStudentImage() == null) {
			query = "UPDATE TB_STUDENT SET STUDENT_NAME= ?, STUDENT_SSN = ?,STUDENT_ADDRESS= ?, STUDENT_PASSWORD = ? WHERE STUDENT_NO=?";
		}

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, student.getStudentName());
			pstmt.setString(2, student.getStudentSSN());
			pstmt.setString(3, student.getStudentAddress());
			pstmt.setString(4, student.getStudentPassword());

			if (student.getStudentImage() == null) {
				pstmt.setString(5, student.getStudentNo());
			}else {
				pstmt.setString(5, student.getStudentImage());
				pstmt.setString(6, student.getStudentNo());
			}
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
}
