package calendar.model.vo;

import java.sql.Date;

public class Calendar implements java.io.Serializable{
	private static final long serialVersionUID = 458757L;
	
	private int calendarNo;
	private java.sql.Date start;
	private java.sql.Date end;
	private String title;
	
	public Calendar() {
		super();
	}

	public Calendar(int calendarNo, Date start, Date end, String title) {
		super();
		this.calendarNo = calendarNo;
		this.start = start;
		this.end = end;
		this.title = title;
	}

	public int getCalendarNo() {
		return calendarNo;
	}

	public void setCalendarNo(int calendarNo) {
		this.calendarNo = calendarNo;
	}

	public java.sql.Date getStart() {
		return start;
	}

	public void setStart(java.sql.Date start) {
		this.start = start;
	}

	public java.sql.Date getEnd() {
		return end;
	}

	public void setEnd(java.sql.Date end) {
		this.end = end;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Calendar [calendarNo=" + calendarNo + ", start=" + start + ", end=" + end + ", title=" + title + "]";
	}

	
	
	
	
	
}
