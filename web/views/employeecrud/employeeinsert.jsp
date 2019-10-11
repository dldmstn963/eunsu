<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="employee.model.vo.Employee"%>
<%
	Employee loginEmployee = (Employee) session.getAttribute("loginEmployee");
%>
<!DOCTYPE html>
<html>
<title>직원 추가 페이지</title>
<script type="text/javascript" src="/eunsu/resources/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
$(function (){
	$("#confirm").click(function(){
		
		$.ajax({
			url:"/eunsu/empconfirm",	
			type:"get",
			data:{empno : $("#empno").val()},
			success:function(data){
				$("#result").html(data);
			}
		})//$.ajax()
	});//confirm click
});//document ready

function validate(){
	var pwre = /^[a-zA-Z0-9]{4,12}$/
	var empnore = /^[E0-9]{4}$/ 
	var ssnre = /^[0-9]{6}-[0-9]{7}$/
	
	var empno = document.getElementById("empno");
	var ssn = document.getElementById("EMPLOYEE_SSN");
	var pw = document.getElementById("EMPLOYEE_PASSWORD");
	
	if(join.EMPLOYEE_NO.value == ""){
		alert("사번을 적어주세요");
		join.EMPLOYEE_NO.focus();
		return false;
	}
	
	if(!check(empnore,empno,"사번은 'E000' 구성으로 작성해주세요")){
		return false;
	}
	
	if(join.EMPLOYEE_NAME.value == ""){
		alert("이름을 적어주세요");
		join.EMPLOYEE_NAME.focus();
		return false;
	}
	
	if(join.EMPLOYEE_SSN.value == ""){
		alert("주민 등록번호를 적어주세요");
		join.EMPLOYEE_SSN.focus();
		return false;
	}
	
	if(!check(ssnre,ssn,"주민 번호를 확인해주세요")){
		return false;
	}
	
	if(join.EMPLOYEE_ADDRESS.value == ""){
		alert("주소를 적어주세요");
		join.EMPLOYEE_ADDRESS.focus();
		return false;
	}
	

	
	if(join.HIRE_DATE.value == ""){
		alert("입사일을 적어주세요");
		join.HIRE_DATE.focus();
		return false;
	}
	
	if(join.SALARY.value == ""){
		alert("월급을 적어주세요");
		join.SALARY.focus();
		return false;
	}
	
	if(join.EMPLOYEE_PASSWORD.value == ""){
		alert("비밀번호를 적어주세요");
		join.EMPLOYEE_PASSWORD.focus();
		return false;
	}
	
	if(!check(pwre,pw,"패스워드는 4~12자의 영문 대소문자와 숫자로만 입력")){
		return false;
	}
	
	if(join.EMPLOYEE_IMAGE.value == ""){
		alert("파일을 첨부해주세요");
		join.EMPLOYEE_IMAGE.focus();
		return false;
	}
	
}

function check(re, what, message) {
    if(re.test(what.value)) {
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
				title="My Account">
			</a>
		</div>
	</div>

	<!-- Navbar on small screens -->
	<div id="navDemo"
		class="w3-bar-block w3-theme-d2 w3-hide w3-hide-large w3-hide-medium w3-large">
		<a href="/eunsu/views/studentcrud/studentinsert.jsp" class="w3-bar-item w3-button w3-padding-large">학생 추가</a>
		<a href="/eunsu/views/studentcrud/studentinsert.jsp" class="w3-bar-item w3-button w3-padding-large">학생 추가</a>
		<a href="#" class="w3-bar-item w3-button w3-padding-large">ㅇㅇ 3</a>
		<a href="#" class="w3-bar-item w3-button w3-padding-large">My
			Profile</a>
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
							<p><a href="/eunsu/views/studentcrud/studentinsert.jsp">학생 추가</a></p>
							<p><a href="/eunsu/views/studentcrud/studentinsert.jsp">학생 수정</a></p>
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
		<h1 align="center">직원 추가</h1>
		<table align="center" border="1" cellspacing="0" cellpadding="10">
		<form name="join" action="/eunsu/employeeinsert" method="post" enctype="multipart/form-data" onsubmit="return validate();">
		<tr><th>사번</th><td><input type="text" name="EMPLOYEE_NO" id="empno">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" id="confirm" value="중복확인">
		<p id="result" style="display:none;"></p></td></tr>
		<tr><th>소속 부서 번호</th><td>
		<select name="EMPDEPART_NO">
		<option value="">선택 해주세요</option>
		<option value="1">행정</option>
		<option value="2">경영</option>
		</select></td></tr>
		<tr><th>사원 이름</th><td><input type="text" name="EMPLOYEE_NAME" id="EMPLOYEE_NAME"></td></tr>
		<tr><th>사원 주민 번호</th><td><input type="text" name="EMPLOYEE_SSN" id="EMPLOYEE_SSN"></td></tr>
		<tr><th>사원 주소</th><td><input type="text" name="EMPLOYEE_ADDRESS" id="EMPLOYEE_ADDRESS"></td></tr>
		<tr><th>입사일</th><td><input type="date" name="HIRE_DATE" id="HIRE_DATE"></td></tr>
		<tr><th>월급</th><td><input type="number" name="SALARY" id="SALARY"></td></tr>
		<tr><th>사원 비밀번호</th><td><input type="text" name="EMPLOYEE_PASSWORD" id="EMPLOYEE_PASSWORD"></td></tr>
		<tr><th>사원 증명사진</th><td><input type="file" name="EMPLOYEE_IMAGE" id="EMPLOYEE_IMAGE"></td></tr>
		<tr><th colspan="2">
		<input type="submit" value="추가"> &nbsp;
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
