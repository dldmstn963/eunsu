<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="employee.model.vo.Employee,java.util.ArrayList,chatting.model.vo.Chat"%>
<%@ page import="student.model.vo.Student"%>
<%@ page import="professor.model.vo.Professor"%>
<%
	Student loginStudent = (Student) session.getAttribute("loginStudent");
	Professor loginProfessor = (Professor) session.getAttribute("loginProfessor");
	Employee loginEmployee = (Employee) session.getAttribute("loginEmployee");
	String sender = ((String) request.getAttribute("sender").toString());
	String receiver = ((String) request.getAttribute("receiver").toString());
	ArrayList<Chat> list = (ArrayList<Chat>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<title>수은 대학교</title>
<script type="text/javascript"
	src="/eunsu/resources/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	function Chat() {
		var f = document.Chat2;
		f.action = "/eunsu/chattinglist";
		f.method = "get"
		f.submit();
	}
	
</script>
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
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/eunsu/resources/css/w3.css">
<link rel="stylesheet"
	href="/eunsu/resources/css/w3-theme-blue-grey.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script type="text/javascript"
	src="/eunsu/resources/js/jquery-3.4.1.min.js"></script>

<body class="w3-theme-l5">

	<!-- Navbar -->
	<div class="w3-top">
		<div class="w3-bar w3-theme-d2 w3-left-align w3-large">
			<a
				class="w3-bar-item w3-button w3-hide-medium w3-hide-large w3-right w3-padding-large w3-hover-white w3-large w3-theme-d2"
				href="javascript:void(0);" onclick="openNav()"><i
				class="fa fa-bars"></i></a> <a href="/eunsu/views/main.jsp"
				class="w3-bar-item w3-button w3-padding-large w3-theme-d4"><i
				class="fa fa-home w3-margin-right"></i>수은 대학교</a> <a
				href="/eunsu/noticelist"
				class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white"
				title="News"><i class="fa fa-flag"></i></a> <a
				href="/eunsu/calendarlist"
				class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white"
				title="Account Settings"><i class="fa fa-calendar"></i></a> <a
				href="#" onclick="Chat();"
				class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white"
				title="Messages"><i class="fa fa-comments"></i></a> <a href="#"
				class="w3-bar-item w3-button w3-hide-small w3-right w3-padding-large w3-hover-white"
				title="My Account"> </a>
			<form name="Chat2">
				<%
					if (loginProfessor != null) {
				%>
				<input type="hidden" name="employeeNo"
					value="<%=loginProfessor.getProfessorNo()%>" />
				<%
					} else if (loginEmployee != null) {
				%>
				<input type="hidden" name="employeeNo"
					value="<%=loginEmployee.getEmployeeNo()%>" />
				<%
					} else if (loginStudent != null) {
				%>
				<input type="hidden" name="employeeNo"
					value="<%=loginStudent.getStudentNo()%>" />
				<%
					}
				%>
			</form>

		</div>
	</div>

	<!-- Navbar on small screens -->
	<div id="navDemo"
		class="w3-bar-block w3-theme-d2 w3-hide w3-hide-large w3-hide-medium w3-large">
		<a href="#" class="w3-bar-item w3-button w3-padding-large">Link 1</a>
		<a href="/eunsu/noticelist"
			class="w3-bar-item w3-button w3-padding-large">공지사항</a> <a
			href="/eunsu/calendarlist"
			class="w3-bar-item w3-button w3-padding-large">일정</a> <a href="#"
			onclick="Chat();" class="w3-bar-item w3-button w3-padding-large">채팅</a>
	</div>

	<!-- Page Container -->
	<div class="w3-container w3-content"
		style="max-width: 1400px; margin-top: 80px">
		<!-- The Grid -->
		<div class="w3-row">
			<!-- Left Column -->
			<div class="w3-col m3">
				<!-- Profile -->
				<div class="w3-card w3-round w3-white">
					<%
						if (loginProfessor != null) {
					%>
					<div class="w3-container">
						<h4 class="w3-center">
							내 정보
							<form action="/eunsu/pmyinfo" method="post">
								<input type="hidden"
									value="<%=loginProfessor.getProfessorNo()%>" name="empno">
								<input type="hidden"
									value="<%=loginProfessor.getProfessorPassword()%>"
									name="emppass"> <input type="submit" value="수정">
							</form>
						</h4>
						<p class="w3-center">
							<img src="<%=loginProfessor.getProfessorImage()%>"
								class="w3-circle" style="height: 106px; width: 106px"
								alt="Avatar">
						</p>
						<hr>
						<p>
							<i class="fa fa-pencil fa-fw w3-margin-right w3-text-theme"></i>
							<%=loginProfessor.getProfessorName()%></p>
						<p>
							<i class="fa fa-pencil fa-fw w3-margin-right w3-text-theme"></i>
							<%=loginProfessor.getCategory()%></p>
						<p>
							<i class="fa fa-pencil fa-fw w3-margin-right w3-text-theme"></i>
							<%=loginProfessor.getDepartmentName()%></p>
						<br> <a href="/eunsu/logout" style="text-decoration: none;">로그아웃</a>
					</div>
					<%
						} else if (loginEmployee != null) {
					%>
					<div class="w3-container">
						<h4 class="w3-center">
							내 정보 &nbsp;
							<form action="/eunsu/emyinfo" method="post">
								<input type="hidden" value="<%=loginEmployee.getEmployeeNo()%>"
									name="empno"> <input type="hidden"
									value="<%=loginEmployee.getEmployeePassword()%>" name="emppass">
								<input type="submit" value="수정">
							</form>
						</h4>
						<p class="w3-center">
							<img src="<%=loginEmployee.getEmployeeimage()%>"
								class="w3-circle" style="height: 106px; width: 106px"
								alt="Avatar">
						</p>
						<hr>
						<p>
							이름 :
							<%=loginEmployee.getEmployeeName()%>
						</p>
						<p>
							소속 부서 :
							<%=loginEmployee.getEmpDepartment()%>
						</p>
						<a href="/eunsu/logout" style="text-decoration: none;">로그아웃</a>
					</div>
					<%
						} else if (loginStudent != null) {
					%>
					<div class="w3-container">
						<h4 class="w3-center">
							내 정보
							<form action="/eunsu/smyinfo" method="post">
								<input type="hidden" value="<%=loginStudent.getStudentNo()%>"
									name="empno"> <input type="hidden"
									value="<%=loginStudent.getStudentPassword()%>" name="emppass">
								<input type="submit" value="수정">
							</form>
						</h4>
						<p class="w3-center">
							<img src="<%=loginStudent.getStudentImage()%>" class="w3-circle"
								style="height: 106px; width: 106px" alt="Avatar">
						</p>
						<hr>
						<p>
							<i class="fa fa-pencil fa-fw w3-margin-right w3-text-theme"></i>
							<%=loginStudent.getStudentName()%></p>
						<p>
							<i class="fa fa-pencil fa-fw w3-margin-right w3-text-theme"></i>
							<%=loginStudent.getCategory()%></p>
						<p>
							<i class="fa fa-pencil fa-fw w3-margin-right w3-text-theme"></i>
							<%=loginStudent.getDepartmentname()%></p>
						<br> <a href="/eunsu/logout" style="text-decoration: none;">로그아웃</a>
					</div>
					<%
						}
					%>
				</div>
				<br>

				<!-- Accordion -->
				<%
					if (loginProfessor != null) {
				%>
				<div class="w3-card w3-round">
					<div class="w3-white">
						<button onclick="myFunction('Demo1')"
							class="w3-button w3-block w3-theme-l1 w3-left-align">
							<i class="fa fa-graduation-cap fa-fw w3-margin-right"></i> 강의 과목
							보기
						</button>
						<div id="Demo1" class="w3-hide w3-container">
							<p>
								<a href="/eunsu/views/professorbasic.jsp">강의 과목 보기</a>
							</p>
						</div>
						<button onclick="myFunction('Demo2')"
							class="w3-button w3-block w3-theme-l1 w3-left-align">
							<i class="fa fa-calendar-check-o fa-fw w3-margin-right"></i> 성적
							입력
						</button>
						<div id="Demo2" class="w3-hide w3-container">
							<p>
								<a href="#" onclick="classUpdate();">성적 수정</a>
							</p>

							<form name="classUpdate2">
								<input type="hidden" name="professorNo"
									value="<%=loginProfessor.getProfessorNo()%>" />
							</form>
						</div>

					</div>
				</div>
				<%
					} else if (loginEmployee != null) {
				%>
				<div class="w3-card w3-round">
					<div class="w3-white">
						<button onclick="myFunction('Demo1')"
							class="w3-button w3-block w3-theme-l1 w3-left-align">
							<i class="fa fa-graduation-cap fa-fw w3-margin-right"></i> 과목 관리
						</button>
						<div id="Demo1" class="w3-hide w3-container">
							<p>
							<p>
								<a href="/eunsu/views/classcrud/classOpen.jsp">수강 신청 페이지 열기</a>
							</p>
							<p>
								<a href="/eunsu/views/classcrud/classinsert.jsp">과목 추가</a>
							</p>
							<p>
								<a href="/eunsu/classslist">과목 수정 및 삭제</a>
							</p>
							</p>
						</div>
						<button onclick="myFunction('Demo2')"
							class="w3-button w3-block w3-theme-l1 w3-left-align">
							<i class="fa fa-calendar-check-o fa-fw w3-margin-right"></i> 학과
							관리
						</button>
						<div id="Demo2" class="w3-hide w3-container">
							<p>
								<a href="/eunsu/views/departmentcrud/departmentinsert.jsp">학과
									추가</a>
							</p>
							<p>
								<a href="/eunsu/departmentlist">학과 수정 및 삭제</a>
							</p>
						</div>
						<button onclick="myFunction('Demo3')"
							class="w3-button w3-block w3-theme-l1 w3-left-align">
							<i class="fa fa-users fa-fw w3-margin-right"></i>학생 관리
						</button>
						<div id="Demo3" class="w3-hide w3-container">
							<p>
								<a href="/eunsu/views/studentcrud/studentinsert.jsp">학생 추가</a>
							</p>
							<p>
								<a href="/eunsu/slist">학생 수정 및 삭제</a>
							</p>
						</div>
						<button onclick="myFunction('Demo4')"
							class="w3-button w3-block w3-theme-l1 w3-left-align">
							<i class="fa fa-users fa-fw w3-margin-right"></i>교수 관리
						</button>
						<div id="Demo4" class="w3-hide w3-container">
							<p>
								<a href="/eunsu/views/professorcrud/professorinsert.jsp">교수
									추가</a>
							</p>
							<p>
								<a href="/eunsu/professorlist">교수 수정 및 삭제</a>
							</p>
						</div>
						<button onclick="myFunction('Demo5')"
							class="w3-button w3-block w3-theme-l1 w3-left-align">
							<i class="fa fa-users fa-fw w3-margin-right"></i>직원 관리
						</button>
						<div id="Demo5" class="w3-hide w3-container">
							<p>
								<a href="/eunsu/views/employeecrud/employeeinsert.jsp">직원 추가</a>
							</p>
							<p>
								<a href="/eunsu/employeelist">직원 수정 및 삭제</a>
							</p>

						</div>
					</div>
				</div>
				<%
					} else {
				%>
				<div class="w3-card w3-round">
					<div class="w3-white">
						<button onclick="myFunction('Demo1')"
							class="w3-button w3-block w3-theme-l1 w3-left-align">
							<i class="fa fa-graduation-cap fa-fw w3-margin-right"></i> 수강 신청
						</button>
						<div id="Demo1" class="w3-hide w3-container">
							<p>
								<a href="#" onclick="classEnroll();">신청하기</a>
							</p>

							<form name="classEnroll2">
								<input type="hidden" name="studentNo"
									value="<%=loginStudent.getStudentNo()%>" />
							</form>


						</div>
						<button onclick="myFunction('Demo2')"
							class="w3-button w3-block w3-theme-l1 w3-left-align">
							<i class="fa fa-calendar-check-o fa-fw w3-margin-right"></i> 수강
							과목 확인
						</button>
						<div id="Demo2" class="w3-hide w3-container">
							<p>
								<a href="#" onclick="gradeCheck();">성적 조회</a>
							</p>
						</div>

					</div>
				</div>
				<%
					}
				%>
				<br>


				<!-- End Left Column -->
			</div>
			<!-- End Grid -->
			<%
				if (loginProfessor != null) {
			%>
			사용할 아이디 : <input type="text" id="sender" size="10"
				value="<%=loginProfessor.getProfessorNo()%>" readonly> <br>
			<%
				} else if (loginEmployee != null) {
			%>
			사용할 아이디 : <input type="text" id="sender" size="10"
				value="<%=loginEmployee.getEmployeeNo()%>" readonly> <br>
			<%
				} else if (loginStudent != null) {
			%>
			사용할 아이디 : <input type="text" id="sender" size="10"
				value="<%=loginStudent.getStudentNo()%>" readonly> <br>
			<%
				}
			%>

			상대방 아이디 : <input type="text" id="receiver" size="10"
				value="<%=receiver%>" readonly> <br>
			<hr>
			<!-- 채팅방 화면 구현하기 -->
			<div style="display: block;" id="chatbox">
				<fieldset>
					<div id="messageWindow">
						<%
							for (Chat c : list) {
						%>
						<%
							if (c.getSender().equals(sender)) {
						%>
						<p class='chat_content'><%=c.getContent()%></p>
						<%
							} else {
						%>
						<p class='chat_content other-side'><%=c.getContent()%></p>
						<%
							}
						%>
						<%
							}
						%>

						<br>

						<div id="message1"></div>
					</div>
					<br> <input type="text" id="inputMessage" name="inputMessage"
						onkeyup="enterKey();"> <input type="button" value="보내기"
						onclick="send();" id="send"> <input type="button"
						value="나가기" id="endBtn">
				</fieldset>
			</div>
			<script type="text/javascript">
			$(function(){ 
				$.ajax({
					url : "/eunsu/chattingnoti",
					data : {
						sender : $('#sender').val(),
						receiver : $('#receiver').val()
					}
				})//ajax});
			});

			
			
			
			
			
			
			
			
	var objDiv = document.getElementById("messageWindow");
	var d = new Date();
	//상대방과 연결할 웹소켓 객체 준비
	var webSocket = null;
	//채팅창 앨리먼트 변수
	var $textarea = $('#message1');
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
				"ws://192.168.20.7:9345/" +
				"<%=request.getContextPath()%>/unicast");

					//웹소켓을 통해서 연결이 될 때 동작할 이벤트핸들러 작성
					webSocket.onopen = function(event) {

					};

					//서버로 부터 메세지를 받을 때 동작할 이벤트핸들러 작성
					webSocket.onmessage = function(event) {
						onMessage(event);
					};

					//서버로 메세지 보낼 때 에러 발생 처리용 이벤트핸들러 작성
					webSocket.onerror = function(event) {
						onError(event)
					};

					//서버와 연결을 닫을 때의 이벤트핸들러 작성
					webSocket.onclose = function(event) {
						onClose(event);
					};
				}

				//보내기 버튼 클릭시 실행되는 send() 함수 작성
				function send() {
					//메세지를 입력하지 않고 버튼 누른 경우
					if ($inputMessage.val() == "") {
						alert("전송할 메세지를 입력하세요.");
					} else { //메세지가 입력된 경우

						$.ajax({
							url : "/eunsu/chatting",
							data : {
								inputMessage : $inputMessage.val(),
								sender : $('#sender').val(),
								receiver : $('#receiver').val()
							},
							success : function(data) {
								$("#p1").html(data);
							}
						});//ajax
						
						$.ajax({
							url : "/eunsu/chattingnoti",
							data : {
								sender : $('#sender').val(),
								receiver : $('#receiver').val()
							}
						});//ajax
						
						
						$textarea.html($textarea.html() + "<p>"
								+ d.getFullYear() + "." + (d.getMonth() + 1)
								+ "." + d.getDate() + " " + d.getHours() + ":"
								+ d.getMinutes()
								+ "</p><p class='chat_content'>나 : "
								+ $inputMessage.val() + "</p>");
						webSocket.send($('#sender').val() + "|"
								+ $inputMessage.val() + "|"
								+ $('#receiver').val());
						$inputMessage.val(''); //기록된 메세지 삭제함
						objDiv.scrollTop = objDiv.scrollHeight;
					}

					//화면이 위로 스크롤되게 처리함
				} //send()

				//웹소켓 이벤트핸들러에 의해 실행되는 함수 작성
				function onMessage(event) {
					//서버로 부터 데이터를 받았을 때 작동되는 함수임
					var message = event.data.split("|");
					//보낸사람 아이디
					var receiverID = message[0];
					//전송온 메세지
					var content = message[1];
					//받을 사람 아이디
					var senderID = message[2];
					console.log(senderID);
					//전송온 메세지가 비었거나, 보낸사람이 내가 연결한
					//사람이 아닐 경우 아무 내용도 실행하지 않는다.
					if (content == ""
							|| !receiverID.match($('#receiver').val())
							|| !senderID.match($('#sender').val())) {
						//비워 놓음
					} else {
						$textarea.html($textarea.html()
								+ "<p class='chat_content other-side'>"
								+ receiverID + " : " + content
								+ "</p><br><br><p>" + d.getFullYear() + "."
								+ (d.getMonth() + 1) + "." + d.getDate() + " "
								+ d.getHours() + ":" + d.getMinutes() + "</p>");
						//화면이 위로 스크롤되게 처리함
						objDiv.scrollTop = objDiv.scrollHeight;
						$.ajax({
							url : "/eunsu/chattingnoti",
							data : {
								sender : $('#sender').val(),
								receiver : $('#receiver').val()
							}
						});//ajax
					}

				} //onMessage()

				function onError(event) {
					alert(event.data);
				}

				function onClose(event) {
				}

				$(function() {
					//'채팅하기' 버튼 클릭시, 서버와 연결되고 
					//채팅창이 나타나게 함
					connection();

					//'나가기' 버튼 클릭시 소켓닫기
					//채팅창  안 보이게 함
					$('#endBtn').on('click', function() {
						webSocket.close();
						window.location = document.referrer;
					});

				});

				//전송할 메세지 입력하면서, 키보드 키에서 손뗄때마다
				//실행되는 이벤트핸들러 함수
				function enterKey() {
					//누른 키가 엔터키(Enter) 이면 메세지 전송함
					if (window.event.keyCode == 13) {
						send();
					}
				}
			</script>


			<p id="p1"></p>

		</div>

		<!-- End Page Container -->
	</div>
	<br>

	<!-- Footer -->
	<footer class="w3-container w3-theme-d3 w3-padding-16">
		<h5>서울특별시 강남구 테헤란로14길 6</h5>
		<h6>수은대학교 | Tel. 02-880-5114 | Fax. 02-885-5272 Copyright 2019 Su
			Eun University All Rights Reserved.</h6>
	</footer>

	<script>
		// Accordion
		function myFunction(id) {
			var x = document.getElementById(id);
			if (x.className.indexOf("w3-show") == -1) {
				x.className += " w3-show";
				x.previousElementSibling.className += " w3-theme-d1";
			} else {
				x.className = x.className.replace("w3-show", "");
				x.previousElementSibling.className = x.previousElementSibling.className
						.replace(" w3-theme-d1", "");
			}
		}

		// Used to toggle the menu on smaller screens when clicking on the menu button
		function openNav() {
			var x = document.getElementById("navDemo");
			if (x.className.indexOf("w3-show") == -1) {
				x.className += " w3-show";
			} else {
				x.className = x.className.replace(" w3-show", "");
			}
		}
	</script>

</body>
</html>
