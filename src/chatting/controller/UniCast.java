package chatting.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/unicast")
public class UniCast {
	//연결한 사용자 아이디 저장용 필드
	//단, 사용자 아이디는 한번만 등록(중복 등록 안되게 함)
	//Set 계열 사용하면 해결. 동기화 처리까지 해결
	private static Set<Session> clients = 
		Collections.synchronizedSet(new HashSet<Session>());

	//전송상태에 따른 메소드 구현
	@OnOpen
	public void onOpen(Session session) {
		//클라이언트가 서버에 연결되는 시점에서 자동 실행되는
		//메소드임
		System.out.println(session);
		//연결 요청한 클라이언트를 Set 에 추가함
		clients.add(session);
	}
	
	@OnMessage
	public void onMessage(String msg, Session session) 
			throws IOException {
		//클라이언트가 보낸 메세지 받는 메소드임.
		System.out.println(msg);
		
		//메세지를 받아서, 다른 클라이언트에게 전송 처리
		//전송처리하는 동안, 다른 클라이언트가 보낸 메세지가
		//처리되지 않도록 동기화 처리함
		//NullPointerException 예외 처리함
		synchronized(clients) {
			//현재 연결된 모든 사용자에게 받은 메세지를 보냄
			for(Session client : clients) {
				//보낸 당사자는 빼고 보냄
				if(!client.equals(session)) {
					client.getBasicRemote().sendText(msg);
				}
			}
		}
	}
	
	@OnError
	public void onError(Throwable error) {
		//메세지 전송과정에서 에러가 발생한 경우 자동 실행됨
		error.printStackTrace();
	}

	@OnClose
	public void onClose(Session session) {
		//해당 session 의 클라이언트가 연결을 끊었음
		//Set 에서 제거함
		clients.remove(session);
	}

}





