package employee.model.vo;

import java.sql.Date;

public class Employee implements java.io.Serializable {
	private static final long serialVersionUID = 3456789L;
	private String employeeNo;
	private String employeeName;
	private String employeeSSN;
	private String employeeAddress;
	private String employeePassword;
	private String employeeimage;
	private String empdepart_no;
	private String empDepartment;
	private java.sql.Date hire_date;
	private int salary;

	public Employee() {
		super();
	}




	public Employee(String employeeNo, String employeeName, String employeeSSN, String employeeAddress,
			String employeePassword, String employeeimage, String empdepart_no, String empDepartment, Date hire_date,
			int salary) {
		super();
		this.employeeNo = employeeNo;
		this.employeeName = employeeName;
		this.employeeSSN = employeeSSN;
		this.employeeAddress = employeeAddress;
		this.employeePassword = employeePassword;
		this.employeeimage = employeeimage;
		this.empdepart_no = empdepart_no;
		this.empDepartment = empDepartment;
		this.hire_date = hire_date;
		this.salary = salary;
	}




	public int getSalary() {
		return salary;
	}



	public void setSalary(int salary) {
		this.salary = salary;
	}

	public java.sql.Date getHire_date() {
		return hire_date;
	}

	public void setHire_date(java.sql.Date hire_date) {
		this.hire_date = hire_date;
	}

	public String getEmpDepartment() {
		return empDepartment;
	}

	public void setEmpDepartment(String empDepartment) {
		this.empDepartment = empDepartment;
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
