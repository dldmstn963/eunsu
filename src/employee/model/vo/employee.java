package employee.model.vo;

public class employee implements java.io.Serializable {
	private static final long serialVersionUID = 3456789L;
	private String employeeNo;
	private String employeeName;
	private String employeeSSN;
	private String employeeAddress;
	private String employeePassword;
	private String employeeimage;
	private String empdepart_no;
	public employee() {
		super();
	}
	public employee(String employeeNo, String employeeName, String employeeSSN, String employeeAddress,
			String employeePassword, String employeeimage, String empdepart_no) {
		super();
		this.employeeNo = employeeNo;
		this.employeeName = employeeName;
		this.employeeSSN = employeeSSN;
		this.employeeAddress = employeeAddress;
		this.employeePassword = employeePassword;
		this.employeeimage = employeeimage;
		this.empdepart_no = empdepart_no;
	}
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeSSN() {
		return employeeSSN;
	}
	public void setEmployeeSSN(String employeeSSN) {
		this.employeeSSN = employeeSSN;
	}
	public String getEmployeeAddress() {
		return employeeAddress;
	}
	public void setEmployeeAddress(String employeeAddress) {
		this.employeeAddress = employeeAddress;
	}
	public String getEmployeePassword() {
		return employeePassword;
	}
	public void setEmployeePassword(String employeePassword) {
		this.employeePassword = employeePassword;
	}
	public String getEmployeeimage() {
		return employeeimage;
	}
	public void setEmployeeimage(String employeeimage) {
		this.employeeimage = employeeimage;
	}
	public String getEmpdepart_no() {
		return empdepart_no;
	}
	public void setEmpdepart_no(String empdepart_no) {
		this.empdepart_no = empdepart_no;
	}
	@Override
	public String toString() {
		return "employee [employeeNo=" + employeeNo + ", employeeName=" + employeeName + ", employeeSSN=" + employeeSSN
				+ ", employeeAddress=" + employeeAddress + ", employeePassword=" + employeePassword + ", employeeimage="
				+ employeeimage + ", empdepart_no=" + empdepart_no + "]";
	}
	
	
	
}
