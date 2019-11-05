package chatting.model.vo;

import java.sql.Date;

public class Chat implements java.io.Serializable{
	private static final long serialVersionUID = 47851L;
	private int chattingNo;
	private String sender;
	private String receiver;
	private String content;
	private String chatDate;
	private int chat_notification;
	private String receiverName;

	
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public int getChat_notification() {
		return chat_notification;
	}
	public void setChat_notification(int chat_notification) {
		this.chat_notification = chat_notification;
	}
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
	public String getChatDate() {
		return chatDate;
	}
	public void setChatDate(String string) {
		this.chatDate = string;
	}
	public Chat(int chattingNo, String sender, String receiver, String content, String chatDate) {
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
				+ content + ", chatDate=" + chatDate + ", chat_notification=" + chat_notification + "]";
	}

	
	
	
	
	
	
}
