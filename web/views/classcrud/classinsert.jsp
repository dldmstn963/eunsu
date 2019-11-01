<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="employee.model.vo.Employee"%>
<%
	Employee loginEmployee = (Employee) session.getAttribute("loginEmployee");
%>
<!DOCTYPE html>
<html>
<title>수은 대학교</title>
<script type="text/javascript"
	src="/eunsu/resources/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#confirm").click(function() {

			$.ajax({
				url : "/eunsu/classsconfirm",
				type : "get",
				data : {
					classno : $("#classno").val()
				},
				success : function(data) {
					$("#result").html(data);
				}
			})//$.ajax()
		});//confirm click
	});//document ready

	function validate() {
		var classnore = /^[C0-9]{8}$/
		var departre = /^[0-9]{3}$/
		
		var classno = document.getElementById("classno");
		var depart = document.getElementById("classdepartno");
		if (join.classno.value == "") {
			alert("과목 번호를 적어주세요");
			join.classno.focus();
			return false;
		}

		if (!check(classnore, classno, "과목번호는 'C0000000' 구성으로 작성해주세요")) {
			return false;
		}

		if (join.classdepartno.value == "") {
			alert("소속학과 번호를 적어주세요");
			join.classdepartno.focus();
			return false;
		}
		
		if (!check(departre, depart, "학과번호는 숫자 세자리로 작성해주세요")) {
			return false;
		}
		
		if (join.classname.value == "") {
			alert("과목명을 적어주세요");
			join.classname.focus();
			return false;
		}

		if (join.classtype.value == "") {
			alert("과목 분류를 선택해주세요");
			join.classtype.focus();
			return false;
		}

		if (join.classprofessor.value == "") {
			alert("담당 교수 번호를 적어주세요");
			join.classprofessor.focus();
			return false;
		}

	}

	function check(re, what, message) {
		if (re.test(what.value)) {
			return true;
		}
		alert(message);
		what.value = "";
		what.focus();
		//return false;
	}
	
	function Chat() {
		var f = document.Chat2;
		f.action = "/eunsu/chattinglist";
		f.method = "get"
		f.submit();
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
				class="fa fa-home w3-margin-right"></i>수은 대학교</a> <a href="/eunsu/noticelist"
				class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white"
				title="News"><i class="fa fa-flag"></i></a> <a href="/eunsu/calendarlist"
				class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white"
				title="Account Settings"><i class="fa fa-calendar"></i></a> <a href="#" onclick="Chat();"
				class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white"
				title="Messages"><i class="fa fa-comments"></i></a> <a href="#"
				class="w3-bar-item w3-button w3-hide-small w3-right w3-padding-large w3-hover-white"
				title="My Account"> </a>
							<form name="Chat2">
								<input type="hidden" name="employeeNo" value="<%=loginEmployee.getEmployeeNo()%>" />
							</form>
				
		</div>
	</div>

	<!-- Navbar on small screens -->
		<div id="navDemo"
		class="w3-bar-block w3-theme-d2 w3-hide w3-hide-large w3-hide-medium w3-large">
		<a href="#" class="w3-bar-item w3-button w3-padding-large">Link 1</a>
		<a href="/eunsu/noticelist" class="w3-bar-item w3-button w3-padding-large">공지사항</a>
		<a href="/eunsu/calendarlist" class="w3-bar-item w3-button w3-padding-large">일정</a>
		<a href="#" onclick="Chat();" class="w3-bar-item w3-button w3-padding-large">채팅</a>
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
							내 정보 <i class="fa fa-pencil"></i>
						</h4>
						<p class="w3-center">
							<img src="<%=loginEmployee.getEmployeeimage()%>"
								class="w3-circle" style="height: 106px; width: 106px"
								alt="Avatar">
						</p>
						<hr>
						<p>
							<i class="fa fa-pencil fa-fw w3-margin-right w3-text-theme"></i>
							<%=loginEmployee.getEmployeeName()%>
						</p>
						<p>
							<i class="fa fa-pencil fa-fw w3-margin-right w3-text-theme"></i>
							<%=loginEmployee.getEmpDepartment()%>
						</p>
						<br> <a href="/eunsu/logout" style="text-decoration: none;">로그아웃</a>
					</div>
				</div>
				<br>

				<!-- Accordion -->
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
				<br>


				<!-- End Left Column -->
			</div>
			<!-- End Grid -->
			<h1 align="center">과목 추가</h1>
			<table align="center" border="1" cellspacing="0" cellpadding="10">
				<form onsubmit="return validate();" name="join" action="/eunsu/classsinsert" method="post">
					<tr>
						<th>과목 번호</th>
						<td><input type="text" name="classno" id="classno">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button"
							id="confirm" value="중복확인">
							<p id="result" style="display: none;"></p></td>
					</tr>
					<tr>
						<th>학과 번호</th>
						<td><input type="text" name="classdepartno"
							id="classdepartno"></td>
					</tr>
					<tr>
						<th>선행 과목 번호</th>
						<td><input type="text" name="preatending" id="preatending"></td>
					</tr>
					<tr>
						<th>과목 이름</th>
						<td><input type="text" name="classname" id="classname"></td>
					</tr>
					<tr>
						<th>과목 분류</th>
						<td><select name="classtype" id="classtype">
								<option value="">선택 해주세요</option>
								<option value="공통과목">공통과목</option>
								<option value="논문지도">논문지도</option>
								<option value="전공선택">전공선택</option>
								<option value="전공필수">전공필수</option>
						</select></td>
					</tr>
					<tr>
						<th>담당 교수 번호</th>
						<td><input type="text" name="classprofessor"
							id="classprofessor"></td>
					</tr>
					<tr>
						<th colspan="2"><input type="submit" value="추가">
							&nbsp; <input type="reset" value="초기화"></th>
					</tr>
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
