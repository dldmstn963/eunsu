<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="professor.model.vo.Professor"%>
<%
Professor loginProfessor = (Professor) session.getAttribute("loginProfessor");
%>
<!DOCTYPE html>
<html>
<title>메인 페이지</title>
<script type="text/javascript"
	src="/eunsu/resources/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
function checkIt(){
	if($("#PROFESSOR_PASSWORD").val() != $("#PROFESSOR_PASSWORD2").val()){
		alert("입력하신 암호와 다릅니다.\n다시 입력하십시오.");
		$("#PROFESSOR_PASSWORD2").select();
		return false
	}
}
</script>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/eunsu/resources/css/w3.css">
<link rel="stylesheet"
	href="/eunsu/resources/css/w3-theme-blue-grey.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<body class="w3-theme-l5">

	<!-- Navbar -->
	<div class="w3-top">
		<div class="w3-bar w3-theme-d2 w3-left-align w3-large">
			<a
				class="w3-bar-item w3-button w3-hide-medium w3-hide-large w3-right w3-padding-large w3-hover-white w3-large w3-theme-d2"
				href="javascript:void(0);" onclick="openNav()"><i
				class="fa fa-bars"></i></a> <a href="/eunsu/views/main.jsp"
				class="w3-bar-item w3-button w3-padding-large w3-theme-d4"><i
				class="fa fa-home w3-margin-right"></i>수은 대학교</a> <a href="#"
				class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white"
				title="News"><i class="fa fa-globe"></i></a> <a href="#"
				class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white"
				title="Account Settings"><i class="fa fa-user"></i></a> <a href="#"
				class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white"
				title="Messages"><i class="fa fa-envelope"></i></a> <a href="#"
				class="w3-bar-item w3-button w3-hide-small w3-right w3-padding-large w3-hover-white"
				title="My Account"> </a>
		</div>
	</div>

	<!-- Navbar on small screens -->
	<div id="navDemo"
		class="w3-bar-block w3-theme-d2 w3-hide w3-hide-large w3-hide-medium w3-large">
		<a href="/eunsu/views/studentcrud/studentinsert.jsp"
			class="w3-bar-item w3-button w3-padding-large">학생 추가</a> <a
			href="/eunsu/views/studentcrud/studentinsert.jsp"
			class="w3-bar-item w3-button w3-padding-large">학생 추가</a> <a href="#"
			class="w3-bar-item w3-button w3-padding-large">ㅇㅇ 3</a> <a href="#"
			class="w3-bar-item w3-button w3-padding-large">My Profile</a>
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
					<div class="w3-container">
						<h4 class="w3-center">
							내 정보<form action="/eunsu/pmyinfo" method="post">
							<input type="hidden" value="<%=loginProfessor.getProfessorNo()%>" name="empno">
							<input type="hidden" value="<%=loginProfessor.getProfessorPassword()%>" name="emppass">
							<input type="submit" value="수정">
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
						<br> <a href="/eunsu/logout" style="text-decoration:none;">로그아웃</a>
					</div>
				</div>
				<br>

				<!-- Accordion -->
				<div class="w3-card w3-round">
					<div class="w3-white">
						<button onclick="myFunction('Demo1')"
							class="w3-button w3-block w3-theme-l1 w3-left-align">
							<i class="fa fa-circle-o-notch fa-fw w3-margin-right"></i> 강의 과목 보기
						</button>
						<div id="Demo1" class="w3-hide w3-container">
							<p><a href="/eunsu/views/professorbasic.jsp">강의 과목 보기</a></p>
						</div>
						<button onclick="myFunction('Demo2')"
							class="w3-button w3-block w3-theme-l1 w3-left-align">
							<i class="fa fa-calendar-check-o fa-fw w3-margin-right"></i> 성적 입력
						</button>
						<div id="Demo2" class="w3-hide w3-container">
							<p>성적 입력</p>
						</div>
						<button onclick="myFunction('Demo3')"
							class="w3-button w3-block w3-theme-l1 w3-left-align">
							<i class="fa fa-users fa-fw w3-margin-right"></i>증명서
						</button>
						<div id="Demo3" class="w3-hide w3-container">
							<br>
							<div class="w3-half">
								<p>재학 증명서</p>
							</div>
							<div class="w3-half">
								<p>졸업 증명서</p>
							</div>
							<div class="w3-half">
								<p>성적 증명서</p>
							</div>
							<div class="w3-half">
								<p>휴학 증명서</p>
							</div>
							<div class="w3-half">
								<p>
									졸업 예정 <br>증명서
								</p>
							</div>
							<div class="w3-half">
								<p>수료 증명서</p>
							</div>
						</div>
					</div>
				</div>
				<br>


				<!-- End Left Column -->
			</div>
			<!-- End Grid -->
			<h1 align="center">내 정보 수정</h1>
		<table align="center" border="1" cellspacing="0" cellpadding="10">
		<form name="join" onsubmit="return checkIt()" action="/eunsu/PMyUpdateServlet" method="post" enctype="multipart/form-data"">
		<tr style="display:none;"><th>사번</th><td><input type="text" name="PROFESSOR_NO" value="<%=loginProfessor.getProfessorNo()%>">
		<tr><th>교수 번호</th><td><%=loginProfessor.getProfessorNo()%></td>
		<tr><th>소속 학과 번호</th><td><%=loginProfessor.getDepartmentNo() %></td></tr>
		<tr><th>이름</th><td><input type="text" name="PROFESSOR_NAME" value="<%=loginProfessor.getProfessorName()%>"></td></tr>
		<tr><th>주민 번호</th><td><input type="text" name="PROFESSOR_SSN" value="<%=loginProfessor.getProfessorSSN()%>"></td></tr>
		<tr><th>주소</th><td><input type="text" name="PROFESSOR_ADDRESS" value="<%=loginProfessor.getProfessorAddress()%>"></td></tr>
		<tr><th>비밀번호</th><td><input type="password" name="PROFESSOR_PASSWORD" id="PROFESSOR_PASSWORD"  value="<%=loginProfessor.getProfessorPassword()%>"></td></tr>
		<tr><th>비밀번호 확인</th><td><input type="password" id="PROFESSOR_PASSWORD2" ></td></tr>
		<tr><th>증명사진</th><td><input type="file" name="PROFESSOR_IMAGE" value="<%=loginProfessor.getProfessorImage()%>"></td></tr>
		<tr><th colspan="2">
		<input type="submit" value="수정"> &nbsp;
		<input type="reset" value="초기화">
		</th></tr>
		</form>
		</table>
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
