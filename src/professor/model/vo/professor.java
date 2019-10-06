package professor.model.vo;

public class professor implements java.io.Serializable{
	private static final long serialVersionUID = 234567L;
	
	private String professorNo;
	private String professorName;
	private String professorSSN;
	private String professorAddress;
	private String professorPassword;
	private String professorImage;
	private String departmentNo;
	
	
	
	public professor() {
		super();
	}



	public professor(String professorNo, String professorName, String professorSSN, String professorAddress,
			String professorPassword, String professorImage, String departmentNo) {
		super();
		this.professorNo = professorNo;
		this.professorName = professorName;
		this.professorSSN = professorSSN;
		this.professorAddress = professorAddress;
		this.professorPassword = professorPassword;
		this.professorImage = professorImage;
		this.departmentNo = departmentNo;
	}



	public String getProfessorNo() {
		return professorNo;
	}



	public void setProfessorNo(String professorNo) {
		this.professorNo = professorNo;
	}



	public String getProfessorName() {
		return professorName;
	}



	public void setProfessorName(String professorName) {
		this.professorName = professorName;
	}



	public String getProfessorSSN() {
		return professorSSN;
	}



	public void setProfessorSSN(String professorSSN) {
		this.professorSSN = professorSSN;
	}



	public String getProfessorAddress() {
		return professorAddress;
	}



	public void setProfessorAddress(String professorAddress) {
		this.professorAddress = professorAddress;
	}



	public String getProfessorPassword() {
		return professorPassword;
	}



	public void setProfessorPassword(String professorPassword) {
		this.professorPassword = professorPassword;
	}



	public String getProfessorImage() {
		return professorImage;
	}



	public void setProfessorImage(String professorImage) {
		this.professorImage = professorImage;
	}



	public String getDepartmentNo() {
		return departmentNo;
	}



	public void setDepartmentNo(String departmentNo) {
		this.departmentNo = departmentNo;
	}



	@Override
	public String toString() {
		return "professor [professorNo=" + professorNo + ", professorName=" + professorName + ", professorSSN="
				+ professorSSN + ", professorAddress=" + professorAddress + ", professorPassword=" + professorPassword
				+ ", professorImage=" + professorImage + ", departmentNo=" + departmentNo + "]";
	}
	
	
	
	
}
