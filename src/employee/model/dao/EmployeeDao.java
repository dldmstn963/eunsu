package employee.model.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import employee.model.vo.Employee;
import professor.model.vo.Professor;
import student.model.vo.Student;

public class EmployeeDao {
	public EmployeeDao() {
	};

	public Employee loginCheck(Connection conn, String no, String pass) {
		Employee employee = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from TB_EMPLOYEE NATURAL join TB_EMPDEPART where EMPLOYEE_NO = ? and EMPLOYEE_PASSWORD = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, no);
			pstmt.setString(2, pass);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				employee = new Employee();

				employee.setEmployeeNo(rset.getString("EMPLOYEE_NO"));
				employee.setEmployeeName(rset.getString("EMPLOYEE_NAME"));
				employee.setEmployeeSSN(rset.getString("EMPLOYEE_SSN"));
				employee.setEmployeeAddress(rset.getString("EMPLOYEE_ADDRESS"));
				employee.setEmpdepart_no(rset.getString("EMPDEPART_NO"));
				employee.setEmployeePassword(rset.getString("EMPLOYEE_PASSWORD"));
				employee.setEmployeeimage(rset.getString("EMPLOYEE_IMAGE"));
				employee.setEmpDepartment(rset.getString("EMPDEPART_NAME"));
				employee.setSalary(rset.getInt("SALARY"));
				employee.setHire_date(rset.getDate("HIRE_DATE"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return employee;
	}

	public int insertEmployee(Connection conn, Employee employee) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "insert into TB_EMPLOYEE values(?,?,?,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, employee.getEmployeeNo());
			pstmt.setString(2, employee.getEmployeeName());
			pstmt.setString(3, employee.getEmployeeSSN());
			pstmt.setString(4, employee.getEmployeeAddress());
			pstmt.setString(5, employee.getEmpdepart_no());
			pstmt.setString(6, employee.getEmployeePassword());
			pstmt.setString(7, employee.getEmployeeimage());
			pstmt.setDate(8, employee.getHire_date());
			pstmt.setInt(9, employee.getSalary());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int confirmEmployee(Connection conn, String empno) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "select EMPLOYEE_NO from tb_employee where EMPLOYEE_NO = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, empno);

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

		String query = "select count(*) from TB_EMPLOYEE";
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

	public ArrayList<Employee> selectList(Connection conn, int startRow, int endRow, int sort) {
		ArrayList<Employee> list = new ArrayList<Employee>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "";
		if (sort == 0) {
			query = "select * from(select rownum rnum,EMPLOYEE_NO,EMPLOYEE_NAME,EMPDEPART_NO,HIRE_DATE,SALARY from(select * from TB_EMPLOYEE order by EMPLOYEE_NO desc)) where rnum >= ? and rnum <= ?";
		} else if (sort == 1) {
			query = "select * from(select rownum rnum,EMPLOYEE_NO,EMPLOYEE_NAME,EMPDEPART_NO,HIRE_DATE,SALARY from(select * from TB_EMPLOYEE order by EMPLOYEE_NO asc)) where rnum >= ? and rnum <= ?";
		} else if (sort == 2) {
			query = "select * from(select rownum rnum,EMPLOYEE_NO,EMPLOYEE_NAME,EMPDEPART_NO,HIRE_DATE,SALARY from(select * from TB_EMPLOYEE order by EMPLOYEE_NAME desc)) where rnum >= ? and rnum <= ?";
		} else if (sort == 3) {
			query = "select * from(select rownum rnum,EMPLOYEE_NO,EMPLOYEE_NAME,EMPDEPART_NO,HIRE_DATE,SALARY from(select * from TB_EMPLOYEE order by EMPLOYEE_NAME asc)) where rnum >= ? and rnum <= ?";
		} else if (sort == 4) {
			query = "select * from(select rownum rnum,EMPLOYEE_NO,EMPLOYEE_NAME,EMPDEPART_NO,HIRE_DATE,SALARY from(select * from TB_EMPLOYEE order by EMPDEPART_NO desc)) where rnum >= ? and rnum <= ?";
		} else if (sort == 5) {
			query = "select * from(select rownum rnum,EMPLOYEE_NO,EMPLOYEE_NAME,EMPDEPART_NO,HIRE_DATE,SALARY from(select * from TB_EMPLOYEE order by EMPDEPART_NO asc)) where rnum >= ? and rnum <= ?";
		} else if (sort == 6) {
			query = "select * from(select rownum rnum,EMPLOYEE_NO,EMPLOYEE_NAME,EMPDEPART_NO,HIRE_DATE,SALARY from(select * from TB_EMPLOYEE order by HIRE_DATE desc)) where rnum >= ? and rnum <= ?";
		} else if (sort == 7) {
			query = "select * from(select rownum rnum,EMPLOYEE_NO,EMPLOYEE_NAME,EMPDEPART_NO,HIRE_DATE,SALARY from(select * from TB_EMPLOYEE order by HIRE_DATE asc)) where rnum >= ? and rnum <= ?";
		} else if (sort == 8) {
			query = "select * from(select rownum rnum,EMPLOYEE_NO,EMPLOYEE_NAME,EMPDEPART_NO,HIRE_DATE,SALARY from(select * from TB_EMPLOYEE order by SALARY desc)) where rnum >= ? and rnum <= ?";
		} else if (sort == 9) {
			query = "select * from(select rownum rnum,EMPLOYEE_NO,EMPLOYEE_NAME,EMPDEPART_NO,HIRE_DATE,SALARY from(select * from TB_EMPLOYEE order by SALARY asc)) where rnum >= ? and rnum <= ?";
		}
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				Employee employee = new Employee();

				employee.setEmployeeNo(rset.getString("EMPLOYEE_NO"));
				employee.setEmployeeName(rset.getString("EMPLOYEE_NAME"));
				employee.setEmpdepart_no(rset.getString("EMPDEPART_NO"));
				employee.setHire_date(rset.getDate("HIRE_DATE"));
				employee.setSalary(rset.getInt("SALARY"));
				list.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int updateStudent(Connection conn, Employee employee) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE TB_EMPLOYEE SET EMPDEPART_NO= ?, SALARY = ? WHERE EMPLOYEE_NO=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, employee.getEmpdepart_no());
			pstmt.setInt(2, employee.getSalary());
			pstmt.setString(3, employee.getEmployeeNo());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteStudent(Connection conn, String EMPLOYEE_NO) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "DELETE FROM TB_EMPLOYEE WHERE EMPLOYEE_NO = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, EMPLOYEE_NO);
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int getSearchCount(Connection conn, Employee employee) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select count(*) from TB_EMPLOYEE where EMPLOYEE_NO like ? and EMPLOYEE_NAME like ? and EMPDEPART_NO like ?  ";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + employee.getEmployeeNo() + "%");
			pstmt.setString(2, "%" + employee.getEmployeeName() + "%");
			pstmt.setString(3, "%" + employee.getEmpdepart_no() + "%");

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

	public ArrayList<Employee> searchEmployee(Connection conn, int startRow, int endRow, Employee employee, int sort) {
		ArrayList<Employee> list = new ArrayList<Employee>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "";

		if (sort == 0) {
			query = "select * from(select rownum rnum,EMPLOYEE_NO,EMPLOYEE_NAME,EMPDEPART_NO,HIRE_DATE,SALARY from(select * from TB_EMPLOYEE where EMPLOYEE_NO like ? and EMPLOYEE_NAME like ? and EMPDEPART_NO like ?  order by EMPLOYEE_NO desc)) where rnum >= ? and rnum <= ?";
		} else if (sort == 1) {
			query = "select * from(select rownum rnum,EMPLOYEE_NO,EMPLOYEE_NAME,EMPDEPART_NO,HIRE_DATE,SALARY from(select * from TB_EMPLOYEE where EMPLOYEE_NO like ? and EMPLOYEE_NAME like ? and EMPDEPART_NO like ?  order by EMPLOYEE_NO asc)) where rnum >= ? and rnum <= ?";
		} else if (sort == 2) {
			query = "select * from(select rownum rnum,EMPLOYEE_NO,EMPLOYEE_NAME,EMPDEPART_NO,HIRE_DATE,SALARY from(select * from TB_EMPLOYEE where EMPLOYEE_NO like ? and EMPLOYEE_NAME like ? and EMPDEPART_NO like ?  order by EMPLOYEE_NAME desc)) where rnum >= ? and rnum <= ?";
		} else if (sort == 3) {
			query = "select * from(select rownum rnum,EMPLOYEE_NO,EMPLOYEE_NAME,EMPDEPART_NO,HIRE_DATE,SALARY from(select * from TB_EMPLOYEE where EMPLOYEE_NO like ? and EMPLOYEE_NAME like ? and EMPDEPART_NO like ?  order by EMPLOYEE_NAME asc)) where rnum >= ? and rnum <= ?";
		} else if (sort == 4) {
			query = "select * from(select rownum rnum,EMPLOYEE_NO,EMPLOYEE_NAME,EMPDEPART_NO,HIRE_DATE,SALARY from(select * from TB_EMPLOYEE where EMPLOYEE_NO like ? and EMPLOYEE_NAME like ? and EMPDEPART_NO like ?  order by EMPDEPART_NO desc)) where rnum >= ? and rnum <= ?";
		} else if (sort == 5) {
			query = "select * from(select rownum rnum,EMPLOYEE_NO,EMPLOYEE_NAME,EMPDEPART_NO,HIRE_DATE,SALARY from(select * from TB_EMPLOYEE where EMPLOYEE_NO like ? and EMPLOYEE_NAME like ? and EMPDEPART_NO like ?  order by EMPDEPART_NO asc)) where rnum >= ? and rnum <= ?";
		} else if (sort == 6) {
			query = "select * from(select rownum rnum,EMPLOYEE_NO,EMPLOYEE_NAME,EMPDEPART_NO,HIRE_DATE,SALARY from(select * from TB_EMPLOYEE where EMPLOYEE_NO like ? and EMPLOYEE_NAME like ? and EMPDEPART_NO like ?  order by HIRE_DATE desc)) where rnum >= ? and rnum <= ?";
		} else if (sort == 7) {
			query = "select * from(select rownum rnum,EMPLOYEE_NO,EMPLOYEE_NAME,EMPDEPART_NO,HIRE_DATE,SALARY from(select * from TB_EMPLOYEE where EMPLOYEE_NO like ? and EMPLOYEE_NAME like ? and EMPDEPART_NO like ?  order by HIRE_DATE asc)) where rnum >= ? and rnum <= ?";
		} else if (sort == 8) {
			query = "select * from(select rownum rnum,EMPLOYEE_NO,EMPLOYEE_NAME,EMPDEPART_NO,HIRE_DATE,SALARY from(select * from TB_EMPLOYEE where EMPLOYEE_NO like ? and EMPLOYEE_NAME like ? and EMPDEPART_NO like ?  order by SALARY desc)) where rnum >= ? and rnum <= ?";
		} else if (sort == 9) {
			query = "select * from(select rownum rnum,EMPLOYEE_NO,EMPLOYEE_NAME,EMPDEPART_NO,HIRE_DATE,SALARY from(select * from TB_EMPLOYEE where EMPLOYEE_NO like ? and EMPLOYEE_NAME like ? and EMPDEPART_NO like ?  order by SALARY asc)) where rnum >= ? and rnum <= ?";
		}
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + employee.getEmployeeNo() + "%");
			pstmt.setString(2, "%" + employee.getEmployeeName() + "%");
			pstmt.setString(3, "%" + employee.getEmpdepart_no() + "%");
			pstmt.setInt(4, startRow);
			pstmt.setInt(5, endRow);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				Employee employee1 = new Employee();

				employee1.setEmployeeNo(rset.getString("EMPLOYEE_NO"));
				employee1.setEmployeeName(rset.getString("EMPLOYEE_NAME"));
				employee1.setEmpdepart_no(rset.getString("EMPDEPART_NO"));
				employee1.setHire_date(rset.getDate("HIRE_DATE"));
				employee1.setSalary(rset.getInt("SALARY"));
				list.add(employee1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

}
