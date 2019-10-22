<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="employee.model.vo.Employee"%>
<%
	Employee loginEmployee = (Employee) session.getAttribute("loginEmployee");
%>
<!DOCTYPE html>
<html>
<title>수강 신청 오픈</title>
<script type="text/javascript"
	src="/eunsu/resources/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	function validate(){
		if($("#year").val() == "" || $("#year").val() < 2019||$("#year").val() > 2100){
			alert('연도를 확인해 주세요');
			$("#year").focus();
		return false;
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
			<h1 align="center">학기 추가</h1>
				<form align="center" onsubmit="return validate();" action="/eunsu/ClassOpenServlet" method="post">
					년도 입력 : 
					<input type="number" name="year" style="width:100px;" id="year">
					학기 입력 : 
					<select name="term">
					<option value="01">1</option>
					<option value="02">2</option>
					<option value="03">3</option>
					</select>
					<br>
					<br>
					<br>
					<input type="submit" value="수강신청 열기" >
				</form>
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
