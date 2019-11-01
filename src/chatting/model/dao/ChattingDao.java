package chatting.model.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import chatting.model.vo.Chat;
import classs.model.vo.Classs;
import comments.model.vo.Comments;

public class ChattingDao {

	public int insertChat(Connection conn, Chat chat) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "insert into tb_chat values(chat.nextval,?,?,?,to_char(sysdate,'yyyy.mm.dd hh24:mi'),default)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, chat.getSender());
			pstmt.setString(2, chat.getReceiver());
			pstmt.setString(3, chat.getContent());
			result = pstmt.executeUpdate();

		} catch (java.sql.SQLIntegrityConstraintViolationException e) {
			result = -1;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int getListCount(Connection conn, String employeeNo) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT COUNT(*) FROM(SELECT DISTINCT chat_receiver "
				+ " FROM TB_CHAT" + " WHERE CHAT_SENDER = ?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, employeeNo);

			rset = pstmt.executeQuery();
			if (rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			listCount = 0;
		} finally {
			close(rset);
			close(pstmt);
		}

		return listCount;
	}

	public ArrayList<Chat> selectList(Connection conn, int startRow, int endRow, String employeeNo) {
		ArrayList<Chat> list = new ArrayList<Chat>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM( "
				+ " SELECT DISTINCT ROWNUM RNUM, CHAT_RECEIVER FROM("
				+ " SELECT DISTINCT CHAT_RECEIVER FROM TB_CHAT"
				+ " WHERE CHAT_SENDER = ? or chat_receiver = ? ))where rnum >= ? and rnum <= ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, employeeNo);
			pstmt.setString(2, employeeNo);
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				Chat chat = new Chat();
				chat.setReceiver(rset.getString("CHAT_RECEIVER"));
				list.add(chat);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public ArrayList<Chat> selectAll(Connection conn, String sender, String receiver) {
		ArrayList<Chat> list = new ArrayList<Chat>();
		PreparedStatement stmt = null;
		ResultSet rset = null;
		String query = "select * " + 
				" from TB_CHAT " + 
				" where (CHAT_SENDER = ? and CHAT_RECEIVER = ?) or (CHAT_SENDER = ? and CHAT_RECEIVER = ?) " + 
				" order by CHAT_NO";
		try {
			stmt = conn.prepareStatement(query);
			stmt.setString(1, sender);
			stmt.setString(2, receiver);
			stmt.setString(3, receiver);
			stmt.setString(4, sender);
			rset = stmt.executeQuery();
			while (rset.next()) {
				Chat chat = new Chat();
				chat.setChatDate(rset.getString("CHAT_DATE"));
				chat.setContent(rset.getString("CHAT_CONTENT"));
				chat.setReceiver(rset.getString("CHAT_RECEIVER"));
				chat.setSender(rset.getString("CHAT_SENDER"));
				list.add(chat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}

		return list;
	}

}
