package comments.model.vo;

import java.sql.Date;

public class Comments implements java.io.Serializable {
	private static final long serialVersionUID = 21355414L;
	
	private int commentsNo;
	private String userId;
	private java.sql.Date commentsdate;
	private String commentscontent;
	private int commentRef;
	private int commentReplyRef;
	private int commentLev;
	public Comments() {
		super();
	}
	public Comments(int commentsNo, String userId, Date commentsdate, String commentscontent, int commentRef,
			int commentReplyRef, int commentLev) {
		super();
		this.commentsNo = commentsNo;
		this.userId = userId;
		this.commentsdate = commentsdate;
		this.commentscontent = commentscontent;
		this.commentRef = commentRef;
		this.commentReplyRef = commentReplyRef;
		this.commentLev = commentLev;
	}
	public int getCommentsNo() {
		return commentsNo;
	}
	public void setCommentsNo(int commentsNo) {
		this.commentsNo = commentsNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public java.sql.Date getCommentsdate() {
		return commentsdate;
	}
	public void setCommentsdate(java.sql.Date commentsdate) {
		this.commentsdate = commentsdate;
	}
	public String getCommentscontent() {
		return commentscontent;
	}
	public void setCommentscontent(String commentscontent) {
		this.commentscontent = commentscontent;
	}
	public int getCommentRef() {
		return commentRef;
	}
	public void setCommentRef(int commentRef) {
		this.commentRef = commentRef;
	}
	public int getCommentReplyRef() {
		return commentReplyRef;
	}
	public void setCommentReplyRef(int commentReplyRef) {
		this.commentReplyRef = commentReplyRef;
	}
	public int getCommentLev() {
		return commentLev;
	}
	public void setCommentLev(int commentLev) {
		this.commentLev = commentLev;
	}
	@Override
	public String toString() {
		return "Comments [commentsNo=" + commentsNo + ", userId=" + userId + ", commentsdate=" + commentsdate
				+ ", commentscontent=" + commentscontent + ", commentRef=" + commentRef + ", commentReplyRef="
				+ commentReplyRef + ", commentLev=" + commentLev + "]";
	}
	 
	
	
	
}
