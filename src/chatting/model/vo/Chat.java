package chatting.model.vo;

import java.sql.Date;

public class Chat implements java.io.Serializable{
	private static final long serialVersionUID = 47851L;
	private int chattingNo;
	private String sender;
	private String receiver;
	private String content;
	private java.sql.Date chatDate;
	public Chat() {
		super();
	}
	public int getChattingNo() {
		return chattingNo;
	}
	public void setChattingNo(int chattingNo) {
		this.chattingNo = chattingNo;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public java.sql.Date getChatDate() {
		return chatDate;
	}
	public void setChatDate(java.sql.Date chatDate) {
		this.chatDate = chatDate;
	}
	public Chat(int chattingNo, String sender, String receiver, String content, Date chatDate) {
		super();
		this.chattingNo = chattingNo;
		this.sender = sender;
		this.receiver = receiver;
		this.content = content;
		this.chatDate = chatDate;
	}
	@Override
	public String toString() {
		return "Chat [chattingNo=" + chattingNo + ", sender=" + sender + ", receiver=" + receiver + ", content="
				+ content + ", chatDate=" + chatDate + "]";
	}
	
	
	
	
	
	
}
