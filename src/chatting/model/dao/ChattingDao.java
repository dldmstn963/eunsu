package chatting.model.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import chatting.model.vo.Chat;

public class ChattingDao {

	public int insertChat(Connection conn, Chat chat) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query="";
		if(chat.getReceiver().substring(0,1).equals("E")) {
		query = "insert into tb_chat values(chat.nextval,?,?,null,null,?,to_char(sysdate,'yyyy.mm.dd hh24:mi'))";
		}
		else if(chat.getReceiver().substring(0,1).equals("P")) {
			query = "insert into tb_chat values(chat.nextval,?,null,null,?,?,to_char(sysdate,'yyyy.mm.dd hh24:mi'))";
		}
		else {
			query = "insert into tb_chat values(chat.nextval,?,null,?,null,?,to_char(sysdate,'yyyy.mm.dd hh24:mi'))";
		}
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, chat.getSender());
			pstmt.setString(2, chat.getReceiver());
			pstmt.setString(3, chat.getContent());
			result = pstmt.executeUpdate();

		} catch(java.sql.SQLIntegrityConstraintViolationException e) {
			result = -1;
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

}
