<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="employee.model.vo.Employee"%>
<%
	Employee loginEmployee = (Employee) session.getAttribute("loginEmployee");
%>
<!DOCTYPE html>
<html>
<title>메인 페이지</title>
<script type="text/javascript"
	src="/eunsu/resources/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#confirm").click(function() {

			$.ajax({
				url : "/eunsu/studentconfirm",
				type : "get",
				data : {
					studentno : $("#studentno").val()
				},
				success : function(data) {
					$("#result").html(data);
				}
			})//$.ajax()
		});//confirm click
	});//document ready

	function validate() {
		var pwre = /^[a-zA-Z0-9]{4,12}$/
		var stunore = /^[A0-9]{7}$/
		var ssnre = /^[0-9]{6}-[0-9]{7}$/
		var departnore = /^[0-9]{3}$/
		var prore = /^[P0-9]{4}$/

		var studentno = document.getElementById("studentno");
		var ssn = document.getElementById("studentssn");
		var pw = document.getElementById("studentpassword");
		var depart = document.getElementById("studentdepartno");
		var pro = document.getElementById("studentcoach");

		if (join.studentno.value == "") {
			alert("학번을 적어주세요");
			join.studentno.focus();
			return false;
		}

		if (!check(stunore, studentno, "학번은 'A000000' 구성으로 작성해주세요")) {
			return false;
		}
		if (join.studentdepartno.value == "") {
			alert("소속 학과를 적어주세요");
			join.studentdepartno.focus();
			return false;
		}
		if (!check(departnore, depart, "학과 번호는 숫자 세자리로 작성해주세요")) {
			return false;
		}
		if (join.studentname.value == "") {
			alert("이름을 적어주세요");
			join.studentname.focus();
			return false;
		}

		if (join.studentssn.value == "") {
			alert("주민 등록 번호를 적어주세요");
			join.studentssn.focus();
			return false;
		}
		if (!check(ssnre, ssn, "주민 번호를 확인해주세요")) {
			return false;
		}
		if (join.studentaddress.value == "") {
			alert("주소를 적어주세요");
			join.studentaddress.focus();
			return false;
		}

		if (join.studententrancedate.value == "") {
			alert("입학일 선택해 주세요");
			join.studententrancedate.focus();
			return false;
		}

		if (join.studentcoach.value == "") {
			alert("담당교수 번호를 적어주세요");
			join.studentcoach.focus();
			return false;
		}
		if (!check(prore, pro, "교수 번호는 'P000' 구성으로 작성해 주세요")) {
			return false;
		}

		if (join.studentpassword.value == "") {
			alert("비밀번호를 적어주세요");
			join.studentpassword.focus();
			return false;
		}
		if (!check(pwre, pw, "패스워드는 4~12자의 영문 대소문자와 숫자로만 입력")) {
			return false;
		}

		if (join.studentimage.value == "") {
			alert("파일을 첨부해주세요");
			join.studentimage.focus();
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
							<i class="fa fa-circle-o-notch fa-fw w3-margin-right"></i> 과목 관리
						</button>
						<div id="Demo1" class="w3-hide w3-container">
							<p>
								<a href="#">과목 관리</a>
							</p>
						</div>
						<button onclick="myFunction('Demo2')"
							class="w3-button w3-block w3-theme-l1 w3-left-align">
							<i class="fa fa-calendar-check-o fa-fw w3-margin-right"></i> 학생
							관리
						</button>
						<div id="Demo2" class="w3-hide w3-container">
							<p>
								<a href="/eunsu/views/studentcrud/studentinsert.jsp">학생 추가</a>
							</p>
							<p>
								<a href="/eunsu/views/studentcrud/studentinsert.jsp">학생 수정</a>
							</p>
						</div>
						<button onclick="myFunction('Demo3')"
							class="w3-button w3-block w3-theme-l1 w3-left-align">
							<i class="fa fa-users fa-fw w3-margin-right"></i>학교 관리
						</button>
						<div id="Demo3" class="w3-hide w3-container">
							<br>
							<div class="w3-half">
								<p>교수 관리</p>
							</div>
							<div class="w3-half">
								<p>직원 관리</p>
							</div>
							<div class="w3-half">
								<p>증명서 관리</p>
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
			<h1 align="center">학생 추가</h1>
			<table align="center" border="1" cellspacing="0" cellpadding="10">
				<form name="join" onsubmit="return validate();"
					action="/eunsu/studentinsert" method="post"
					enctype="multipart/form-data">
					<tr>
						<th>학번</th>
						<td><input type="text" name="studentno" id="studentno">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
							type="button" id="confirm" value="중복확인">
							<p id="result" style="display: none;"></p></td>
					</tr>
					<tr>
						<th>학과 번호</th>
						<td><input type="text" name="studentdepartno"
							id="studentdepartno"></td>
					</tr>
					<tr>
						<th>학생 이름</th>
						<td><input type="text" name="studentname" id="studentname"></td>
					</tr>
					<tr>
						<th>학생 주민 번호</th>
						<td><input type="text" name="studentssn" id="studentssn"></td>
					</tr>
					<tr>
						<th>학생 주소</th>
						<td><input type="text" name="studentaddress"
							id="studentaddress"></td>
					</tr>
					<tr>
						<th>입학일</th>
						<td><input type="date" name="studententrancedate"
							id="studententrancedate"></td>
					</tr>
					<tr>
						<th>담당 교수 번호</th>
						<td><input type="text" name="studentcoach" id="studentcoach"></td>
					</tr>
					<tr>
						<th>학생 비밀번호</th>
						<td><input type="text" name="studentpassword"
							id="studentpassword"></td>
					</tr>
					<tr>
						<th>학생 증명사진</th>
						<td><input type="file" name="studentimage" id="studentimage"></td>
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
