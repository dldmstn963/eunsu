<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>webSocket : unicast</title>
<style type="text/css">
	#messageWindow {
		background: LightSkyBlue;
		height: 300px;
		overflow: auto;
	}
	
	.chat_content {
		background: rgb(255, 255, 102);
		padding: 10px;
		border-radius: 10px;
		display: inline-block;
		position: relative;
		margin: 10px;
		float: right;
		clear: both;
	}
	
	.chat_content:after {
		content: '';
		positon: absolute;
		right: 0;
		top: 50%;
		width: 0;
		height: 0;
		border: 20px solid transparent;
		border-left-color: rgb(255, 255, 102);
		border-right: 0;
		border-top: 0;
		margin-top: -3.5px;
		margin-right: -10px;
	}
	
	.other-side {
		background: white;
		float: left;
		clear: both;
	}
	
	.other-side:after {
		content: '';
		positon: absolute;
		right: 0;
		top: 50%;
		width: 0;
		height: 0;
		border: 20px solid transparent;
		border-right-color: white;
		border-left: 0;
		border-top: 0;
		margin-top: -3.5px;
		margin-right: -10px;
	}
</style>
<script type="text/javascript"
	src="/eunsu/resources/js/jquery-3.4.1.min.js"></script>
</head>
<body>
<h1>unicast</h1>
<h3>네트워크 상에서 클라이언트끼리 1:1 통신하는 방식</h3>
<p>예를 들어, 카톡, 전화통화, 문자 메세지 등의 통신방식을 말함</p>
<hr>
사용할 아이디 : <input type="text" id="sender" size="10"> <br>
상대방 아이디 : <input type="text" id="receiver" size="10"> <br>
<button id="start">채팅하기</button>
<hr>
<!-- 채팅방 화면 구현하기 -->
<div style="display:none;" id="chatbox">
	<fieldset>
		<div id="messageWindow"></div> <br>
		<input type="text" id="inputMessage" onkeyup="enterKey();">
		<input type="button" value="보내기" onclick="send();">
		<input type="button" value="나가기" id="endBtn">
	</fieldset>
</div>

<script type="text/javascript">
	//상대방과 연결할 웹소켓 객체 준비
	var webSocket = null;
	//채팅창 앨리먼트 변수
	var $textarea = $('#messageWindow');
	//상대방에게 전송할 메세지 입력 앨리먼트 변수
	var $inputMessage = $('#inputMessage');
	
	/*
		웹소켓 생성 후 사용될 메소드들
		1. open() : 웹소켓 객체 생성시 실행됨.
				서버와 연결해 주는 메소드임
		2. send() : 서버에 메세지 전송하는 메소드임
		3. message() : 서버에서 데이터를 받는 메소드임
		4. error() : 서버에 데이터 전송 중 에러 발생시
				자동 실행되는 메소드임
		5. close() : 서버와 연결 끊을 때 사용하는 메소드임.
	*/
	
	function connection(){
		/*
		웹소켓 객체는 생성자를 통해 생성됨
		객체 생성시에 서버와 자동 연결됨.
		사용되는 프로토콜은 ws:// 임.
		*/	
		webSocket = new WebSocket(
				"ws://192.168.20.3:8888/" +
				"<%= request.getContextPath() %>/unicast");
	
		//웹소켓을 통해서 연결이 될 때 동작할 이벤트핸들러 작성
		webSocket.onopen = function(event){
			$textarea.html("<p class='chat_content'>"
					+ $('#sender').val() + 
					"님이 입장하셨습니다.</p><br>");
			//웹소켓을 통해 채팅서버에 메세지 전송함
			webSocket.send($('#sender').val() + 
					"|님이 입장함.");
		};
		
		//서버로 부터 메세지를 받을 때 동작할 이벤트핸들러 작성
		webSocket.onmessage = function(event){
			onMessage(event);
		};
		
		//서버로 메세지 보낼 때 에러 발생 처리용 이벤트핸들러 작성
		webSocket.onerror = function(event){
			onError(event)
		};
		
		//서버와 연결을 닫을 때의 이벤트핸들러 작성
		webSocket.onclose = function(event){
			onClose(event);
		};
	}
		
	//보내기 버튼 클릭시 실행되는 send() 함수 작성
	function send(){
		//메세지를 입력하지 않고 버튼 누른 경우
		if($inputMessage.val() == "") {
			alert("전송할 메세지를 입력하세요.");
		}else{  //메세지가 입력된 경우
			$textarea.html($textarea.html() + 
				"<p class='chat_content'>나 : "
				+ $inputMessage.val() + "</p><br>");
			webSocket.send($('#sender').val() + "|"
				+ $inputMessage.val());
			$inputMessage.val('');  //기록된 메세지 삭제함
		}
		
		//화면이 위로 스크롤되게 처리함
		$textarea.scrollTop($textarea.height());
	}  //send()
	
	//웹소켓 이벤트핸들러에 의해 실행되는 함수 작성
	function onMessage(event){
		//서버로 부터 데이터를 받았을 때 작동되는 함수임
		var message = event.data.split("|");
		//보낸사람 아이디
		var receiverID = message[0];
		//전송온 메세지
		var content = message[1];
		
		//전송온 메세지가 비었거나, 보낸사람이 내가 연결한
		//사람이 아닐 경우 아무 내용도 실행하지 않는다.
		if(content == "" || 
				!receiverID.match($('#receiver').val())){
			//비워 놓음
		}else{
			$textarea.html($textarea.html() +
					"<p class='chat_content other-side'>"
					+ receiverID + " : " + content 
					+ "</p><br>");
			//화면이 위로 스크롤되게 처리함
			$textarea.scrollTop($textarea.height());
		}
		
	} //onMessage()
	
	function onError(event){
		alert(event.data);
	}
	
	function onClose(event){
		alert(event);
	}

$(function(){
	//'채팅하기' 버튼 클릭시, 서버와 연결되고 
	//채팅창이 나타나게 함
	$('#start').on('click', function(){
		//채팅창 영역 보이게 함
		$('#chatbox').css("display", "block");
		//클릭한 버튼은 안 보이게 함
		$(this).css("display", "none");
		//서버와 연결함
		connection();
	});
	
	//'나가기' 버튼 클릭시 소켓닫기
	//채팅창  안 보이게 함
	$('#endBtn').on('click', function(){
		//채팅창 안 보이게 함
		$('#chatbox').css("display", "none");
		//채팅하기 버튼은 다시 보이게 함
		$('#start').css("display", "inline");
		//서버로 종료 메세지 전송함
		webSocket.send($('#sender').val() 
				+ "님이 퇴장하였습니다.");
		//소켓닫기
		webSocket.close();
	});
});


	//전송할 메세지 입력하면서, 키보드 키에서 손뗄때마다
	//실행되는 이벤트핸들러 함수
	function enterKey(){
		//누른 키가 엔터키(Enter) 이면 메세지 전송함
		if(window.event.keyCode == 13){
			send();
		}
	}

</script>

</body>
</html>







