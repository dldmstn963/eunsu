package department.model.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import classs.model.vo.Classs;
import department.model.vo.Department;

public class DepartmentDao {

	public int insertDepartment(Connection conn, Department department) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "insert into TB_DEPARTMENT values(?,?,?,'Y',?)";

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, department.getDepartmentNo());
			pstmt.setString(2, department.getDepartmentName());
			pstmt.setString(3, department.getCategory());
			pstmt.setInt(4, department.getCapacity());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int confirmDepartment(Connection conn, String departno) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "select DEPARTMENT_NO from TB_DEPARTMENT where DEPARTMENT_NO = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, departno);

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

		String query = "select count(*) from tb_department";
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

	public ArrayList<Department> selectList(Connection conn, int startRow, int endRow, int sort) {
		ArrayList<Department> list = new ArrayList<Department>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "";
		if (sort == 0) {
			query = "select * from(select rownum rnum, DEPARTMENT_NO,DEPARTMENT_NAME,CATEGORY,OPEN_YN,CAPACITY from(select * from tb_department order by DEPARTMENT_NO ASC)) where rnum >= ? and rnum <= ?";
		} else if (sort == 1) {
			query = "select * from(select rownum rnum, DEPARTMENT_NO,DEPARTMENT_NAME,CATEGORY,OPEN_YN,CAPACITY from(select * from tb_department order by DEPARTMENT_NO DESC)) where rnum >= ? and rnum <= ?";
		} else if (sort == 2) {
			query = "select * from(select rownum rnum, DEPARTMENT_NO,DEPARTMENT_NAME,CATEGORY,OPEN_YN,CAPACITY from(select * from tb_department order by DEPARTMENT_NAME DESC)) where rnum >= ? and rnum <= ?";
		} else if (sort == 3) {
			query = "select * from(select rownum rnum, DEPARTMENT_NO,DEPARTMENT_NAME,CATEGORY,OPEN_YN,CAPACITY from(select * from tb_department order by DEPARTMENT_NAME ASC)) where rnum >= ? and rnum <= ?";
		} else if (sort == 4) {
			query = "select * from(select rownum rnum, DEPARTMENT_NO,DEPARTMENT_NAME,CATEGORY,OPEN_YN,CAPACITY from(select * from tb_department order by CATEGORY DESC)) where rnum >= ? and rnum <= ?";
		} else if (sort == 5) {
			query = "select * from(select rownum rnum, DEPARTMENT_NO,DEPARTMENT_NAME,CATEGORY,OPEN_YN,CAPACITY from(select * from tb_department order by CATEGORY ASC)) where rnum >= ? and rnum <= ?";
		} else if (sort == 6) {
			query = "select * from(select rownum rnum, DEPARTMENT_NO,DEPARTMENT_NAME,CATEGORY,OPEN_YN,CAPACITY from(select * from tb_department order by OPEN_YN DESC)) where rnum >= ? and rnum <= ?";
		} else if (sort == 7) {
			query = "select * from(select rownum rnum, DEPARTMENT_NO,DEPARTMENT_NAME,CATEGORY,OPEN_YN,CAPACITY from(select * from tb_department order by OPEN_YN ASC)) where rnum >= ? and rnum <= ?";
		} else if (sort == 8) {
			query = "select * from(select rownum rnum, DEPARTMENT_NO,DEPARTMENT_NAME,CATEGORY,OPEN_YN,CAPACITY from(select * from tb_department order by CAPACITY DESC)) where rnum >= ? and rnum <= ?";
		} else if (sort == 9) {
			query = "select * from(select rownum rnum, DEPARTMENT_NO,DEPARTMENT_NAME,CATEGORY,OPEN_YN,CAPACITY from(select * from tb_department order by CAPACITY ASC)) where rnum >= ? and rnum <= ?";
		}
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				Department department = new Department();

				department.setDepartmentNo(rset.getString("DEPARTMENT_NO"));
				department.setDepartmentName(rset.getString("DEPARTMENT_NAME"));
				department.setCapacity(rset.getInt("CAPACITY"));
				department.setOpenYN(rset.getString("OPEN_YN"));
				department.setCategory(rset.getString("CATEGORY"));
				list.add(department);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int updateClasss(Connection conn, Department department) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE tb_department SET DEPARTMENT_NAME= ?,CATEGORY = ?,OPEN_YN=?,CAPACITY=? WHERE DEPARTMENT_NO=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, department.getDepartmentName());
			pstmt.setString(2, department.getCategory());
			pstmt.setString(3, department.getOpenYN());
			pstmt.setInt(4, department.getCapacity());
			pstmt.setString(5, department.getDepartmentNo());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteDepartment(Connection conn, String departNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "DELETE FROM TB_DEPARTMENT WHERE DEPARTMENT_NO = ?";
		try {
			pstmt = conn.prepareStatement("ALTER TABLE TB_CLASS DROP CONSTRAINTS FK_CLASS_02");
			result = pstmt.executeUpdate();
			pstmt = conn.prepareStatement(
					"ALTER TABLE TB_CLASS ADD CONSTRAINTS FK_CLASS_02 FOREIGN KEY(DEPARTMENT_NO) REFERENCES TB_DEPARTMENT ON DELETE CASCADE");
			result = pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement("ALTER TABLE TB_PROFESSOR DROP CONSTRAINTS FK_PROFESSOR_DEPARTMENTNO");
			result = pstmt.executeUpdate();
			pstmt = conn.prepareStatement(
					"ALTER TABLE TB_PROFESSOR ADD CONSTRAINTS FK_PROFESSOR_DEPARTMENTNO FOREIGN KEY(DEPARTMENT_NO) REFERENCES TB_DEPARTMENT ON DELETE CASCADE");
			result = pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement("ALTER TABLE TB_STUDENT DROP CONSTRAINTS FK_STUDENT_01");
			result = pstmt.executeUpdate();
			pstmt = conn.prepareStatement(
					"ALTER TABLE TB_STUDENT ADD CONSTRAINTS FK_STUDENT_01 FOREIGN KEY(DEPARTMENT_NO) REFERENCES TB_DEPARTMENT ON DELETE CASCADE");
			result = pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, departNo);
			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int getSearchCount(Connection conn, Department department) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select count(*) from TB_DEPARTMENT where DEPARTMENT_NO like ? and DEPARTMENT_NAME like ? and CATEGORY like ? and OPEN_YN like ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + department.getDepartmentNo() + "%");
			pstmt.setString(2, "%" + department.getDepartmentName() + "%");
			pstmt.setString(3, "%" + department.getCategory() + "%");
			pstmt.setString(4, "%" + department.getOpenYN() + "%");

			rset = pstmt.executeQuery();
			if (rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			listCount = 0;
		} finally {
			close(rset);
			close(pstmt);
		}

		return listCount;
	}

	public ArrayList<Department> searchClasss(Connection conn, int startRow, int endRow, Department department,
			int sort) {
		ArrayList<Department> list = new ArrayList<Department>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "";
		if (sort == 0) {
			query = "select * from(select rownum rnum, DEPARTMENT_NO,DEPARTMENT_NAME,CATEGORY,OPEN_YN,CAPACITY from(select * from tb_department where DEPARTMENT_NO like ? and DEPARTMENT_NAME like ? and CATEGORY like ? and OPEN_YN like ?  order by DEPARTMENT_NO ASC)) where rnum >= ? and rnum <= ?";
		} else if (sort == 1) {
			query = "select * from(select rownum rnum, DEPARTMENT_NO,DEPARTMENT_NAME,CATEGORY,OPEN_YN,CAPACITY from(select * from tb_department where DEPARTMENT_NO like ? and DEPARTMENT_NAME like ? and CATEGORY like ? and OPEN_YN like ?  order by DEPARTMENT_NO DESC)) where rnum >= ? and rnum <= ?";
		} else if (sort == 2) {
			query = "select * from(select rownum rnum, DEPARTMENT_NO,DEPARTMENT_NAME,CATEGORY,OPEN_YN,CAPACITY from(select * from tb_department where DEPARTMENT_NO like ? and DEPARTMENT_NAME like ? and CATEGORY like ? and OPEN_YN like ?  order by DEPARTMENT_NAME DESC)) where rnum >= ? and rnum <= ?";
		} else if (sort == 3) {
			query = "select * from(select rownum rnum, DEPARTMENT_NO,DEPARTMENT_NAME,CATEGORY,OPEN_YN,CAPACITY from(select * from tb_department where DEPARTMENT_NO like ? and DEPARTMENT_NAME like ? and CATEGORY like ? and OPEN_YN like ?  order by DEPARTMENT_NAME ASC)) where rnum >= ? and rnum <= ?";
		} else if (sort == 4) {
			query = "select * from(select rownum rnum, DEPARTMENT_NO,DEPARTMENT_NAME,CATEGORY,OPEN_YN,CAPACITY from(select * from tb_department where DEPARTMENT_NO like ? and DEPARTMENT_NAME like ? and CATEGORY like ? and OPEN_YN like ?  order by CATEGORY DESC)) where rnum >= ? and rnum <= ?";
		} else if (sort == 5) {
			query = "select * from(select rownum rnum, DEPARTMENT_NO,DEPARTMENT_NAME,CATEGORY,OPEN_YN,CAPACITY from(select * from tb_department where DEPARTMENT_NO like ? and DEPARTMENT_NAME like ? and CATEGORY like ? and OPEN_YN like ?  order by CATEGORY ASC)) where rnum >= ? and rnum <= ?";
		} else if (sort == 6) {
			query = "select * from(select rownum rnum, DEPARTMENT_NO,DEPARTMENT_NAME,CATEGORY,OPEN_YN,CAPACITY from(select * from tb_department where DEPARTMENT_NO like ? and DEPARTMENT_NAME like ? and CATEGORY like ? and OPEN_YN like ?  order by OPEN_YN DESC)) where rnum >= ? and rnum <= ?";
		} else if (sort == 7) {
			query = "select * from(select rownum rnum, DEPARTMENT_NO,DEPARTMENT_NAME,CATEGORY,OPEN_YN,CAPACITY from(select * from tb_department where DEPARTMENT_NO like ? and DEPARTMENT_NAME like ? and CATEGORY like ? and OPEN_YN like ?  order by OPEN_YN ASC)) where rnum >= ? and rnum <= ?";
		} else if (sort == 8) {
			query = "select * from(select rownum rnum, DEPARTMENT_NO,DEPARTMENT_NAME,CATEGORY,OPEN_YN,CAPACITY from(select * from tb_department where DEPARTMENT_NO like ? and DEPARTMENT_NAME like ? and CATEGORY like ? and OPEN_YN like ?  order by CAPACITY DESC)) where rnum >= ? and rnum <= ?";
		} else if (sort == 9) {
			query = "select * from(select rownum rnum, DEPARTMENT_NO,DEPARTMENT_NAME,CATEGORY,OPEN_YN,CAPACITY from(select * from tb_department where DEPARTMENT_NO like ? and DEPARTMENT_NAME like ? and CATEGORY like ? and OPEN_YN like ? order by CAPACITY ASC)) where rnum >= ? and rnum <= ?";
		}
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + department.getDepartmentNo() + "%");
			pstmt.setString(2, "%" + department.getDepartmentName() + "%");
			pstmt.setString(3, "%" + department.getCategory() + "%");
			pstmt.setString(4, "%" + department.getOpenYN() + "%");
			pstmt.setInt(5, startRow);
			pstmt.setInt(6, endRow);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				Department departmentlist = new Department();
				departmentlist.setDepartmentNo(rset.getString("DEPARTMENT_NO"));
				departmentlist.setDepartmentName(rset.getString("DEPARTMENT_NAME"));
				departmentlist.setCategory(rset.getString("CATEGORY"));
				departmentlist.setOpenYN(rset.getString("OPEN_YN"));
				departmentlist.setCapacity(rset.getInt("CAPACITY"));
				list.add(departmentlist);
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
