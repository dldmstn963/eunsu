package grade.model.vo;

public class Grade implements java.io.Serializable {
	private static final long serialVersionUID = 222L;
	
	private String termNo;
	private String studentNo;
	private String classNo;
	private double point;
	private String className;
	private double allavgpoint;
	private double yearavgpoint;
	private String classType;
	private String departmentName;
	private String studentName;
	public Grade() {
		super();
	}
	public Grade(String termNo, String studentNo, String classNo, int point) {
		super();
		this.termNo = termNo;
		this.studentNo = studentNo;
		this.classNo = classNo;
		this.point = point;
	}
	
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getClassType() {
		return classType;
	}
	public void setClassType(String classType) {
		this.classType = classType;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getClassName() {
		return className;
	}
	public double getAllavgpoint() {
		return allavgpoint;
	}
	public void setAllavgpoint(double allavgpoint) {
		this.allavgpoint = allavgpoint;
	}
	public double getYearavgpoint() {
		return yearavgpoint;
	}
	public void setYearavgpoint(double yearavgpoint) {
		this.yearavgpoint = yearavgpoint;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getTermNo() {
		return termNo;
	}
	public void setTermNo(String termNo) {
		this.termNo = termNo;
	}
	public String getStudentNo() {
		return studentNo;
	}
	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}
	public String getClassNo() {
		return classNo;
	}
	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}
	public double getPoint() {
		return point;
	}
	public void setPoint(double point) {
		this.point = point;
	}
	public Grade(String termNo, String studentNo, String classNo, double point, String className, double allavgpoint,
			double yearavgpoint, String classType, String departmentName, String studentName) {
		super();
		this.termNo = termNo;
		this.studentNo = studentNo;
		this.classNo = classNo;
		this.point = point;
		this.className = className;
		this.allavgpoint = allavgpoint;
		this.yearavgpoint = yearavgpoint;
		this.classType = classType;
		this.departmentName = departmentName;
		this.studentName = studentName;
	}
	

	
	
	
}
