package chatting.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;

import chatting.model.dao.ChattingDao;
import chatting.model.vo.Chat;

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

}
