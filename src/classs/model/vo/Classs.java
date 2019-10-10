package classs.model.vo;

/**
 * @author admin
 *
 */
public class Classs implements java.io.Serializable{
	private static final long serialVersionUID = 4567L;
	private String classNo;
	private String className;
	private String classType;
	private String departmentNo;
	private String preatendingClassNo;
	private String professorNo;
	public Classs() {
		super();
	}

	public Classs(String classNo, String className, String classType, String departmentNo, String preatendingClassNo,
			String professorNo) {
		super();
		this.classNo = classNo;
		this.className = className;
		this.classType = classType;
		this.departmentNo = departmentNo;
		this.preatendingClassNo = preatendingClassNo;
		this.professorNo = professorNo;
	}

	public String getProfessorNo() {
		return professorNo;
	}

	public void setProfessorNo(String professorNo) {
		this.professorNo = professorNo;
	}

	public String getClassNo() {
		return classNo;
	}
	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getClassType() {
		return classType;
	}
	public void setClassType(String classType) {
		this.classType = classType;
	}
	public String getDepartmentNo() {
		return departmentNo;
	}
	public void setDepartmentNo(String departmentNo) {
		this.departmentNo = departmentNo;
	}
	public String getPreatendingClassNo() {
		return preatendingClassNo;
	}
	public void setPreatendingClassNo(String preatendingClassNo) {
		this.preatendingClassNo = preatendingClassNo;
	}
	@Override
	public String toString() {
		return "classs [classNo=" + classNo + ", className=" + className + ", classType=" + classType
				+ ", departmentNo=" + departmentNo + ", preatendingClassNo=" + preatendingClassNo + "]";
	}
	
	
}
