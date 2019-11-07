package chatting.model.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import calendar.model.vo.Calendar;
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
		String query = "SELECT COUNT(*) FROM(SELECT DISTINCT chat_receiver " + " FROM TB_CHAT"
				+ " WHERE CHAT_SENDER = ?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, employeeNo);

			rset = pstmt.executeQuery();
			if (rset.next()) {
				listCount= rset.getInt(1);
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
		PreparedStatement pstmt2 = null;
		ResultSet rset = null;
		ResultSet rset2 = null;
		String query = "";
		try {
			query = "SELECT RNUM, CHAT_SENDER, NAME FROM( " + "SELECT DISTINCT ROWNUM RNUM, CHAT_SENDER FROM( "
					+ "SELECT DISTINCT CHAT_SENDER FROM TB_CHAT " + "WHERE CHAT_SENDER = ? or chat_receiver = ? )) "
					+ "JOIN TB_CHATID ON (CHAT_SENDER = IDNO) " + "where rnum >= ? and rnum <= ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, employeeNo);
			pstmt.setString(2, employeeNo);
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);
			ArrayList list2 = new ArrayList();
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Chat chat = new Chat();
				list2.add(rset.getString("CHAT_SENDER"));
				chat.setReceiver(rset.getString("CHAT_SENDER"));
				chat.setReceiverName(rset.getString("NAME"));
				
				pstmt2 = conn.prepareStatement("SELECT CHAT_SENDER,CHAT_RECEIVER,COUNT(CHAT_NOTIFICATION)as num  " + 
						"FROM TB_CHAT " + 
						"where chat_receiver = ? and chat_sender = ? " + 
						"GROUP BY CHAT_SENDER,CHAT_RECEIVER");
				pstmt2.setString(1, employeeNo);
				pstmt2.setString(2, rset.getString("CHAT_SENDER"));
				rset2 = pstmt2.executeQuery();
				while (rset2.next()) {
					chat.setChat_notification(rset2.getInt("num"));
				}
				list.add(chat);
			}

			query = "SELECT RNUM, CHAT_RECEIVER, NAME FROM( " + "SELECT DISTINCT ROWNUM RNUM, CHAT_RECEIVER FROM( "
					+ "SELECT DISTINCT CHAT_RECEIVER FROM TB_CHAT " + "WHERE CHAT_SENDER = ? or chat_receiver = ? ))  "
					+ "JOIN TB_CHATID ON (CHAT_RECEIVER = IDNO) " + "where rnum >= ? and rnum <= ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, employeeNo);
			pstmt.setString(2, employeeNo);
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Chat chat = new Chat();
				if (!(list2.contains(rset.getString("CHAT_RECEIVER")))) {
					chat.setReceiver(rset.getString("CHAT_RECEIVER"));
					chat.setReceiverName(rset.getString("NAME"));
					list.add(chat);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		Collections.sort(list, new Comparator<Chat>() {
			@Override
			public int compare(Chat b1, Chat b2) {
				if(b1.getChat_notification() > b2.getChat_notification()) {
					return -1;
				}else if(b1.getChat_notification() < b2.getChat_notification()) {
					return 1;
				}
				return 0;
			}
		});
		System.out.println("리스트 : " + list);
		return list;
	}

	public ArrayList<Chat> selectAll(Connection conn, String sender, String receiver) {
		ArrayList<Chat> list = new ArrayList<Chat>();
		PreparedStatement stmt = null;
		ResultSet rset = null;
		String query = "select * " + " from TB_CHAT "
				+ " where (CHAT_SENDER = ? and CHAT_RECEIVER = ?) or (CHAT_SENDER = ? and CHAT_RECEIVER = ?) "
				+ " order by CHAT_NO";
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

	public int chatNoti(Connection conn, Chat chat) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE TB_CHAT " + 
				"SET CHAT_NOTIFICATION = null " + 
				"WHERE CHAT_SENDER = ? and CHAT_RECEIVER = ? ";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, chat.getReceiver());
			pstmt.setString(2, chat.getSender());
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int getListCount(Connection conn) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT COUNT(*) FROM TB_CHATID";
		try {
			pstmt = conn.prepareStatement(query);
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

	public ArrayList<Chat> selectList(Connection conn, int startRow, int endRow) {
		ArrayList<Chat> list = new ArrayList<Chat>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM "
				+ " (SELECT ROWNUM RNUM,IDNO,NAME FROM (SELECT * FROM TB_CHATID ORDER BY IDNO)) WHERE RNUM >= ?  AND RNUM <= ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Chat calendar = new Chat();
				calendar.setReceiver(rset.getString("IDNO"));
				calendar.setReceiverName(rset.getString("NAME"));

				list.add(calendar);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int getListCount(Connection conn, String type, String id, String name) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "";
		if(type.equals("직원")) {
			query = "SELECT COUNT(*) FROM ( " + 
					"SELECT * FROM TB_CHATID " + 
					"WHERE IDNO LIKE 'E%') " + 
					"WHERE IDNO LIKE ? and name LIKE ?";
		}else if(type.equals("교수")) {
			query = "SELECT COUNT(*) FROM ( " + 
					"SELECT * FROM TB_CHATID " + 
					"WHERE IDNO LIKE 'P%') " + 
					"WHERE IDNO LIKE ? and name LIKE ?";
		}else if(type.equals("학생")) {
			query = "SELECT COUNT(*) FROM ( " + 
					"SELECT * FROM TB_CHATID " + 
					"WHERE IDNO NOT LIKE 'E%' AND IDNO NOT LIKE 'P%') " + 
					"WHERE IDNO LIKE ? and name LIKE ?";
		}else {
			query = "SELECT COUNT(*) FROM TB_CHATID " + 
					"WHERE IDNO LIKE ? and name LIKE ? ";
		}
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+id+"%");
			pstmt.setString(2, "%"+name+"%");
			rset = pstmt.executeQuery();
			if (rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}

		return listCount;
	}

	public ArrayList<Chat> selectList(Connection conn, int startRow, int endRow, String type, String id, String name) {
		ArrayList<Chat> list = new ArrayList<Chat>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "";
		
		if(type.equals("직원")) {
			query = "SELECT * FROM (SELECT ROWNUM RNUM,IDNO,NAME FROM (SELECT * FROM ( " + 
					"SELECT * FROM TB_CHATID " + 
					"WHERE IDNO LIKE 'E%') " + 
					"WHERE IDNO LIKE ? and name LIKE ?))WHERE RNUM >= ?  AND RNUM <= ?";
		}else if(type.equals("교수")) {
			query = "SELECT * FROM (SELECT ROWNUM RNUM,IDNO,NAME FROM (SELECT * FROM ( " + 
					"SELECT * FROM TB_CHATID " + 
					"WHERE IDNO LIKE 'P%') " + 
					"WHERE IDNO LIKE ? and name LIKE ?))WHERE RNUM >= ?  AND RNUM <= ?";
		}else if(type.equals("학생")) {
			query = "SELECT * FROM (SELECT ROWNUM RNUM,IDNO,NAME FROM (SELECT * FROM ( " + 
					"SELECT * FROM TB_CHATID " + 
					"WHERE IDNO NOT LIKE 'E%' AND IDNO NOT LIKE 'P%') " + 
					"WHERE IDNO LIKE ? and name LIKE ?))WHERE RNUM >= ?  AND RNUM <= ?";
		}else {
			query = "SELECT * FROM (SELECT ROWNUM RNUM,IDNO,NAME FROM (SELECT * FROM TB_CHATID " + 
					"WHERE IDNO LIKE ? and name LIKE ?))WHERE RNUM >= ?  AND RNUM <= ?";
		}
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+id+"%");
			pstmt.setString(2, "%"+name+"%");
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Chat calendar = new Chat();
				calendar.setReceiver(rset.getString("IDNO"));
				calendar.setReceiverName(rset.getString("NAME"));
				list.add(calendar);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

}
