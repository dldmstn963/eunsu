package chatting.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import chatting.model.dao.ChattingDao;
import chatting.model.vo.Chat;
import classs.model.vo.Classs;
import comments.model.vo.Comments;

public class ChattingService {
	ChattingDao cdao = new ChattingDao();
	
	public int insertChat(Chat chat) {
		Connection conn = getConnection();
		int result = cdao.insertChat(conn, chat);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int getListCount(String employeeNo) {
		Connection conn = getConnection();
		int listCount = cdao.getListCount(conn,employeeNo);
		close(conn);
		return listCount;
	}

	public ArrayList<Chat> selectList(int startRow, int endRow, String employeeNo) {
		Connection conn = getConnection();
		ArrayList<Chat> list = cdao.selectList(conn, startRow, endRow, employeeNo);
		close(conn);
		return list;
	}

	public ArrayList<Chat> selectAll(String sender, String receiver) {
		Connection conn = getConnection();
		ArrayList<Chat> list = cdao.selectAll(conn, sender,receiver);
		close(conn);
		return list;
	}

	public int chatNoti(Chat chat) {
		Connection conn = getConnection();
		int result = cdao.chatNoti(conn, chat);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int getListCount() {
		Connection conn = getConnection();
		int listCount = cdao.getListCount(conn);
		close(conn);
		return listCount;
	}

	public ArrayList<Chat> selectList(int startRow, int endRow) {
		Connection conn = getConnection();
		ArrayList<Chat> list = cdao.selectList(conn, startRow, endRow);
		close(conn);
		return list;
	}

	public int getListCount(String type, String id, String name) {
		Connection conn = getConnection();
		int listCount = cdao.getListCount(conn,type,id,name);
		close(conn);
		return listCount;
	}

	public ArrayList<Chat> selectList(int startRow, int endRow, String type, String id, String name) {
		Connection conn = getConnection();
		ArrayList<Chat> list = cdao.selectList(conn, startRow, endRow,type, id, name);
		close(conn);
		return list;
	}

}
