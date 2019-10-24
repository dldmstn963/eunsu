package notice.model.vo;

import java.sql.Date;

public class Notice implements java.io.Serializable {
	private static final long serialVersionUID = 21354L;
	
	private int noticeNo;
	private String employeeNo;
	private String noticeTitle;
	private java.sql.Date noticeDate;
	private int views;
	private String noticecontent;
	private String oriFile;
	private String reFile;
	public Notice() {
		super();
	}
	public Notice(int noticeNo, String employeeNo, String noticeTitle, Date noticeDate, int views, String noticecontent,
			String oriFile, String reFile) {
		super();
		this.noticeNo = noticeNo;
		this.employeeNo = employeeNo;
		this.noticeTitle = noticeTitle;
		this.noticeDate = noticeDate;
		this.views = views;
		this.noticecontent = noticecontent;
		this.oriFile = oriFile;
		this.reFile = reFile;
	}
	public int getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	public String getNoticeTitle() {
		return noticeTitle;
	}
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	public java.sql.Date getNoticeDate() {
		return noticeDate;
	}
	public void setNoticeDate(java.sql.Date noticeDate) {
		this.noticeDate = noticeDate;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public String getNoticecontent() {
		return noticecontent;
	}
	public void setNoticecontent(String noticecontent) {
		this.noticecontent = noticecontent;
	}
	public String getOriFile() {
		return oriFile;
	}
	public void setOriFile(String oriFile) {
		this.oriFile = oriFile;
	}
	public String getReFile() {
		return reFile;
	}
	public void setReFile(String reFile) {
		this.reFile = reFile;
	}
	@Override
	public String toString() {
		return "Notice [noticeNo=" + noticeNo + ", employeeNo=" + employeeNo + ", noticeTitle=" + noticeTitle
				+ ", noticeDate=" + noticeDate + ", views=" + views + ", noticecontent=" + noticecontent + ", oriFile="
				+ oriFile + ", reFile=" + reFile + "]";
	}
	
	
	
	
	
}
