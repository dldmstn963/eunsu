package grade.model.vo;

public class grade implements java.io.Serializable {
	private static final long serialVersionUID = 222L;
	
	private String termNo;
	private String studentNo;
	private String classNo;
	private int point;
	public grade() {
		super();
	}
	public grade(String termNo, String studentNo, String classNo, int point) {
		super();
		this.termNo = termNo;
		this.studentNo = studentNo;
		this.classNo = classNo;
		this.point = point;
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
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	@Override
	public String toString() {
		return "grade [termNo=" + termNo + ", studentNo=" + studentNo + ", classNo=" + classNo + ", point=" + point
				+ "]";
	}
	
	
	
}
